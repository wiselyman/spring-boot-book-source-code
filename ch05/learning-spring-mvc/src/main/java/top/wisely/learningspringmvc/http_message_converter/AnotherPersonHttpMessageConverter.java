package top.wisely.learningspringmvc.http_message_converter;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;
import top.wisely.learningspringmvc.domain.AnotherPerson;

import java.io.IOException;
import java.nio.charset.Charset;

public class AnotherPersonHttpMessageConverter extends AbstractHttpMessageConverter<AnotherPerson> {

    public AnotherPersonHttpMessageConverter() {
        super(new MediaType("application","another-person", Charset.defaultCharset()));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return AnotherPerson.class.isAssignableFrom(clazz);
    }

    @Override
    protected AnotherPerson readInternal(Class<? extends AnotherPerson> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String body = StreamUtils.copyToString(inputMessage.getBody(), Charset.defaultCharset());
        String[] personStr = body.split("-");
        return new AnotherPerson(Long.valueOf(personStr[0]), personStr[1], Integer.valueOf(personStr[2]));
    }

    @Override
    protected void writeInternal(AnotherPerson anotherPerson, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        String out = "Hello:" + anotherPerson.getId() + "-" +
                anotherPerson.getName() + "-" +
                anotherPerson.getAge();
        StreamUtils.copy(out, Charset.defaultCharset(), outputMessage.getBody());
    }
}
