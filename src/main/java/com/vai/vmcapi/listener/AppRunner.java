package com.vai.vmcapi.listener;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vai.vmcapi.repo.entity.CityEntity;
import com.vai.vmcapi.repo.entity.DistrictEntity;
import com.vai.vmcapi.repo.entity.WardEntity;
import com.vai.vmcapi.repo.jpa.CityRepository;
import com.vai.vmcapi.repo.jpa.DistrictRepository;
import com.vai.vmcapi.repo.jpa.WardRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private WardRepository wardRepository;

    @Override
    public void run(String... args) throws Exception {
        initAddress();
    }

    @PostConstruct
    public void initAddress() throws IOException{
        Iterable<CityEntity> cityRepositoryAll = cityRepository.findAll();
        if (cityRepositoryAll.iterator().hasNext()) {
            return;
        }
        ObjectMapper objectMapper = new ObjectMapper();

        // Load and parse cities.json
        InputStream citiesStream = new ClassPathResource("data/cities.json").getInputStream();
        List<CityEntity> cities = objectMapper.readValue(citiesStream, new TypeReference<List<CityEntity>>() {});

        // Load and parse districts.json
        InputStream districtsStream = new ClassPathResource("data/districts.json").getInputStream();
        List<DistrictEntity> districts = objectMapper.readValue(districtsStream, new TypeReference<List<DistrictEntity>>() {});

        // Set relationships for districts

        // Load and parse wards.json
        InputStream wardsStream = new ClassPathResource("data/wards.json").getInputStream();
        List<WardEntity> wards = objectMapper.readValue(wardsStream, new TypeReference<List<WardEntity>>() {});

        // Save entities to the database
        cityRepository.saveAll(cities);
        districtRepository.saveAll(districts);
        wardRepository.saveAll(wards);
    }
}
