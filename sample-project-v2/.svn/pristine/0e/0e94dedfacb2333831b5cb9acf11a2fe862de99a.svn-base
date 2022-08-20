package com.globits.da.service.impl;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.globits.da.dto.ProductDto;
import com.globits.da.service.FileUploadService;
import com.globits.da.service.ProductService;

//@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Override
	public ProductDto uploadProductImage(MultipartFile uploadFile, UUID productID) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	@Value("${attachment.path}")
//	private String attachmentPath;
//	
//	@Value("${file.folder}")
//	private String filePath;
//
//	@Value("${localhost.path}")
//	private String hostPath;
//
//	@Value("${server.servlet.context-path}")
//	private String contextPath;
//
//	@Value("${attachment.context.path}")
//	private String attachmentContextPath;
//
//	@Autowired
//	private ProductService productService;
//	
//	@Override
//	public ProductDto uploadProductImage(MultipartFile uploadFile, UUID productID) {
//		String absolutePath = this.attachmentPath + "product/" + productID + "/";
//		String fileName = uploadFile.getOriginalFilename().split("\\.(?=[^\\.]+$)")[0];
//		String extension = uploadFile.getOriginalFilename().split("\\.(?=[^\\.]+$)")[1];
//		
//		System.out.println(absolutePath);
//		
//		try {
//			if (!new File(absolutePath).exists()) {
//                new File(absolutePath).mkdirs();
//            } else {
//            	File folder = new File(absolutePath);
//            	// DUng tam thoi xoa all file, mai sau co nhieu anh cua san pham thi can sua lai ham nay
//            	if (folder.listFiles() != null) {
//            		for (File file : folder.listFiles()) {
//            			file.delete();
//            		}
//            	}
//            	new File(absolutePath).delete();
//            	new File(absolutePath).mkdirs();
//            }
//		
//		File fileToBeSaved = new File(absolutePath, fileName + "." + extension);
//		uploadFile.transferTo(fileToBeSaved);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
////		String mainImageUrl = this.hostPath + "resources/product/" + productID + "/" + fileName + "." + extension;
//		String mainImageUrl = this.filePath + "product\\" + productID + "\\" + fileName + "." + extension;
//		ProductDto dto = productService.updateImage(mainImageUrl, productID);
//		return dto;
//	}
}
