package dnd.weapons;

public class Sword extends Weapon {

    public String name;
    public int damageDie;

    public Sword(){
        this.name = "sword";
        this.damageDie = 8;
    }

    public String getName(){
        return this.name;
    }

    public int getDie(){
        return this.damageDie;
    }

    public void getDesc(){
        System.out.println("A fine blade. Be careful! It's sharp.");
    }

}
