package io.pivotal.track;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="addresses", path="addresses")
public interface AddressRepository extends JpaRepository<Address, Long> {

}
