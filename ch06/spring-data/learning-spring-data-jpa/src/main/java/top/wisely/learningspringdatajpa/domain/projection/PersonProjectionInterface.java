package top.wisely.learningspringdatajpa.domain.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.wisely.learningspringdatajpa.domain.model.Address;
import top.wisely.learningspringdatajpa.domain.model.Person;

public interface PersonProjectionInterface {

    String getName();

    Address getAddress();

    @Value("#{target.name + ' s age is' + target.age}")
    String getAgeDesc();

    default String getCityDesc(){
        return getName() + " lives in " + getAddress().getCity();
    }

    @Value("#{@personProjectionHelper.getInfo(target)}")
    String getInfo();

    @Value("#{args[0] + ' ' + target.name + ' !'}")
    String getHello(String greeting);
}
