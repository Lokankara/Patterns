package patterns.injection.ioc.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@Setter
@Getter
public class BeanDefinition {
    private String id;
    private String beanClassName;
    private Map<String, String> dependencies = Collections.emptyMap();
    private Map<String, String> refDependencies = Collections.emptyMap();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeanDefinition that = (BeanDefinition) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(beanClassName, that.beanClassName) &&
                Objects.equals(dependencies, that.dependencies) &&
                Objects.equals(refDependencies, that.refDependencies);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, beanClassName, dependencies, refDependencies);
    }
}
