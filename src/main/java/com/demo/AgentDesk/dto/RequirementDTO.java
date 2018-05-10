package com.demo.AgentDesk.dto;

import com.demo.AgentDesk.util.Constants;
import com.demo.AgentDesk.util.RangeFilterStates;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
/**
 * Created by ashutoshpandey on 09/05/18.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequirementDTO {

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    //Customer distance could be input
    private Double distance;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private Integer minNumberOfBedrooms;

    private Integer maxNumberOfBedrooms;

    private Integer minNumberOfBathrooms;

    private Integer maxNumberOfBathrooms;

    private Integer page;

    @Getter(onMethod = @__( @JsonIgnore ))
    private RangeFilterStates priceFilter;

    @Getter(onMethod = @__( @JsonIgnore ))
    private RangeFilterStates bathroomFilter;

    @Getter(onMethod = @__( @JsonIgnore ))
    private RangeFilterStates bedroomFilter;

/*

    Requirements can be without a min or a max for the budget, bedroom and a bathroom but either min or max would be surely present.
    For a property and requirement to be considered a valid match, distance should be within 10 miles, the budget is +/- 25%, bedroom and bathroom should be +/- 2.

*/
    public void validate(){

        if(latitude == null || longitude == null)
            throw new ValidationException("Please chose your current location");

        validateAndSetPriceFilters();
        validateAndSetBedroomFilters();
        validateAndExtractBathroomFilters();

        if(distance == null)
            distance = Constants.DEFAULT_DISTANCE;

    }

    private void validateAndExtractBathroomFilters() {
        if(minNumberOfBathrooms == null && maxNumberOfBathrooms == null)
            throw new ValidationException("Please select valid value of number of bathrooms ");

        bathroomFilter = RangeFilterStates.BOTH_PRESENT;
        if(maxNumberOfBathrooms == null){
            bathroomFilter = RangeFilterStates.MIN_PRESENT;
            maxNumberOfBathrooms = minNumberOfBathrooms + Constants.BEDROOM_OFFSET;
        } else if(minNumberOfBathrooms == null) {
            minNumberOfBathrooms = maxNumberOfBathrooms - Constants.BEDROOM_OFFSET;
            if(minNumberOfBathrooms < 0)
                minNumberOfBathrooms = 0;
        }
    }

    private void validateAndSetBedroomFilters() {
        if(minNumberOfBedrooms == null && maxNumberOfBedrooms == null)
            throw new ValidationException("Please select valid value of number of bedrooms ");

        bedroomFilter = RangeFilterStates.BOTH_PRESENT;
        if(maxNumberOfBedrooms == null){
            bedroomFilter = RangeFilterStates.MIN_PRESENT;
            maxNumberOfBedrooms = minNumberOfBedrooms + Constants.BEDROOM_OFFSET;
        } else if(minNumberOfBedrooms == null) {
            bedroomFilter = RangeFilterStates.MAX_PRESENT;
            minNumberOfBedrooms = maxNumberOfBedrooms - Constants.BEDROOM_OFFSET;
            if(minNumberOfBedrooms < 0)
                minNumberOfBedrooms = 0;
        }
    }

    private void validateAndSetPriceFilters() {
        if(minPrice == null && maxPrice == null)
            throw new ValidationException("Please select price range");
        priceFilter = RangeFilterStates.BOTH_PRESENT;
        if(maxPrice == null){
            priceFilter = RangeFilterStates.MIN_PRESENT;
            maxPrice = minPrice.add(minPrice.multiply(Constants.TWENTY_FIVE).divide(Constants.ONE_HUNDRED));
        } else if(minPrice == null) {
            priceFilter = RangeFilterStates.MAX_PRESENT;
            minPrice = maxPrice.subtract(maxPrice.multiply(Constants.TWENTY_FIVE).divide(Constants.ONE_HUNDRED));
            if(minPrice.compareTo(BigDecimal.ZERO) < 0 )
                minPrice = BigDecimal.ZERO;
        }
    }

}
