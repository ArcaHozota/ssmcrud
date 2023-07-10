package jp.co.toshiba.ppok.service;

import java.util.List;

import jp.co.toshiba.ppok.dto.CityDto;
import jp.co.toshiba.ppok.utils.Pagination;

/**
 * サービスインターフェス
 *
 * @author Administrator
 */
public interface CentreService {

	/**
	 * 都市IDによって都市情報を検索する
	 *
	 * @param id 都市ID
	 * @return CityDto
	 */
	CityDto getCityInfo(Integer id);

	/**
	 * 入力した都市情報を保存する
	 *
	 * @param cityDto 都市情報DTO
	 */
	void save(CityDto cityDto);

	/**
	 * 入力した都市情報を更新する
	 *
	 * @param cityDto 都市情報DTO
	 */
	void update(CityDto cityDto);

	/**
	 * 都市IDによって都市情報を削除する
	 *
	 * @param id 都市ID
	 */
	void removeById(Integer id);

	/**
	 * 都市名が重複することを確認する
	 *
	 * @param cityName 都市名
	 * @return true: 重複する, false: 重複しない;
	 */
	Boolean checkDuplicated(String cityName);

	/**
	 * 大陸の名称リストを取得する
	 *
	 * @return List<String>
	 */
	List<String> findAllContinents();

	/**
	 * 大陸によって全ての国の名称リストを取得する
	 *
	 * @param continent 大陸名称
	 * @return List<String>
	 */
	List<String> findNationsByCnt(String continent);

	/**
	 * 選択された都市の大陸によって全ての国の名称リストを取得する
	 *
	 * @param id 都市ID
	 * @return List<String>
	 */
	List<String> findNationsByCityId(Integer id);

	/**
	 * キーワードによって都市情報を検索する
	 *
	 * @param pageNum  ページナンバー
	 * @param pageSize ページサイズ
	 * @param keyword  キーワード
	 * @return Pagination<CityDto>
	 */
	Pagination<CityDto> findByKeywords(Integer pageNum, Integer pageSize, String keyword);

	/**
	 * 国によって公用語を取得する
	 *
	 * @param nation 国名
	 * @return String
	 */
	String findLanguageByCty(String nation);
}
