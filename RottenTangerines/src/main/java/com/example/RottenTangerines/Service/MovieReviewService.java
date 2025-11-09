package com.example.RottenTangerines.Service;

import com.example.RottenTangerines.DTO.ReviewRequest;
import com.example.RottenTangerines.DTO.ReviewResponse;
import com.example.RottenTangerines.Entity.MovieReview;
import com.example.RottenTangerines.Repository.MovieReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MovieReviewService {

    private final MovieReviewRepository repo;
    private final FileImageService fileImageService;

    //생성
    @Transactional
    public MovieReview register(ReviewRequest reviewRequest) {
        MovieReview movieReview = MovieReview.builder()
                .title(reviewRequest.getTitle())
                .watchedDate(reviewRequest.getWatchedDate())
                .content(reviewRequest.getContent())
                .rating(reviewRequest.getRating())
                .build();
        return repo.save(movieReview);
    }

    //전체 조회
    @Transactional
    public List<MovieReview> findAll() {
        return repo.findAll();
    }

    //개별 조회
    @Transactional
    public MovieReview findByMovieId(int movieId) {
        return repo.findById(movieId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id에 해당하는 값이 없습니다."));
    }


    // 이미지를 포함한 수정
    @Transactional
    public MovieReview updateImageReview(int movieId, String title, Date watchedDate, String content, int rating, MultipartFile poster){
        MovieReview newReview = repo.findById(movieId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id에 해당하는 값이 없습니다."));
        fileImageService.deleteposter(newReview.getPosterPath());
        String posterPath = (poster != null && !poster.isEmpty()) ? fileImageService.store(poster) : null;

        //fileImageService.deleteposter(movieReview.getPosterPath());
            newReview.updateTitle(title);
            newReview.updateWatchedDate(watchedDate);
            newReview.updateContent(content);
            newReview.updateRating(rating);
            newReview.updatePosterPath(posterPath);
            newReview.updateUpdatedDate();
            return repo.save(newReview);
    }

    //삭제
    @Transactional
    public void deleteReview(int movieId) {
        MovieReview movieReview = repo.findById(movieId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id에 해당하는 값이 없습니다."));
        repo.deleteById(movieId); //위에 적은거 알아서 실행되고 여기까지 안내려오는거?

        // (사진) 파일 삭제
        fileImageService.deleteposter(movieReview.getPosterPath());
    }
    // 이미지 포함 등록
    @Transactional
    public ReviewResponse registerPoster(String title, Date watchedDate, String content, Integer rating, MultipartFile poster) {
        // 필수 값 설정
        if(title == null || title.isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "제목은 필수입니다.");
        }

        // 파일 저장
        String posterPath = (poster != null && !poster.isEmpty()) ? fileImageService.store(poster) : null;

        MovieReview movieReview = MovieReview.builder()
                .title(title)
                .watchedDate(watchedDate)
                .content(content)
                .rating(rating)
                .posterPath(posterPath)
                .build();
        MovieReview saved = repo.save(movieReview);
        return ReviewResponse.fromEntity(saved);

    }
}
