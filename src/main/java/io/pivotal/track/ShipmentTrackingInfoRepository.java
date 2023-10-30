package io.pivotal.track;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="shipments", path="shipments")
public interface ShipmentTrackingInfoRepository extends CrudRepository<ShipmentTrackingInfo, String> {

}
