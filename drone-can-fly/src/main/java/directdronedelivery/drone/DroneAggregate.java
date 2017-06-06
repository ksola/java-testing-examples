package directdronedelivery.drone;

import directdronedelivery.weather.Weather;

public class DroneAggregate {
    
    protected Integer droneID;
    protected DroneType droneType;
    protected DroneStatus status;
    protected Weather weather;
    protected Integer cargoID;
    
    protected DroneAggregate() {
    }
    
    public DroneAggregate(Integer droneID, DroneType droneType, DroneStatus status, Weather weather) {
        this.droneID = droneID;
        this.droneType = droneType;
        this.status = status;
        this.weather = weather;
    }
    
    public void attachCargo(Integer cargoID) {
        this.cargoID = cargoID;
    }
    
    public void detachCargo() {
        this.cargoID = null;
    }

    public Integer getDroneID() {
        return droneID;
    }

    public DroneType getDroneType() {
        return droneType;
    }

    public void setStatus(DroneStatus status) {
        this.status = status;
    }

    public DroneStatus getStatus() {
        return status;
    }

    public Weather getWeather() {
        return weather;
    }

    public Integer getCargoID() {
        return cargoID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DroneAggregate that = (DroneAggregate) o;

        return droneID != null ? droneID.equals(that.droneID) : that.droneID == null;
    }

    @Override
    public int hashCode() {
        return droneID != null ? droneID.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "DroneAggregate{" +
                "droneID=" + droneID +
                ", droneType=" + droneType +
                ", status=" + status +
                ", weather=" + weather +
                ", cargoID=" + cargoID +
                '}';
    }
}
