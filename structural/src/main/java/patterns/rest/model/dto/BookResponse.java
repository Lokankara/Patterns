package patterns.rest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private String textNumber;
    private String type;
    private LocalDate issued;
    private String title;
    private String language;
    private String authors;
    private String subjects;
    private String loCC;
    private String bookshelves;
}
