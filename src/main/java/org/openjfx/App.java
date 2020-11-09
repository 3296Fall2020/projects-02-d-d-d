package org.openjfx;


import dnd.data.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import com.google.gson.*;

//import io.grpc.ManagedChannel;
//import io.grpc.ManagedChannelBuilder;
//import java.util.concurrent.TimeUnit;
//import dnd.data.GrpcClient;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws InterruptedException, IOException {

        //Testing that making a character works
        Character Alex = new Character("Alex");
        System.out.println("The character's name is " + Alex.getName());
        System.out.println("The character's strength is " + Alex.getStrength());
        System.out.println("The character's dexterity is " + Alex.getDexterity());
        System.out.println("The character's constitution is " + Alex.getConstitution());
        System.out.println("The character's intelligence is " + Alex.getIntelligence());
        System.out.println("The character's wisdom is " + Alex.getWisdom());
        System.out.println("The character's charisma is " + Alex.getCharisma());
        System.out.println("The character's XP is " + Alex.getXP());
        System.out.println("The character's level is " + Alex.getLevel());

        //Testing that increasing XP works
        Alex.addXP(300);
        System.out.println("+300 XP!");
        System.out.println("The character's XP is now " + Alex.getXP());
        System.out.println("The character's level is now " + Alex.getLevel());


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        User usr = new User("Steve");

        HttpPost post = new HttpPost("http://localhost:5000/user");

        post.setEntity(new StringEntity(gson.toJson(usr)));
        post.setHeader("Content-type", "application/json");

//        HttpClient httpClient = HttpClientBuilder.create().build();
//        HttpResponse response = httpClient.execute(post);
//        System.out.println(response.getStatusLine());

        /*
        String server = "localhost:50051";
        String user = "Mike";

        ManagedChannel channel = ManagedChannelBuilder.forTarget(server).usePlaintext().build();

        try {
            GrpcClient client = new GrpcClient(channel);
            client.CreateUser(user);
        } finally {
            // ManagedChannels use resources like threads and TCP connections. To prevent leaking these
            // resources the channel should be shut down when it will no longer be used. If it may be used
            // again leave it running.
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        } */

        launch();
    }

}