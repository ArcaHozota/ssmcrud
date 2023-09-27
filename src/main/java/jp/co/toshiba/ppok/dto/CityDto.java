package jp.co.toshiba.ppok.dto;

import jp.co.toshiba.ppok.entity.CityView;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 都市情報DTO
 *
 * @author ArcaHozota
 * @since 6.24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public final class CityDto extends CityView {

	private static final long serialVersionUID = -4246591226136439978L;
}
