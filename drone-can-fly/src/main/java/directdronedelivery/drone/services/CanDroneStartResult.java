package directdronedelivery.drone.services;

import java.util.Set;


public class CanDroneStartResult {

    public CanDroneStartResult(boolean canDroneStart, Set<ErrorReason> reasons) {

        this.canDroneStart = canDroneStart;
        this.reasons = reasons;
    }

    /**
     * Decision if the drone can start
     */
    private boolean canDroneStart;
    
    /**
     * Reasons why the drone cannot start
     */
    private Set<ErrorReason> reasons;

    public boolean isCanDroneStart() {
        return canDroneStart;
    }

    public Set<ErrorReason> getReasons() {
        return reasons;
    }
}
