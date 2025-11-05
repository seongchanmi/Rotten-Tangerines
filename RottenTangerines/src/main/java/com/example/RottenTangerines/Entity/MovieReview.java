package com.example.RottenTangerines.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "movie_reviews")
@SequenceGenerator(
        name = "movie_reviews_seq",
        sequenceName = "movie_reviews_seq",
        allocationSize = 1
)
public class MovieReview {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_reviews_seq")
    private Integer Id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(name = "watched_Date")
    private LocalDate watchedDate;

    @Column(length = 500)
    private String posterPath;

    @Column(nullable = false, length = 5000)
    private String content;

    @Column(precision = 2,scale = 1)
    private Double rating;

    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
}
