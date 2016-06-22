package directdronedelivery.weather;

import org.assertj.core.api.AbstractAssert;

import directdronedelivery.drone.services.CanDroneStartResult;

public class CanDronStartResultAssertions extends AbstractAssert<CanDronStartResultAssertions, CanDroneStartResult> {
    
    protected CanDronStartResultAssertions(CanDroneStartResult actual) {
        super(actual, CanDronStartResultAssertions.class);
    }
    
    public static CanDronStartResultAssertions assertThat(CanDroneStartResult actual) {
        return new CanDronStartResultAssertions(actual);
    }
    
    // TASK 06. Implement own domain assertions and use it in FlightControlServiceTest 
    public CanDronStartResultAssertions canStart() {
        return null;
    }
    
    public CanDronStartResultAssertions canNotStartBecauseOf(String... errorMessages) {
        return null;
    }
}
