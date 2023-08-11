package kairati.atulya.spring6restmvc.dao;

import kairati.atulya.spring6restmvc.models.Beer;
import kairati.atulya.spring6restmvc.models.enums.BeerStyle;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class BeerDAOMockImpl implements BeerDAO {

    private final Map<Integer, Beer> brewery = new HashMap<>();

    public BeerDAOMockImpl(Map<Integer, Beer> brewery) {

        Beer b1 = Beer.builder().id(1).version(34).beerName("Pox Pox 1").beerStyle(BeerStyle.PORTER).upc("r45t").quantityOnHand(32).price(new BigDecimal("420")).createdDate(LocalDateTime.MIN).updateDate(LocalDateTime.now()).build();

        Beer b2 = Beer.builder().id(2).version(34).beerName("Box Pox 2").beerStyle(BeerStyle.ALE).upc("24ewf324").quantityOnHand(32).price(new BigDecimal("69")).createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build();

        Beer b3 = Beer.builder().id(3).version(34).beerName("Vox Pox 3").beerStyle(BeerStyle.IPA).upc("564tg").quantityOnHand(32).price(new BigDecimal("42")).createdDate(LocalDateTime.MIN).updateDate(LocalDateTime.now()).build();

        this.brewery.put(b1.getId(), b1);
        this.brewery.put(b2.getId(), b2);
        this.brewery.put(b3.getId(), b3);
    }

    @Override
    public Beer getBeerById(Integer id) {
        return brewery.get(id);
    }

    @Override
    public List<Beer> getAllBeer() {
        return brewery.values().stream().toList();
    }

    @Override
    public Beer addBeer(Beer beer) {

        brewery.put(beer.getId(), beer);

        return beer;
    }

    @Override
    public boolean existBeerById(Integer id) {
        return brewery.containsKey(id);
    }

    @Override
    public Beer updateBeerById(Beer beer) {

        brewery.put(beer.getId(), beer);

        return beer;
    }

    @Override
    public void deleteBeerById(Integer id) {
        brewery.remove(id);
    }

    @Override
    public void patchBeerById(Integer id, Beer beer) {
        brewery.put(id, beer);
    }
}
