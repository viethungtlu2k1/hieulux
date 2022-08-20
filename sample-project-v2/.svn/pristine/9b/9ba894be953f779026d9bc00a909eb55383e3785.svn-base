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
import com.globits.da.dto.ChannelAdsDto;
import com.globits.da.dto.search.SearchDto;
import com.globits.da.service.ChannelAdsService;

@RestController
@RequestMapping(value = "/api/channel")
public class RestChannelAdsController {

	@Autowired
	private ChannelAdsService service;

	@Secured({ AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/{pageIndex}/{pageSize}", method = RequestMethod.GET)
	public ResponseEntity<Page<ChannelAdsDto>> getPage(@PathVariable int pageIndex, @PathVariable int pageSize) {
		Page<ChannelAdsDto> results = service.getPage(pageSize, pageIndex);
		return new ResponseEntity<Page<ChannelAdsDto>>(results, HttpStatus.OK);
	}

	@Secured({ AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ChannelAdsDto> save(@RequestBody ChannelAdsDto dto) {
		ChannelAdsDto result = service.saveOrUpdate(null, dto);
		return new ResponseEntity<ChannelAdsDto>(result, HttpStatus.OK);
	}

	@Secured({ AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ChannelAdsDto> save(@RequestBody ChannelAdsDto dto, @PathVariable UUID id) {
		ChannelAdsDto result = service.saveOrUpdate(id, dto);
		return new ResponseEntity<ChannelAdsDto>(result, HttpStatus.OK);
	}

	@Secured({ AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ChannelAdsDto> getById(@PathVariable UUID id) {
		ChannelAdsDto result = service.getCertificate(id);
		return new ResponseEntity<ChannelAdsDto>(result, HttpStatus.OK);
	}

	@Secured({ AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable UUID id) {
		Boolean result = service.deleteKho(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

//	@Secured({ AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<ChannelAdsDto>> getAllChannel() {
		List<ChannelAdsDto> result = service.getAllChannelAds();
		return new ResponseEntity<List<ChannelAdsDto>>(result, HttpStatus.OK);
	}

	@Secured({ AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/searchByPage", method = RequestMethod.POST)
	public ResponseEntity<Page<ChannelAdsDto>> searchByPage(@RequestBody SearchDto searchDto) {
		Page<ChannelAdsDto> page = this.service.searchByPage(searchDto);
		return new ResponseEntity<Page<ChannelAdsDto>>(page, HttpStatus.OK);
	}

//	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/checkCode", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkCode(@RequestParam(value = "id", required = false) UUID id,
			@RequestParam("code") String code) {
		Boolean result = service.checkCode(id, code);
		return new ResponseEntity<Boolean>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

}
