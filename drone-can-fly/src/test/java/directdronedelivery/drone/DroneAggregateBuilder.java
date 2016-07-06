package directdronedelivery.drone;

import directdronedelivery.drone.DroneAggregate;
import directdronedelivery.drone.DroneStatus;
import directdronedelivery.weather.Weather;

// TODO 05: Implement builder methods as in previous tasks
public class DroneAggregateBuilder {

    public static DroneAggregateBuilder aSmallDroneWithNiceWeatherAndCargo() {
        return null;
    }

    public DroneAggregateBuilder withCargoId(Integer cargoId) {
        return null;
    }
    
    public DroneAggregate build() {
        return null;
    }

    public static DroneAggregateBuilder aBigDroneWithBadWeatherAndCargo() {
        return null;
    }

    
    public DroneAggregateBuilder but() {
        return this;
    }

    public DroneAggregateBuilder withWeather(Weather weather) {
        
        return this;
    }

    public DroneAggregateBuilder withStatus(DroneStatus cargoLoading) {
        return null;
    }

    public DroneAggregateBuilder withoutCargo() {
        return null;
    }
    

}
