class WeatherData {
    private String location;
    private double temperature;
    private double humidity;

    public WeatherData(String location, double temperature, double humidity) {
        this.location = location;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
}

interface WeatherAPI {
    WeatherData fetchWeather(String location);
}

class WeatherAdapter implements WeatherAPI {
    private ExternalWeatherAPI externalAPI;

    public WeatherAdapter(ExternalWeatherAPI externalAPI) {
        this.externalAPI = externalAPI;
    }

    @Override
    public WeatherData fetchWeather(String location) {
        ExternalWeatherData externalData = externalAPI.requestWeather(location);
        WeatherData weatherData = new WeatherData(
                location,
                externalData.getTemperature(),
                externalData.getHumidity()
        );
        return weatherData;
    }
}

class ExternalWeatherData {
    private double temp;
    private double humidity;

    public ExternalWeatherData(double temp, double humidity) {
        this.temp = temp;
        this.humidity = humidity;
    }

    public double getTemperature() {
        return temp;
    }

    public void setTemperature(double temp) {
        this.temp = temp;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
}

class ExternalWeatherAPI {
    public ExternalWeatherData requestWeather(String location) {
        return new ExternalWeatherData(25.5, 60.0);
    }
}

public class Adapter{
    public static void main(String[] args) {
        ExternalWeatherAPI externalAPI = new ExternalWeatherAPI();
        WeatherAPI adapter = new WeatherAdapter(externalAPI);
        WeatherData weatherData = adapter.fetchWeather("New York");
        System.out.println("Location: " + weatherData.getLocation());
        System.out.println("Temperature: " + weatherData.getTemperature() + " °C");
        System.out.println("Humidity: " + weatherData.getHumidity() + "%");
    }
}
