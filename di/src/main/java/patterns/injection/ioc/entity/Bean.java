package patterns.injection.ioc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Bean {
    private String id;
    private Object value;
}
