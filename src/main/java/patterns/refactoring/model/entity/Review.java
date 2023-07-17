package patterns.refactoring.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    private Long reviewId;
    private String country;
    private String overview;
    private String director;
    private String backdropPath;
    private double rating;
    private List<Actor> actors;

    public String getActor() {
        return String.join(", ", actors.stream().map(Actor::getName).toList());
    }
}

