package dk.via.group1.urbanmicrofarm_backend.mlClient;

import dk.via.group1.urbanmicrofarm_backend.dto.mlDto.WaterPredictionRequestDto;
import dk.via.group1.urbanmicrofarm_backend.dto.mlDto.WaterPredictionResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service public class MLPredictionClientImpl implements MLPredictionClient
{

  private final RestClient restClient;
  private final String wateringPredictionUrl;
  private final String plantHealthPredictionUrl;

  public MLPredictionClientImpl(RestClient restClient,
      @Value("${ml.wateringPrediction.url}") String wateringPredictionUrl,
      @Value("${ml.plantHealthPrediction.url}") String plantHealthPredictionUrl)
  {
    this.restClient = restClient;
    this.wateringPredictionUrl = wateringPredictionUrl;
    this.plantHealthPredictionUrl = plantHealthPredictionUrl;
  }

  @Override public WaterPredictionResponseDto predictWater(
      WaterPredictionRequestDto request)
  {
    return restClient.post().uri(wateringPredictionUrl).body(request).retrieve()
        .body(WaterPredictionResponseDto.class);
  }
}