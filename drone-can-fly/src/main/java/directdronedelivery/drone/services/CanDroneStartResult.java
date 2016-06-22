package directdronedelivery.drone.services;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class CanDroneStartResult {

    /**
     * Decision if the drone can start
     */
    @Getter private boolean canDroneStart;
    
    /**
     * Reasons why the drone cannot start
     */
    @Getter private Set<ErrorReason> reasons;
    
}
