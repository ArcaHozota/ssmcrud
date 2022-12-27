package jp.co.toshiba.ppok.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import jp.co.toshiba.ppok.mapper.CityMapper;
import jp.co.toshiba.ppok.mapper.CityViewMapper;
import jp.co.toshiba.ppok.mapper.CountryMapper;
import jp.co.toshiba.ppok.service.CityViewService;

/**
 * 業務処理ロジック
 *
 * @author Administrator
 * @date 2022-12-16
 */
@Service
public class CityViewServiceImpl extends ServiceImpl<CityViewMapper, CityView> implements CityViewService {

    @Resource
    private CityViewMapper cityViewMapper;

    @Resource
    private CityMapper cityMapper;

    @Resource
    private CountryMapper countryMapper;

    /**
     * Search continents of cities located on.
     *
     * @return List<CityDto>
     */
    @Override
    public List<CityView> getContinents() {
        return this.cityViewMapper.selectContinents();
    }

    /**
     * Search nation's name of cities.
     *
     * @param continent name of continent which the nation located on.
     * @return List<CityDto>
     */
    @Override
    public List<CityView> getNations(final String continent) {
        return this.cityViewMapper.selectNations(continent);
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
        final Long count = this.cityMapper.selectCount(queryWrapper);
        return count >= 1;
    }

    /**
     * Search city info by id.
     *
     * @param id city id
     * @return CityDto
     */
    @Override
    public CityView getCityInfo(final Long id) {
        final CityView cityView = new CityView();
        final City city = this.cityMapper.selectById(id);
        BeanUtils.copyProperties(city, cityView, "countryCode", "isDeleted");
        final String countryCode = city.getCountryCode();
        final Country nation = this.countryMapper.selectById(countryCode);
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
        this.cityMapper.insert(city);
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
        this.cityMapper.updateById(city);
    }

    /**
     * Delete city info by id.
     *
     * @param id city id
     */
    @Override
    public void deleteCityInfo(final Long id) {
        this.cityMapper.deleteById(id);
    }

    /**
     * 共通の業務挿入処理
     *
     * @param cityView 都市ビューエンティティ
     * @param city     都市エンティティ
     */
    private void insertCommon(final CityView cityView, final City city) {
        BeanUtils.copyProperties(cityView, city, "nation", "continent");
        final String nationName = cityView.getNation();
        final LambdaQueryWrapper<Country> queryWrapper = Wrappers.lambdaQuery(new Country());
        queryWrapper.eq(Country::getName, nationName);
        final Country nation = this.countryMapper.selectOne(queryWrapper);
        city.setCountryCode(nation.getCode());
    }
}