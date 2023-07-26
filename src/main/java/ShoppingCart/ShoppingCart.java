package ShoppingCart;

import Snacks.Snacks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    public Map<Snacks, Integer> cart = new HashMap<>();

    public int shopping(Snacks snacks, int amount) {

        if(cart.containsKey(snacks)){
            // if the cart already contains the snacks, then we just add the amount to the existing amount
            cart.replace(snacks, cart.get(snacks) + amount);

        }else{
            cart.put(snacks, amount);
        }
        return 0;
    }

    public boolean hasName(String name){
        for(Snacks snacks : cart.keySet()){
            if(snacks.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public int get_amount(String name){
        for(Snacks snacks : cart.keySet()){
            if(snacks.getName().equals(name)){
                return cart.get(snacks);
            }
        }
        return 0;
    }

    public int get_size(){
        return cart.size();
    }

    public boolean containsKey(Snacks snacks){
        return cart.containsKey(snacks);
    }

    public boolean isEmpty(){
        return cart.isEmpty();
    }

    public ShoppingCart remove(Snacks snacks){
        cart.remove(snacks);
        return this;
    }

    public Snacks getSnacks(int index){
        List<Snacks> snacks = new ArrayList<>(cart.keySet());
        return snacks.get(index);
    }

    public int getAmount(int index){
        List<Integer> amount = new ArrayList<>(cart.values());
        return amount.get(index);
    }

    public Map<Snacks,Integer> getShoppingCart(){
        return cart;
    }

    public void removeItems(Snacks snacks, int amount) {
        if (cart.get(snacks) == amount) {
            cart.remove(snacks);
        }
        else {
            cart.replace(snacks, cart.get(snacks) - amount);
        }
    }

    public void removeAll() {
        cart.clear();
    }
}
