package kairati.atulya.spring6restmvc.dao;

import kairati.atulya.spring6restmvc.models.Beer;

import java.util.List;

public interface BeerDAO {

    Beer getBeerById(Integer id);

    List<Beer> getAllBeer();

    Beer addBeer(Beer beer);

    boolean existBeerById(Integer id);

    Beer updateBeerById(Beer beer);

    void deleteBeerById(Integer id);

    void patchBeerById(Integer id, Beer beer);
}
