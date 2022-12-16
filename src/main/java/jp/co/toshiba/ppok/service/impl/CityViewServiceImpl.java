package jp.co.toshiba.ppok.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import jp.co.toshiba.ppok.entity.City;
import jp.co.toshiba.ppok.entity.Country;
import jp.co.toshiba.ppok.mapper.CityViewMapper;
import jp.co.toshiba.ppok.mapper.CityMapper;
import jp.co.toshiba.ppok.mapper.NationMapper;
import jp.co.toshiba.ppok.service.CityViewService;
import jp.co.toshiba.ppok.entity.CityView;
import jp.co.toshiba.ppok.utils.CustomException;

/**
 * @author Administrator
 */
@Service
public class CityViewServiceImpl extends ServiceImpl<CityViewMapper, CityView> implements CityViewService {

    @Resource
    private CityViewMapper cityViewMapper;

    @Resource
    private CityMapper cityMapper;

    @Resource
    private NationMapper nationMapper;

    /**
     * Search continents of cities located on.
     *
     * @return List<CityDto>
     */
    @Override
    public List<CityView> getContinents() {
        return cityViewMapper.selectContinents();
    }

    /**
     * Search nation's name of cities.
     *
     * @param continent name of continent which the nation located on.
     * @return List<CityDto>
     */
    @Override
    public List<CityView> getNations(final String continent) {
        return cityViewMapper.selectNations(continent);
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
    public CityView getCityInfo(final Long id) {
        final CityView cityView = new CityView();
        final City city = cityMapper.selectById(id);
        BeanUtils.copyProperties(city, cityView, "countryCode", "isDeleted");
        final String countryCode = city.getCountryCode();
        final Country nation = nationMapper.selectById(countryCode);
        cityView.setContinent(nation.getContinent());
        cityView.setNation(nation.getName());
        return cityView;
    }

    /**
     * Save city info.
     *
     * @param cityView city info
     */
    @Override
    public void saveCityInfo(final CityView cityView) {
        final City city = new City();
        this.insertCommon(cityView, city);
        city.setIsDeleted(0);
        cityMapper.insert(city);
    }

    /**
     * Update city info.
     *
     * @param cityView city info
     */
    @Override
    public void updateCityInfo(final CityView cityView) {
        final City city = new City();
        this.insertCommon(cityView, city);
        System.out.println(city);
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

    /**
     * 共通の業務挿入処理
     *
     * @param cityView 都市ビューエンティティ
     * @param city     都市エンティティ
     */
    private void insertCommon(CityView cityView, City city) {
        final Country country = new Country();
        BeanUtils.copyProperties(cityView, city, "nation", "continent");
        final String nationName = cityView.getNation();
        final LambdaQueryWrapper<Country> queryWrapper = Wrappers.lambdaQuery(new Country());
        queryWrapper.eq(Country::getName, nationName);
        final Country nation = nationMapper.selectOne(queryWrapper);
        if (nation != null) {
            if (cityView.getContinent().equals(nation.getContinent())) {
                city.setCountryCode(nation.getCode());
            } else {
                throw new CustomException("Cannot change the continent that the country located on.");
            }
        } else {
            country.setCode(nationName.substring(0, 3).toUpperCase());
            country.setName(nationName);
            country.setContinent(cityView.getContinent());
            nationMapper.insert(country);
            city.setCountryCode(country.getCode());
        }
    }
}