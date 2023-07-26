package GUI;

import GUI.Useful_functions.CountDownProgressBar;
import GUI.Useful_functions.J_constructor;
import Generated.Generated;
import Roles.Cashier;
import Roles.Customer;
import Roles.Seller;
import Snacks.Snacks;
import Store.Store;

import javax.swing.*;

import static GUI.MainPages.Customer_page.customer_page;
import static GUI.MainPages.Default_page.default_page;
@Generated
public class History_page {
    public static void history_page(Store store,Cashier cashier,   Customer customer){
        JFrame history_page = J_constructor.frame_constructor("History", 800, 500);
        JButton cancel_button = J_constructor.button(history_page, "Cancel", 720, 0, 80, 30);
        cancel_button.addActionListener(e -> {
            history_page.dispose();
            default_page();
        });
        CountDownProgressBar countDownProgressBar = new CountDownProgressBar(history_page, customer);


        JComboBox<String> order_list = new JComboBox<>();
        order_list.setBounds(0, 0, 200, 30);
        history_page.add(order_list);
        for(int i = 0; i < customer.orders.size(); i ++){
            order_list.addItem(customer.orders.get(i).get_index());
        }
        JTextArea text_area = J_constructor.text_area(history_page, 50, 50, 700, 360);
        JButton button = J_constructor.button(history_page, "show", 220, 0, 100, 30);
        button.addActionListener(e -> {
            // if order_list.getSelectedItem() == null, pop up a window to tell the customer to select an order or create a new order
            if(order_list.getSelectedItem() == null){
                JOptionPane.showMessageDialog(null, "Please select an order or create a new order");
                customer_page(store, customer);
            }
            else {

                // get the keyset of the order
                countDownProgressBar.restart();


                text_area.setText("");
                text_area.append("Order " + customer.orders.get(Integer.parseInt((String) order_list.getSelectedItem()) - 1).get_index() + ":\n");

                for (int i = 0; i < customer.orders.size(); i++) {
                    if (customer.orders.get(i).get_index().equals(order_list.getSelectedItem())) {
                        for (Snacks snacks : customer.orders.get(i).getItems().keySet()) {
                            text_area.append(snacks.getName() + "   |   amount : " + customer.orders.get(i).getItems().get(snacks) + "   |   each price is: " + snacks.getPrice() + "\n");
                        }
                        text_area.append("Total price: " + customer.orders.get(i).get_total_price());
                    }
                }
            }


        });




        history_page.setVisible(true);
    }
}
