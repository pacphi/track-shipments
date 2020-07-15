package io.pivotal.track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository repo;
    
    @Test
    public void testThatAddressMayBeSavedThenFoundById() {
        Address detachedAddress = Address
            .create()
                .withCity("Bothell")
                .withState("WA")
                .withZip("98012")
                .withCountry("USA");
        
        Address address = repo.save(detachedAddress);
        Assertions.assertNotNull(address.getId());
        
        Address foundAddress = repo.findById(address.getId()).get();
        Assertions.assertEquals(detachedAddress.getCity(), foundAddress.getCity());
        Assertions.assertEquals(detachedAddress.getState(), foundAddress.getState());
        Assertions.assertEquals(detachedAddress.getZip(), foundAddress.getZip());
        Assertions.assertEquals(detachedAddress.getCountry(), foundAddress.getCountry());
    }

}
