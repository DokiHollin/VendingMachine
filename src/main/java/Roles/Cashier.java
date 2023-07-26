package Roles;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cashier extends Roles{

    static Map<Double, Integer> remainCash = new HashMap<>();
    static double[] index = {100, 50, 20, 10, 5, 2, 1, 0.5, 0.2, 0.1, 0.05};

    public Cashier(String username, String password) {
        super(username, password);
    }

    public static int get_store_total_balance() {
        int total = 0;
        for (int i = 0; i < index.length; i++) {
            total += remainCash.get(index[i]) * index[i];
        }
        return total;
    }

    public Map<Double,Integer> getCash(String path) {
        File file = new File(path);
        Scanner scan;
        int count = 0;
        try {
            scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String tmp = scan.nextLine();
                if (count > 0) {
                    String[] cash = tmp.split(",");
                    remainCash.put(Double.parseDouble(cash[0]), Integer.parseInt(cash[1]));
                }
                count++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return remainCash;
    }


    public Map<Double, Integer> the_change_amount(double amount, double total_price){
        double change = amount - total_price;
        int[] change_count = new int[index.length];
        for (int i = 0; i < index.length; i++) {
            while (change >= index[i]) {
                change -= index[i];
                change_count[i]++;
            }
        }
        Map<Double, Integer> change_amount = new HashMap<>();
        for (int i = 0; i < index.length; i++) {
            change_amount.put(index[i], change_count[i]);
        }
        return change_amount;
    }

    public Map<Double, Integer> change_give_to_customer(Map<Double, Integer> change){
        // return the non-zero change
        Map<Double, Integer> change_give = new HashMap<>();
        for (Double key : change.keySet()) {
            if (change.get(key) != 0) {
                change_give.put(key, change.get(key));
            }
        }
        if (change_give.size() == 0) {
            return null;
        }
        return change_give;
    }

    public Map<Double, Integer> change_amount_to_map(double amount){
        int[] change_count = new int[index.length];
        for (int i = 0; i < index.length; i++) {
            while (amount >= index[i]) {
                amount -= index[i];
                change_count[i]++;
            }
        }
        Map<Double, Integer> change_amount = new HashMap<>();
        for (int i = 0; i < index.length; i++) {
            change_amount.put(index[i], change_count[i]);
        }
        return change_amount;
    }

    public void addCash(Map<Double, Integer> change){
        for (int i = 0; i < index.length; i++) {
            remainCash.put(index[i], remainCash.get(index[i]) + change.get(index[i]));
        }
    }


    public Map<Double, Integer> updateCash(Map<Double, Integer> change_amount,String path){
        Map<Double, Integer> remainCash = getCash(path);
        for (Map.Entry<Double, Integer> entry : change_amount.entrySet()) {
            remainCash.put(entry.getKey(), remainCash.get(entry.getKey()) - entry.getValue());
        }
        update(path);
        return remainCash;
    }

//    public Map<Double, Integer> getRemainCash() {
//        return remainCash;
//    }





    public int modifyCash(double unit, int quantity, String path) {
        if (quantity < 0) {
            return -1;
        }
        remainCash.replace(unit, quantity);
        System.out.println(remainCash);
        update(path);
        return 0;
    }

    public void update(String path) {
        File file = new File(path);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Unit,Quantity\n");
            for (double v : index) {
                bw.write(String.valueOf(v));
                bw.write(",");
                bw.write(String.valueOf(remainCash.get(v)));
                bw.write("\n");
            }
            bw.flush();
            bw.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void setCash(Map<Double,Integer> newOne){
        remainCash = newOne;
    }

}
