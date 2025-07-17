package de.iu.ipwa02.likeherotozero.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data; // Lombok für Getter, Setter, etc.

@Entity // Markiert diese Klasse als JPA-Entität
@Data   // Lombok generiert automatisch Getter, Setter, toString, equals, hashCode
public class Co2Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;
    private int msyear;
    private double emissionValue; // In kt
}