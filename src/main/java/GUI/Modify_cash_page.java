package GUI;
import GUI.Useful_functions.J_constructor;
import Generated.Generated;
import Roles.Cashier;
import Roles.Customer;
import Roles.Seller;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Map;

import static GUI.Useful_functions.J_constructor.button;
import static GUI.MainPages.Cashier_page.cashier_page;
import static GUI.MainPages.Cashier_page.sortByKey;
import static GUI.MainPages.Default_page.default_page;
@Generated
public class Modify_cash_page {
    public static void modify_cash(Cashier cashier) {
        JFrame modify_cash = J_constructor.frame_constructor("Modify cash", 800, 500);
        JButton cancel_button = button( modify_cash,"Cancel", 720, 0, 80, 30);
        cancel_button.addActionListener(e -> {
            modify_cash.dispose();
            default_page();
        });        Map<Double, Integer> unit = sortByKey(cashier.getCash("src/main/resources/RemainCash"));
        // get the keys of the map and put it into an array
        ArrayList<Double> keys = new ArrayList<>(unit.keySet());
        JLabel unit_label = J_constructor.label(modify_cash, "Which unit do you want to change", 100, 100, 200, 50);
        JComboBox<Double> unit_box = new JComboBox<>();
        for (Double key : keys) {
            unit_box.addItem(key);
        }
        unit_box.setBounds(300, 110, 100, 30);
        modify_cash.add(unit_box);

        JLabel quantity_label = J_constructor.label(modify_cash, "Quantity you want to set", 100, 200, 200, 50);
        JTextField quantity_field = J_constructor.text_field(modify_cash, 300, 210, 100, 30);

        JButton confirm = button(modify_cash, "Confirm", 100, 300, 200, 50);
        confirm.addActionListener(e -> {
            double unit_value = (double) unit_box.getSelectedItem();
            int quantity = Integer.parseInt(quantity_field.getText());
            if (cashier.modifyCash(unit_value, quantity,"src/main/resources/RemainCash") == -1) {
                JOptionPane.showMessageDialog(null, " the amount can't smaller than 0");
            } else {
                JOptionPane.showMessageDialog(modify_cash, "Modify successfully");
                modify_cash.dispose();
                cashier_page(cashier);
            }

        });


        modify_cash.setVisible(true);

    }
}
