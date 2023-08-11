package kairati.atulya.spring6restmvc.models;

import kairati.atulya.spring6restmvc.models.enums.BeerStyle;

import java.math.BigDecimal;

public record BeerAttributes(
        String beerName,
        BeerStyle beerStyle,
        Integer quantityOnHand,
        BigDecimal price
) {
}
