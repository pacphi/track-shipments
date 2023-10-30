package io.pivotal.track;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="service-levels", path="service-levels")
public interface ServiceLevelRepository extends CrudRepository<ServiceLevel, String> {

}
