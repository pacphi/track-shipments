package io.pivotal.track;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

@SpringBootApplication
@EnableLoadTimeWeaving
public class TrackShipmentsMvpApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackShipmentsMvpApplication.class, args);
	}
}
