package directdrondelivery.exceptions;

import java.util.Set;

import directdronedelivery.drone.services.ErrorReason;
import lombok.Getter;

@SuppressWarnings("serial")
public class DroneCannotStartException extends Exception {

    @Getter private Set<ErrorReason> messages;
    
    public DroneCannotStartException(Set<ErrorReason> messages) {
        this.messages = messages;
    }
}
