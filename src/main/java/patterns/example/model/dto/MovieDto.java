package patterns.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import patterns.example.factory.MovieType;
import patterns.example.factory.Type;
import patterns.example.model.entity.Genre;
import patterns.example.model.entity.Review;

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
