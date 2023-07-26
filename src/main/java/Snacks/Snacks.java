package Snacks;

import Store.Store;
import App.App;

public class Snacks {
    private String type;
    private String name;
    private double price;
    private int amount;
    private int id;

    public Snacks(String name, double price, int amount, int id, String type) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.id = id;
        //this.id = App.store.store.get(type).size();
        this.type = type;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
