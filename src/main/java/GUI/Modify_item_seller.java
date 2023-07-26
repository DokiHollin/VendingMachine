package GUI;

import GUI.MainPages.Default_page;
import GUI.Useful_functions.J_constructor;
import Generated.Generated;
import Roles.Cashier;
import Roles.Customer;
import Roles.Seller;
import Snacks.Snacks;
import Store.Store;

import javax.swing.*;

import java.util.List;

import static GUI.Useful_functions.J_constructor.beautiful_ui;
import static GUI.Useful_functions.J_constructor.button;
import static GUI.MainPages.Default_page.default_page;
@Generated
public class Modify_item_seller {
    public static void modify_item_seller(Store store) {
        beautiful_ui();
        JFrame modify_item_seller = J_constructor.frame_constructor("Modify item", 800, 500);
        JButton cancel_button = button( modify_item_seller,"Cancel", 720, 0, 80, 30);
        cancel_button.addActionListener(e -> {
            modify_item_seller.dispose();
            default_page();
        });

        List<Snacks> snacks = store.get_snacks();



        JLabel item_name_label = J_constructor.label(modify_item_seller, "Item name", 0, 0, 200, 30);
//        JComboBox<String> item_name_list = J_constructor.combo_box(modify_item_seller, , 100, 0, 100, 30);
        JComboBox<String> item_name_list = new JComboBox<>();
        for (Snacks snack : snacks) {
            item_name_list.addItem(snack.getName());
        }

        item_name_list.setBounds(100, 0, 100, 30);
        modify_item_seller.add(item_name_list);


        JLabel item_type_label = J_constructor.label(modify_item_seller, "Item type", 0, 30, 100, 30);
        JComboBox<String> item_type_list = J_constructor.combo_box(modify_item_seller, store.get_item_name(), 100, 30, 200, 30);

        JLabel item_price_label = J_constructor.label(modify_item_seller, "Item price", 0, 60, 100, 30);
        JTextField item_price_text_field = J_constructor.text_field(modify_item_seller, 100, 60, 200, 30);

        JLabel item_quantity_label = J_constructor.label(modify_item_seller, "Item quantity", 0, 90, 100, 30);
        JTextField item_quantity_text_field = J_constructor.text_field(modify_item_seller, 100, 90, 200, 30);

        JLabel id_label = J_constructor.label(modify_item_seller, "ID", 0, 120, 100, 30);
        JTextField id_text_field = J_constructor.text_field(modify_item_seller, 100, 120, 200, 30);



        JButton modify_item_confirm_button = button(modify_item_seller, "Confirm", 0, 150, 100, 30);


        modify_item_confirm_button.addActionListener(e -> {
            if(item_name_list.getSelectedItem() == null){
                JOptionPane.showMessageDialog(null, "Please select an item");
            }else if(item_price_text_field.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the price");
            }else if(item_quantity_text_field.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the quantity");
            }else if(item_type_list.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Please select an item type");
            }
            else{
                try{
                    String item_name = (String) item_name_list.getSelectedItem();
                    double price = Double.parseDouble(item_price_text_field.getText());
                    int quantity = Integer.parseInt(item_quantity_text_field.getText());
                    String item_type = (String) item_type_list.getSelectedItem();
                    int id = Integer.parseInt(id_text_field.getText());
                    if (Seller.modifyItem(item_name, item_type, price, quantity, id) == 1) {
                        JOptionPane.showMessageDialog(null, "Modify success");
                        modify_item_seller.dispose();
                        Default_page.default_page();
                    } else if (Seller.modifyItem(item_name, item_type, price, quantity, id) == 0) {
                        JOptionPane.showMessageDialog(null, "The amount of item out of the limit");
                    } else if (Seller.modifyItem(item_name, item_type, price, quantity, id) == -2) {
                        JOptionPane.showMessageDialog(null, "Please enter a positive amount");
                    } else if (Seller.modifyItem(item_name, item_type, price, quantity, id) == -3) {
                        JOptionPane.showMessageDialog(null, "Please enter a positive price");
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid id");
                    }
                    store.update();




                }catch (Exception exception){
                    JOptionPane.showMessageDialog(null, "Please enter a valid number");
                }

            }


        });

        modify_item_seller.setVisible(true);


    }
}
