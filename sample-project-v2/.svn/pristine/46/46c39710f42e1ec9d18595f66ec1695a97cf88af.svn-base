package com.globits.da.rest;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.globits.da.domain.Product;
import com.globits.da.dto.ProductDto;
import com.globits.da.service.FileUploadService;
import com.globits.da.service.ProductService;

@RestController
@RequestMapping(path = "/api/upload")
public class RestFileUploadController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/image/product", method = RequestMethod.POST)
	public ResponseEntity<ProductDto> uploadImageProduct(@RequestParam("file") MultipartFile uploadfile,
			@RequestParam("productID") UUID productID) {
		ProductDto result = null;
		String path = "";
		String hostPath = "";
		if (env.getProperty("localhost.path") != null) {
			hostPath = env.getProperty("localhost.path");
		}
		if (env.getProperty("attachment.path") != null) {
			path = env.getProperty("attachment.path");
		}
		path += "statics/product/" + productID + "/";
		hostPath += "public/image/product/";
		System.out.println(path);
		try { 
			String filename = uploadfile.getOriginalFilename().split("\\.(?=[^\\.]+$)")[0];
			String extension = uploadfile.getOriginalFilename().split("\\.(?=[^\\.]+$)")[1];
			String filePath = path;
			if (!new File(filePath).exists()) {
                new File(filePath).mkdirs();
            } else {
            	File folder = new File(filePath);
            	if (folder.listFiles() != null) {
            		for (File file : folder.listFiles()) {
            			file.delete();
            		}
            	}
            	new File(filePath).delete();
            	new File(filePath).mkdirs();
            }
			try {
				File fileToBeSaved = new File(filePath, filename + "." + extension);
				uploadfile.transferTo(fileToBeSaved); 
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			hostPath += productID +"/" + filename + "/" + extension;
			result = productService.updateImage(hostPath, productID);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ProductDto>(result, HttpStatus.OK);
	}

//	@RequestMapping(value = "/image", method = RequestMethod.POST)
//	public ResponseEntity<ProductDto> uploadImage(@RequestParam("file") MultipartFile uploadFile,
//			@RequestParam("productID") UUID productID) {
//		ProductDto dto = fileUploadService.uploadProductImage(uploadFile, productID);
//		return new ResponseEntity<ProductDto>(dto, (dto != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
//	}
}
