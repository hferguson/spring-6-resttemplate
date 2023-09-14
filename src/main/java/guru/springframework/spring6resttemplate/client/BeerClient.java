package guru.springframework.spring6resttemplate.client;

import guru.springframework.spring6resttemplate.model.BeerDTO;
import guru.springframework.spring6resttemplate.model.BeerStyle;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface BeerClient {

    BeerDTO createBeer(BeerDTO newDto);

    void deleteBeer(UUID beerId);
    BeerDTO getBeerById(UUID id);


    Page<BeerDTO> listBeers();
    Page<BeerDTO> listBeers(Integer pageNumber, Integer pageSize);
    Page<BeerDTO> listBeers(String beerName);

    Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle);

    Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory);

    Page<BeerDTO> listBeers(String beerName, BeerStyle beerStyle, Boolean showInventory, Integer pageNumber, Integer pageSize);


    BeerDTO updateBeer(BeerDTO beerDto);


}
