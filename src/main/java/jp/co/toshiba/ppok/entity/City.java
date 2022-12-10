package jp.co.toshiba.ppok.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class City {

    /**
     * This field corresponds to the database column WORLD_CITY.ID
     */
    @TableId
    private Long id;

    /**
     * This field corresponds to the database column WORLD_CITY.NAME
     */
    private String name;

    /**
     * This field corresponds to the database column WORLD_CITY.COUNTRY_CODE
     */
    private String countryCode;

    /**
     * This field corresponds to the database column WORLD_CITY.DISTRICT
     */
    private String district;

    /**
     * This field corresponds to the database column WORLD_CITY.POPULATION
     */
    private Long population;

    /**
     * This field corresponds to the database column WORLD_CITY.IS_DELETED
     */
    @TableLogic
    private Integer isDeleted;
}