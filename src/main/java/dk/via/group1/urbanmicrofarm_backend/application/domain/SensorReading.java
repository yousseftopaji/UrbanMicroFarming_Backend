package dk.via.group1.urbanmicrofarm_backend.application.domain;

import java.time.Instant;
import java.time.LocalDateTime;

public class SensorReading
{
  private long id;
  private final double value;
  private final Instant timestamp;

  public SensorReading(long id, double value, Instant timestamp)
  {
    this.id = id;
    this.value = value;
    this.timestamp = timestamp;
  }

  public SensorReading(double value, Instant timestamp)
  {
    this.value = value;
    this.timestamp = timestamp;
  }

  public long getId()
  {
    return id;
  }

  public double getValue()
  {
    return value;
  }

  public Instant getTimestamp()
  {
    return timestamp;
  }
}


