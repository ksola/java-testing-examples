package directdronedelivery.firsttest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import directdronedelivery.drone.DroneAggregate;
import directdronedelivery.drone.DroneStatus;
import directdronedelivery.drone.services.CanDroneStartResult;
import directdronedelivery.drone.services.ErrorReason;
import directdronedelivery.drone.services.FlightControlService;
import directdronedelivery.weather.DroneAggregateBuilder;
import directdronedelivery.weather.Weather;

public class FlightControlServiceTest {
    
    private FlightControlService flightControlService;
    @Before
    public void before() {
        flightControlService = new FlightControlService();
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
        Weather badWeather = null; // TASK 05a Implement a WeatherBuilder.aBadWeather() method. 
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
}
