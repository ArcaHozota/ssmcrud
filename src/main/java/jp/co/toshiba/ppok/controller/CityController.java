package jp.co.toshiba.ppok.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;

import jakarta.validation.Valid;
import jp.co.toshiba.ppok.service.CityDtoService;
import jp.co.toshiba.ppok.utils.CityDto;
import jp.co.toshiba.ppok.utils.RestMsg;

/**
 * Center Terminal Controller Handle the retrieve and update requests.
 *
 * @author Administrator
 */
@RestController
public class CityController {

    @Autowired
    private CityDtoService cityDtoService;

    /**
     * Retrieve the city data.
     *
     * @return page(JSON)
     */
    @GetMapping(value = "/city")
    public RestMsg getCities(@RequestParam(value = "pageNum", defaultValue = "1") final Integer pageNum) {
        PageMethod.startPage(pageNum, 12);
        final List<CityDto> list = cityDtoService.getAll();
        final PageInfo<CityDto> pageInfo = new PageInfo<>(list, 7);
        return RestMsg.success().add("pageInfo", pageInfo);
    }

    /**
     * Save the input messages.
     *
     * @param cityDto the input message of cities
     * @return RestMsg.success();
     */
    @PostMapping(value = "/city")
    public RestMsg saveCityInfos(@Valid final CityDto cityDto, final BindingResult result) {
        final Map<String, Object> map = new HashMap<>(5);
        if (result.hasErrors()) {
            final List<FieldError> fieldErrors = result.getFieldErrors();
            for (final FieldError fieldError : fieldErrors) {
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return RestMsg.failure().add("errorFields", map);
        } else {
            cityDtoService.saveCityInfo(cityDto);
            return RestMsg.success();
        }
    }

    /**
     * Check the input city name already existed or not.
     *
     * @param cityName the input name
     * @return RestMsg.success()
     */
    @GetMapping(value = "/checklist")
    public RestMsg checkCityName(@RequestParam("cityName") final String cityName) {
        final String regex = "^[a-zA-Z_-]{4,17}$";
        if (cityName.matches(regex)) {
            if (cityDtoService.checkDuplicated(cityName)) {
                return RestMsg.failure().add("validatedMsg", "City name is duplicate.");
            } else {
                return RestMsg.success();
            }
        } else {
            return RestMsg.failure().add("validatedMsg", "Name of cities should be in 4~17 Latin alphabets.");
        }
    }

    /**
     * Search the selected city's name.
     *
     * @param id the ID of city
     * @return RestMsg.success().add(data)
     */
    @GetMapping(value = "/city/{id}")
    public RestMsg getCityName(@PathVariable("id") final Long id) {
        final CityDto city = cityDtoService.getCityInfo(id);
        return RestMsg.success().add("citySelected", city);
    }

    /**
     * Update city info.
     *
     * @param cityDto the input message of cities
     * @return RestMsg.success()
     */
    @PutMapping(value = "/city/{id}")
    public RestMsg saveCityChanges(@RequestBody final CityDto cityDto) {
        cityDtoService.updateCityInfo(cityDto);
        return RestMsg.success();
    }

    /**
     * Delete the selected city info.
     *
     * @param id the ID of city
     * @return RestMsg.success()
     */
    @DeleteMapping(value = "/city/{id}")
    public RestMsg deleteCityInfo(@PathVariable("id") final Long id) {
        cityDtoService.deleteCityInfo(id);
        return RestMsg.success();
    }
}