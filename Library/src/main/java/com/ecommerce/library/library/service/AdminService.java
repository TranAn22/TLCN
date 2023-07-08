package com.ecommerce.library.library.service;

import com.ecommerce.library.library.dto.AdminDto;
import com.ecommerce.library.library.model.Admin;

public interface AdminService {
    Admin findByUsername(String username);
    Admin save(AdminDto adminDto);
}
