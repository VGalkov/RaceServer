package ru.galkov.raceserver.db_access.model;

import javax.persistence.*;

@Entity
@Table(name = "nfc_marks")
public class NFCmarks {

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "id")
	 private Long id;
	 
	 @Column(name = "label", unique = true, nullable = false)
	 private String label;

	 @Column(name = "race_id")
	 private Long race_id;
	 	 
	 @Column(name = "longtitude")
	 private Double longtitude;
	 
	 @Column(name = "latitude")
	 private Double latitude;
	 
	 @Column(name = "altitude")
	 private Double altitude;
	 
	 @Column(name = "counter", unique = true, nullable = false)
	 private int counter;

	 
// ===========================================

	 
	

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public Long getRace_id() {
		return race_id;
	}

	public Double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(Double longtitude) {
		this.longtitude = longtitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getAltitude() {
		return altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	public void setRace_id(Long race_id) {
		this.race_id = race_id;
	}
	 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	 
	 
}
