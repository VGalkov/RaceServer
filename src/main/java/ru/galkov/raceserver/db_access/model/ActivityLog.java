package ru.galkov.raceserver.db_access.model;

import java.util.Date;

import javax.persistence.*;
import org.json.JSONObject;

@Entity
@Table(name = "activitylog")
public class ActivityLog {
		
		 @Id
		 @GeneratedValue(strategy = GenerationType.AUTO)
		 @Column(name = "id")
		 private Long id;

		 @Column(name = "asker")
		 private String asker;
		 
		 @Column(name = "dt")
		 private Date dt;

		 @Column(name = "json_in")
		 private String json_in;

		 @Column(name = "json_out")
		 private String json_out;

		 @Column(name = "login")
		 private String login;
		 
		 @Column(name = "level")
		 private String level;		 
		 
		 // ================================
		 
		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public String getLevel() {
			return level;
		}

		public void setLevel(String level) {
			this.level = level;
		}

		public void setJson_in(String json_in) {
			this.json_in = json_in;
		}

		public void setJson_out(String json_out) {
			this.json_out = json_out;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getAsker() {
			return asker;
		}

		public void setAsker(String asker) {
			this.asker = asker;
		}

		public Date getDt() {
			return dt;
		}

		public void setDt(Date dt) {
			this.dt = dt;
		}



		public void setJson_in(JSONObject inBoundJSON) {
			this.json_in = inBoundJSON.toString();
		}


		public void setJson_out( JSONObject json_out) {
			this.json_out = json_out.toString();
		}
		
		

		public String getJson_in() {
			return json_in;
		}


		public String getJson_out() {
			return json_out;
		}


}
