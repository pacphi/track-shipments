package io.pivotal.track;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
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
        Assert.assertNotNull("Address should have an identifier", address.getId());
        
        Address foundAddress = repo.findById(address.getId()).get();
        Assert.assertEquals(detachedAddress.getCity(), foundAddress.getCity());
        Assert.assertEquals(detachedAddress.getState(), foundAddress.getState());
        Assert.assertEquals(detachedAddress.getZip(), foundAddress.getZip());
        Assert.assertEquals(detachedAddress.getCountry(), foundAddress.getCountry());
    }

}
