package dk.via.group1.urbanmicrofarm_backend.dto.growingSetup;

public class GrowingSetupAssignDto
{
  private int userId;
  private String serialNumber;

  public int getUserId() { return userId; }
  public void setUserId(int userId) { this.userId = userId; }

  public String getSerialNumber() { return serialNumber; }
  public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
}