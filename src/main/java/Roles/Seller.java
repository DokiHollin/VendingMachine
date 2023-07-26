package Roles;

import App.App;
import Order.Order;
import Snacks.Snacks;
import Store.Store;

import java.io.*;
import java.util.*;

public class Seller extends Roles{
    public static Map<String, Integer> sold = new HashMap<> ();

    public Seller(String username, String password) {
        super(username, password);
//        setSold("src/main/resources/SellerReport");
    }

    public static int modifyItem(String name, String type, double price, int amount, int id) {
        if (amount > 15) {
            return 0;
        }
        if (amount <= 0) {
            return -2;
        }
        if (price <= 0) {
            return -3;
        }
        for (Snacks snacks: Store.store.get(type)) {
            if (snacks.getId() == id && !snacks.getName().equals(name)) {
                return -1;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < Store.store.get(Store.items[i]).size(); j++) {
                if (Store.store.get(Store.items[i]).get(j).getName().equals(name)) {
                    Store.store.get(Store.items[i]).get(j).setAmount(amount);
                    Store.store.get(Store.items[i]).get(j).setType(type);
                    Store.store.get(Store.items[i]).get(j).setPrice(price);
                    Store.store.get(Store.items[i]).get(j).setId(id);
                }
            }
        }
        return 1;
    }

    public int fillItem(Snacks snacks) {
        return App.store.addItem(snacks);
    }

    public static void deleteItem(Snacks snacks, int amount) {
        App.store.removeItem(snacks, amount);
    }

//    public void setSold(String path) {
//        File file = new File(path);
//        try {
//            Scanner scanner = new Scanner(file);
//            int count = 0;
//            while (scanner.hasNextLine()) {
//                String[] tmp = scanner.nextLine().split(",");
//                if (count > 0) {
//                    tmp = scanner.nextLine().split(",");
//                    sold.put(tmp[1], Integer.parseInt(tmp[2]));
//                }
//                count++;
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static List<String[]> generateReport(String path) {
        File file = new File(path);
        List<String[]> report = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            int count = 0;
            while (scanner.hasNextLine()) {
                if (count > 0) {
                    String[] tmp = scanner.nextLine().split(",");
                    report.add(tmp);
                }
                count++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return report;
    }

//    public static void addAndUpdate(Order order, String path) {
//        for (Snacks snacks: order.getItems().keySet()) {
//            if (sold.containsKey(snacks.getName())) {
//                sold.put(snacks.getName(), order.getItems().get(snacks) + sold.get(snacks.getName()));
//            }
//         }
//
//        File file = new File(path);
//        try {
//            FileWriter fileWriter = new FileWriter(file);
//            fileWriter.write("");
//            fileWriter.flush();
//            fileWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            FileWriter fw = new FileWriter(file, true);
//            BufferedWriter bw = new BufferedWriter(fw);
//            bw.write("Code,Name,Amount\n");
//            for (String s: sold.keySet()) {
//                for (Snacks snacks: App.store.get_snacks()) {
//                    if (snacks.getName().equals(s)) {
////                        bw.write(snacks.getId());
//                        bw.write("1");
//                        bw.write(",");
//                        bw.write(s);
//                        bw.write(",");
//                        bw.write(Integer.toString(order.get_amount(s)));
//                        bw.write("\n");
//                    }
//                }
//            }
//            bw.flush();
//            bw.close();
//        } catch (IOException | NullPointerException e) {
//            e.printStackTrace();
//        }
//    }
}
