package com.example.RottenTangerines.DTO;

import com.example.RottenTangerines.Entity.MovieReview;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ReviewResponse {

    private Integer id;

    private String title;

    private Date watchedDate;

    private String content;

    private Integer rating;

    private String posterPath;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updatedDate;

    public static ReviewResponse fromEntity(MovieReview movieReview){
        return ReviewResponse.builder()
                .id(movieReview.getId())
                .title(movieReview.getTitle())
                .watchedDate(movieReview.getWatchedDate())
                .content(movieReview.getContent())
                .rating(movieReview.getRating())
                .createdDate(movieReview.getCreatedDate())
                .updatedDate(movieReview.getUpdatedDate())
                .posterPath(movieReview.getPosterPath())
                .build();
    }

    public MovieReview toEntity(){
        return MovieReview.builder()
                .id(this.id)
                .title(this.title)
                .watchedDate(this.watchedDate)
                .content(this.content)
                .rating(this.rating)
                .createdDate(this.createdDate)
                .updatedDate(this.updatedDate)
                .posterPath(this.posterPath)
                .build();
    }
}
