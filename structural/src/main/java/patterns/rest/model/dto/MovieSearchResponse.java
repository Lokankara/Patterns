package patterns.rest.model.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class MovieSearchResponse {

    private List<MovieDetailsResponse> results;

}
