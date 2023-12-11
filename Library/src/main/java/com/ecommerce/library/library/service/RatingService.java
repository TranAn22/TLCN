package com.ecommerce.library.library.service;

import com.ecommerce.library.library.model.Rating;
import org.springframework.stereotype.Service;

import java.util.List;
public interface RatingService {
    Rating save(Rating rating);
    List<Rating> getAllByProductId(long id);

}
