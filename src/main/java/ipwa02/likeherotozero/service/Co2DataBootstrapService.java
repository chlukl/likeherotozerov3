package ipwa02.likeherotozero.service;

import ipwa02.likeherotozero.model.Co2Data;
import ipwa02.likeherotozero.repository.Co2DataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class Co2DataBootstrapService {

    private final Co2DataRepository repo;

    public Co2DataBootstrapService(Co2DataRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public int loadCsvFromClasspath(String path) {
        int imported = 0;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new ClassPathResource(path).getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            boolean header = true;
            while ((line = br.readLine()) != null) {
                if (header) { header = false; continue; }
                String[] t = line.split(";");
                if (t.length < 3) {
                    log.warn("Import: Zu wenige Spalten: '{}'", line);
                    continue;
                }
                String country = t[0].trim();
                int year = Integer.parseInt(t[1].trim());
                double emission = Double.parseDouble(t[2].trim());
                repo.save(new Co2Data(country, year, emission, true));
                imported++;
            }
        } catch (Exception e) {
            log.error("CSV-Import fehlgeschlagen", e);
            throw new IllegalStateException("CSV konnte nicht geladen werden: " + path);
        }
        log.info("Import abgeschlossen: {} Datensätze importiert", imported);
        return imported;
    }
}
