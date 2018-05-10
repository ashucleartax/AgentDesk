package com.demo.AgentDesk.dto;

import lombok.*;

/**
 * Created by ashutoshpandey on 09/05/18.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PropertyDistanceWrapper implements Comparable<PropertyDistanceWrapper> {

    int matchPercent;

    //TODO distance should be here
    PropertyDistance property;

    @Override
    public int compareTo(PropertyDistanceWrapper o) {
        if(this.matchPercent < o.getMatchPercent())
            return 1;
        if(this.matchPercent == o.getMatchPercent())
            return 0;
        return -1;
    }
}
