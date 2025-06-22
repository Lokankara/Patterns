package patterns.injection.ioc.io;


import patterns.injection.ioc.entity.BeanDefinition;

import java.util.List;

public interface BeanDefinitionReader {

    List<BeanDefinition> getBeanDefinitions();
}
