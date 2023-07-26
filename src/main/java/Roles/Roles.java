package Roles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Roles {
    public static List<Roles> roles = new ArrayList<>();
    private final String username;
    private final String password;

    public Roles(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static void setupRoles(String path) {
        File file = new File(path);
        Scanner scan;
        int count = 0;
        try {
            scan = new Scanner(file);
            while (scan.hasNextLine()) {
                if (count > 0) {
                    String[] tmp = scan.nextLine().split(",");
                    switch (tmp[2]) {
                        case "Cashier" -> roles.add(new Cashier(tmp[0], tmp[1]));
                        case "Seller" -> roles.add(new Seller(tmp[0], tmp[1]));
                        case "Customer" -> roles.add(new Customer(1000000, 1, tmp[0], tmp[1]));
                        case "Owner" -> roles.add(new Owner(tmp[0], tmp[1]));
                    }
                }
                count++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
