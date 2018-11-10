package ru.galkov.raceserver.db_access.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "main_log")
public class MainLog {
	
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

	 @Column(name = "start_id")
	 private Long start_id;

	 @Column(name = "longtitude")
	 private Double longtitude;
	 
	 @Column(name = "latitude")
	 private Double latitude;
	 
	 @Column(name = "altitude")
	 private Double altitude;

	 @Column(name = "mark_label")
	 private String mark_label;
	 
	 @Column(name = "login")
	 private String login;

	 @Column(name = "master_mark_label")
	 private String master_mark_label;
	 
	 
	 @Column(name = "master_mark_delta")
	 private long master_mark_delta;
	 
	 @Column(name = "mark_master_latitude")
	 private Double mark_master_latitude;
	 
	 @Column(name = "mark_master_longtitude")
	 private Double mark_master_longtitude;
	 
	 @Column(name = "mark_master_altitude")
	 private Double mark_master_altitude;
	 
// ===================================================================	 

	 
	 
	public Long getStart_id() {
		return start_id;
	}

	public Double getMark_master_latitude() {
		return mark_master_latitude;
	}

	public void setMark_master_latitude(Double mark_master_latitude) {
		this.mark_master_latitude = mark_master_latitude;
	}

	public Double getMark_master_longtitude() {
		return mark_master_longtitude;
	}

	public void setMark_master_longtitude(Double mark_master_longtitude) {
		this.mark_master_longtitude = mark_master_longtitude;
	}

	public Double getMark_master_altitude() {
		return mark_master_altitude;
	}

	public void setMark_master_altitude(Double mark_master_altitude) {
		this.mark_master_altitude = mark_master_altitude;
	}

	public String getMasterMarkLabel() {
		return master_mark_label;
	}

	public void setMasterMarkLabel(String master_mark_label) {
		this.master_mark_label = master_mark_label;
	}

	public long getMasterMarkLabelDelta() {
		return master_mark_delta;
	}

	public void setMasterMarkDelta(long master_mark_delta2) {
		this.master_mark_delta = master_mark_delta2;
	}

	public String getMark_label() {
		return mark_label;
	}

	public void setMark_label(String mark_label) {
		this.mark_label = mark_label;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public void setStart_id(Long start_id) {
		this.start_id = start_id;
	}
	
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
 
	 
}
