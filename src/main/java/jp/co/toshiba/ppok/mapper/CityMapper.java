package jp.co.toshiba.ppok.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import jp.co.toshiba.ppok.entity.City;
import oracle.jdbc.driver.OracleSQLException;

/**
 * テーブルWORLD_CITYのマッパー
 *
 * @author Administrator
 */
@Mapper
public interface CityMapper {

	/**
	 * 保存
	 *
	 * @param city 都市エンティティ
	 */
	@Transactional(rollbackFor = OracleSQLException.class)
	void saveById(City city);

	/**
	 * 論理削除
	 *
	 * @param id 都市ID
	 */
	@Transactional(rollbackFor = OracleSQLException.class)
	void removeById(@Param("id") Integer id);

	/**
	 * 更新
	 *
	 * @param city 都市エンティティ
	 */
	@Transactional(rollbackFor = OracleSQLException.class)
	void updateById(City city);

	/**
	 * 重複性をチェックする
	 *
	 * @param cityName 都市名
	 * @return 0: 重複しない, 1: 重複する
	 */
	Integer checkDuplicatedName(@Param("cityName") String cityName);
}