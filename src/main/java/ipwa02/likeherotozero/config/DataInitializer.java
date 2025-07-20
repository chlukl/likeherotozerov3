package ipwa02.likeherotozero.config;

import ipwa02.likeherotozero.service.Co2DataBootstrapService;
import ipwa02.likeherotozero.repository.Co2DataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final Co2DataBootstrapService service;
    private final Co2DataRepository repo;

    public DataInitializer(Co2DataBootstrapService service, Co2DataRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        if (repo.count() == 0) {
            int n = service.loadCsvFromClasspath("data/owid-co2-data-2023");
            log.info("Bootstrap-Import abgeschlossen: {} Datensätze geladen", n);
        } else {
            log.info("Datenbank enthält bereits {} Datensätze – Import übersprungen", repo.count());
        }
    }
}
