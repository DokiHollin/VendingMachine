package Login;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Login {
    String path;
    public Login(String path) {
        this.path = path;
    }

    public String checkLogin(String username, String password) {
        File file = new File(this.path);
        Scanner scanner;
        int count = 0;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String tmp = scanner.nextLine();
                if (count > 0) {
                    String[] detail = tmp.split(",");
                    if (username.equals(detail[0]) && password.equals(detail[1])) {
                        return detail[2];
                    }
                }
                count++;
            }
        } catch (FileNotFoundException e) {
            return null;
        }
        return null;
    }
}
