package GUI;

import GUI.Useful_functions.Card_processing_page;
import GUI.Useful_functions.CountDownProgressBar;
import GUI.Useful_functions.J_constructor;
import Generated.Generated;
import Order.Order;
import Roles.Cashier;
import Roles.Customer;
import Roles.Seller;
import Store.Store;

import javax.swing.*;

import static GUI.Useful_functions.J_constructor.button;
import static GUI.MainPages.Default_page.default_page;
@Generated
public class Checkout_page {
    public static void checkout_page(Store store, Customer customer) {
        System.out.println(customer.getUsername());
        JFrame checkout_page = J_constructor.frame_constructor("Checkout", 800, 500);
        CountDownProgressBar countDownProgressBar = new CountDownProgressBar(checkout_page, customer);

        JButton cancel_button = button( checkout_page,"Cancel", 720, 0, 80, 30);
        cancel_button.addActionListener(e -> {
            countDownProgressBar.stop();
            checkout_page.dispose();
            default_page();
            customer.clearShoppingCart();
        });
        JLabel label = J_constructor.label(checkout_page,"Shopping cart", 50, 50, 200, 50);
        JTextPane text_pane = new JTextPane();
        text_pane.setBounds(50, 100, 700, 200);
        text_pane.setEditable(false);

        JScrollPane scroll_pane = new JScrollPane(text_pane);
        scroll_pane.setBounds(50, 100, 700, 200);
        checkout_page.add(scroll_pane);

        for(int i = 0; i < customer.shoppingCart.cart.size(); i++){
            text_pane.setText(text_pane.getText() + customer.shoppingCart.getSnacks(i).getName()+" price is: "+customer.shoppingCart.getSnacks(i).getPrice() +" amount is: "+customer.shoppingCart.getAmount(i)+"\n");
        }

        Order order = new Order(customer, customer.shoppingCart.cart);
//        customer.createOrder();
        System.out.println(order.get_total_price());

//        System.out.println(customer.orders.get(customer.orders.size() - 1).getItems());


        double total_price = (double) Math.round(order.get_total_price() * 100) / 100;

        text_pane.setText(text_pane.getText() + "Total price is: $" + total_price);

        JLabel label1 = J_constructor.label(checkout_page,"Payment method", 50, 350, 150, 50);
        JButton cash = button(checkout_page,"Cash", 50, 400, 100, 50);
        JButton card = button(checkout_page,"Card", 200, 400, 100, 50);
        cash.addActionListener(e -> {
            countDownProgressBar.stop();
            checkout_page.dispose();
            Cash_page.cash_page(store, new Cashier("T", "1"), customer, total_price,order);
        });
        card.addActionListener(e -> {
            countDownProgressBar.stop();
            checkout_page.dispose();
            String [] info = customer.getCardInfo("src/main/resources/CustomerInfo");
            if (info != null) {
//                Card_processing_page.card_processing_page(customer, total_price, order);
                Card_page.card_page(store, customer, total_price, order, info[0], info[1]);
            } else {
                Card_page.card_page(store, customer, total_price, order, null, null);

            }

        });





        checkout_page.setVisible(true);
    }
}
