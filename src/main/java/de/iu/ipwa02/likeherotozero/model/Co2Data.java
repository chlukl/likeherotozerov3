package de.iu.ipwa02.likeherotozero.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Co2Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;
    private Integer msyear; //Integer statt int, damit Platzhalter im Backend greift (sonst 0)
    private double emissionValue;

    private Boolean approved = false; //Boolean statt boolean - Probleme in Co2DataService

    public Co2Data(String country, int msyear, double emissionValue, Boolean approved) {
        this.country = country;
        this.msyear = msyear;
        this.emissionValue = emissionValue;
        this.approved = approved;
    }
}
