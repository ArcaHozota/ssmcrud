package jp.co.toshiba.ppok.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jp.co.toshiba.ppok.utils.Pagination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;
import jp.co.toshiba.ppok.service.CityViewService;
import jp.co.toshiba.ppok.entity.CityView;
import jp.co.toshiba.ppok.utils.RestMsg;

import javax.annotation.Resource;

/**
 * Center Terminal Controller Handle the retrieve and update requests.
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/public/grssmcrud")
public class CentreController {

    @Resource
    private CityViewService cityViewService;

    /**
     * Retrieve the city data.
     *
     * @return page(JSON)
     */
    @GetMapping(value = "/city")
    public RestMsg getCities(@RequestParam(value = "pageNum", defaultValue = "1") final Integer pageNum,
                             @RequestParam(value = "name", defaultValue = "") final String name) {
        // 聲明分頁構造器；
        final Pagination<CityView> pageInfo = new Pagination<>(pageNum, 15);
        // 聲明條件構造器；
        final LambdaQueryWrapper<CityView> queryWrapper = Wrappers.lambdaQuery(new CityView());
        // 添加過濾條件；
        queryWrapper.like(StringUtils.isNotEmpty(name), CityView::getName, name);
        // 添加排序條件；
        queryWrapper.orderByAsc(CityView::getId);
        // 執行查詢；
        this.cityViewService.page(pageInfo, queryWrapper);
        // 設置總頁數；
        final int totalPage = (int) pageInfo.getPages();
        pageInfo.setTotalPages(totalPage);
        // 設置分頁導航條頁碼數量；
        pageInfo.calcByNaviPages(7);
        return RestMsg.success().add("pageInfo", pageInfo);
    }

    /**
     * Search the selected city's name.
     *
     * @param id the ID of city
     * @return RestMsg.success().add(data)
     */
    @GetMapping(value = "/city/{id}")
    public RestMsg getCityInfo(@PathVariable("id") final Long id) {
        final CityView city = cityViewService.getCityInfo(id);
        return RestMsg.success().add("citySelected", city);
    }

    /**
     * Save the input messages.
     *
     * @param cityView the input message of cities
     * @return RestMsg.success()
     */
    @PostMapping(value = "/city")
    public RestMsg saveCityInfos(@Valid final CityView cityView, final BindingResult result) {
        final Map<String, Object> map = new HashMap<>(5);
        if (result.hasErrors()) {
            final List<FieldError> fieldErrors = result.getFieldErrors();
            for (final FieldError fieldError : fieldErrors) {
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return RestMsg.failure().add("errorFields", map);
        } else {
            cityViewService.saveCityInfo(cityView);
            return RestMsg.success();
        }
    }

    /**
     * Update city info.
     *
     * @param cityView the input message of cities
     * @return RestMsg.success()
     */
    @PutMapping(value = "/city/{id}")
    public RestMsg updateCityInfo(@RequestBody final CityView cityView) {
        cityViewService.updateCityInfo(cityView);
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
        cityViewService.deleteCityInfo(id);
        return RestMsg.success();
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
            if (cityViewService.checkDuplicated(cityName)) {
                return RestMsg.failure().add("validatedMsg", "City name is duplicate.");
            } else {
                return RestMsg.success();
            }
        } else {
            return RestMsg.failure().add("validatedMsg", "Name of cities should be in 4~17 Latin alphabets.");
        }
    }

    /**
     * Get list of continents.
     *
     * @return RestMsg.success().add(data)
     */
    @GetMapping(value = "/continents")
    public RestMsg getListOfContinents() {
        final List<CityView> cnlist = cityViewService.getContinents();
        return RestMsg.success().add("continents", cnlist);
    }

    /**
     * Get list of nations.
     *
     * @return RestMsg.success().add(data)
     */
    @GetMapping(value = "/nations")
    public RestMsg getListOfNations(@RequestParam("continentVal") final String continent) {
        final List<CityView> list = cityViewService.getNations(continent);
        return RestMsg.success().add("nations", list);
    }

    /**
     * Get list of nations.
     *
     * @return RestMsg.success().add(data)
     */
    @GetMapping(value = "/nations")
    public RestMsg getListOfNationsById(@RequestParam("byId") final Long id) {
        final List<String> list = new ArrayList<>();
        final CityView cityInfo = this.cityViewService.getCityInfo(id);
        final String nationName = cityInfo.getNation();
        list.add(nationName);
        final String continent = cityInfo.getContinent();
        final List<CityView> nationsList = this.cityViewService.getNations(continent);
        nationsList.forEach(item ->{
            if (!nationName.equals(item.getNation())) {
                list.add(item.getNation());
            }
        });
        return RestMsg.success().add("nationsWithName", list);
    }
}