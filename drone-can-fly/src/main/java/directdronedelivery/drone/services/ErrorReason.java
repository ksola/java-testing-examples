package directdronedelivery.drone.services;

public enum ErrorReason {
    BAD_WEATHER("Cant fly because of bad weather"),
    CARGO_NOT_LOADED("Cant fly cargo not loaded"),
    WRONG_STATUS("Cant fly because DroneStatus not READY_FOR_TAKE_OFF");
    
    private String message;
    
    private ErrorReason(String errorReason) {
        this.message = errorReason;
    }
}
