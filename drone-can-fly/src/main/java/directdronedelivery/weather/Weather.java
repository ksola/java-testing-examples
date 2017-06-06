package directdronedelivery.weather;


public class Weather {
    
    protected double windInPMS;
    protected boolean lightningsPossible;
    protected boolean precipitationPossible;
    protected int temperatureInCelsius;

    public Weather(double windInPMS, boolean lightningsPossible, boolean precipitationPossible, int temperatureInCelsius) {
        this.windInPMS = windInPMS;
        this.lightningsPossible = lightningsPossible;
        this.precipitationPossible = precipitationPossible;
        this.temperatureInCelsius = temperatureInCelsius;
    }

    protected Weather() {
    }
    
    // TODO 02: tdd implementation: implement first test cases and extend Weather to pass tests... repeat
    public boolean isGoodToFly() {
        return false;
    }

    public double getWindInPMS() {
        return windInPMS;
    }

    public boolean isLightningsPossible() {
        return lightningsPossible;
    }

    public boolean isPrecipitationPossible() {
        return precipitationPossible;
    }

    public int getTemperatureInCelsius() {
        return temperatureInCelsius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Weather weather = (Weather) o;

        if (Double.compare(weather.windInPMS, windInPMS) != 0) return false;
        if (lightningsPossible != weather.lightningsPossible) return false;
        if (precipitationPossible != weather.precipitationPossible) return false;
        return temperatureInCelsius == weather.temperatureInCelsius;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(windInPMS);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (lightningsPossible ? 1 : 0);
        result = 31 * result + (precipitationPossible ? 1 : 0);
        result = 31 * result + temperatureInCelsius;
        return result;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "windInPMS=" + windInPMS +
                ", lightningsPossible=" + lightningsPossible +
                ", precipitationPossible=" + precipitationPossible +
                ", temperatureInCelsius=" + temperatureInCelsius +
                '}';
    }
}
