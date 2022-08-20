package com.globits.da.rest;

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
import com.globits.da.dto.CampaignDto;
import com.globits.da.dto.search.SearchDto;
import com.globits.da.service.CampaignService;

@RestController
@RequestMapping("/api/campaign")
public class RestCampaignController {
	@Autowired
	CampaignService CampaignService;
	 

	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/{pageIndex}/{pageSize}", method = RequestMethod.GET)
	public ResponseEntity<Page<CampaignDto>> getPage(@PathVariable int pageIndex, @PathVariable int pageSize) {
		Page<CampaignDto> results = CampaignService.getPage(pageSize, pageIndex);
		return new ResponseEntity<Page<CampaignDto>>(results, HttpStatus.OK);
	}

	@Secured({   AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CampaignDto> save(@RequestBody CampaignDto dto) {
		CampaignDto result = CampaignService.saveOrUpdate(null, dto);
		return new ResponseEntity<CampaignDto>(result, HttpStatus.OK);
	}

	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<CampaignDto> save(@RequestBody CampaignDto dto, @PathVariable UUID id) {
		CampaignDto result = CampaignService.saveOrUpdate(id, dto);
		return new ResponseEntity<CampaignDto>(result, HttpStatus.OK);
	}

	@Secured({   AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CampaignDto> getList(@PathVariable UUID id) {
		CampaignDto result = CampaignService.getCertificate(id);
		return new ResponseEntity<CampaignDto>(result, HttpStatus.OK);
	}

	@Secured({   AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable UUID id) {
		Boolean result = CampaignService.deleteKho(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/searchByPage", method = RequestMethod.POST)
	public ResponseEntity<Page<CampaignDto>> searchByPage(@RequestBody SearchDto searchDto) {
		Page<CampaignDto> page = this.CampaignService.searchByPage(searchDto);
		return new ResponseEntity<Page<CampaignDto>>(page, HttpStatus.OK);
	}

	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/checkCode", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkCode(@RequestParam(value = "id", required = false) UUID id,
			@RequestParam("code") String code) {
		Boolean result = CampaignService.checkCode(id, code);
		return new ResponseEntity<Boolean>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
}
