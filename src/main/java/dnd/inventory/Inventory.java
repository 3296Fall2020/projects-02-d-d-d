package dnd.inventory;

import dnd.items.Item;

import java.util.HashMap;
import java.util.Iterator;

public class Inventory {

    HashMap inventory;
    int size;

    public Inventory(){
        inventory = new HashMap();
        size = 0;
    }

    public boolean hasItem(Item item){
        if (inventory.containsKey(item))
            return true;
        return false;
    }

    public void put(Item item, int quantity){
        inventory.put(item, quantity);
    }

    public void examine(Item item){
        if (hasItem(item))
            item.getDesc();
    }

    //lists all items in inventory
    public void open(){
        Iterator itr = inventory.entrySet().iterator();
        while (itr.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)itr.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
        }
    }

}
