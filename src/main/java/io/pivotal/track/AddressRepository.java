package io.pivotal.track;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="addresses", path="addresses")
public interface AddressRepository extends CrudRepository<Address, Long> {

}
