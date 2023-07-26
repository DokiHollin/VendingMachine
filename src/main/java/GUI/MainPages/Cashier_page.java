package GUI.MainPages;

import GUI.Useful_functions.J_constructor;
import GUI.Modify_cash_page;
import GUI.Useful_functions.Remaining_cash;
import GUI.ShowTransactionHistory;
import Generated.Generated;
import Roles.Cashier;
import Roles.Customer;
import Roles.Seller;

import javax.swing.*;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import static GUI.Useful_functions.J_constructor.beautiful_ui;
import static GUI.Useful_functions.J_constructor.button;
import static GUI.MainPages.Default_page.default_page;
@Generated
public class Cashier_page {
    public static void cashier_page( Cashier cashier) {
        beautiful_ui();
        JFrame cashier_page = J_constructor.frame_constructor("Cashier_page", 800, 500);
        JButton cancel_button = button( cashier_page,"Cancel", 720, 0, 80, 30);
        cancel_button.addActionListener(e -> {
            cashier_page.dispose();
            default_page();
        });
        JButton show_cash = button(cashier_page,"Show cash", 100, 100, 200, 50);
        show_cash.addActionListener(e -> {
            Map<Double,Integer> map = sortByKey(cashier.getCash("src/main/resources/RemainCash"));
            Remaining_cash.remaining_cash(cashier_page,map);
        });


        // modify cash function

        JButton modify_cash = button(cashier_page,"Modify cash", 100, 200, 200, 50);
        modify_cash.addActionListener(e1 -> {
            cashier_page.dispose();
            Modify_cash_page.modify_cash(cashier);
        });

        JButton show_history = button(cashier_page,"Show Transaction History", 500, 100, 200, 50);
        show_history.addActionListener(e -> {
            ShowTransactionHistory.show(cashier_page);

        });


        cashier_page.setVisible(true);
    }

    // 将map 按照 key 从大到小 排序
    public static Map<Double, Integer> sortByKey(Map<Double, Integer> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<Double, Integer> sortedMap = new TreeMap<>(Comparator.reverseOrder());
        sortedMap.putAll(map);
        return sortedMap;
    }
}
