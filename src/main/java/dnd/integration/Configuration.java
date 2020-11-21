package dnd.integration;

public class Configuration {
    public String url = "http://localhost:5000";
    public final String token_endpoint = "/token";
    public final String create_user_endpoint = "/users";
    public final String create_game_endpoint = "/games";
    public final String get_game_endpoint = "/games/";
    public final String production_url = "http://ec2-18-222-81-22.us-east-2.compute.amazonaws.com:5000";

    public Configuration() {
        if (System.getenv("DDD_ENV") == null) {
            this.url = production_url;
        }
    }
}
