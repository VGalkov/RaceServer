package ru.galkov.raceserver.db_access.model;
import javax.persistence.*;


@Entity
@Table(name = "race")
public class Race {
	
    public Race() {
    }
	
	/*
    @ManyToOne
    @JoinColumn(name = "race_id") 
    */
    
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "id")
	 private Long id;
	 
	 @Column(name = "label", unique = true, nullable = false)
	 private String label;
	 
	 @Column(name = "active")
	 private String active;
	 
	 @Column(name = "longtitude")
	 private Double longtitude;
	 
	 @Column(name = "altitude")
	 private Double altitude;
	 
	 @Column(name = "latitude")
	 private Double latitude;


	 
	 // ==============================================
	 
	 
		public String getActive() {
		return active;
	}

	public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
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

	public void setActive(String active) {
		this.active = active;
	}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

 

}
