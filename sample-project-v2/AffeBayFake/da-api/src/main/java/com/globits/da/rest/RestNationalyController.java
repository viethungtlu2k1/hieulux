package com.globits.da.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globits.da.AFFakeConstants;
import com.globits.da.dto.NationalyDto;
import com.globits.da.dto.search.SearchDto;
import com.globits.da.service.NationalyService;

@RestController
@RequestMapping("/api/nationaly")
public class RestNationalyController {

	@Autowired
	NationalyService service;
	
	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "getPage/{pageIndex}/{pageSize}", method = RequestMethod.GET)
	public ResponseEntity<Page<NationalyDto>> getPage(@PathVariable int pageIndex, @PathVariable int pageSize) {
		Page<NationalyDto> results = service.GetPage(pageSize, pageIndex);
		return new ResponseEntity<Page<NationalyDto>>(results, HttpStatus.OK);
	}
	
	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<NationalyDto> save(@RequestBody NationalyDto dto, @PathVariable UUID id) {
		NationalyDto result = service.SaveOrUpdate(id, dto);
		return new ResponseEntity<NationalyDto>(result, HttpStatus.OK);
	}
	
	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/{id_nation}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> DeleteById(@PathVariable UUID id_nation) {
		Boolean results = service.DeleteNation(id_nation);
		return new ResponseEntity<Boolean>(results, HttpStatus.OK);
	}
	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<NationalyDto> getPage(@PathVariable UUID id) {
		NationalyDto results = service.GetOneEntity(id);
		return new ResponseEntity<NationalyDto>(results, HttpStatus.OK);
	}
	
	@Secured({   AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<NationalyDto> save(@RequestBody NationalyDto dto) {
		NationalyDto result = service.SaveOrUpdate(null, dto);
		return new ResponseEntity<NationalyDto>(result, HttpStatus.OK);
	}
	
	@Secured({   AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/getAllNation",method = RequestMethod.GET)
	public ResponseEntity<List<NationalyDto>> getAllNation() {
		List<NationalyDto> result = service.getAllNation();
		return new ResponseEntity<List<NationalyDto>>(result, HttpStatus.OK);
	}
	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/searchByPage", method = RequestMethod.POST)
	public ResponseEntity<Page<NationalyDto>> searchByPage(@RequestBody SearchDto searchDto) {
		Page<NationalyDto> page = service.searchByPage(searchDto);
		return new ResponseEntity<Page<NationalyDto>>(page, HttpStatus.OK);
	}
	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/checkCode", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkCode(@RequestParam(value = "id", required = false) UUID id,
			@RequestParam("code") String code) {
		Boolean result = service.checkCode(id, code);
		return new ResponseEntity<Boolean>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
}
