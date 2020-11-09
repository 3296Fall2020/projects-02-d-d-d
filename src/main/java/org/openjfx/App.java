package org.openjfx;


import dnd.combat_test.CombatTest;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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

        /*Gson gson = new GsonBuilder().setPrettyPrinting().create();
        User usr = new User("Steve");

        HttpPost post = new HttpPost("http://localhost:5000/user");

        post.setEntity(new StringEntity(gson.toJson(usr)));
        post.setHeader("Content-type", "application/json");

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(post);
        System.out.println(response.getStatusLine());*/

        CombatTest combatTest = new CombatTest();
        combatTest.test_combat();

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