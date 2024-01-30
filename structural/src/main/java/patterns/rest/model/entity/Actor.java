package patterns.rest.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class Actor {
    private Long actorId;
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
