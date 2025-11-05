package com.example.RottenTangerines.DTO;

import com.example.RottenTangerines.Entity.MovieReview;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewResponse {

    private Integer id;

    private String title;

    //private watchtedDate;

    private String content;

    private Integer rating;

    private String posterPath;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime updatedDate;

    public static ReviewResponse form(MovieReview movieReview){
        return ReviewResponse.builder()
                .id(movieReview.getId())
                .title(movieReview.getTitle())
                //.watchedDate(movieReview.getWatchedDate())
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
                //.watchedDate(this.watchedDate)
                .content(this.content)
                .rating(this.rating)
                .createdDate(this.createdDate)
                .updateDate(this.updatedDate)
                .posterPath(this.posterPath)
                .build();
    }
}
