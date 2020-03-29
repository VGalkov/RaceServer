package ru.galkov.raceserver.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "race")
public class Users {
	 // http://www.quizful.net/post/java_enums
	enum Level { Admin, User, Guest }

    public Users() {
    }
	
	/*
    @ManyToOne
    @JoinColumn(name = "user_id")
    */
    
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "id")
	 private Long id;
	 
	 @Column(name = "login")
	 private String login;

	 @Column(name = "password")
	 private String password;
	 
	 @Column(name = "level")
	 private Level level;

	 
// ========================================================
	 
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

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	 
	 
}
