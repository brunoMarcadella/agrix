package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FertilizerService;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping(value = "/crops")
public class CropController {

  private final CropService cropService;
  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Crop controller.
   *
   * @param cropService the crop service
   */
  @Autowired
  public CropController(CropService cropService, FertilizerService fertilizerService) {
    this.cropService = cropService;
    this.fertilizerService = fertilizerService;
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{id}")
  public CropDto getCropById(@PathVariable Long id) throws CropNotFoundException {
    return CropDto.fromEntity(
        cropService.findById(id)
    );
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  @GetMapping
  @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
  public List<CropDto> getAllCrops() {
    List<Crop> allCrops = cropService.findAll();
    return allCrops.stream()
        .map(CropDto::fromEntity)
        .toList();
  }

  /**
   * Gets crops by date.
   *
   * @param start the start
   * @param end   the end
   * @return the crops by date
   */
  @GetMapping("/search")
  public List<CropDto> getCropsByDate(@RequestParam String start, @RequestParam String end) {
    List<Crop> crops = cropService.getCropsByDate(start, end);

    return crops.stream().map(CropDto::fromEntity)
        .toList();
  }

  /**
   * Add crop fertilizer crop dto.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the crop dto
   * @throws CropNotFoundException       the crop not found exception
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> addCropFertilizer(
      @PathVariable Long cropId,
      @PathVariable Long fertilizerId
  ) throws CropNotFoundException, FertilizerNotFoundException {
    CropDto cropDto = CropDto.fromEntity(
        cropService.addCropFertilizer(cropId, fertilizerId)
    );

    return ResponseEntity.status(HttpStatus.CREATED).body(
        "Fertilizante e plantação associados com sucesso!"
    );
  }

  /**
   * Gets fertilizers by crop id.
   *
   * @param cropId the crop id
   * @return the fertilizers by crop id
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{cropId}/fertilizers")
  public List<FertilizerDto> getFertilizersByCropId(
      @PathVariable Long cropId
  ) throws CropNotFoundException {
    return cropService.findById(cropId).getFertilizers().stream()
        .map(FertilizerDto::fromEntity)
        .toList();
  }

}
