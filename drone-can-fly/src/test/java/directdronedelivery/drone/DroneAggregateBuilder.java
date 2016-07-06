package directdronedelivery.drone;

import directdronedelivery.drone.DroneAggregate;
import directdronedelivery.drone.DroneStatus;
import directdronedelivery.weather.Weather;
import directdronedelivery.weather.WeatherBuilder;

public class DroneAggregateBuilder {

	private DroneAggregate droneUnderConstruction;
	
	public DroneAggregateBuilder() {
		droneUnderConstruction = new DroneAggregate();
	}
	
    public static DroneAggregateBuilder aSmallDroneWithNiceWeatherAndCargo() {
    	DroneAggregateBuilder builder = new DroneAggregateBuilder();
		return builder.
				withStatus(DroneStatus.READY_FOR_TAKE_OFF).
				withType(DroneType.SMALL_FOUR_ROTORS).
				withCargoId(1).
				withWeather(WeatherBuilder.aWeather().likeNiceWeather().build());
    }

    public DroneAggregateBuilder withCargoId(Integer cargoId) {
    	droneUnderConstruction.cargoID = cargoId;
        return this;
    }
    
    public DroneAggregate build() {
        return droneUnderConstruction;
    }

    public static DroneAggregateBuilder aBigDroneWithBadWeatherAndCargo() {
        return aSmallDroneWithNiceWeatherAndCargo().but().withType(DroneType.BIG_SIX_ROTORS);
    }

    
    public DroneAggregateBuilder but() {
        return this;
    }

    public DroneAggregateBuilder withWeather(Weather weather) {
        droneUnderConstruction.weather = weather;
        return this;
    }

    public DroneAggregateBuilder withStatus(DroneStatus status) {
    	droneUnderConstruction.status = status;
        return this;
    }

    public DroneAggregateBuilder withoutCargo() {
    	droneUnderConstruction.detachCargo();
        return this;
    }
    
    public DroneAggregateBuilder withType(DroneType droneType) {
    	droneUnderConstruction.droneType = droneType;
    	return this;
    }

}
