package term2.hashtable.example;

public class Country {
	private String name;
	private String capital;
	private String language;
	private Integer area;
	private String currency;
	private Integer phoneCode;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Integer getPhoneCode() {
		return phoneCode;
	}
	public void setPhoneCode(Integer phoneCode) {
		this.phoneCode = phoneCode;
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
		Country other = (Country) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phoneCode == null) {
			if (other.phoneCode != null)
				return false;
		} else if (!phoneCode.equals(other.phoneCode))
			return false;
		return true;
	}
	
	
}