package jp.co.toshiba.ppok.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.toshiba.ppok.dto.CityDto;
import jp.co.toshiba.ppok.service.CentreService;
import jp.co.toshiba.ppok.utils.Pagination;
import jp.co.toshiba.ppok.utils.RestMessage;

/**
 * Center Terminal Controller handle the retrieve and update requests.
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/public/grssmcrud")
public class CentreController {

	/**
	 * pageSize
	 */
	private static final Integer PAGESIZE = 17;

	/**
	 * Central service interface
	 */
	@Resource
	private CentreService centreService;

	/**
	 * Retrieve the city data.
	 *
	 * @return page(JSON)
	 */
	@GetMapping(value = "/city")
	public RestMessage getCities(@RequestParam(value = "pageNum", defaultValue = "1") final Integer pageNum,
			@RequestParam(value = "keyword", defaultValue = "") final String keyword) {
		final Pagination<CityDto> cityInfos = this.centreService.findByKeywords(pageNum, PAGESIZE, keyword);
		return RestMessage.success().add("pageInfo", cityInfos);
	}

	/**
	 * Search the selected city's name.
	 *
	 * @param id the ID of city
	 * @return RestMsg.success().add(data)
	 */
	@GetMapping(value = "/city/{id}")
	public RestMessage getCityInfo(@PathVariable("id") final Integer id) {
		final CityDto cityInfo = this.centreService.getCityInfo(id);
		return RestMessage.success().add("citySelected", cityInfo);
	}

	/**
	 * Save inputted city info.
	 *
	 * @param cityDto the input message of cities
	 * @return RestMsg.success()
	 */
	@PostMapping(value = "/city")
	public RestMessage saveCityInfo(@RequestBody final CityDto cityDto) {
		this.centreService.save(cityDto);
		return RestMessage.success();
	}

	/**
	 * Update city info.
	 *
	 * @param cityDto the input message of cities
	 * @return RestMsg.success()
	 */
	@PutMapping(value = "/city/{id}")
	public RestMessage updateCityDto(@RequestBody final CityDto cityDto) {
		this.centreService.update(cityDto);
		return RestMessage.success();
	}

	/**
	 * Delete the selected city info.
	 *
	 * @param id the ID of city
	 * @return RestMsg.success()
	 */
	@DeleteMapping(value = "/city/{id}")
	public RestMessage deleteCityDto(@PathVariable("id") final Integer id) {
		this.centreService.removeById(id);
		return RestMessage.success();
	}

	/**
	 * Check the input city name already existed or not.
	 *
	 * @param cityName the input name
	 * @return RestMsg.success()
	 */
	@GetMapping(value = "/checklist")
	public RestMessage checkCityName(@RequestParam("cityName") final String cityName) {
		final String regex = "^[a-zA-Z-\\p{IsWhiteSpace}]{4,17}$";
		if (cityName.matches(regex)) {
			final Boolean duplicated = this.centreService.checkDuplicated(cityName);
			if (Boolean.TRUE.equals(duplicated)) {
				return RestMessage.failure().add("validatedMsg", "City name is duplicate.");
			} else {
				return RestMessage.success();
			}
		} else {
			return RestMessage.failure().add("validatedMsg", "Name of cities should be in 4~17 Latin alphabets.");
		}
	}

	/**
	 * Get list of continents.
	 *
	 * @return RestMsg.success().add(data)
	 */
	@GetMapping(value = "/continents")
	public RestMessage getListOfContinents() {
		final List<String> cnList = this.centreService.findAllContinents();
		return RestMessage.success().add("continents", cnList);
	}

	/**
	 * Get list of nations.
	 *
	 * @return RestMsg.success().add(data)
	 */
	@GetMapping(value = "/countries")
	public RestMessage getListOfNations(@RequestParam("continentVal") final String continent) {
		final List<String> nationList = this.centreService.findNationsByCnt(continent);
		return RestMessage.success().add("nations", nationList);
	}

	/**
	 * Get list of nations.
	 *
	 * @return RestMsg.success().add(data)
	 */
	@GetMapping(value = "/countries/{id}")
	public RestMessage getListOfNationsById(@PathVariable("id") final Integer id) {
		final List<String> nationList = this.centreService.findNationsByCityId(id);
		return RestMessage.success().add("nationsByName", nationList);
	}

	/**
	 * Get language by nation.
	 *
	 * @return RestMsg.success().add(data)
	 */
	@GetMapping(value = "/language")
	public RestMessage getLanguages(@RequestParam("nationVal") final String nation) {
		final String language = this.centreService.findLanguageByCty(nation);
		return RestMessage.success().add("languages", language);
	}
}