package com.ecommerce.library.library.repository;

import com.ecommerce.library.library.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,Long> {

    List<Rating> getAllByProductId(long id);
}
