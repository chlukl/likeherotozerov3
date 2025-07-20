package ipwa02.likeherotozero.service;

import ipwa02.likeherotozero.model.Co2Data;
import ipwa02.likeherotozero.repository.Co2DataRepository;
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
        return co2DataRepository.findFirstByCountryIgnoreCaseAndApprovedTrueOrderByMsyearDesc(country);
    }

    public List<String> findAllCountries() {
        return co2DataRepository.findByApprovedTrueOrderByCountryAsc().stream()
                .map(Co2Data::getCountry)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public Co2Data saveData(Co2Data data) {
        if (data.getApproved() == null) {
            data.setApproved(false);
        }
        return co2DataRepository.save(data);
    }

    public List<Co2Data> getPendingData() {
        return co2DataRepository.findByApprovedFalse();
    }

    public void approveData(Long id) {
        Co2Data data = co2DataRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Datensatz nicht gefunden")
        );
        data.setApproved(true);
        co2DataRepository.save(data);
    }
}