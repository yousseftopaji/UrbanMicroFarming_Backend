package dk.via.group1.urbanmicrofarm_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
    "dk.via.group1.urbanmicrofarm_backend.application.domain",
    "dk.via.group1.urbanmicrofarm_backend.application.services.sensor_reading_service",
    "dk.via.group1.urbanmicrofarm_backend.application.services.watering",
    "dk.via.group1.urbanmicrofarm_backend.database",
    "dk.via.group1.urbanmicrofarm_backend.dto",
    "dk.via.group1.urbanmicrofarm_backend.mqtt",
    "dk.via.group1.urbanmicrofarm_backend.mlClient",
    "dk.via.group1.urbanmicrofarm_backend.mapper"
    // Note: explicitly EXCLUDE "dk.via.group1.urbanmicrofarm_backend.user.model"
})
@EntityScan(basePackages = {
    "dk.via.group1.urbanmicrofarm_backend.database.entities"
})


@EnableJpaRepositories(basePackages = "dk.via.group1.urbanmicrofarm_backend.database.repository")
@ConfigurationPropertiesScan
public class UrbanMicroFarmBackendApplication
{

  public static void main(String[] args)
  {
    SpringApplication.run(UrbanMicroFarmBackendApplication.class, args);
  }

}
