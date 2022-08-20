package com.globits.da.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globits.da.AFFakeConstants;
import com.globits.da.domain.Product;
import com.globits.da.dto.CategoryDto;
import com.globits.da.dto.ProductDto;
import com.globits.da.dto.search.ProductSearchByPageDto;
import com.globits.da.dto.search.ProductSearchDto;
import com.globits.da.dto.search.SearchDto;
import com.globits.da.service.ProductService;

@RestController
@RequestMapping(path = "api/product")
public class RestProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping(path = "{page}/{pageSize}")
	@Secured({   AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	public ResponseEntity<Page<ProductDto>> getPage(@PathVariable("page") Integer page,
			@PathVariable("pageSize")  Integer pageSize) {
		Page<ProductDto> result = productService.getPage(page, pageSize);
		return new ResponseEntity<Page<ProductDto>>(result, HttpStatus.OK);
	}
	
	@Secured({   AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ProductDto> save(@RequestBody ProductDto dto) {
		ProductDto result = productService.saveOrUpdate(null, dto);
		return new ResponseEntity<ProductDto>(result, HttpStatus.OK);
	}

	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ProductDto> update(@RequestBody ProductDto dto, @PathVariable(name = "id") UUID id) {
		ProductDto result = productService.saveOrUpdate(id, dto);
		return new ResponseEntity<ProductDto>(result, HttpStatus.OK);
	}
	
	@Secured({   AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProductDto> getProductById(@PathVariable("id") UUID id) {
		ProductDto result = productService.getProductById(id);
		return new ResponseEntity<ProductDto>(result, HttpStatus.OK);
	}
	
	@Secured({   AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") UUID id) {
		Boolean result = productService.deleteProduct(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	public ResponseEntity<Boolean> deleteManyProduct(@RequestParam(name = "id", required = true) String listProductId) {
		List<String> stringList = new ArrayList<String>(Arrays.asList(listProductId.split(",")));
		List<UUID> uuidList = new ArrayList<UUID>();
		for (int i = 0; i<stringList.size(); i++) {
			uuidList.add(i, UUID.fromString(stringList.get(i)));
		}
		Boolean result = productService.deleteManyProduct(uuidList);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/searchByPage", method = RequestMethod.POST)
	@Secured({   AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	public ResponseEntity<Page<ProductDto>> searchByPage(@RequestBody ProductSearchByPageDto searchDto) {
		Page<ProductDto> page = productService.searchByPage(searchDto);
		return new ResponseEntity<Page<ProductDto>>(page, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/filterByPage", method = RequestMethod.POST)
//	@Secured({   AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	public ResponseEntity<Page<ProductDto>> filterByPage(@RequestBody ProductSearchDto searchDto) {
		Page<ProductDto> page = productService.filterByPage(searchDto);
		return new ResponseEntity<Page<ProductDto>>(page, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@Secured({   AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	public ResponseEntity<Page<ProductDto>> search(@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "rpp", required = false) Integer rpp,
			@RequestParam(name = "sort", required = false) String sort,
			@RequestParam(name = "direction", required = false) String direction,
			@RequestParam(name = "search", required = false) String search) {
		ProductSearchDto productSearchDto = new ProductSearchDto(page, rpp, sort, direction, search);
		System.out.println("abc");
		System.out.println(productSearchDto);
		Page<ProductDto> result = productService.search(productSearchDto);
		return new ResponseEntity<Page<ProductDto>>(result, HttpStatus.OK);
	}
	
	@Secured({  AFFakeConstants.ROLE_ADMIN, AFFakeConstants.ROLE_SUPER_ADMIN })
	@RequestMapping(value = "/checkCode", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkCode(@RequestParam(value = "id", required = false) UUID id,
			@RequestParam("code") String code) {
		Boolean result = productService.checkCode(id, code);
		return new ResponseEntity<Boolean>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
}
