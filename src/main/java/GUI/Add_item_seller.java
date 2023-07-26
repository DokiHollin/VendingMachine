package GUI;

import App.App;
import GUI.MainPages.Default_page;
import GUI.Useful_functions.J_constructor;
import Generated.Generated;
import Snacks.Snacks;
import Store.Store;

import javax.swing.*;
import java.util.List;
@Generated
public class Add_item_seller {
    public static void add_item_seller(Store store) {
        JFrame add_item_seller = J_constructor.frame_constructor("Add item", 800, 500);
        JButton cancel_button = J_constructor.button( add_item_seller,"Cancel", 720, 0, 80, 30);
        cancel_button.addActionListener(e -> {
            add_item_seller.dispose();
            Default_page.default_page();
        });

        List<Snacks> snacks = store.get_snacks();
        JLabel item_name_label = J_constructor.label(add_item_seller, "Item name", 0, 0, 100, 30);
        JTextField item_name_text_field = J_constructor.text_field(add_item_seller, 100, 0, 100, 30);

        JLabel item_type_label = J_constructor.label(add_item_seller, "Item type", 0, 30, 100, 30);
        JComboBox<String> item_type_list = J_constructor.combo_box(add_item_seller, store.get_item_name(), 100, 30, 100, 30);

        JLabel item_price_label = J_constructor.label(add_item_seller, "Item price", 0, 60, 100, 30);
        JTextField item_price_text_field = J_constructor.text_field(add_item_seller, 100, 60, 100, 30);

        JLabel item_quantity_label = J_constructor.label(add_item_seller, "Item quantity", 0, 90, 100, 30);
        JTextField item_quantity_text_field = J_constructor.text_field(add_item_seller, 100, 90, 100, 30);

        JButton add_item_confirm_button = J_constructor.button(add_item_seller, "Confirm", 0, 150, 100, 30);

        add_item_confirm_button.addActionListener(e -> {
            String name =  item_name_text_field.getText();
            String type = (String) item_type_list.getSelectedItem();
            try {
                double price = Double.parseDouble(item_price_text_field.getText());
                int amount = Integer.parseInt(item_quantity_text_field.getText());
                int id = App.store.store.get(type).size() + 1;
                store.addItem(new Snacks(name, price, amount, id, type));
                store.update();
                JOptionPane.showMessageDialog(add_item_seller, "Item added");
                add_item_seller.dispose();
                Default_page.default_page();
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(add_item_seller, "Invalid input, please try again");
                add_item_seller.dispose();
                Add_item_seller.add_item_seller(store);
            }
        });
//asdfasfasdfasf




        add_item_seller.setVisible(true);

    }
}
