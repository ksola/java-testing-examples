package directdronedelivery.firsttest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import directdrondelivery.cargo.Cargo;
import directdrondelivery.cargo.CargoDao;
import directdrondelivery.exceptions.DroneCannotStartException;
import directdronedelivery.drone.DroneAggregate;
import directdronedelivery.drone.DroneStatus;
import directdronedelivery.drone.services.CanDroneStartResult;
import directdronedelivery.drone.services.ErrorReason;
import directdronedelivery.drone.services.FlightControlService;
import directdronedelivery.drone.services.MessagingService;
import directdronedelivery.weather.DroneAggregateBuilder;
import directdronedelivery.weather.Weather;

public class FlightControlServiceTest {
    
	private FlightControlService flightControlService;
    private MessagingService mMessagingService;
    private CargoDao mCargoDao;
    private Map<Integer, Cargo> mCargoMap = new HashMap<>();
    
    @Before
    public void before() {
    	// TODO 7a: make mock for MessagingService using Mockito.mock(...)
    	// (...)
    	// TODO 7b: make mock for CargoDao using Mockito.mock(...)
    	// (...)
        flightControlService = new FlightControlService(mCargoDao, mMessagingService);
        
        loadDummyCargoToMap();
    }
    
    @Test
    public void shouldAllowToFlyWhenWeatherIsGoodAndDroneIsSmall() throws Exception {
        // given
        DroneAggregate droneAggregate = DroneAggregateBuilder.aSmallDroneWithNiceWeather().withCargoId(1).build();
        
        // when 
        CanDroneStartResult canDronStart = flightControlService.canDronStart(droneAggregate);
        
        // then
        assertThat(canDronStart.isCanDroneStart()).isTrue();
        assertThat(canDronStart.getReasons()).isEmpty();
    }
    
    @Test
    public void shouldAllowToFlyWhenWeatherIsBadAndDroneIsBig() throws Exception {
        // given
        DroneAggregate droneAggregate = DroneAggregateBuilder.aBigDroneWithBadWeather().withCargoId(1).build();
        
        // when 
        CanDroneStartResult canDronStart = flightControlService.canDronStart(droneAggregate);
        
        // then
        assertThat(canDronStart.isCanDroneStart()).isTrue();
        assertThat(canDronStart.getReasons()).isEmpty();
    }
    
    @Test
    public void shouldNotAllowToFlyWhenWeatherIsBadAndDroneIsSmall() throws Exception {
        // given
        Weather badWeather = null; // TODO 05a Implement a WeatherBuilder.aBadWeather() method. 
        DroneAggregate droneAggregate = DroneAggregateBuilder.aSmallDroneWithNiceWeather().but().withWeather(badWeather).build();
        
        // when 
        CanDroneStartResult canDronStart = flightControlService.canDronStart(droneAggregate);
        
        // then
        assertThat(canDronStart.isCanDroneStart()).isFalse();
        assertThat(canDronStart.getReasons()).hasSize(1);
        assertThat(canDronStart.getReasons().iterator().next()).isEqualTo(ErrorReason.BAD_WEATHER);
    }
    
    @Test
    public void shouldNotAllowToFlyWhenWeatherIsNiceAndDroneIsSmallButCargoNotLoaded() throws Exception {
        // given
        DroneAggregate droneAggregate = DroneAggregateBuilder.aSmallDroneWithNiceWeather().but().withoutCargo().build();
        
        // when 
        CanDroneStartResult canDronStart = flightControlService.canDronStart(droneAggregate);
        
        // then
        assertThat(canDronStart.isCanDroneStart()).isFalse();
        assertThat(canDronStart.getReasons()).hasSize(1);
        assertThat(canDronStart.getReasons().iterator().next()).isEqualTo(ErrorReason.CARGO_NOT_LOADED);
    }
    
    @Test
    public void shouldNotAllowToFlyWhenWeatherIsNiceAndDroneIsSmallButWrongStatus() throws Exception {
        // given
        DroneAggregate droneAggregate = DroneAggregateBuilder.aSmallDroneWithNiceWeather().but()
                .withStatus(DroneStatus.CARGO_LOADING).build();
        
        // when 
        CanDroneStartResult canDronStart = flightControlService.canDronStart(droneAggregate);
        
        // then
        assertThat(canDronStart.isCanDroneStart()).isFalse();
        assertThat(canDronStart.getReasons()).hasSize(1);
        assertThat(canDronStart.getReasons().iterator().next()).isEqualTo(ErrorReason.WRONG_STATUS);
    }
    
    @Test
    public void shouldNotAllowToFlyWhenWeatherIsNiceAndDroneIsSmallButWrongStatusAndCargoNotLoaded() throws Exception {
        // given
        DroneAggregate droneAggregate = DroneAggregateBuilder.aSmallDroneWithNiceWeather().but()
                .withStatus(DroneStatus.CARGO_LOADING).withoutCargo().build();
        
        // when 
        CanDroneStartResult canDronStart = flightControlService.canDronStart(droneAggregate);
        
        // then
        assertThat(canDronStart.isCanDroneStart()).isFalse();
        assertThat(canDronStart.getReasons()).hasSize(2);
        assertThat(canDronStart.getReasons()).contains(ErrorReason.WRONG_STATUS);
        assertThat(canDronStart.getReasons()).contains(ErrorReason.CARGO_NOT_LOADED);
    }
    
    @Test
    public void shouldNotStartDroneBecauseOfCargo() throws Exception {
        // given
        DroneAggregate droneAggregate = DroneAggregateBuilder.aSmallDroneWithNiceWeather().withCargoId(1).build();

        // TODO 07c: For droneAggregate object invoke flightControlService.startDrone(droneAggregate).
        //           Method startDrone should throw a DroneCannotStartException, 
        //           use try-catch block and check if exception has been thrown (check errorMessage inside the exception).

    }

    @Test
    public void shouldStartDroneAndSendSMSAndEmailNotification() throws Exception {
        // given
        Cargo cargoId_1 = mCargoMap.get(1);
        DroneAggregate droneAggregate = DroneAggregateBuilder.aSmallDroneWithNiceWeather().withCargoId(1).build();
        
        // TODO 07d: Use Mockito.when(...).thenReturn(...) to simulate response from mCargoDao.findCargoById(1). Object cargoId_1 should be here returned.
        //           For droneAggregate object invoke flightControlService.startDrone(droneAggregate).
        //           In the next step check if dronAggregate.getStatus == DroneStatus.DURING_MAINTENANCE and verify
        //           using Mockito.verify(...) if method mMessagingService.sendEmail(...) and .sendSMS(...) were invoked
        //           with the following two parameters: generateTestMessage(cargoId_1), cargoId_1.getRecipientEmail().
        //           E.g. (...).sendEmail(generateTestMessage(cargoId_1), cargoId_1.getRecipientEmail())
        
    }
    
    @Test
    public void shouldStartDroneAndSendOnlySMSNotification() throws Exception {
        // given
        Cargo cargoId_2 = mCargoMap.get(2);
        DroneAggregate droneAggregate = DroneAggregateBuilder.aSmallDroneWithNiceWeather().withCargoId(2).build();

        // TODO 07e: Use Mockito.when(...).thenReturn(...) to simulate response from mCargoDao.findCargoById(2). Object cargoId_2 should be here returned.
        //           For droneAggregate object invoke flightControlService.startDrone(droneAggregate).
        //           In the next step check if dronAggregate.getStatus == DroneStatus.DURING_MAINTENANCE and verify
        //           using Mockito.verify(...) if method mMessagingService.sendSMS(...) was invoked
        //           with the following two parameters: generateTestMessage(cargoId_2), cargoId_2.getRecipientEmail().
        //           and method mMessagingService.sendEmail(...) wasn't invoked at all. 
        //           As two parameters in method sendEmail(...) use Matchers.anyString().
        //           E.g. (...).sendEmail(Matchers.anyString(), Matchers.anyString())

    }

    @Test
    public void shouldStartDroneAndSendNoNotification() throws Exception {
        // given
        Cargo cargoId_3 = mCargoMap.get(3);
        DroneAggregate droneAggregate = DroneAggregateBuilder.aSmallDroneWithNiceWeather().withCargoId(3).build();
        // TODO 07f: Use Mockito.when(...).thenReturn(...) to simulate response from mCargoDao.findCargoById(3). Object cargoId_3 should be here returned.
        //           For droneAggregate object invoke flightControlService.startDrone(droneAggregate).
        //           In the next step check if dronAggregate.getStatus == DroneStatus.DURING_MAINTENANCE and verify
        //           using Mockito.verify(...) if method mMessagingService.sendSMS(...) and .sendEmail(...) weren't invoked at all.
        //           As two parameters in methods sendEmail(...) and sendSMS(...) use Matchers.anyString().
        //           E.g. (...).sendEmail(Matchers.anyString(), Matchers.anyString())

    }
    
    
    private void loadDummyCargoToMap() {
        Cargo cargoId_1 = new Cargo();
        cargoId_1.setCargoID(1);
        cargoId_1.setCargoName("Klocki Lego StarWars (10220231)");
        cargoId_1.setRecipientName("Adam Kowalski");
        cargoId_1.setRecipientEmail("adam.kowalski@mail.com");
        cargoId_1.setRecipientPhone("123-321-123");
        mCargoMap.put(1, cargoId_1);
        
        Cargo cargoId_2 = new Cargo();
        cargoId_2.setCargoID(2);
        cargoId_2.setCargoName("Pilka adidas BEAU JEU");
        cargoId_2.setRecipientName("Robert Lewandowski");
        cargoId_2.setRecipientEmail("222-333-111");
        mCargoMap.put(2, cargoId_2);
        
        Cargo cargoId_3 = new Cargo();
        cargoId_3.setCargoID(3);
        cargoId_3.setCargoName("Narty ELAN");
        cargoId_3.setRecipientName("Kamil Stoch");
        mCargoMap.put(3, cargoId_3);
    }
    
    private String generateTestMessage(Cargo cargo) {
        Integer cargoID = cargo.getCargoID();
        String cargoName = cargo.getCargoName();
        String recipientName = cargo.getRecipientName();
        
		return "Przesylka o nr: " + cargoID + " (" + cargoName + ") " + "jest w drodze. Odbiorca: " + recipientName + ".";
    }
}
