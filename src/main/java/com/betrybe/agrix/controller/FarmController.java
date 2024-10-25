package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropCreationDto;
import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FarmCreationDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FarmService;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Farm controller.
 */
@RestController
@RequestMapping(value = "/farms")
public class FarmController {

  private final FarmService farmService;
  private final CropService cropService;

  /**
   * Instantiates a new Farm controller.
   *
   * @param farmService the farm service
   */
  @Autowired
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
   * Create farm farm dto.
   *
   * @param farmCreationDto the farm creation dto
   * @return the farm dto
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FarmDto createFarm(@RequestBody FarmCreationDto farmCreationDto) {
    return FarmDto.fromEntity(
        farmService.create(farmCreationDto.toEntity())
    );
  }

  /**
   * Gets farm by id.
   *
   * @param id the id
   * @return the farm by id
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{id}")
  public FarmDto getFarmById(@PathVariable Long id) throws FarmNotFoundException {
    return FarmDto.fromEntity(
        farmService.findById(id)
    );
  }

  /**
   * Gets all farms.
   *
   * @return the all farms
   */
  @GetMapping
  @PreAuthorize("hasAuthority('USER') or hasAuthority('MANAGER') or hasAuthority('ADMIN')")
  public List<FarmDto> getAllFarms() {
    List<Farm> allFarms = farmService.findAll();
    return allFarms.stream()
        .map(FarmDto::fromEntity)
        .toList();
  }

  @PostMapping("/{farmId}/crops")
  @ResponseStatus(HttpStatus.CREATED)
  public CropDto createFarmCrop(
      @PathVariable Long farmId,
      @RequestBody CropCreationDto cropCreationDto
  ) throws FarmNotFoundException, CropNotFoundException {
    Crop savedCrop = cropService.createCrop(farmId, cropCreationDto.toEntity());
    return CropDto.fromEntity(savedCrop);
  }

  /**
   * Gets all crops.
   *
   * @param farmId the farm id
   * @return the all crops
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{farmId}/crops")
  public List<CropDto> getAllCrops(@PathVariable Long farmId) throws FarmNotFoundException {
    farmService.findById(farmId);
    List<Crop> allCrops = cropService.findAll();
    return allCrops.stream()
        .filter(crop -> crop.getFarm() != null && crop.getFarm().getId().equals(farmId))
        .map(CropDto::fromEntity)
        .toList();
  }

}
