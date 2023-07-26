package GUI;

import GUI.MainPages.Seller_page;
import GUI.Useful_functions.J_constructor;
import Generated.Generated;
import Snacks.Snacks;
import Store.Store;

import javax.swing.*;

import static Roles.Seller.deleteItem;
import static Store.Store.get_item;
@Generated
public class Delete_item_seller {
    public static void delete_item_seller(Store store) {
        JFrame delete_item_seller = J_constructor.frame_constructor("Delete item", 800, 500);
        JLabel item_name = J_constructor.label(delete_item_seller, "Item name", 0, 0, 100, 30);
        JTextField item_name_text = J_constructor.text_field(delete_item_seller, 0, 30, 100, 30);

        JLabel amount = J_constructor.label(delete_item_seller, "Amount", 0, 60, 100, 30);
        JTextField amount_text = J_constructor.text_field(delete_item_seller, 0, 90, 100, 30);

        JButton delete_button = J_constructor.button(delete_item_seller, "Delete", 0, 120, 100, 30);
        delete_button.addActionListener(e -> {
            String name = item_name_text.getText();
            try {
                Snacks snack = get_item(name);

                int amount_of_snack = Integer.parseInt(amount_text.getText());
                deleteItem(snack, amount_of_snack);
                store.update();


                JOptionPane.showMessageDialog(delete_item_seller, "Item deleted");
                delete_item_seller.dispose();
                Seller_page.seller_page(store);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(delete_item_seller, "Item not found or amount isn't a number");
            }
        });
        delete_item_seller.setVisible(true);

    }
}
