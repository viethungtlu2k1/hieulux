package com.globits.da.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.globits.da.AFFakeConstants;
import com.globits.da.dto.LandingPageDto;
import com.globits.da.dto.search.PagingSearchDto;
import com.globits.da.service.LandingPageService;

@Controller
@RequestMapping(path = "api/landingPage")
public class RestLandingPageController {

	@Autowired
	LandingPageService service;
	
	@GetMapping(path = "{page}/{pageSize}")
//	@Secured({   AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	public ResponseEntity<Page<LandingPageDto>> getPage(@PathVariable("page") Integer page,
			@PathVariable("pageSize")  Integer pageSize) {
		Page<LandingPageDto> result = service.getPage(page, pageSize);
		return new ResponseEntity<Page<LandingPageDto>>(result, HttpStatus.OK);
	}
	
//	@Secured({   AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<LandingPageDto> save(@RequestBody LandingPageDto dto) {
		LandingPageDto result = service.saveOrUpdate(null, dto);
		return new ResponseEntity<LandingPageDto>(result, HttpStatus.OK);
	}

//	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<LandingPageDto> update(@RequestBody LandingPageDto dto, @PathVariable(name = "id") UUID id) {
		LandingPageDto result = service.saveOrUpdate(id, dto);
		return new ResponseEntity<LandingPageDto>(result, HttpStatus.OK);
	}
	
//	@Secured({   AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<LandingPageDto> getProductById(@PathVariable("id") UUID id) {
		LandingPageDto result = service.getById(id);
		return new ResponseEntity<LandingPageDto>(result, HttpStatus.OK);
	}
	
//	@Secured({   AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") UUID id) {
		Boolean result = service.deleteById(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/searchByPage", method = RequestMethod.POST)
//	@Secured({   AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	public ResponseEntity<Page<LandingPageDto>> searchByPage(@RequestBody PagingSearchDto searchDto) {
		Page<LandingPageDto> page = service.searchByPage(searchDto);
		return new ResponseEntity<Page<LandingPageDto>>(page, HttpStatus.OK);
	}
	
//	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/checkCode", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkCode(@RequestParam(value = "id", required = false) UUID id,
			@RequestParam("code") String code) {
		Boolean result = service.checkCode(id, code);
		return new ResponseEntity<Boolean>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
}
