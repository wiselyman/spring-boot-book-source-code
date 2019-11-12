package top.wisely.learningspringmvc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemoBean {

    private Date date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date1;

}
