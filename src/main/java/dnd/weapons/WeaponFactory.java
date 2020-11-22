package dnd.weapons;

import dnd.characters.Character;

import java.lang.reflect.InvocationTargetException;

public class WeaponFactory {

    public static Weapon createWeapon(String name) {
        Weapon toRet = null;
        try {
            toRet = (Weapon) Class.forName("dnd.weapons." + name).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException | ClassNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return toRet;
    }
}