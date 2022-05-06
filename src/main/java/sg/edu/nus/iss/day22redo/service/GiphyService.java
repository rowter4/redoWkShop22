package sg.edu.nus.iss.day22redo.service;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class GiphyService {
   
    @Value("${giphy.api.key}")
    private String giphyKey;

    public static final String GIPHY_DEFAULT_URL = "https://api.giphy.com/v1/gifs/search"; 

    public List<String> getGifs(String searchPhrase) {
        return getGifs(searchPhrase, 10, "pg");
    }

    public List<String> getGifs(String searchPhrase, String rating) {
        return getGifs(searchPhrase, 10, rating);
    }

    public List<String> getGifs(String searchPhrase, Integer limit) {
        return getGifs(searchPhrase, limit, "pg");
    }
    
    public List<String> getGifs(String searchPhrase, Integer limit, String rating) {
        
        String url = UriComponentsBuilder.fromUriString(GIPHY_DEFAULT_URL)
                    .queryParam("api_key", giphyKey)
                    .queryParam("q", searchPhrase)
                    .queryParam("limit", limit)
                    .queryParam("rating", rating)   
                    .toUriString();

        RequestEntity<Void> req = RequestEntity.get(url).build();
        ResponseEntity<String> resp = null;
        RestTemplate template = new RestTemplate();
        List<String> results = new LinkedList<>();

        try{
            resp = template.exchange(req, String.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return results;
        }

        JsonReader reader = Json.createReader(new StringReader(resp.getBody()));
        // StringReader doesn't throw exception
        JsonObject object = reader.readObject();
        JsonArray array = object.getJsonArray("data");

        for (int i=0; i< array.size(); i++) {
            JsonObject gifs = array.getJsonObject(i);
            String imageUrl = gifs.getJsonObject("images")
                                    .getJsonObject("fixed_width")
                                    .getString("url");

            results.add(imageUrl);
        }
        
        return results;
    }

}
