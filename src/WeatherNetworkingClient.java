import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherNetworkingClient{
    private String APIkey;
    private String baseUrl;


    public WeatherNetworkingClient(){
        APIkey = "4ae4749f231b466d8d512536221905";
        baseUrl = " http://api.weatherapi.com/v1";

    }

    public String getTemperature(String zipCode)
    {
         String endPoint = "/current.json/temp_f";
         String urlTempF = baseUrl + endPoint + "?api_key=" + APIkey;

        // use the makeAPICall helper method to make a network request to the Now Playing API
        String response = makeAPICall(urlTempF);

        // call the parseNowPlayingJSON helper method, then return
        // the arraylist of Movie objects that gets returned by that helper method
         ArrayList<Movie> moviesNowPlaying = parseNowPlayingJSON(response);

        return response;
    }



    private double parseTemperature(String json){
        double temperature = 0;
        JSONObject jsonObj = new JSONObject(json);
        JSONObject current = new JSONObject(jsonObj.getString("current"));
        temperature = current.getDouble("temp_f");
        return temperature;
    }

    private String makeAPICall(String url)
    {
        try {
            URI myUri = URI.create(url); // creates a URI object from the url string
            HttpRequest request = HttpRequest.newBuilder().uri(myUri).build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}