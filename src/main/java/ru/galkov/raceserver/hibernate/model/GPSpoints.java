package ru.galkov.raceserver.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "gps_points")
public class GPSpoints {

	/*
    @ManyToOne
    @JoinColumn(name = "gps_point_id")
    */
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "id")
	 private Long id;
	 
	 @Column(name = "label")
	 private String label;
	 
	 @Column(name = "latitude")
	 private Long latitude;
	 
	 @Column(name = "longitude")
	 private Double longitude;
	 
	 @Column(name = "altitude")
	 private Double altitude;

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

	public Long getLatitude() {
		return latitude;
	}

	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getAltitude() {
		return altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}
	 
	 
	 
}
