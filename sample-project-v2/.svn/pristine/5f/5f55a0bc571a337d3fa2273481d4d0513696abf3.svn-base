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
import com.globits.da.dto.CampaignTypeDto;
import com.globits.da.dto.search.SearchDto;
import com.globits.da.service.CampaignTypeService;

@RestController
@RequestMapping("/api/campaignType")
public class RestCampaignTypeController {
	@Autowired
	CampaignTypeService campaignTypeService;
	 

	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/{pageIndex}/{pageSize}", method = RequestMethod.GET)
	public ResponseEntity<Page<CampaignTypeDto>> getPage(@PathVariable int pageIndex, @PathVariable int pageSize) {
		Page<CampaignTypeDto> results = campaignTypeService.getPage(pageSize, pageIndex);
		return new ResponseEntity<Page<CampaignTypeDto>>(results, HttpStatus.OK);
	}

	@Secured({   AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CampaignTypeDto> save(@RequestBody CampaignTypeDto dto) {
		CampaignTypeDto result = campaignTypeService.saveOrUpdate(null, dto);
		return new ResponseEntity<CampaignTypeDto>(result, HttpStatus.OK);
	}

	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<CampaignTypeDto> save(@RequestBody CampaignTypeDto dto, @PathVariable UUID id) {
		CampaignTypeDto result = campaignTypeService.saveOrUpdate(id, dto);
		return new ResponseEntity<CampaignTypeDto>(result, HttpStatus.OK);
	}

	@Secured({   AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CampaignTypeDto> getList(@PathVariable UUID id) {
		CampaignTypeDto result = campaignTypeService.getCertificate(id);
		return new ResponseEntity<CampaignTypeDto>(result, HttpStatus.OK);
	}

	@Secured({   AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable UUID id) {
		Boolean result = campaignTypeService.deleteKho(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/searchByPage", method = RequestMethod.POST)
	public ResponseEntity<Page<CampaignTypeDto>> searchByPage(@RequestBody SearchDto searchDto) {
		Page<CampaignTypeDto> page = this.campaignTypeService.searchByPage(searchDto);
		return new ResponseEntity<Page<CampaignTypeDto>>(page, HttpStatus.OK);
	}

	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/checkCode", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkCode(@RequestParam(value = "id", required = false) UUID id,
			@RequestParam("code") String code) {
		Boolean result = campaignTypeService.checkCode(id, code);
		return new ResponseEntity<Boolean>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
}
