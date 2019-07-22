package com.pandora.boot.demo.importdemo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

public class UserServiceSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        Map<String , Object> map = importingClassMetadata.getAnnotationAttributes(EnableUserService.class.getName(), true);
        for(Map.Entry<String, Object> entry : map.entrySet()){
            System.out.println("key is : " + entry.getKey() + " value is : " + entry.getValue());
        }


        return new String[]{UserServiceImpl.class.getName()};
    }
}
