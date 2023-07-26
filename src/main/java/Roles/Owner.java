package Roles;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Owner extends Roles {
    public static List<String[]> tmp = new ArrayList<>();

    public Owner(String username, String password) {
        super(username, password);
        setTmp("src/main/resources/Account");
    }

    public void removeRole(String username,String path) {
        for (Roles role: roles) {
            if (role.getUsername().equals(username)) {
                roles.remove(role);
                tmp.removeIf(tmp1 -> tmp1[0].equals(username));
                update(path);
                return;
            }
        }
    }

    public static int addRole(String username, String password, String type, String path) {
        for (Roles role: roles) {
            if (role.getUsername().equals(username)) {
                return 0;
            }
        }
        switch (type) {
            case "Cashier" -> roles.add(new Cashier(username, password));
            case "Seller" -> roles.add(new Seller(username, password));
            case "Customer" -> roles.add(new Customer(1000000, 1, username, password));
            case "Owner" -> roles.add(new Owner(username, password));
        }
        tmp.add(new String[] {username, password, type});
        update(path);
        return 1;
    }

    public void modifyItem(String name, String type, double price, int amount, int id) {
        Seller.modifyItem(name, type, price, amount, id);
    }

    public static void update(String path) {
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
            for (String[] strings : tmp) {
                bw.write(strings[0]);
                bw.write(",");
                bw.write(strings[1]);
                bw.write(",");
                bw.write(strings[2]);
                bw.write("\n");
            }
            bw.flush();
            bw.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void setTmp(String path) {
        File file = new File(path);
        Scanner scan;
        int count = 0;
        try {
            scan = new Scanner(file);
            while (scan.hasNextLine()) {
                if (count > 0) {
                    String[] detail = scan.nextLine().split(",");
                    tmp.add(detail);
                }
                count++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String[]> getCanceled(String path) {
        File file = new File(path);
        Scanner scan;
        int count = 0;
        List<String []> report = new ArrayList<>();
        try {
            scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String x = scan.nextLine();
                if (count > 0) {
                    String[] detail = x.split(",");
                    report.add(detail);
                }
                count++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return report;
    }

}
