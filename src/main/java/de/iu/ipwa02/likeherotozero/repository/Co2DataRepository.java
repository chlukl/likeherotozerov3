package de.iu.ipwa02.likeherotozero.repository;

import de.iu.ipwa02.likeherotozero.model.Co2Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Co2DataRepository extends JpaRepository<Co2Data, Long> {

    Optional<Co2Data> findFirstByCountryIgnoreCaseOrderByMsyearDesc(String country);
}