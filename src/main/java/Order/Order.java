package Order;

import Roles.Customer;
import Snacks.Snacks;

import java.util.List;
import java.util.Map;

public class Order {
    private Customer customer;
    private Map<Snacks, Integer> items;

    public Order(Customer customer, Map<Snacks, Integer> items) {
        this.customer = customer;
        this.items = items;
    }

    public double get_total_price() {
        double total_price = 0;
        for (Snacks snacks : items.keySet()) {
            total_price += snacks.getPrice() * items.get(snacks);
        }
        return total_price;
    }

    public String get_index(){
        return String.valueOf(customer.orders.indexOf(this) + 1);
    }

//    public int get_amount(String name){
//        int amount;
//        for (Snacks snacks : items.keySet()) {
//            if (snacks.getName().equals(name)) {
//                amount = items.get(snacks);
//                return amount;
//            }
//        }
//        return 0;
//
//    }

    public Map<Snacks, Integer> getItems() {
        return items;
    }

//    public void setItems(Map<Snacks, Integer> items) {
//        this.items = items;
//    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
