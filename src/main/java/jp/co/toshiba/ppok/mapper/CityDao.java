package jp.co.toshiba.ppok.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import jp.co.toshiba.ppok.entity.CityDto;

@Mapper
public interface CityDao extends BaseMapper<CityDto> {

	/**
	 * Search the cities in wcv.
	 *
	 * @return List<CityDto>
	 */
	List<CityDto> selectByCityView();

	/**
	 * Search continents of cities located on.
	 *
	 * @return List<CityDto>
	 */
	List<CityDto> selectContinents();

	/**
	 * Search nation's name of cities.
	 *
	 * @param continent name of continent which the nation located on.
	 * @return List<CityDto>
	 */
	List<CityDto> selectNations(String continent);
}