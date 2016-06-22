package directdronedelivery.weather;

//TASK 04: Weather rewrite tests using existing WeatherBuilder
public class WeatherBuilder {
    private Weather underConstruction = null;
    
    public static WeatherBuilder aWeather() {
        WeatherBuilder builder = new WeatherBuilder();
        builder.underConstruction = new Weather();
        return builder;
    }
    
    public WeatherBuilder likeNiceWeather() {
        withWindInMetersPerSecond(0.8);
        withLightningsPossible(false);
        withPrecipitationPossible(false);
        withTemperatureInCelsius(20);
        return this;
    }
    
    public WeatherBuilder but() {
        return this;
    }
    
    public WeatherBuilder withWindInMetersPerSecond(double windInMPS) {
        underConstruction.windInPMS = windInMPS;
        return this;
    }
    
    public WeatherBuilder withLightningsPossible(boolean lightningsPossible) {
        underConstruction.lightningsPossible = lightningsPossible;
        return this;
    }
    
    public WeatherBuilder withPrecipitationPossible(boolean precipitationPossible) {
        underConstruction.precipitationPossible = precipitationPossible;
        return this;
    }
    
    public WeatherBuilder withTemperatureInCelsius(int temperatureInCelsius) {
        underConstruction.temperatureInCelsius = temperatureInCelsius;
        return this;
    }
    
    public Weather build() {
        Weather builded = underConstruction;
        underConstruction = new Weather();
        return builded;
    }
}
