package Roles;

import App.App;
import Order.Order;
import ShoppingCart.ShoppingCart;
import Snacks.Snacks;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Customer extends Roles{
    public ShoppingCart shoppingCart;
    private double balance;
    private int id;
    public List<Order> orders;

    public Map<String, String> cardInfo = new HashMap<>();

    public Customer(double balance, int id, String username, String password) {
        super(username, password);
        orders = new ArrayList<>();
        shoppingCart = new ShoppingCart();
        this.balance = balance;
        this.id = id;
    }

    public int addToShoppingCart(Snacks snacks, int amount) {
        if(amount <= 0){
            return 1;
        }
        if(amount > snacks.getAmount()){
            return 2;
        }
        shoppingCart.shopping(snacks, amount);
        return  0;
    }


    public int removeFromShoppingCart(Snacks snacks, int amount) {
        if(amount <= 0){
            return -1;
        }



        if(!this.getShoppingCart().containsKey(snacks)){
            System.out.println("This snacks does not exist in shoppingCart");
            return -2;
        }

        int temp = 0;
        // get the amount of snacks in the shopping cart
        for(int i = 0; i < this.getShoppingCart().get_size(); i++){
            if(this.getShoppingCart().getSnacks(i).getName().equals(snacks.getName())){
                temp = this.getShoppingCart().getAmount(i);
            }
        }

        if(amount > temp){
            System.out.println("exceed amount");
            return -3;
        }
        removeItems(snacks, amount);
        return 0;
    }

    public ShoppingCart getShoppingCart() {
        return this.shoppingCart;
    }


//    public Order get_recent_order() {
//        return orders.get(orders.size() - 1);
//    }



    public void removeItems(Snacks snacks, int amount) {
        shoppingCart.removeItems(snacks, amount);
    }

    public void clearShoppingCart() {
        shoppingCart.removeAll();
    }

    public void createOrder() {
        Map<Snacks, Integer> tmp = new HashMap<>(shoppingCart.cart);
        orders.add(new Order(this, tmp));
    }

//    public void payForOrder() {
//        for (Order order : orders) {
//            this.balance -= order.get_total_price();
//            deleteItems(order);
//        }
//        App.store.update();
//    }
//
//
//
//    public void cancelOrders() {
//        orders.clear();
//    }
//
//    public void removeOrder(Order order) {
//        orders.remove(order);
//    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public void deleteItems(Order order) {
//        Map<Snacks, Integer> trade = order.getItems();
//        for (Snacks snacks: trade.keySet()) {
//            App.store.removeItem(snacks, trade.get(snacks));
//        }
//    }

    public boolean checkCard(String name, String number) {
        if (App.cardInfo.containsKey(name) && Objects.equals(App.cardInfo.get(name), number)) {
            return true;
        }
        return false;
    }

    public String[] getCardInfo(String path) {
        File file = new File(path);
        List<String[]> detail = new ArrayList<>();
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String []tmp = scan.nextLine().split(",");
                detail.add(tmp);
            }
            for (int i = detail.size() - 1; i > 0; i--) {
                if (detail.get(i)[0].equals(getUsername())) {
                    return new String[] {detail.get(i)[1], detail.get(i)[2]};
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void addCardInfo(String path, String cardName, String cardNumber) {
        File file = new File(path);
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(getUsername());
            fw.write(",");
            fw.write(cardName);
            fw.write(",");
            fw.write(cardNumber);
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancel(String path, String reason) {
        File file = new File(path);
        try {
            FileWriter fw = new FileWriter(file, true);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            fw.write(dtf.format(now));
            fw.write(",");
            fw.write(getUsername());
            fw.write(",");
            fw.write(reason);
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
