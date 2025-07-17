package de.iu.ipwa02.likeherotozero.repository;

import de.iu.ipwa02.likeherotozero.model.Co2Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Co2DataRepository extends JpaRepository<Co2Data, Long> {

    // Spring Data JPA generiert die Abfrage automatisch aus dem Methodennamen!
    // Finde den ersten (neuesten) Eintrag für ein Land, sortiert nach Jahr absteigend.
    Optional<Co2Data> findFirstByCountryIgnoreCaseOrderByMsyearDesc(String country);
}