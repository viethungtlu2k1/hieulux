package com.globits.da.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.globits.da.dto.*;
import com.globits.da.dto.search.CurrencySearchDTO;
@Service
public interface CurrencyService {
	public Page<CurrencyDTO> getPage(int pageSize, int pageIndex);
	public CurrencyDTO saveOrUpdate(UUID id,CurrencyDTO dto);

	Page<CurrencyDTO> searchByPage(CurrencySearchDTO dto);
	Boolean checkCode (UUID id,String code);
	public Boolean deleteCheckById(UUID id);
//	public EmployeeDTO getById(UUID id);
	public CurrencyDTO getCertificate(UUID id);
	//test
	public List<CurrencyDTO> getAll();

}
