package guru.springframework.spring6resttemplate.client;

import guru.springframework.spring6resttemplate.model.BeerDTO;
import guru.springframework.spring6resttemplate.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BeerClientImplTest {
    @Autowired
    BeerClientImpl beerClient;

    BeerDTO buildTestBeer(String newName) {
        if (newName == null) {
            newName = "Mango Bob's";
        }
        BeerDTO newDto = BeerDTO.builder()
                .price(new BigDecimal("10.99"))
                .beerName(newName)
                .beerStyle(BeerStyle.IPA)
                .quantityOnHand(500)
                .upc("12345")
                .build();
        return newDto;
    }
    @Test
    void testCreateBeer() {
        BeerDTO newDto = buildTestBeer(null);
        BeerDTO savedDto = beerClient.createBeer(newDto);
        System.out.println(savedDto.getId());
        assertNotNull(savedDto);

    }

    @Test
    void testDeleteBeer() {
        BeerDTO newDto = buildTestBeer("Mango Bob's 2");
        BeerDTO beerDto = beerClient.createBeer(newDto);

        beerClient.deleteBeer(beerDto.getId());

        assertThrows(HttpClientErrorException.class, () -> {
            beerClient.getBeerById(beerDto.getId());
        });
    }
    @Test
    void testGetBeerById() {
        Page<BeerDTO> beerDTOs = beerClient.listBeers();
        BeerDTO dto = beerDTOs.getContent().get(0);
        BeerDTO byId = beerClient.getBeerById(dto.getId());

        assertNotNull(byId);
    }
    @Test
    void testListBeers() {

        beerClient.listBeers(null);

    }

    @Test
    void testListBeersFilterName() {

        beerClient.listBeers("ALE");

    }

    @Test
    void testListBeersFilterStyle() {
        beerClient.listBeers(null, BeerStyle.LAGER, null, null, null);
    }

    @Test
    void testListBeersPage2() {
        beerClient.listBeers(2, 25);
    }

    @Test
    void testUpdateBeer() {
        BeerDTO newDto =  buildTestBeer(null);
        BeerDTO beerDto = beerClient.createBeer(newDto);
        final String newName = "Mango Bob's 3";
        beerDto.setBeerName(newName);
        BeerDTO updated = beerClient.updateBeer(beerDto);
        assertEquals(newName, updated.getBeerName());
    }
}