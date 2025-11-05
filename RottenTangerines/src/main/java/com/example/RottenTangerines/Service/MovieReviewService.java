package com.example.RottenTangerines.Service;

import com.example.RottenTangerines.DTO.ReviewRequest;
import com.example.RottenTangerines.Entity.MovieReview;
import com.example.RottenTangerines.Repository.MovieReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MovieReviewService {

    private MovieReviewRepository repo;

    //생성
    @Transactional
    public MovieReview register(ReviewRequest reviewRequest) {
        MovieReview movieReview = MovieReview.builder()
                .title(reviewRequest.getTitle())
                .watchedDate(reviewRequest.getWatchedDate())
                .content(reviewRequest.getContent())
                .rating(reviewRequest.getRating())
                .posterPath(reviewRequest.getPosterPath())
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

    //수정
    @Transactional
    public MovieReview updateReview(int movieId, MovieReview movieReview) {
        MovieReview newReview = repo.findById(movieId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id에 해당하는 값이 없습니다."));
        newReview.updateTitle(movieReview.getTitle());
        newReview.updateWatchedDate(movieReview.getWatchedDate());
        newReview.updateContent(movieReview.getContent());
        newReview.updateRating(movieReview.getRating());
        newReview.updatePosterPath(movieReview.getPosterPath());
        return repo.save(newReview);
    }

    //삭제
    @Transactional
    public void deleteReview(int movieId) {
        MovieReview movieReview = repo.findById(movieId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id에 해당하는 값이 없습니다."));
        repo.deleteById(movieId); //위에 적은거 알아서 실행되고 여기까지 안내려오는거?
    }
}
