package com.example.RottenTangerines.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "movie_reviews")

public class MovieReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(name = "watched_Date")
    private Date watchedDate;

    @Column(length = 500)
    private String posterPath;

    @Column(nullable = false, length = 5000)
    private String content;

    @Min(1)
    @Max(5)
    private Integer rating;

    @Column(name = "created_date", updatable = false)
    @Builder.Default
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateWatchedDate(Date watchedDate) {
        this.watchedDate = watchedDate;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateRating(Integer rating) {
        this.rating = rating;
    }

    public void updatePosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void updateUpdatedDate() {
        this.updatedDate = LocalDateTime.now();
    }
}
