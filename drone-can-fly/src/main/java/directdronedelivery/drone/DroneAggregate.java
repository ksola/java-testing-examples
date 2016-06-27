package directdronedelivery.drone;

import directdronedelivery.weather.Weather;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(of = "droneID")
@ToString
public class DroneAggregate {
    
    @Getter protected Integer droneID;
    @Getter protected DroneType droneType;
    @Getter @Setter protected DroneStatus status;
    @Getter protected Weather weather;
    @Getter protected Integer cargoID;
    
    protected DroneAggregate() {
    }
    
    public DroneAggregate(Integer droneID, DroneType droneType, DroneStatus status, Weather weather) {
        this.droneID = droneID;
        this.droneType = droneType;
        this.status = status;
        this.weather = weather;
    }
    
    public void attachCargo(Integer cargoID) {
        this.cargoID = cargoID;
    }
    
    public void detachCargo() {
        this.cargoID = null;
    }


}
