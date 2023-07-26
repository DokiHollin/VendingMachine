package Builder;

import Snacks.Snacks;

public class SnacksBuilder implements Builder {
    private String type;
    private String name;
    private double price;
    private int amount;
    private int id;

    @Override
    public void buildName(String name) {
        this.name = name;
    }

    @Override
    public void buildPrice(double price) {
        this.price = price;
    }

    @Override
    public void buildAmount(int amount) { // test amount <= 15

        this.amount = amount;
    }

    @Override
    public void buildId(int id) {
        this.id =id;
    }

    @Override
    public void buildType(String type) {  // test type <= 4
        this.type = type;
    }

    @Override
    public Snacks buildSnacks() {
        return new Snacks(name,price,amount,id,type);
    }
}
