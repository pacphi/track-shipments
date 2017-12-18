package io.pivotal.track;


import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
class RepositoryRestResourceConfig extends RepositoryRestConfigurerAdapter {

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    config.exposeIdsFor(
            ServiceLevel.class,
            ShipmentTrackingInfo.class,
            //TrackingHistory.class,
            TrackingStatus.class);
  }
}