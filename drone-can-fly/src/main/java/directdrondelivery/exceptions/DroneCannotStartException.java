package directdrondelivery.exceptions;

import java.util.Set;

import directdronedelivery.drone.services.ErrorReason;

@SuppressWarnings("serial")
public class DroneCannotStartException extends Exception {

    private Set<ErrorReason> messages;
    
    public DroneCannotStartException(Set<ErrorReason> messages) {
        this.messages = messages;
    }

    public Set<ErrorReason> getMessages() {
        return messages;
    }
}
