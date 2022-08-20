package com.globits.da.repository;


import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.globits.da.dto.*;
import java.util.List;



import com.globits.da.domain.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, UUID> {
	@Query(value = "SELECT count(currency.id) FROM Currency currency WHERE currency.code = ?1 and (currency.id <> ?2 or ?2 is null) ")
	Long checkCode(String code, UUID id);
	@Query("SELECT new com.globits.da.dto.CurrencyDTO(ed) FROM Currency ed")
	Page<CurrencyDTO> getListPage( Pageable pageable);
	
	@Query(value = "SELECT new com.globits.da.dto.CurrencyDTO(ed) FROM Currency ed")
	List<CurrencyDTO> getAllCurrency();
	
	@Query(value = "SELECT ed FROM Currency ed WHERE ed.code =?1")
	Currency findByCode(String Code);
}
