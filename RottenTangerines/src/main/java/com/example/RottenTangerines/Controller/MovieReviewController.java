package com.example.RottenTangerines.Controller;

import com.example.RottenTangerines.DTO.ReviewRequest;
import com.example.RottenTangerines.DTO.ReviewResponse;
import com.example.RottenTangerines.Entity.MovieReview;
import com.example.RottenTangerines.Service.MovieReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;

@Slf4j
@RestController
//@RequestMapping("/api")
@RequiredArgsConstructor
public class MovieReviewController {

    private final MovieReviewService service;

    // JSON 전용 등록
    @PostMapping(value = "/reviews/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewResponse> create(@RequestBody @Valid ReviewRequest request){
        MovieReview saved = service.register(request);
        return ResponseEntity.ok(ReviewResponse.fromEntity(saved));
    }

    // 이미지 포함 등록
    @PostMapping(value = "/reviews/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewResponse> createImage(
            @RequestParam("title") String title,
            @RequestParam("watchedDate") Date watchedDate,
            @RequestParam("content") String content,
            @RequestParam("rating") Integer rating,
            @RequestParam(value = "posterPath", required = false) MultipartFile poster
            ){
           ReviewResponse saved = service.registerPoster(title, watchedDate, content, rating, poster);
        return ResponseEntity.ok(saved);
    }

    //전체 조회
    @GetMapping("/home")
    public ResponseEntity<List<ReviewResponse>> getAll(){
        List<ReviewResponse> list = service.findAll()
                .stream()
                .map(movieReview -> ReviewResponse.fromEntity(movieReview))
                .toList();
        return ResponseEntity.ok(list);
    }

    //개별 조회
    @GetMapping("/reviews/{id}")
    public ResponseEntity<ReviewResponse> reviewFindById(@PathVariable int id){
        MovieReview movieReview = service.findByMovieId(id);
        return ResponseEntity.ok(ReviewResponse.fromEntity(movieReview));
    }

    //삭제
    //일단 돌려 받을 값 없는 것을 상정하고 작성
    @DeleteMapping("/delete/{id}") //삭제 주소 보류
    public ResponseEntity<Void> deleteReview(@PathVariable int id){
        service.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    // 이미지 수정
    //(value = "/reviews/update/image/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PutMapping(value = "/reviews/update/image/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewResponse> updateReviewImage(@PathVariable int id,
                                                            @RequestParam("title") String title,
                                                            @RequestParam("watchedDate") Date watchedDate,
                                                            @RequestParam("content") String content,
                                                            @RequestParam("rating") Integer rating,
                                                            @RequestParam(value = "posterPath", required = false) MultipartFile poster
    ){
        MovieReview updated = service.updateImageReview(id, title, watchedDate, content, rating, poster);
        return ResponseEntity.ok(ReviewResponse.fromEntity(updated));

    }

}
