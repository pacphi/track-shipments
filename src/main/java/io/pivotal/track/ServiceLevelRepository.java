package io.pivotal.track;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="service-levels", path="service-levels")
public interface ServiceLevelRepository extends JpaRepository<ServiceLevel, String> {

}
