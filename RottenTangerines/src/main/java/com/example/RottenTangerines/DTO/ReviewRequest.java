package com.example.RottenTangerines.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Getter
public class ReviewRequest {

    @NotBlank(message = "제목 필수 작성")
    private String title;

    @NotNull(message = "관람일 필수 작성")
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date watchedDate;

    @NotBlank(message = "내용 필수 작성")
    private String content;

    @NotNull(message = "평점 필수 작성")
    @Column(nullable = false)
    private Integer rating;

    @NotBlank(message = "사진 필수 작성")
    private String posterPath;

}