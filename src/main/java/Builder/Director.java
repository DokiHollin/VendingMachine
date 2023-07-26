package Builder;

import Snacks.Snacks;

public class Director {
    private Builder snackBuilder;

    public Director(Builder snackBuilder) {
        this.snackBuilder = snackBuilder;
    }

    public Snacks construct(String name, double price, int amount, int id, String type) {
        this.snackBuilder.buildName(name);
        this.snackBuilder.buildPrice(price);
        this.snackBuilder.buildAmount(amount);
        this.snackBuilder.buildId(id);
        this.snackBuilder.buildType(type);
        return this.snackBuilder.buildSnacks();
    }
}
