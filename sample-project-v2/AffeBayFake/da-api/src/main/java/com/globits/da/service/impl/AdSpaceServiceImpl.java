package com.globits.da.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.Constants;
import com.globits.da.domain.AdSpace;
import com.globits.da.domain.Category;
import com.globits.da.domain.DetailCategoryAdSpace;
import com.globits.da.domain.DetailNationalyAdSpace;
import com.globits.da.domain.Nationaly;
import com.globits.da.dto.AdSpaceDto;
import com.globits.da.dto.CategoryAdSpaceDto;
import com.globits.da.dto.NationalyAdSpaceDto;
import com.globits.da.dto.search.SearchDto;
import com.globits.da.repository.AdSpaceRepository;
import com.globits.da.repository.CategoryRepository;
import com.globits.da.repository.DetailAdSpaceCategoryRepository;
import com.globits.da.repository.DetailAdSpaceNationalyRepository;
import com.globits.da.repository.NationalyRepository;
import com.globits.da.service.AdSpaceService;

@Service
public class AdSpaceServiceImpl extends GenericServiceImpl<AdSpace, UUID> implements AdSpaceService {

	@Autowired
	private AdSpaceRepository repos;

	@Autowired
	private NationalyRepository nationalRepos;

	@Autowired
	private CategoryRepository categoryRepos;

	@Autowired
	private DetailAdSpaceNationalyRepository detailRepos;

	@Autowired
	private DetailAdSpaceCategoryRepository detailCaAdRepos;

	@Override
	public Page<AdSpaceDto> searchByPage(SearchDto dto) {
		if (dto == null) {
			return null;
		}
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;

		String whereClause = "";
		String orderBy = " ORDER BY entity.createDate DESC";
		String sqlCount = "select count(entity.id) from  AdSpace as entity where (1=1) ";
		String sql = "select new com.globits.da.dto.AdSpaceDto(entity) from  AdSpace as entity where (1=1) ";
		
		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			whereClause += " AND ( entity.name LIKE :text OR entity.description LIKE :text )";
		}

		sql += whereClause + orderBy;
		sqlCount += whereClause;
		Query q = manager.createQuery(sql, AdSpaceDto.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			q.setParameter("text", '%' + dto.getKeyword() + '%');
			qCount.setParameter("text", '%' + dto.getKeyword() + '%');
		}
		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<AdSpaceDto> entities = q.getResultList();
		long count = (long) qCount.getSingleResult();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<AdSpaceDto> result = new PageImpl<AdSpaceDto>(entities, pageable, count);
		return result;
	}

	@Override
	public AdSpaceDto saveOrUpdate(UUID id, AdSpaceDto dto) {

		if (dto != null) {
			Set<Nationaly> nationalyDtos = dto.getNationaly();
			Set<Nationaly> nationalies = new HashSet<Nationaly>();
			
			Set<Category> categoryDtos = dto.getCategory();
			Set<Category> categories = new HashSet<Category>();

			List<DetailNationalyAdSpace> listNationalSpace = new ArrayList<>();
			List<DetailCategoryAdSpace> listCategorySpace = new ArrayList<>();

			AdSpace entity = null;
			Nationaly nationaly = null;
			Category category = null;
			DetailNationalyAdSpace detailNaAdSpace = null;
			DetailCategoryAdSpace detailCaAdSpace = null;

			if (dto.getId() != null) {
				entity = repos.getOne(id);
				listNationalSpace = detailRepos.findAllByAdSpaceId(entity.getId());
				listCategorySpace = detailCaAdRepos.findAllByAdSpaceId(entity.getId());
				
				for (DetailNationalyAdSpace detail : listNationalSpace) {
					detailRepos.deleteByAdSpaceId(detail.getAdSpace().getId());
				}
				
				for (DetailCategoryAdSpace detail : listCategorySpace) {
					detailCaAdRepos.deleteByAdSpaceId(detail.getAdSpace().getId());
				}

			}
			if (entity == null) {
				entity = new AdSpace();
			}
			
			for (Nationaly nationCode : nationalyDtos) {
				nationaly = nationalRepos.findOneByCode(nationCode.getCode());
				if (nationaly != null) {
					nationalies.add(nationaly);
				}
			}
			
			for (Category item : categoryDtos) {
				category = categoryRepos.findOneByCode(item.getCode());
				if (category != null) {
					categories.add(category);
				}
			}

			for (Nationaly nationalyEntity : nationalies) {
				detailNaAdSpace = new DetailNationalyAdSpace(entity, nationalyEntity);
				listNationalSpace.add(detailNaAdSpace);
			}

			for (Category categoryEntity : categories) {
				detailCaAdSpace = new DetailCategoryAdSpace(entity, categoryEntity);
				listCategorySpace.add(detailCaAdSpace);
			}

			entity.setName(dto.getName());
			entity.setDescription(dto.getDescription());
			entity.setExperience(dto.getExperience());

			switch (dto.getChannel()) {
			case 1:
				entity.setChannel(Constants.ChannelAds.Webiste.getValue());
				entity.setUrlWebsite(dto.getUrlWebsite());
				break;
			case 2:
				entity.setChannel(Constants.ChannelAds.Contextual_Advertiser.getValue());
				entity.setContextualAdvertisingSystem(dto.getContextualAdvertisingSystem());
				break;
			case 3:
				entity.setChannel(Constants.ChannelAds.Social_Netword.getValue());
				switch (dto.getNetwork()) {
				case "Facebook":
					entity.setNetwork(Constants.Social_Netword.Facebook.name());
					break;
				case "Zalo":
					entity.setNetwork(Constants.Social_Netword.Zalo.name());
					break;
				case "Tiktok":
					entity.setNetwork(Constants.Social_Netword.Tiktok.name());
					break;
				case "Youtube":
					entity.setNetwork(Constants.Social_Netword.Other.name());
					break;

				default:
					entity.setNetwork(Constants.Social_Netword.Other.name());
					break;
				}
				entity.setUrlPage(dto.getUrl_page());
				entity.setFollower(dto.getFollower());
				entity.setLikeAmount(dto.getLikeAmount());
				break;
			case 4:
				entity.setChannel(Constants.ChannelAds.Youtube_channel.getValue());
				entity.setUrlYoutube(dto.getUrlYoutube());
				entity.setNameYoutube(dto.getNameYoutube());
				entity.setSubscribeYoutube(dto.getSubscribeYoutube());
				break;

			default:
				break;
			}

			for (Nationaly nationalyEntity : nationalies) {
				detailNaAdSpace.setAdSpace(entity);
				detailNaAdSpace.setNationaly(nationalyEntity);
			}

			for (Category categoryEntity : categories) {
				detailCaAdSpace.setAdSpace(entity);
				detailCaAdSpace.setCategory(categoryEntity);
			}

			entity = repos.save(entity);

			for (DetailNationalyAdSpace detailEntity : listNationalSpace) {
				detailNaAdSpace = detailRepos.save(detailEntity);
			}

			for (DetailCategoryAdSpace detailEntity : listCategorySpace) {
				detailCaAdSpace = detailCaAdRepos.save(detailEntity);
			}

			if (entity == null) {
				return new AdSpaceDto(entity);
			}
		}

		return null;

	}

	@Override
	public Boolean checkName(UUID id, String name) {
		if (name != null && StringUtils.hasText(name)) {
			Long count = repos.checkName(name, id);
			return count != 0l;
		}
		return null;
	}

	@Override
	public Boolean deleteById(UUID id) {

		List<DetailNationalyAdSpace> listNationalSpace = new ArrayList<>();
		List<DetailCategoryAdSpace> listCategorySpace = new ArrayList<>();

		if (id != null) {
			listNationalSpace = detailRepos.findAllByAdSpaceId(id);
			listCategorySpace = detailCaAdRepos.findAllByAdSpaceId(id);

			for (DetailNationalyAdSpace detail : listNationalSpace) {
				detailRepos.deleteById(detail.getId());
			}

			for (DetailCategoryAdSpace detail : listCategorySpace) {
				detailCaAdRepos.deleteById(detail.getId());
			}

			repos.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public AdSpaceDto getAdSpaceById(UUID id) {
		if (id != null) {
			AdSpace adSpace = repos.getOne(id);
			if (adSpace != null) {
				return new AdSpaceDto(adSpace);
			}
		}
		return null;
	}

	@Override
	public List<NationalyAdSpaceDto> getNationalyAdSpaceByAdSpaceId(UUID id) {
		if(id != null) {
			List<NationalyAdSpaceDto> list = new ArrayList<>();
			List<DetailNationalyAdSpace> entities = detailRepos.findAllByAdSpaceId(id);
			for(DetailNationalyAdSpace item : entities) {
				NationalyAdSpaceDto dto = new NationalyAdSpaceDto(item);
				list.add(dto);
			}
			return list;
		}
		return null;
	}

	@Override
	public List<CategoryAdSpaceDto> getCategoryAdSpaceByAdSpaceId(UUID id) {
		if(id != null) {
			List<CategoryAdSpaceDto> list = new ArrayList<>();
			List<DetailCategoryAdSpace> entities = detailCaAdRepos.findAllByAdSpaceId(id);
			for(DetailCategoryAdSpace item : entities) {
				CategoryAdSpaceDto dto = new CategoryAdSpaceDto(item);
				list.add(dto);
			}
			return list;
		}
		return null;
	}

}
