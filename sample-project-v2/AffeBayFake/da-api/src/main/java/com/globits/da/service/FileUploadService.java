package com.globits.da.service;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.globits.da.dto.ProductDto;

public interface FileUploadService {
	public ProductDto uploadProductImage(MultipartFile uploadFile, UUID productID);
}
