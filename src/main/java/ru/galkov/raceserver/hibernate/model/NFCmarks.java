package ru.galkov.raceserver.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nfc_marks")
public class NFCmarks {

	/*
    @ManyToOne
    @JoinColumn(name = "mark_id") 
    many-many gps_point_id => gps_points
    */
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "id")
	 private Long id;
	 
	 @Column(name = "label")
	 private String label;

	 @Column(name = "gps_point_id")
	 private Long gps_point_id;


	 
// ===========================================
	 
	public Long getGps_point_id() {
		return gps_point_id;
	}

	public void setGps_point_id(Long gps_point_id) {
		this.gps_point_id = gps_point_id;
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
