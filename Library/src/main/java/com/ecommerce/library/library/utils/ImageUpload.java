package com.ecommerce.library.library.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class ImageUpload {
    private final String UPLOAD_FOLDER = "static/img/image_product";

    public boolean uploadImage(MultipartFile imageProduct) {
        boolean isUpload = false;
        try {
            String absolutePath = getClass().getClassLoader().getResource(UPLOAD_FOLDER).getPath();
            Files.copy(imageProduct.getInputStream(),
                    Paths.get(absolutePath + File.separator, imageProduct.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            isUpload = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpload;
    }

    public boolean checkExisted(MultipartFile imageProduct) {
        boolean isExisted = false;
        try {
            String absolutePath = getClass().getClassLoader().getResource(UPLOAD_FOLDER).getPath();
            File file = new File(absolutePath + File.separator + imageProduct.getOriginalFilename());
            isExisted = file.exists();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isExisted;
    }
}
