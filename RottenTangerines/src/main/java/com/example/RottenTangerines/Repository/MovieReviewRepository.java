package com.example.RottenTangerines.Repository;

import com.example.RottenTangerines.Entity.MovieReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieReviewRepository extends JpaRepository<MovieReview, Integer> {
}
