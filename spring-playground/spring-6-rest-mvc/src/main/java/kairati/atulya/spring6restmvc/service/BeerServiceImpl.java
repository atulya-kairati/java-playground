package kairati.atulya.spring6restmvc.service;

import kairati.atulya.spring6restmvc.dao.BeerDAO;
import kairati.atulya.spring6restmvc.models.Beer;
import kairati.atulya.spring6restmvc.models.BeerAttributes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j // logger from lombok
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerDAO beerDAO;

    public BeerServiceImpl(BeerDAO beerDAO) {
        this.beerDAO = beerDAO;
    }

    @Override
    public Beer getBeerById(Integer id) {

        log.debug("Getting beer id[%d] in service".formatted(id));

        return beerDAO.getBeerById(id);
    }

    @Override
    public List<Beer> getAllBeer() {
        return beerDAO.getAllBeer();
    }

    @Override
    public Beer addBeer(Beer beer) {
        if (beerDAO.existBeerById(beer.getId())) {
            // TODO: throw an exception
        }

        Beer savedBeer = Beer.builder()
                .id(beer.getId())
                .version(beer.getVersion())
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .upc("Hehehaha")
                .quantityOnHand(beer.getQuantityOnHand())
                .price(beer.getPrice())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        return beerDAO.addBeer(savedBeer);
    }


    @Override
    public Beer updateBeerById(BeerAttributes beerAttributes, Integer id) {

        if (!beerDAO.existBeerById(id)) {
            // TODO: Throw exception
        }

        Beer updatedBeer = getBeerById(id);

        updatedBeer.setBeerName(beerAttributes.beerName());
        updatedBeer.setBeerStyle(beerAttributes.beerStyle());
        updatedBeer.setQuantityOnHand(beerAttributes.quantityOnHand());
        updatedBeer.setPrice(beerAttributes.price());
        updatedBeer.setUpdateDate(LocalDateTime.now());

        return updatedBeer;
    }

    @Override
    public void deleteBeerById(Integer id) {
        if (!beerDAO.existBeerById(id)) {
            // TODO: Throw exception
        }

        beerDAO.deleteBeerById(id);
    }

    @Override
    public void patchBeerById(BeerAttributes beerAttributes, Integer id) {
        if (!beerDAO.existBeerById(id)) {
            // TODO: Throw exception
        }

        Beer existingBeer = getBeerById(id);

        if (beerAttributes.beerName() != null) {
            existingBeer.setBeerName(beerAttributes.beerName());
        }
        if (beerAttributes.beerStyle() != null) {
            existingBeer.setBeerStyle(beerAttributes.beerStyle());
        }
        if (beerAttributes.quantityOnHand() != null) {
            existingBeer.setQuantityOnHand(beerAttributes.quantityOnHand());
        }
        if (beerAttributes.price() != null) {
            existingBeer.setPrice(beerAttributes.price());
        }

    }
}
