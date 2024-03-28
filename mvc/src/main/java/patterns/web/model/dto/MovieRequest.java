package patterns.web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import patterns.web.factory.Type;
import patterns.web.model.entity.Actor;
import patterns.web.model.entity.Genre;
import patterns.web.model.entity.Review;

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
