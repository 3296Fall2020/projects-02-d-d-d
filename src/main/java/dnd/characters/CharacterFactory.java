package dnd.characters;


import java.lang.reflect.InvocationTargetException;

public class CharacterFactory {

    public static Character createCharacter(String race, String name) {
        Character toRet = null;
        try {
            toRet = (Character) Class.forName("dnd.characters." + race).getDeclaredConstructor(String.class).newInstance(name);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException | ClassNotFoundException e) {
            System.out.println("Failed to create character: " + e.toString());
        }
        return toRet;
    }
}
