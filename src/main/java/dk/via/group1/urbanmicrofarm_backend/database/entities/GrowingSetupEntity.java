package dk.via.group1.urbanmicrofarm_backend.database.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity @Table(name = "growing_setup", schema = "urban_micro_farm_app") public class GrowingSetupEntity
{

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int setup_id;

  @Column(nullable = false) private String serialNumber;

  private String location;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @OneToMany(mappedBy = "growingSetup") private List<ActuatorEntity> actuators;

  @OneToMany(mappedBy = "growingSetup") private List<SensorEntity> sensors;

  public int getSetupId()
  {
    return setup_id;
  }

  public void setSetupId(int setupId)
  {
    this.setup_id = setupId;
  }

  public String getSerialNumber()
  {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber)
  {
    this.serialNumber = serialNumber;
  }

  public String getLocation()
  {
    return location;
  }

  public void setLocation(String location)
  {
    this.location = location;
  }

  public Long getUserId()
  {
    return user != null ? user.getId() : null;
  }

  public void setUserId(Long userId)
  {
    if (userId == null)
    {
      this.user = null;
    }
    else
    {
      if (this.user == null)
      {
        this.user = new UserEntity();
      }
      this.user.setId(userId);
    }
  }

  public UserEntity getUser()
  {
    return user;
  }

  public void setUser(UserEntity user)
  {
    this.user = user;
  }

  public List<ActuatorEntity> getActuators()
  {
    return actuators;
  }

  public void setActuators(List<ActuatorEntity> actuators)
  {
    this.actuators = actuators;
  }

  public List<SensorEntity> getSensors()
  {
    return sensors;
  }

  public void setSensors(List<SensorEntity> sensors)
  {
    this.sensors = sensors;
  }
}
