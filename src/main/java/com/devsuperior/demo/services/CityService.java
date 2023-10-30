package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    @Transactional
    public CityDTO insert(CityDTO city) {
        City saved = repository.save(new City(null, city.getName()));
        return new CityDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<CityDTO> findAll() {
        List<CityDTO> result = repository.findAll()
                .stream()
                .map(CityDTO::new)
                .sorted((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()))
                .collect(Collectors.toList());
        if (result.isEmpty()) {
            throw new NotFoundException("Cidades não encontradas");
        }

        return result;
    }

}
