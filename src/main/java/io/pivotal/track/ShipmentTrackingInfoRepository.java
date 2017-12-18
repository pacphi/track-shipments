package io.pivotal.track;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="shipments", path="shipments")
public interface ShipmentTrackingInfoRepository extends JpaRepository<ShipmentTrackingInfo, String> {

}
