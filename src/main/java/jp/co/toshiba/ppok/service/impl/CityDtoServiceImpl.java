package jp.co.toshiba.ppok.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import jp.co.toshiba.ppok.entity.City;
import jp.co.toshiba.ppok.entity.Nation;
import jp.co.toshiba.ppok.mapper.CityDao;
import jp.co.toshiba.ppok.mapper.CityMapper;
import jp.co.toshiba.ppok.mapper.NationMapper;
import jp.co.toshiba.ppok.service.CityDtoService;
import jp.co.toshiba.ppok.entity.CityDto;
import jp.co.toshiba.ppok.utils.CustomException;

/**
 * @author Administrator
 */
@Service
public class CityDtoServiceImpl extends ServiceImpl<CityDao, CityDto> implements CityDtoService {

    @Resource
    private CityDao cityDao;

    @Resource
    private CityMapper cityMapper;

    @Resource
    private NationMapper nationMapper;

    /**
     * Search the cities in wcv.
     *
     * @return List<City>
     */
    @Override
    public List<CityDto> getAll() {
        return cityDao.selectByCityView();
    }

    /**
     * Search continents of cities located on.
     *
     * @return List<CityDto>
     */
    @Override
    public List<CityDto> getContinents() {
        return cityDao.selectContinents();
    }

    /**
     * Search nation's name of cities.
     *
     * @param continent name of continent which the nation located on.
     * @return List<CityDto>
     */
    @Override
    public List<CityDto> getNations(final String continent) {
        return cityDao.selectNations(continent);
    }

    /**
     * Check the duplicated name of cities.
     *
     * @param name city name
     * @return true: duplicated, false: can be inserted
     */
    @Override
    public boolean checkDuplicated(final String name) {
        final LambdaQueryWrapper<City> queryWrapper = Wrappers.lambdaQuery(new City());
        queryWrapper.eq(name != null, City::getName, name);
        final Long count = cityMapper.selectCount(queryWrapper);
        return count >= 1;
    }

    /**
     * Search city infos by id.
     *
     * @param id city id
     * @return CityDto
     */
    @Override
    public CityDto getCityInfo(final Long id) {
        final CityDto cityDto = new CityDto();
        final City city = cityMapper.selectById(id);
        BeanUtils.copyProperties(city, cityDto, "countryCode", "isDeleted");
        final String countryCode = city.getCountryCode();
        final Nation nation = nationMapper.selectById(countryCode);
        cityDto.setContinent(nation.getContinent());
        cityDto.setNation(nation.getName());
        return cityDto;
    }

    /**
     * Save city info.
     *
     * @param cityDto city info
     */
    @Override
    public void saveCityInfo(final CityDto cityDto) {
        final City city = new City();
        final Nation country = new Nation();
        BeanUtils.copyProperties(cityDto, city, "nation", "continent");
        final String nationName = cityDto.getNation();
        final LambdaQueryWrapper<Nation> queryWrapper = Wrappers.lambdaQuery(new Nation());
        queryWrapper.eq(Nation::getName, nationName);
        final Nation nation = nationMapper.selectOne(queryWrapper);
        if (nation != null) {
            if (cityDto.getContinent().equals(nation.getContinent())) {
                city.setCountryCode(nation.getCode());
            } else {
                throw new CustomException("Cannot change the continent that the country located on.");
            }
        } else {
            country.setCode(nationName.substring(0, 3).toUpperCase());
            country.setName(nationName);
            country.setContinent(cityDto.getContinent());
            nationMapper.insert(country);
            city.setCountryCode(country.getCode());
        }
        city.setIsDeleted(0);
        cityMapper.insert(city);
    }

    /**
     * Update city info.
     *
     * @param cityDto city info
     */
    @Override
    public void updateCityInfo(final CityDto cityDto) {
        final City city = new City();
        final Nation country = new Nation();
        BeanUtils.copyProperties(cityDto, city, "nation", "continent");
        final String nationName = cityDto.getNation();
        final LambdaQueryWrapper<Nation> queryWrapper = Wrappers.lambdaQuery(new Nation());
        queryWrapper.eq(Nation::getName, nationName);
        final Nation nation = nationMapper.selectOne(queryWrapper);
        if (nation != null) {
            if (cityDto.getContinent().equals(nation.getContinent())) {
                city.setCountryCode(nation.getCode());
            } else {
                throw new CustomException("Cannot change the continent that the country located on.");
            }
        } else {
            country.setCode(nationName.substring(0, 3).toUpperCase());
            country.setName(nationName);
            country.setContinent(cityDto.getContinent());
            nationMapper.insert(country);
            city.setCountryCode(country.getCode());
        }
        cityMapper.updateById(city);
    }

    /**
     * Delete city info by id.
     *
     * @param id city id
     */
    @Override
    public void deleteCityInfo(final Long id) {
        cityMapper.deleteById(id);
    }
}
