package ru.test.authorinformation.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "genre")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE genre SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private boolean deleted = Boolean.FALSE;

    @Column(name = "date_of_creation")
    @CreatedDate
    @JsonIgnore
    private LocalDateTime dateOfCreation;

    @Column(name = "date_of_modification")
    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime dateOfModification;

    public Genre(String description) {
        this.description = description;
    }

    public Genre(Long id, String description) {
        this.id = id;
        this.description = description;
    }
}
