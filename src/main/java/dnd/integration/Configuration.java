package dnd.integration;

public class Configuration {

    private static Configuration instance = null;
    public String url = "http://localhost:5000";
    public final String token_endpoint = "/token";
    public final String create_user_endpoint = "/users";
    public final String create_and_delete_game_endpoint = "/games";
    public final String get_game_endpoint = "/games/";
    public final String get_all_game_endpoint = "/games/saved";
    public final String production_url = "http://ec2-18-222-81-22.us-east-2.compute.amazonaws.com:5000";

    private Configuration() {
        if (System.getenv("DDD_ENV") == null) {
            this.url = production_url;
        }
    }

    public static Configuration getInstance()
    {
        if (instance == null)
            instance = new Configuration();
        return instance;
    }
}
