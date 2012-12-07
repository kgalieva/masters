package kontr;

public class PassengerCar extends Car {

	protected int horsepower;

	private int hasProtection = -1;

	public int getHasProtection() {
		return hasProtection;
	}

	public void setHasProtection(int hasProtection) {
		this.hasProtection = hasProtection;
	}

	public int getHorsepower() {
		return horsepower;
	}

	public void setHorsepower(int horsepower) {
		this.horsepower = horsepower;
	}

}
