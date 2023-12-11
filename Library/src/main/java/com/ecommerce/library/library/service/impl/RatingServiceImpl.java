package com.ecommerce.library.library.service.impl;

import com.ecommerce.library.library.model.Rating;
import com.ecommerce.library.library.repository.RatingRepository;
import com.ecommerce.library.library.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    RatingRepository ratingRepository;
    @Override
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllByProductId(long id) {
        return ratingRepository.getAllByProductId(id);
    }
}
