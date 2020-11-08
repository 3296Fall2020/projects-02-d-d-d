package org.openjfx;


import dnd.data.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;


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


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        User usr = new User("Steve");

        HttpPost post = new HttpPost("http://localhost:5000/user");

        post.setEntity(new StringEntity(gson.toJson(usr)));
        post.setHeader("Content-type", "application/json");

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(post);
        System.out.println(response.getStatusLine());

        // Demonstration of dices rolls ------------------------------------------------------
        Dice die = new Dice();
        System.out.println("Rolling a 6-sided die: " + die.roll(6));
        System.out.println("Rolling a 20-sided die: " + die.roll(20));
        System.out.println("Rolling a 20-sided die 10 times: " + Arrays.toString(die.roll(20, 10)));
        System.out.println("Rolling a 20-sided die with a minimum value of 15: " + die.rollWithMin(20, 15));
        System.out.println("Rolling a 20-sided die with a minimum value of 15 (10 times): " + Arrays.toString(die.rollWithMin(20, 10, 15)));
        System.out.println();
        // -----------------------------------------------------------------------------------

        // Demonstration of quiz generation --------------------------------------------------
        MathQuiz quiz = new AdditionQuiz();
        int[] quizResults = quiz.generateQuiz(3, 5);
        System.out.println(quizResults[0] + " + " + quizResults[1] + " = " + quizResults[2]);

        SubtractionQuiz quiz2 = new SubtractionQuiz();
        int[] quizResults2 = quiz2.generateQuiz(3, 3);
        System.out.println(quizResults2[0] + " - " + quizResults2[1] + " = " + quizResults2[2]);
        int[] quizResults5 = quiz2.generateQuizWithNegatives(4, 4);
        System.out.println(quizResults5[0] + " - " + quizResults5[1] + " = " + quizResults5[2]);

        MathQuiz quiz3 = new MultiplicationQuiz();
        int[] quizResults3 = quiz3.generateQuiz(2, 2);
        System.out.println(quizResults3[0] + " * " + quizResults3[1] + " = " + quizResults3[2]);


        DivisionQuiz quiz4 = new DivisionQuiz();
        int[] quizResults4 = quiz4.generateQuiz(3, 2);
        System.out.println(quizResults4[0] + " / " + quizResults4[1] + " = " + quizResults4[2]);


        DivisionQuiz quiz6 = new DivisionQuiz();
        int[] quizResults6 = quiz6.generateQuizWithRemainder(3, 2);
        System.out.println(quizResults6[0] + " / " + quizResults6[1] + " = " + quizResults6[2] + " R " + quizResults6[3]);
        // -----------------------------------------------------------------------------------

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