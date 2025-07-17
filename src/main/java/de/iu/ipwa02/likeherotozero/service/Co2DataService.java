package de.iu.ipwa02.likeherotozero.service;

import de.iu.ipwa02.likeherotozero.model.Co2Data;
import de.iu.ipwa02.likeherotozero.repository.Co2DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Co2DataService {

    private final Co2DataRepository co2DataRepository;

    @Autowired
    public Co2DataService(Co2DataRepository co2DataRepository) {
        this.co2DataRepository = co2DataRepository;
    }

    public Optional<Co2Data> getLatestEmissionByCountry(String country) {
        return co2DataRepository.findFirstByCountryIgnoreCaseOrderByMsyearDesc(country);
    }

    public Co2Data saveData(Co2Data data) {
        return co2DataRepository.save(data);
    }

    // Hilfsmethode für das Dropdown-Menü im Frontend
    public List<String> findAllCountries() {
        return co2DataRepository.findAll().stream()
                .map(Co2Data::getCountry)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}