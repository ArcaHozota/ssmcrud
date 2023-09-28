package jp.co.toshiba.ppok.dto;

/**
 * 都市情報DTO
 *
 * @author ArcaHozota
 * @since 6.24
 */
public record CityDto(Long id, String name, String continent, String nation, String district, Long population,
		String language) {
}
