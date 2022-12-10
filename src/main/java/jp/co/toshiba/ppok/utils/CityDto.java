package jp.co.toshiba.ppok.utils;

import lombok.Data;

/**
 * the dto of view wvc
 *
 * @author Administrator
 */

@Data
public class CityDto {

    private Long id;

    private String name;

    private String continent;

    private String nation;

    private String district;

    private Long population;
}
