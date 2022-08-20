package com.globits.da.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globits.da.AFFakeConstants;
import com.globits.da.dto.AdSpaceDto;
import com.globits.da.dto.CategoryAdSpaceDto;
import com.globits.da.dto.NationalyAdSpaceDto;
import com.globits.da.dto.search.SearchDto;
import com.globits.da.service.AdSpaceService;

@RestController
@RequestMapping(value = "/api/space")
public class RestAdSpaceController {

	@Autowired
	private AdSpaceService service;

	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@PostMapping(value = "/searchByPage")
	public ResponseEntity<Page<AdSpaceDto>> search(@RequestBody SearchDto dto) {
		Page<AdSpaceDto> result = service.searchByPage(dto);
		return new ResponseEntity<Page<AdSpaceDto>>(result, HttpStatus.OK);
	}

	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@GetMapping(value = "/{id}")
	public ResponseEntity<AdSpaceDto> getAdSpaceById(@PathVariable UUID id) {
		AdSpaceDto result = service.getAdSpaceById(id);
		return new ResponseEntity<AdSpaceDto>(result, HttpStatus.OK);
	}

//	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@PostMapping(value = "")
	public ResponseEntity<AdSpaceDto> createAdSpace(@RequestBody AdSpaceDto dto) {
		AdSpaceDto result = service.saveOrUpdate(null, dto);
		return new ResponseEntity<AdSpaceDto>(result, HttpStatus.OK);
	}

	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@PutMapping(value = "/{id}")
	public ResponseEntity<AdSpaceDto> updateAdSpace(@RequestBody AdSpaceDto dto, @PathVariable UUID id) {
		AdSpaceDto result = service.saveOrUpdate(id, dto);
		return new ResponseEntity<AdSpaceDto>(result, HttpStatus.OK);
	}

	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> deleteAdSpace(@PathVariable UUID id) {
		Boolean result = service.deleteById(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@GetMapping(value = "/checkName")
	public ResponseEntity<Boolean> checkName(@RequestParam(name = "id") UUID id, @RequestParam(name = "name") String name) {
		Boolean result = service.checkName(id, name);
		return new ResponseEntity<Boolean>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@GetMapping(value = "/nationaly/{id}")
	public ResponseEntity<List<NationalyAdSpaceDto>> getNationByAdSpace(@PathVariable UUID id) {
		List<NationalyAdSpaceDto> result = service.getNationalyAdSpaceByAdSpaceId(id);
		return new ResponseEntity<List<NationalyAdSpaceDto>>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@GetMapping(value = "/category/{id}")
	public ResponseEntity<List<CategoryAdSpaceDto>> getCategoryByAdSpace(@PathVariable UUID id) {
		List<CategoryAdSpaceDto> result = service.getCategoryAdSpaceByAdSpaceId(id);
		return new ResponseEntity<List<CategoryAdSpaceDto>>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

}
