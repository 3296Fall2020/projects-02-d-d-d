package dnd.characters;

import javafx.scene.control.TextField;
import org.w3c.dom.Text;

//Allows us to obtain username from login screen for default character creation
public class UserNameSingleton {

    private static UserNameSingleton instance = new UserNameSingleton();
    public static UserNameSingleton getInstance(){
        return instance;
    }

    private TextField userName;

    public TextField getUserName() {
        return userName;
    }


    public void  setUserName(TextField userName) {
        this.userName = userName;
    }



}
