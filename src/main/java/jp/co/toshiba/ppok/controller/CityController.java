package jp.co.toshiba.ppok.controller;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import jp.co.toshiba.ppok.service.CityDtoService;
import jp.co.toshiba.ppok.utils.CityDto;
import jp.co.toshiba.ppok.utils.RestMsg;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Center Terminal Controller
 * Handle the retrieve and update requests.
 *
 * @author Administrator
 */
@RestController
public class CityController {

    @Resource
    private CityDtoService cityDtoService;


    /**
     * Retrieve the city data.
     *
     * @return page(JSON)
     */
    @GetMapping(value = "/city")
    public RestMsg getCities(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageMethod.startPage(pageNum,12);
        List<CityDto> list = cityDtoService.getAll();
        PageInfo<CityDto> pageInfo = new PageInfo<>(list,7);
        return RestMsg.success().add("pageInfo", pageInfo);
    }

//    /**
//     * Save the inputed messages.
//     *
//     * @param city the input message of cities;
//     * @return Message.success();
//     */
//    @PostMapping(value = "/city")
//    public Message saveCityInfos(@Valid City city, BindingResult result) {
//        Map<String, Object> map = new HashMap<>();
//        if (result.hasErrors()) {
//            List<FieldError> fieldErrors = result.getFieldErrors();
//            for (FieldError fieldError : fieldErrors) {
//                map.put(fieldError.getField(), fieldError.getDefaultMessage());
//            }
//            return Message.failure().add("errorFields", map);
//        } else {
//            cityService.insert(city);
//            return Message.success();
//        }
//    }
//
//    /**
//     * Check the input city name already existed or not.
//     */
//    @GetMapping(value = "/checklist")
//    public Message checkCityName(@RequestParam("cityName") String cityName) {
//        final String regex = "^[a-zA-Z_-]{4,17}$";
//        if (cityName.matches(regex)) {
//            if (cityService.selectCount(null) == 0) {
//                // wrong
//                return Message.success();
//            } else {
//                return Message.failure().add("validatedMsg", "City name is duplicate.");
//            }
//        } else {
//            return Message.failure().add("validatedMsg", "Name of cities should be in 4~17 Latin alphabets.");
//        }
//    }
//
//    /**
//     * Search the selected city's name.
//     */
//    @GetMapping(value = "/city/{id}")
//    public Message getCityName(@PathVariable("id") Integer id) {
//        City cities = cityService.selectById(id);
//        return Message.success().add("citiesSelected", cities);
//    }
//
//    /**
//     * Save the input changed city info.
//     */
//    @PutMapping(value = "/city/{id}")
//    public Message saveCityChanges(@RequestBody City city) {
//        cityService.updateById(city);
//        return Message.success();
//    }
//
//    /**
//     * Delete the selected city info.
//     */
//    @DeleteMapping(value = "/city/{id}")
//    public Message deleteCityInfo(@PathVariable("id") Integer id) {
//        cityService.deleteById(id);
//        return Message.success();
//    }
}