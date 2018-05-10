package com.demo.AgentDesk.util;

import java.math.BigDecimal;

/**
 * Created by ashutoshpandey on 09/05/18.
 */
public interface Constants {

    //in kilometers
    //If you prefer to work in miles, set the earth’s radius $R to 3959.
    double EARTH_RADIUS = 6371;

    //in kilometers
    double DEFAULT_DISTANCE = 10;

    BigDecimal ONE_HUNDRED = new BigDecimal(100);

    BigDecimal TWENTY_FIVE = new BigDecimal(25);

    Integer BEDROOM_OFFSET = 2;

}
