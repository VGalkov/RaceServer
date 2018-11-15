package ru.galkov.raceserver.db_access.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class Users {

    public Users() {
    }
	
    
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "id")
	 private Long id;
	 
	 @Column(name = "login", unique = true, nullable = false)
	 private String login;

	 @Column(name = "password")
	 private String password;
	 
	 @Column(name = "level")
	 private String level;
	 
	 @Column(name = "fio")
	 private String fio;
	 
	 @Column(name = "comment")
	 private String comment;
	 
	 
	 @Column(name = "longtitude")
	 private Double longtitude;
	 
	 @Column(name = "altitude")
	 private Double altitude;
	 
	 @Column(name = "latitude")
	 private Double latitude;	 

	 
	 @Column(name = "master_mark_label")
	 private String master_mark_label;

	 
	 @Column(name = "registred_race_idl")
	 private long registred_race_id;
	 
	 @Column(name = "registred_start_id")
	 private long registred_start_id;
	 
// ========================================================
	 
	 
	//	setRegistredRaceId(res.getDouble("registred_race_id"));
//setRegistredStartId(res.getDouble("registred_start_id")); 
	 
	 
	public String getMaster_mark_label() {
		return master_mark_label;
	}

	public long getRegistred_race_id() {
		return registred_race_id;
	}

	public void setRegistred_race_id(long registred_race_id) {
		this.registred_race_id = registred_race_id;
	}

	public long getRegistred_start_id() {
		return registred_start_id;
	}

	public void setRegistred_start_id(long registred_start_id) {
		this.registred_start_id = registred_start_id;
	}

	public Double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(Double longtitude) {
		this.longtitude = longtitude;
	}

	public Double getAltitude() {
		return altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setMaster_mark_label(String master_mark_label) {
		this.master_mark_label = master_mark_label;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String inLevel) {
		this.level = inLevel;
	}
	 
	 
}
