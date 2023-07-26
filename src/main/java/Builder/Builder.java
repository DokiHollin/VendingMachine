package Builder;

import Snacks.Snacks;

public interface Builder {
    void buildName(String name);
    void buildPrice(double price);
    void buildAmount(int amount);
    void buildId(int id);
    void buildType(String type);
    Snacks buildSnacks();
}
