package dnd.integration;

import dnd.characters.Character;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;



/* SessionManger exposes the api through a facade, allowing for the authentication of sessions,
** the fetching of games for the active user and for saving games.
*/
public class SessionManager {

    private boolean authenticated;
    private LocalDateTime tokenTime;
    private List<String> credentialsCache = new ArrayList<>();
    private Configuration config;
    private HttpClient client;


    public SessionManager() {
        this.config = new Configuration();
        this.authenticated = false;
    }

    // Authenticate the session with the given username and password, store the token in the
    // class http client for future use
    public boolean authenticateSession(String username, String password) {
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials
                = new UsernamePasswordCredentials(username, password);
        provider.setCredentials(AuthScope.ANY, credentials);

        HttpClient client = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(provider)
                .build();

        try {
            HttpResponse response = client.execute(
                    new HttpGet(config.url + config.token_endpoint));
            int statusCode = response.getStatusLine()
                    .getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                String content = EntityUtils.toString(response.getEntity());
                JsonObject message = JsonParser.parseString(content).getAsJsonObject();
                this.tokenTime = LocalDateTime.now();

                provider = new BasicCredentialsProvider();
                credentials = new UsernamePasswordCredentials(message.get("token").toString(), "x");
                provider.setCredentials(AuthScope.ANY, credentials);

                this.client = HttpClientBuilder.create()
                        .setDefaultCredentialsProvider(provider)
                        .build();
                if (!authenticated) {
                    this.credentialsCache.add(username);
                    this.credentialsCache.add(password);
                }
                this.authenticated = true;
                return true;
            }
        } catch (java.io.IOException e) {
            System.out.println("Failed to get token: " + e.getLocalizedMessage());
        }

        return false;
    }

    // attempt to create a user with the given credentials
    public boolean createUser(String desiredUsername, String desiredPassword) {
        JsonObject message = new JsonObject();
        message.addProperty("username", desiredUsername);
        message.addProperty("password", desiredPassword);


        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(config.url + config.create_user_endpoint);

        HttpEntity stringEntity = new StringEntity(message.toString(), ContentType.APPLICATION_JSON);
        post.setEntity(stringEntity);
        try {
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() != 201) {
                return false;
            }
            String content = EntityUtils.toString(response.getEntity());
            if (content.contains(desiredUsername)) {
                return true;
            }
        } catch (java.io.IOException e) {
            System.out.println("Failed to create user: " + e.getLocalizedMessage());
        }
        
        return false;
    }

    public boolean ensureAuthentication() {
        // if you have not logged in at all, client needs to login at least once for cacheing
        if (!this.authenticated) {
            return false;
        }
        LocalDateTime now = LocalDateTime.now();
        System.out.println(this.tokenTime.until(now, ChronoUnit.MINUTES));
        // check if we need to renew our api token
        if (this.tokenTime.until(now, ChronoUnit.MINUTES) > 9) {
            return this.authenticateSession(this.credentialsCache.get(0), this.credentialsCache.get(1));
        }
        return true;
    }


    public List<String> fetchGamesForPlayer() {
        // failed authentication, either log in for the first time or
        if (!this.ensureAuthentication()) {
            return null;
        }
        List<String> games = new ArrayList<>();
        return games;
    }

    public List<Character> fetchCharactersForGame(String gameName) {
        if (!this.ensureAuthentication()) {
            return null;
        }
        List<Character> characters = new ArrayList<>();
        return characters;
    }

    // Save a game to the db, denoting if this is an existing game or a new one
    public boolean saveGame(String gameTitle, List<Character> characters, boolean newGame) {
        if (!this.ensureAuthentication()) {
            return false;
        }
        if (newGame) {

        } else {

        }
        return false;
    }


}
