package io.pivotal.track;

import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.stereotype.Component;

@Component
class RepositoryRestResourceConfig implements RepositoryRestConfigurer {

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    config.exposeIdsFor(
            ServiceLevel.class,
            ShipmentTrackingInfo.class,
            TrackingStatus.class);
  }
}