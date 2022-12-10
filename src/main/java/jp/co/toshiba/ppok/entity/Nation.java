package jp.co.toshiba.ppok.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Nation {

    /**
     * This field corresponds to the database column WORLD_COUNTRY.CODE
     */
    @TableId
    private String code;

    /**
     * This field corresponds to the database column WORLD_COUNTRY.NAME
     */
    private String name;

    /**
     * This field corresponds to the database column WORLD_COUNTRY.CONTINENT
     */
    private String continent;

    /**
     * This field corresponds to the database column WORLD_COUNTRY.REGION
     */
    private String region;

    /**
     * This field corresponds to the database column WORLD_COUNTRY.SURFACE_AREA
     */
    private BigDecimal surfaceArea;

    /**
     * This field corresponds to the database column WORLD_COUNTRY.INDEPENDENCE_YEAR
     */
    private Integer independenceYear;

    /**
     * This field corresponds to the database column WORLD_COUNTRY.POPULATION
     */
    private Long population;

    /**
     * This field corresponds to the database column WORLD_COUNTRY.LIFE_EXPECTANCY
     */
    private BigDecimal lifeExpectancy;

    /**
     * This field corresponds to the database column WORLD_COUNTRY.GNP
     */
    private BigDecimal gnp;

    /**
     * This field corresponds to the database column WORLD_COUNTRY.GNP_OLD
     */
    private BigDecimal gnpOld;

    /**
     * This field corresponds to the database column WORLD_COUNTRY.LOCAL_NAME
     */
    private String localName;

    /**
     * This field corresponds to the database column WORLD_COUNTRY.GOVERNMENT_FORM
     */
    private String governmentForm;

    /**
     * This field corresponds to the database column WORLD_COUNTRY.HEAD_OF_STATE
     */
    private String headOfState;

    /**
     * This field corresponds to the database column WORLD_COUNTRY.CAPITAL
     */
    private Long capital;

    /**
     * This field corresponds to the database column WORLD_COUNTRY.CODE2
     */
    private String code2;

    /**
     * This field corresponds to the database column WORLD_COUNTRY.IS_DELETED
     */
    @TableLogic
    private Integer isDeleted;
}