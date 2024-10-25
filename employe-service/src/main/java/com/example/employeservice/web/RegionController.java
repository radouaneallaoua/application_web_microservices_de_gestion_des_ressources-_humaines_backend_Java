package com.example.employeservice.web;


import com.example.employeservice.Dtos.RegionRequestDto;
import com.example.employeservice.Dtos.RegionResponseDto;
import com.example.employeservice.services.RegionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions")
public class RegionController {
    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }


    @GetMapping("")
    public ResponseEntity<List<RegionResponseDto>> allRegions(){
        return regionService.getAllRegions();
    }


    @PostMapping("")
    public ResponseEntity<RegionResponseDto> addRegion(String label){
        RegionRequestDto regionRequestDto=RegionRequestDto.builder()
                .label(label)
                .build();
        return  regionService.addRegion(regionRequestDto);
    }

    @GetMapping("/{regionId}")
    public ResponseEntity<RegionResponseDto> getRegionById(@PathVariable Long regionId){
        return  regionService.getRegionById(regionId);
    }
    @DeleteMapping("/{regionId}")
    public ResponseEntity<String> deleteRegionById(@PathVariable Long regionId){
        return   regionService.deleteRegionById(regionId);
    }

    @PutMapping("/{regionId}")
    public ResponseEntity<RegionResponseDto> updateRegion(@PathVariable Long regionId,String label){
        RegionRequestDto regionRequestDto=RegionRequestDto.builder()
                .label(label)
                .build();
        return  regionService.updateRegion(regionId,regionRequestDto);
    }

}

