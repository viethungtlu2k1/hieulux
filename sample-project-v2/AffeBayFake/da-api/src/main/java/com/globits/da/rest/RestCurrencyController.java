package com.globits.da.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.globits.da.AFFakeConstants;
import com.globits.da.dto.CurrencyDTO;
import com.globits.da.dto.search.CurrencySearchDTO;
import com.globits.da.service.CurrencyService;

@RestController
@RequestMapping("/api/currency")
public class RestCurrencyController {
	@Autowired
	CurrencyService currencyService;
	
	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/{pageIndex}/{pageSize}", method = RequestMethod.GET)
	public ResponseEntity<Page<CurrencyDTO>> getPage(@PathVariable int pageIndex, @PathVariable int pageSize) {
		Page<CurrencyDTO> results = currencyService.getPage(pageSize, pageIndex);
		return new ResponseEntity<Page<CurrencyDTO>>(results, HttpStatus.OK);
	}

//	@Secured({   AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CurrencyDTO> save(@RequestBody CurrencyDTO dto) {
		CurrencyDTO result = currencyService.saveOrUpdate(null, dto);
		return new ResponseEntity<CurrencyDTO>(result, HttpStatus.OK);
	}

	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<CurrencyDTO> save(@RequestBody CurrencyDTO dto, @PathVariable UUID id) {
		CurrencyDTO result = currencyService.saveOrUpdate(id, dto);
		return new ResponseEntity<CurrencyDTO>(result, HttpStatus.OK);
	}

//	@Secured({   AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ResponseEntity<CurrencyDTO> getList(@PathVariable UUID id) {
//		CurrencyDTO result = currencyService.getCertificate(id);
//		return new ResponseEntity<CurrencyDTO>(result, HttpStatus.OK);
//	}

	@Secured({   AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable UUID id) {
		Boolean result = currencyService.deleteCheckById(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/searchByPage", method = RequestMethod.POST)
	public ResponseEntity<Page<CurrencyDTO>> searchByPage(@RequestBody CurrencySearchDTO searchDto) {
		Page<CurrencyDTO> page = this.currencyService.searchByPage(searchDto);
		return new ResponseEntity<Page<CurrencyDTO>>(page, HttpStatus.OK);
	}

	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/checkCode", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkCode(@RequestParam(value = "id", required = false) UUID id,
			@RequestParam("code") String code) {
		Boolean result = currencyService.checkCode(id, code);
		return new ResponseEntity<Boolean>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	@Secured({AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CurrencyDTO> getList(@PathVariable UUID id) {
		CurrencyDTO result = currencyService.getCertificate(id);
		return new ResponseEntity<CurrencyDTO>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAll")
	public @ResponseBody List<CurrencyDTO> test1() {
		
		List<CurrencyDTO> employeeDTOs = currencyService.getAll();
		
		return employeeDTOs;
	}
}
