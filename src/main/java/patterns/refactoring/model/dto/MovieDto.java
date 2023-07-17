package patterns.refactoring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import patterns.refactoring.factory.Type;
import patterns.refactoring.model.entity.Genre;
import patterns.refactoring.model.entity.Review;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private Long movieId;
    private String title;
    private Review review;
    private Type priceCode;
    private List<Genre> genres;
}
