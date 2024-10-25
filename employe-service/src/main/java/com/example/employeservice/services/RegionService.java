package com.example.employeservice.services;

import com.example.employeservice.Dtos.RegionRequestDto;
import com.example.employeservice.Dtos.RegionResponseDto;
import com.example.employeservice.Exception.RegionNotFoundException;
import com.example.employeservice.entities.Region;
import com.example.employeservice.repository.RegionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RegionService {
    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }


    public ResponseEntity<List<RegionResponseDto>> getAllRegions(){
        List<Region> regions=regionRepository.findAll();
        List<RegionResponseDto> list=new ArrayList<>();
        regions.forEach(c->{
            list.add(c.toDto());
        });
        return ResponseEntity.ok(list);
    }


    public ResponseEntity<RegionResponseDto> getRegionById(Long regionId){
        Region region=regionRepository.findById(regionId).orElseThrow(()-> new RegionNotFoundException("region not found"));
        return ResponseEntity.ok(region.toDto());
    }
    public ResponseEntity<RegionResponseDto> addRegion(RegionRequestDto regionRequestDto){
       Region region=Region.builder()
               .label(regionRequestDto.getLabel())
               .build();
       Region savedRegion=regionRepository.save(region);
        return ResponseEntity.ok(savedRegion.toDto());
    }

    public ResponseEntity<RegionResponseDto> updateRegion(Long regionId, RegionRequestDto regionRequestDto){
        Region region=regionRepository.findById(regionId).orElseThrow(()-> new RegionNotFoundException("region not found"));
        region.setLabel(regionRequestDto.getLabel());
        Region savedRegion=regionRepository.save(region);
        return ResponseEntity.ok(savedRegion.toDto());
    }

    public ResponseEntity<String> deleteRegionById(Long regionId) {
        regionRepository.deleteById(regionId);
        return ResponseEntity.ok("region supprimee avec succes");
    }
}
