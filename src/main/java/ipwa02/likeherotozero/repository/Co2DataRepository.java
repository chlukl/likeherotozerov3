package ipwa02.likeherotozero.repository;

import ipwa02.likeherotozero.model.Co2Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Co2DataRepository extends JpaRepository<Co2Data, Long> {
    List<Co2Data> findByApprovedTrueOrderByCountryAsc();
    Optional<Co2Data> findFirstByCountryIgnoreCaseAndApprovedTrueOrderByMsyearDesc(String country);
    List<Co2Data> findByApprovedFalse();
}