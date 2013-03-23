package term2.hashtable.example;

public class City {
	private String name;
	private Country counrty;
	private Float latitude;
	private Float longitude;
	private Boolean isCapital;
	private Boolean hasAirport;
	private Long population;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Country getCounrty() {
		return counrty;
	}
	public void setCounrty(Country counrty) {
		this.counrty = counrty;
	}
	public Float getLatitude() {
		return latitude;
	}
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	public Float getLongitude() {
		return longitude;
	}
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	public Boolean getIsCapital() {
		return isCapital;
	}
	public void setIsCapital(Boolean isCapital) {
		this.isCapital = isCapital;
	}
	public Boolean getHasAirport() {
		return hasAirport;
	}
	public void setHasAirport(Boolean hasAirport) {
		this.hasAirport = hasAirport;
	}
	public Long getPopulation() {
		return population;
	}
	public void setPopulation(Long population) {
		this.population = population;
	}
	@Override
	public int hashCode() {
		//TODO
		return 0;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (counrty == null) {
			if (other.counrty != null)
				return false;
		} else if (!counrty.equals(other.counrty))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
