package Store;

import Builder.Director;
import Builder.SnacksBuilder;
import Order.Order;
import Roles.Cashier;
import Roles.Customer;
import Snacks.Snacks;
import java.io.*;
import java.util.*;

public class Store {

    public List<Snacks> drinks = new ArrayList<>();
    public List<Snacks> chocolates = new ArrayList<>();
    public List<Snacks> chips = new ArrayList<>();
    public List<Snacks> candies = new ArrayList<>();
    public static String [] items = {"Drinks", "Chocolates", "Chips", "Candies"};



    public static Map<String, List<Snacks>> store = new HashMap<>();

    public Store() {
        configList("src/main/resources/Candies","src/main/resources/Chips",
                "src/main/resources/Chocolates","src/main/resources/Drinks");
        store.put("Drinks", drinks);
        store.put("Chocolates", chocolates);
        store.put("Chips", chips);
        store.put("Candies", candies);
    }

    public List<Snacks> get_snacks(){
        List<Snacks> snacks = new ArrayList<>();
        snacks.addAll(drinks);
        snacks.addAll(chocolates);
        snacks.addAll(chips);
        snacks.addAll(candies);
        return snacks;
    }


    public void delete_item_in_order(Order order) {
        // delete the items in the order
        for (Snacks snacks : order.getItems().keySet()) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < store.get(items[i]).size(); j++) {
                    if (store.get(items[i]).get(j).getName().equals(snacks.getName())) {
                        store.get(items[i]).get(j).setAmount(store.get(items[i]).get(j).getAmount() - order.getItems().get(snacks));
                    }
                }
            }
        }

    }


    public String[] get_item_name() {
        return items;
    }

    public int addItem(Snacks snacks) {
        if(snacks.getAmount() > 15){
            System.out.println("exceed max amount");
            return 1;
        }

        if (checkItem(snacks.getName()) != -1) {
            if(store.get(snacks.getType()).get(checkItem(snacks.getName())).getAmount() + snacks.getAmount() > 15){
                System.out.println("exceed max amount");
                return 2;
            }
            store.get(snacks.getType()).get(checkItem(snacks.getName())).setAmount(store.get(snacks.getType()).get(checkItem(snacks.getName())).getAmount() + snacks.getAmount());
            update();
            return 3;
        }
        store.get(snacks.getType()).add(snacks);
        update();
        return 4;
    }

    public void removeItem(Snacks snacks, int amount) {
        for (int i = 0; i < store.get(snacks.getType()).size(); i++) {
            if (store.get(snacks.getType()).get(i).getName().equals(snacks.getName())) {
                store.get(snacks.getType()).get(i).setAmount(store.get(snacks.getType()).get(i).getAmount() - amount);
                if (store.get(snacks.getType()).get(i).getAmount() == 0) {
                    store.get(snacks.getType()).remove(snacks);
                }
                return;
            }
        }
    }

    public int checkItem(String name) {
        for (int i = 0; i < store.size(); i++) {
            for (int j = 0; j < store.get(items[i]).size(); j++) {
                if (store.get(items[i]).get(j).getName().equals(name)) {
                    return j;
                }
            }
        }
        return -1;
    }

    public List<Snacks> get_items(String type) {
        return store.get(type);
    }

    public static Snacks get_item(String name){
        for (int i = 0; i < store.size(); i++) {
            for (int j = 0; j < store.get(items[i]).size(); j++) {
                if (store.get(items[i]).get(j).getName().equals(name)) {
                    return store.get(items[i]).get(j);
                }
            }
        }
        return null;
    }

    public static void complete_an_order_cash(Store store, Customer customer, Cashier cashier, Map<Double, Integer> money, double total_price) {
        customer.createOrder();
        cashier.addCash(money);
        cashier.update("src/main/resources/RemainCash");
        store.delete_item_in_order(customer.orders.get(customer.orders.size() - 1));
        customer.setBalance(customer.getBalance() - total_price);
        store.update();
    }

    public static void complete_an_order_card(Store store, Customer customer, double total_price) {
        customer.createOrder();
        store.delete_item_in_order(customer.orders.get(customer.orders.size() - 1));
        customer.setBalance(customer.getBalance() - total_price);
        store.update();
    }

    public void configList(String a, String b, String c, String d) {
        this.candies.clear();
        this.chocolates.clear();
        this.chips.clear();
        this.drinks.clear();
        File candiesF = new File(a);
        File chipsF = new File(b);
        File chocolatesF = new File(c);
        File drinksF = new File(d);
        readFile(candiesF, "Candies", candies);
        readFile(chipsF, "Chips", chips);
        readFile(chocolatesF, "Chocolates", chocolates);
        readFile(drinksF, "Drinks", drinks);
    }




    public void readFile(File f, String type, List<Snacks> snacks) {
        Scanner scan;
        try {
            int count = 0;
            scan = new Scanner(f);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (count > 0) {
                    String[] tmp = line.split(",");
                    snacks.add(new Director(new SnacksBuilder()).construct(tmp[1], Double.parseDouble(tmp[2]), Integer.parseInt(tmp[3]), Integer.parseInt(tmp[0]), type));
                }
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        File candiesF = new File("src/main/resources/Candies");
        File chipsF = new File("src/main/resources/Chips");
        File chocolatesF = new File("src/main/resources/Chocolates");
        File drinksF = new File("src/main/resources/Drinks");
        writeFile(candiesF, candies);
        writeFile(chipsF, chips);
        writeFile(chocolatesF, chocolates);
        writeFile(drinksF, drinks);
    }

    public void writeFile(File f, List<Snacks> snacks) {
        try {
            FileWriter fileWriter = new FileWriter(f);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fw = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("id,name,price,amount\n");
            for (Snacks snacks1: snacks) {
                bw.write(String.valueOf(snacks1.getId()));
                bw.write(",");
                bw.write(snacks1.getName());
                bw.write(",");
                bw.write(String.valueOf(snacks1.getPrice()));
                bw.write(",");
                bw.write(String.valueOf(snacks1.getAmount()));
                bw.write("\n");
            }
            bw.flush();
            bw.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.out.println("error");
        }
    }
}
