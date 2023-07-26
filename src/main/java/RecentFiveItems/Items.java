package RecentFiveItems;

import java.io.*;
import java.util.*;

public class Items {

    public static List<String[]> show(String username,String path) {
        File file = new File(path);
        List<String[]> report = new ArrayList<>();
        Scanner scanner;
        List<String[]> xx = new ArrayList<>();
        int count = 0;
        try {
            scanner = new Scanner(file);
            String tmp = null;
            while (scanner.hasNextLine()) {
                tmp = scanner.nextLine();
                String[] x = tmp.split("!");
                String amount = x[1].split(" ")[0];
                String item = x[1].split(" ", 2)[1];
                String name = x[5];
                if (username.equals(name)) {
                    xx.add(new String[] {amount, item});
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (int i = xx.size() - 1; i > 0; i--) {
            if (count < 5) {
                report.add(xx.get(i));
            }
            count++;
        }
        return report;
    }
}
