package jp.co.toshiba.ppok.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jp.co.toshiba.ppok.utils.CityDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CityDao extends BaseMapper<CityDto> {

    /**
     * Search the cities in wcv.
     *
     * @return List<City>
     */
    List<CityDto> selectByCityView();
}