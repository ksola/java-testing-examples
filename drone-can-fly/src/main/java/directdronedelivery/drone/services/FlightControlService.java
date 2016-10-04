package directdronedelivery.drone.services;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import directdrondelivery.exceptions.DroneCannotStartException;
import directdronedelivery.cargo.Cargo;
import directdronedelivery.cargo.CargoDao;
import directdronedelivery.drone.DroneAggregate;
import directdronedelivery.drone.DroneStatus;
import directdronedelivery.drone.DroneType;

public class FlightControlService {
    
	private MessagingService mMessagingService = null;
    private CargoDao mCargoDao = null;
    
    public FlightControlService(CargoDao cargoDao, MessagingService messagingService) {
        mMessagingService = messagingService;
        mCargoDao = cargoDao;
    }
    
    /**
     * Service checks if drone can start. The following condition have to be fulfilled:
     * 1) DroneStatus = READY_FOR_TAKE_OFF,
     * 2) cargoId is filled,
     * 3) Small Drone (SMALL_FOUR_ROTORS) needs good weather, big drone (BIG_SIX_ROTORS) starts at any weather.
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
    
    /**
     * Method check if the drone can start. When he does it changes drone status to DURING_MAINTENANCE. 
     * Additionally informs recipient that the drone is on the way.
     * 
     * @param droneAggregate
     * @throws DroneCannotStartException
     */
    public void startDrone(DroneAggregate droneAggregate) throws DroneCannotStartException {
        CanDroneStartResult canDronStartResult = canDronStart(droneAggregate);
        
        if(canDronStartResult.isCanDroneStart()) {
            droneAggregate.setStatus(DroneStatus.DURING_MAINTENANCE);
            
            Cargo cargo = mCargoDao.findCargoById(droneAggregate.getCargoID());
            sentMessageToRecipient(cargo);
        }
        else {
            throw new DroneCannotStartException(canDronStartResult.getReasons());
        }
    }

    private void sentMessageToRecipient(Cargo cargo) {
        String messageText = generateMessage(cargo);
        
        String phone = cargo.getRecipientPhone();
        if(phone != null && phone.length() > 0) {
            mMessagingService.sendSMS(messageText, phone);
        }
        
        String email = cargo.getRecipientEmail();
        if(email != null && email.length() > 0) {
            mMessagingService.sendEmail(messageText, email);
        }
    }

    private String generateMessage(Cargo cargo) {
        Integer cargoID = cargo.getCargoID();
        String cargoName = cargo.getCargoName();
        String recipientName = cargo.getRecipientName();
        
		return "Przesylka o nr: " + cargoID + " (" + cargoName + ") " + "jest w drodze. Odbiorca: " + recipientName + ".";
    }
}
