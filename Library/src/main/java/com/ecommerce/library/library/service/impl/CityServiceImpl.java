package com.ecommerce.library.library.service.impl;

import com.ecommerce.library.library.model.City;
import com.ecommerce.library.library.repository.CityRepository;
import com.ecommerce.library.library.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }
}
