package patterns.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import patterns.factory.Type;
import patterns.model.entity.Genre;
import patterns.model.entity.Review;

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
