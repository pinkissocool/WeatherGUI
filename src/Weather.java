//data model class
public class Weather{
    private double temperature;

    private String weather;

    public Weather(double temp, String weather){
        this.temperature = temperature;
        this.weather = weather;
    }

    public double getTemperature(){
        return temperature;
    }

    public String getWeather(){
        return weather;
    }

}