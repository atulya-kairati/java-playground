package kairati.atulya.spring6restmvc.service;

import kairati.atulya.spring6restmvc.models.Beer;
import kairati.atulya.spring6restmvc.models.BeerAttributes;

import java.util.List;
import java.util.UUID;

public interface BeerService {

    Beer getBeerById(Integer id);

    List<Beer> getAllBeer();

    Beer addBeer(Beer beer);

    Beer updateBeerById(BeerAttributes beerAttributes, Integer id);

    void deleteBeerById(Integer id);

    void patchBeerById(BeerAttributes beerAttributes, Integer id);
}
