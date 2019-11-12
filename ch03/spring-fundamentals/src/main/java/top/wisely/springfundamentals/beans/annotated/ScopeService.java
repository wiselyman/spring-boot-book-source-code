package top.wisely.springfundamentals.beans.annotated;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
public class ScopeService {
}


//@Service
//@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) //@Scope("singleton")
//public class ScopeService {
//}
