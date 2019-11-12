package top.wisely.learningspringsecurity.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class WebSecurity {
    public boolean checkUsernameLenEq3(Authentication authentication){
       if ( authentication.getName().length() == 3)
           return true;
       return false;
    }
}
