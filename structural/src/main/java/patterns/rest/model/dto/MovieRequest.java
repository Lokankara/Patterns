package patterns.rest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import patterns.rest.factory.Type;
import patterns.rest.model.entity.Actor;
import patterns.rest.model.entity.Genre;
import patterns.rest.model.entity.Review;

import java.util.List;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequest {

    private Long movieId;
    private String title;
    private Review review;
    private Type priceCode;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Genre> genres;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Actor> actors;
}
