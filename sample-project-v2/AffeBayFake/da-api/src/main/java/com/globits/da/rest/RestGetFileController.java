package com.globits.da.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.globits.da.repository.ProductRepository;

@Controller
@RequestMapping(path = "public")
public class RestGetFileController {
	@Autowired
	private Environment env;
	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping(path = "/image/product/{productId}/{filename}/{type}", method = RequestMethod.GET)
	public void getImage(HttpServletResponse response, 
			@PathVariable String productId, 
			@PathVariable String filename, 
			@PathVariable String type) throws IOException {
		String path = "";
		if(env.getProperty("file.folder") != null) {
			path = env.getProperty("file.folder");
		}
		path += "statics/product/" + productId +  "/";
		System.out.println(path + filename + "." + type);
	    File file = new File(path + filename + "." + type);
	    if(file.exists()) {
	        String contentType = "application/octet-stream";
	        response.setContentType(contentType);
	        OutputStream out = response.getOutputStream();
	        FileInputStream in = new FileInputStream(file);
	        // copy from in to out
	        IOUtils.copy(in, out);
	        out.close();
	        in.close();
	    }else {
	        throw new FileNotFoundException();
	    }
	}

//	@Autowired
//	TimeSheetServiceV2 service;
//	@RequestMapping(method = RequestMethod.POST)
//	public ResponseEntity<TimeSheetV2Dto> saveTimeSheet(@RequestBody TimeSheetV2Dto dto) {
//		TimeSheetV2Dto result = service.createTimeSheet(dto);
//		return new ResponseEntity<TimeSheetV2Dto>(result, HttpStatus.OK);
//	}
	
	
//	@RequestMapping(path = "/getFile/{id}", method = RequestMethod.GET)
//	public void getFile(HttpServletResponse response, @PathVariable UUID id) throws IOException {
//		String path = "";
//		if(env.getProperty("file.folder") != null) {
//			path = env.getProperty("file.folder");
//		} 
//		HopDong hopDong = contractRespo.findById(id).get();
//		
//	   
//	    try {
//	    	 File file = new File(path +hopDong.getUrl());
//	    	     byte[] data = FileUtils.readFileToByteArray(file);
//	        // Thiết lập thông tin trả về
//	        response.setContentType("application/octet-stream");
//	        response.setHeader("Content-disposition", "attachment; filename=" + file.getName());
//	        response.setContentLength(data.length);
//	        InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
//	        FileCopyUtils.copy(inputStream, response.getOutputStream());
//	      } catch (Exception ex) {
//	        ex.printStackTrace();
//	      }
//
//	    
//	}
}
