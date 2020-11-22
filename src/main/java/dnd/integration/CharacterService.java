package dnd.integration;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dnd.characters.Character;
import dnd.characters.CharacterFactory;
import dnd.weapons.Weapon;
import dnd.weapons.WeaponFactory;

import java.util.Arrays;
import java.util.List;

/*
The CharacterService class provides services for characters, packaging input into the proper HTTP object such that
the session manager can make requests with its client.
 */
public class CharacterService {

    public Character constructCharacterFromJson(JsonObject js) {
        String race = js.get("race").getAsString();
        String name = js.get("character_name").getAsString();
        String weaponType = js.get("active_weapon").getAsString();

        Character character = CharacterFactory.createCharacter(race, name);
        Weapon weapon = WeaponFactory.createWeapon(weaponType);
        character.setWeapon(weapon);

        int[] abilities = {
                js.get("strength").getAsInt(),
                js.get("dexterity").getAsInt(),
                js.get("constitution").getAsInt(),
                js.get("intelligence").getAsInt(),
                js.get("wisdom").getAsInt(),
                js.get("charisma").getAsInt(),
        };

        int[] abilityModifiers = {
                js.get("strength_modifier").getAsInt(),
                js.get("dexterity_modifier").getAsInt(),
                js.get("constitution_modifier").getAsInt(),
                js.get("intelligence_modifier").getAsInt(),
                js.get("wisdom_modifier").getAsInt(),
                js.get("charisma_modifier").getAsInt(),
        };


        character.setAbilities(abilities);
        character.setAbilityModifier(abilityModifiers);

        character.setLevel(js.get("level").getAsInt());
        character.addXP(js.get("xp").getAsInt());
        character.setClassMembership(js.get("character_class").getAsString());
        character.setHitPoints(js.get("hitpoints").getAsInt());
        character.setLanguage(js.get("language").getAsString());
        character.setSpeed(js.get("speed").getAsInt());
        character.setHealingDie(js.get("healing_die").getAsInt());

        return character;
    }

    public JsonObject constructJsonFromCharacter(Character character) {
        JsonObject js = new JsonObject();
        js.addProperty("race", character.getRace());
        js.addProperty("character_name", character.getName());
        js.addProperty("active_weapon", character.getWeapon().getClass().getSimpleName());
        js.addProperty("strength", character.getStrength());
        js.addProperty("dexterity", character.getDexterity());
        js.addProperty("constitution", character.getConstitution());
        js.addProperty("intelligence", character.getIntelligence());
        js.addProperty("wisdom", character.getWisdom());
        js.addProperty("charisma", character.getCharisma());
        js.addProperty("level", character.getLevel());
        js.addProperty("xp", character.getXP());
        js.addProperty("character_class", character.getClassMembership());
        js.addProperty("hitpoints", character.getHitPoints());
        js.addProperty("language", character.getLanguage());
        js.addProperty("speed", character.getSpeed());
        js.addProperty("healing_die", character.getHealingDie());
        js.addProperty("strength_modifier", character.getStrengthMod());
        js.addProperty("dexterity_modifier", character.getDexterityMod());
        js.addProperty("constitution_modifier", character.getConstitutionMod());
        js.addProperty("intelligence_modifier", character.getIntelligenceMod());
        js.addProperty("wisdom_modifier", character.getWisdomMod());
        js.addProperty("charisma_modifier", character.getCharismaMod());
        return js;
    }

    public void fillCharacterListFromJson(JsonArray characters, List<Character> toFillCharacters) {
        for (JsonElement ele:
             characters) {
            toFillCharacters.add(constructCharacterFromJson(ele.getAsJsonObject()));
        }
    }

    public JsonArray createJsonListFromCharacterList(List<Character> characters) {
        JsonArray js = new JsonArray();
        for (Character character:
             characters) {
            js.add(constructJsonFromCharacter(character));
        }
        return js;
    }

}
