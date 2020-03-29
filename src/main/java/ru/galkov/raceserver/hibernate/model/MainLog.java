package ru.galkov.raceserver.hibernate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "main_log")
public class MainLog {

	/*
    @ManyToOne
    @JoinColumn(name = "mark_id") */
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "id")
	 private Long id;
	 
	 @Column(name = "datetime")
	 private Date dt;

	 @Column(name = "user_id")
	 private Long user_id;

	 @Column(name = "race_id")
	 private Long race_id;
	 
	 @Column(name = "mark_id")
	 private Long mark_id;
	 
	 @Column(name = "gps_point_id")
	 private Long gps_point_id;

	 
// ===================================================================	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getRace_id() {
		return race_id;
	}

	public void setRace_id(Long race_id) {
		this.race_id = race_id;
	}

	public Long getMark_id() {
		return mark_id;
	}

	public void setMark_id(Long mark_id) {
		this.mark_id = mark_id;
	}

	public Long getGps_id() {
		return gps_point_id;
	}

	public void setGps_id(Long gps_point_id) {
		this.gps_point_id = gps_point_id;
	}	 
	 
}
