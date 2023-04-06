package jp.co.toshiba.ppok.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class Language implements Serializable {

	private static final long serialVersionUID = 487191389336636732L;

	private String countryCode;

	private String language;

	private String isOfficial;

	private BigDecimal percentage;

	/**
	 * This field corresponds to the database column LOGIC_DELETE_FLG
	 */
	private String logicDeleteFlg;
}
