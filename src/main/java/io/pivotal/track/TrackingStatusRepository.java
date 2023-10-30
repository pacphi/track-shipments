package io.pivotal.track;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="tracking-statii", path="tracking-statii")
public interface TrackingStatusRepository extends CrudRepository<TrackingStatus, UUID> {

}
