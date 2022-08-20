package com.globits.da.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.Product;
import com.globits.da.dto.ProductDto;
import com.globits.da.dto.search.ProductSearchByPageDto;
import com.globits.da.dto.search.ProductSearchDto;

@Service
public interface ProductService extends GenericService<Product, UUID> {
	public Page<ProductDto> getPage(Integer page, Integer pageSize);
//	public HashMap<String, Object> getProduct(ProductSearchDto productSearchDto);
	public ProductDto saveOrUpdate(UUID id,ProductDto dto);
	public ProductDto getProductById(UUID id);
	public Boolean deleteProduct(UUID id);
	public Boolean deleteManyProduct(List<UUID> listProductId);
	public Page<ProductDto> searchByPage(ProductSearchByPageDto productSearchDto);
	public Page<ProductDto> filterByPage(ProductSearchDto dto);
	public Page<ProductDto> search(ProductSearchDto productSearchDto);
	Boolean checkCode (UUID id,String code);
	public ProductDto updateImage(String mainImageUrl, UUID id);
}
