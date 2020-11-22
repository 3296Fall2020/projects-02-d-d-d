package dnd.integration;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dnd.characters.Character;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import java.util.List;

/*
The GameService class provides services for games, packaging input into the proper HTTP object such that
the session manager can make requests with its client.
 */
public class GameService {

    private Configuration config = Configuration.getInstance();

    public HttpGet getGameForName(String gameName) {
        return new HttpGet(config.url + config.get_game_endpoint + "/" + gameName);
    }

    public HttpGet getGamesForUser() {
        return new HttpGet(config.url + config.get_all_game_endpoint);
    }

    public HttpPost deleteGameForUser(String gameName) {
        return new HttpPost(config.url + config.create_and_delete_game_endpoint + "/" + gameName + "/delete");
    }

    public HttpPost createOrUpdateGame(String gameName, JsonArray characterList) {
        HttpPost post = new HttpPost(config.url + config.create_and_delete_game_endpoint);

        JsonObject message = new JsonObject();
        message.addProperty("game_name", gameName);
        message.add("characters", characterList);

        HttpEntity stringEntity = new StringEntity(message.toString(), ContentType.APPLICATION_JSON);
        post.setEntity(stringEntity);
        return post;
    }
}
