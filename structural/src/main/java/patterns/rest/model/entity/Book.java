package patterns.rest.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Book {
    @Id
    private String textNumber;
    private String type;
    private LocalDate issued;
    private String title;
    private String language;
    private String authors;
    @Column(length = 1024)
    private String subjects;
    private String loCC;
    private String bookshelves;
}
