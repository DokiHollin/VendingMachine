package GUI.MainPages;

import GUI.Add_item_seller;
import GUI.Delete_item_seller;
import GUI.Seller_history;
import GUI.Useful_functions.J_constructor;
import GUI.Modify_item_seller;
import Generated.Generated;
import Roles.Cashier;
import Roles.Customer;
import Roles.Seller;
import Store.Store;

import javax.swing.*;

import static GUI.Useful_functions.J_constructor.*;
import static GUI.MainPages.Default_page.default_page;
@Generated
public class Seller_page {
    public static void seller_page(Store store) {
        beautiful_ui();
        JFrame seller_page = J_constructor.frame_constructor("Seller page", 800, 500);
        JButton cancel_button = button( seller_page,"Cancel", 720, 0, 80, 30);
        cancel_button.addActionListener(e -> {
            seller_page.dispose();
            default_page();
        });
        JButton modify_item_button = button(seller_page, "modify item", 0, 0, 100, 30);
        modify_item_button.addActionListener(e -> {
            seller_page.dispose();
            Modify_item_seller.modify_item_seller(store);
        });

        JButton add_item_button = button(seller_page, "add item", 0, 50, 100, 30);
        add_item_button.addActionListener(e -> {
            seller_page.dispose();
            Add_item_seller.add_item_seller(store);
        });

        JButton remove_item_button = button(seller_page, "remove item", 0, 100, 100, 30);
        remove_item_button.addActionListener(e -> {
            seller_page.dispose();
            Delete_item_seller.delete_item_seller(store);
        });

        JButton history_button = button(seller_page, "history", 0, 150, 100, 30);
        history_button.addActionListener(e -> {
            seller_page.dispose();
            Seller_history.seller_history(store);
        });



        seller_page.setVisible(true);
    }
}
