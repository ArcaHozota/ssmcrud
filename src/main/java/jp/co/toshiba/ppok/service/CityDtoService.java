package jp.co.toshiba.ppok.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jp.co.toshiba.ppok.utils.CityDto;

import java.util.List;

public interface CityDtoService extends IService<CityDto> {

    /**
     * Search the cities in wcv.
     *
     * @return List<CityDto>
     */
    List<CityDto> getAll();

    /**
     * Search city infos by id.
     *
     * @param id city id
     * @return List<CityDto>
     */
    CityDto getCityInfo(Long id);

    /**
     * Save city info.
     *
     * @param cityDto city info
     */
    void saveCityInfo(CityDto cityDto);

    /**
     * Update city info.
     *
     * @param cityDto city info
     */
    void updateCityInfo(CityDto cityDto);

    /**
     * Delete city info by id.
     * @param id city id
     */
    void deleteCityInfo(Long id);

    /**
     * Check the duplicated name of cities.
     *
     * @param name city name
     * @return true: duplicated, false: insertable
     */
    boolean checkDuplicated(String name);
}
