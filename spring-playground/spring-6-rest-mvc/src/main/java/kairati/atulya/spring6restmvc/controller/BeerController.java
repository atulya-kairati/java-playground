package kairati.atulya.spring6restmvc.controller;


import kairati.atulya.spring6restmvc.models.Beer;
import kairati.atulya.spring6restmvc.models.BeerAttributes;
import kairati.atulya.spring6restmvc.service.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@Slf4j // logger from lombok
@AllArgsConstructor // generate all args constructor for the properties
@RestController()
@RequestMapping("/api/v1/beer")
public class BeerController {
    private final BeerService beerService;

    @GetMapping
    public List<Beer> getAllBeer() {

        log.debug("Controller: getAllBeer()");

        return beerService.getAllBeer();
    }

    @PostMapping
    public ResponseEntity<String> addBeer(
            @RequestBody Beer beer
    ){
        Beer savedBeer = beerService.addBeer(beer);
        return ResponseEntity.created(URI.create("/api/v1/beer/" + savedBeer.getId())).body("Beer Added");
    }

    @GetMapping("/{beerId}")
    public Beer getBeerById(
            @PathVariable("beerId") Integer id
    ) {

        log.debug("Controller:  getBeerById([%d])".formatted(id));

        return beerService.getBeerById(id);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<String> updateBeerById(
            @RequestBody BeerAttributes beerAttributes,
            @PathVariable("beerId") Integer id
     ) {
        beerService.updateBeerById(beerAttributes, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{beerId}")
    public ResponseEntity<String> deleteBeerById(
            @PathVariable("beerId") Integer id
    ) {

        beerService.deleteBeerById(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{beerId}")
    public ResponseEntity<String> patchBeerById(
            @RequestBody BeerAttributes beerAttributes,
            @PathVariable("beerId") Integer id
    ) {
        beerService.patchBeerById(beerAttributes, id);
        return ResponseEntity.noContent().build();
    }
}
