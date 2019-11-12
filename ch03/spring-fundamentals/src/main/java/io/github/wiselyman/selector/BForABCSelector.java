package io.github.wiselyman.selector;

import io.github.wiselyman.annotations.EnableABC;
import io.github.wiselyman.annotations.EnableB;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

public class BForABCSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        AnnotationAttributes attributes =
                AnnotationAttributes.fromMap(
                        importingClassMetadata.getAnnotationAttributes
                                (EnableABC.class.getName(), false));
        boolean isUppercase = attributes.getBoolean("isUppercase");
        if(isUppercase == true)
            return new String[]{"io.github.wiselyman.config.BUppercaseConfig"};
        else
            return new String[]{"io.github.wiselyman.config.BLowercaseConfig"};
    }
}
