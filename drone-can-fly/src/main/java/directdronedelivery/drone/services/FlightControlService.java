package directdronedelivery.drone.services;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import directdronedelivery.drone.DroneAggregate;
import directdronedelivery.drone.DroneStatus;
import directdronedelivery.drone.DroneType;

public class FlightControlService {
    
    /**
     * Service checks if drone can start. The following condition have to be fulfilled:
     * 1) DroneStatus = READY_FOR_TAKE_OFF 
     * 2) cargoId is filled
     * 3) Weather isGoodToFly equal true when DroneType equal SMALL_FOUR_ROTORS or DroneType equal BIG_SIX_ROTORS.
     * 
     * @param droneAggregate
     * @return 
     */
    public CanDroneStartResult canDronStart(DroneAggregate droneAggregate) {
        Set<ErrorReason> messages = new HashSet<>();
        
        if(droneAggregate.getStatus() != DroneStatus.READY_FOR_TAKE_OFF){
            messages.add(ErrorReason.WRONG_STATUS);
        }
        if(Objects.isNull(droneAggregate.getCargoID())){
            messages.add(ErrorReason.CARGO_NOT_LOADED);
        }
        if(droneAggregate.getDroneType() == DroneType.SMALL_FOUR_ROTORS && !droneAggregate.getWeather().isGoodToFly()){
            messages.add(ErrorReason.BAD_WEATHER);
        }
        
        return new CanDroneStartResult(messages.isEmpty(), messages);
    }
}
