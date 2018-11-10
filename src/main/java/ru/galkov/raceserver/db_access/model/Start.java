package ru.galkov.raceserver.db_access.model;

import javax.persistence.*;

@Entity
@Table(name = "start")
public class Start {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "id")
	 private Long id;
	 

	 @Column(name = "race_id")
	 private Long race_id;
	 
	 @Column(name = "label", unique = true, nullable = false)
	 private String label;

	 //TODO переделать на норбальный Date тип!
	 @Column(name = "start_time")
	 private String start_time;
//	 private Date start_time;
	 
	 //TODO переделать на норбальный Date тип!
	 @Column(name = "stop_time")
//	 private Date stop_time;
	 private String stop_time;
	 
	 @Column(name = "active")
	 private String active;

	public String getActive() {
		return active;
	}

	public String getStart_time() {
		return start_time;
	}


	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getStop_time() {
		return stop_time;
	}

	public void setStop_time(String stop_time) {
		this.stop_time = stop_time;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Long getRace_id() {
		return race_id;
	}

	public void setRace_id(Long race_id) {
		this.race_id = race_id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	 
	 
}
