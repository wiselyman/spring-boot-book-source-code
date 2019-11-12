package top.wisely.learningspringintegration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileHeaders;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.mail.dsl.Mail;
import top.wisely.learningspringintegration.service.SendingGateway;

import java.io.File;
import java.util.concurrent.TimeUnit;

@Configuration
public class IntegrationConfig {


    @Bean
    public IntegrationFlow flowFormGateway() {
        return IntegrationFlows.from(SendingGateway.class)
                .<String> filter(payload -> payload.startsWith("h"))
                .<String,String> transform(payload -> payload + "!")
                .<String, Boolean> route(payload -> payload.startsWith("hello"),
                        mapping -> mapping.channelMapping(true, "fileChannel")
                                          .channelMapping(false, "emailChannel"))
                .get();
    }

    @Bean
    IntegrationFlow flowFromChannelToFile(){
        return IntegrationFlows.from("fileChannel")
                .handle(Files.outboundAdapter(new File("/Users/wangyunfei/file"))
                        .fileExistsMode(FileExistsMode.APPEND)
                        .appendNewLine(true))
                .get();
    }

    @Bean
    IntegrationFlow flowFromChannelToMail(){
        return IntegrationFlows.from("emailChannel")
                .enrichHeaders(Mail.headers()
                        .subject("来自localhost的信息")
                        .from("wisely-man@126.com")
                        .to("wisely-man@126.com"))
                .handle(Mail.outboundAdapter("smtp.126.com")
                        .port(25)
                        .protocol("smtp")
                        .credentials("wisely-man@126.com", "******"))
                .get();
    }

    @Bean IntegrationFlow copyFileFlow(){
        return IntegrationFlows.from(Files.inboundAdapter(new File("/Users/wangyunfei/file")).patternFilter("*.txt"),
                                            c -> c.poller(Pollers.fixedRate(5, TimeUnit.SECONDS)))
                .enrichHeaders(h -> {
                    h.headerExpression(FileHeaders.FILENAME, "copy.txt", true);
                })
                .handle(Files.outboundAdapter(new File("/Users/wangyunfei/output"))
                            .fileExistsMode(FileExistsMode.REPLACE_IF_MODIFIED))
                .get();
    }
}
