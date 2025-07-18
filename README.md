Internationale Hochschule - IPWA02-01 Fallstudie
Like Hero To Zero - CO₂ Emissionen Tracker

Im Rahmen der Fallstudie Aufgabenstellung 1 wurde hier eine moderne Web-Anwendung zur Visualisierung und Verwaltung von weltweiten CO₂-Emissionsdaten entwickelt.

"Like Hero To Zero" ist eine Spring Boot-basierte Webanwendung, die es umweltpolitischen es umweltpolitisch interessierten Bürger:innen ermöglicht, aktuelle CO₂-Emissionsdaten ihres Landes einzusehen. 
Zusätzlich bietet sie ein geschütztes Backend-Portal für registrierte Wissenschaftler:innen zur Datenpflege und -korrektur.

Features im öffentlichen Bereich:
Ländersuche mit Autocomplete-Funktion
Aktuelle CO2-Daten für über 250 Länder (Stand 2023)

Features im Backend-portal
Login mit Spring Security
Dateneingabe für neue CO₂-Messungen
Review-System für Datenfreigabe
Automatischer CSV-Import beim Start

Techstack: 
Spring Boot
Spring Data JPA
Spring Security
Hibernate
H2 Database
Thymeleaf
Bootstrap
JavaScript
Maven
Java

Schnellstart-Anleitung:
Repository Klonen
git clone https://github.com/chlukl/likeherotozerov3.git
cd likeherotozerov3

Abhängigkeiten installieren und Anwendung starten
mvn spring-boot:run

Die Anwendung ist unter http://localhost:8080 verfügbar
Der Backend-Bereich ist mit folgenden Zugangsdaten zugänglich:
Benutzername: wissenschaftler
Passwort: passwort123
