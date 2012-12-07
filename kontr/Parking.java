package kontr;

public class Parking {

	public static final int TRUCK_PARKING = 1;
	public static final int PASSENGER_CAR_PARKING = 2;

	private Integer typeOfParking;
	private Integer maxVolume;
	private Integer count;
	private Double income;

	public Parking(Integer typeOfParking, Integer maxVolume) {
		this.typeOfParking = typeOfParking;
		this.maxVolume = maxVolume;
		count = 0;
		income = 0.0;
	}

	public void incCount(int amount) {
		count += amount;
	}

	public void decCount(int amount) {
		count -= amount;
	}

	public boolean hasPlaces() {
		return maxVolume > count;
	}

	public Double getIncome() {
		return income;
	}

	public void incIncome(Car car) {
		this.income += calculateParkingPrice(car);
	}

	private double calculateParkingPrice(Car car) {
		if (typeOfParking.equals(PASSENGER_CAR_PARKING)) {
			PassengerCar passengerCar = (PassengerCar) car;
			// TODO
			return 0.0;
		} else {
			Truck truck = (Truck) car;
			// TODO
			return 0.0;
		}
	}

	public void printReport() {
		System.out.println("--- Report ---");
		System.out.println(toString());
	}

	@Override
	public String toString() {
		return "Parking [typeOfParking=" + typeOfParking + ", maxVolume="
				+ maxVolume + ", count=" + count + ", income=" + income + "]";
	}
}
