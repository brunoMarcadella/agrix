package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.repository.CropRepository;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Crop service.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;
  private final FarmService farmService;
  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Crop service.
   *
   * @param cropRepository the crop repository
   * @param farmService    the farm service
   */
  @Autowired
  public CropService(CropRepository cropRepository, FarmService farmService,
      FertilizerService fertilizerService) {
    this.cropRepository = cropRepository;
    this.farmService = farmService;
    this.fertilizerService = fertilizerService;
  }

  public Crop findById(Long id) throws CropNotFoundException {
    return cropRepository.findById(id)
        .orElseThrow(CropNotFoundException::new);
  }

  public List<Crop> findAll() {
    return cropRepository.findAll();
  }

  /**
   * Create crop crop.
   *
   * @param farmId the farm id
   * @param crop   the crop
   * @return the crop
   * @throws FarmNotFoundException the farm not found exception
   */
  public Crop createCrop(
      Long farmId,
      Crop crop
  ) throws FarmNotFoundException {
    Crop savedCrop = cropRepository.save(crop);
    Farm farm = farmService.findById(farmId);

    savedCrop.setFarm(farm);

    return cropRepository.save(crop);
  }

  /**
   * Gets crops by date.
   *
   * @param start the start
   * @param end   the end
   * @return the crops by date
   */
  public List<Crop> getCropsByDate(String start, String end) {
    List<Crop> crops = findAll();

    return crops.stream()
        .filter(crop -> LocalDate.parse(start).isBefore(crop.getHarvestDate())
            && LocalDate.parse(end).isAfter(crop.getHarvestDate()))
        .toList();
  }

  /**
   * Add crop fertilizer crop.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the crop
   * @throws CropNotFoundException       the crop not found exception
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  public Crop addCropFertilizer(
      Long cropId,
      Long fertilizerId
  ) throws CropNotFoundException, FertilizerNotFoundException {
    Crop crop = findById(cropId);
    Fertilizer fertilizer = fertilizerService.findById(fertilizerId);

    crop.getFertilizers().add(fertilizer);

    return cropRepository.save(crop);
  }
}
