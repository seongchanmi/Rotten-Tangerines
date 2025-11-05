package com.example.RottenTangerines.Entity;

import jakarta.persistence.*;
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
@SequenceGenerator(
        name = "movie_reviews_seq",
        sequenceName = "movie_reviews_seq",
        allocationSize = 1
)
public class MovieReview {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_reviews_seq")
    private Integer id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(name = "watched_Date")
    private Date watchedDate;

    @Column(length = 500)
    private String posterPath;

    @Column(nullable = false, length = 5000)
    private String content;

    @Column(precision = 2,scale = 1)
    private Integer rating;

    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

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
}
