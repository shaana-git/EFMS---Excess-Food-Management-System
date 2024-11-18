package src.src;

import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class FindLocation {
    private static String lat;
    private static String lng;
    public FindLocation(String addr) throws IOException, InterruptedException{
        ObjectMapper mapper = new ObjectMapper();
        Geocoder geocoder = new Geocoder();

        String response = geocoder.GeocodeSync(addr);
        JsonNode responseJsonNode = mapper.readTree(response);

        JsonNode items = responseJsonNode.get("items");

        for (JsonNode item : items) {
            JsonNode address = item.get("address");
            String label = address.get("label").asText();
            JsonNode position = item.get("position");

            lat = position.get("lat").asText();
            lng = position.get("lng").asText();
            System.out.println(label + " is located at " + lat + "," + lng + ".");
        }
    }
    public String getlat(){
        return this.lat;
    }
    public String getlong(){
        return this.lng;
    }

    public static void main(String[] args) throws IOException, InterruptedException{
        new FindLocation("Daalchini, Grand Southern Trunk Rd, Chromepet, Chennai, Tamil Nadu 600044");
    }
}
