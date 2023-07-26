package GUI.Useful_functions;

import GUI.MainPages.Default_page;
import Generated.Generated;
import Order.Order;
import Roles.Customer;
import Snacks.Snacks;
import Store.Store;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static GUI.Useful_functions.Save_card_option.save_card_option;
import static Store.Store.complete_an_order_card;
import static Store.Store.complete_an_order_cash;

@Generated
public class Card_processing_page {
    public static void card_processing_page(Customer customer, double total_price, Order order, String card_number, String card_password) {
        JFrame card_processing_page = J_constructor.frame_constructor("Card processing page", 800, 500);
        CountDownProgressBar countDownProgressBar = new CountDownProgressBar(card_processing_page,customer);

        JLabel order_detail = J_constructor.label(card_processing_page,"Order detail", 50, 50, 200, 50);
        JScrollPane scroll_pane = J_constructor.scroll_pane(card_processing_page, 50, 100, 700, 200);
        JTextPane text_pane = new JTextPane();
        text_pane.setText("Order detail: \n");
        // add the order detail to text_pane
        for(Map.Entry<Snacks, Integer> each: order.getItems().entrySet()){
            text_pane.setText(text_pane.getText() +"Product: "+ each.getKey().getName() + "  amount:  " + each.getValue() + "  price:  " + each.getKey().getPrice() +"\n" );
        }
        text_pane.setText(text_pane.getText() + "Total price: " + total_price);
        JButton pay = J_constructor.button(card_processing_page,"Pay", 50, 350, 100, 50);
        JButton cancel = J_constructor.button(card_processing_page,"Cancel", 200, 350, 100, 50);

        pay.addActionListener(e -> {
            countDownProgressBar.stop();
            complete_an_order_card(new Store(), customer, total_price);
            JOptionPane.showMessageDialog(null, "Payment successful!");
            card_processing_page.dispose();
            customer.setBalance(customer.getBalance() - total_price);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            File file = new File("src/main/resources/TransactionHistory");
            try{
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);
                for(Map.Entry<Snacks, Integer> each: order.getItems().entrySet()) {
                    bw.write(dtf.format(now));
                    bw.write("!");
                    bw.write(each.getValue()+ " " + each.getKey().getName());
                    bw.write("!");
                    bw.write(each.getValue() * each.getKey().getPrice() + "");
                    bw.write("!");
                    bw.write("null");
                    bw.write("!");
                    bw.write("Card");
                    bw.write("!");
                    bw.write(customer.getUsername());
                    bw.write("!");
                    bw.write(each.getKey().getId()+"");
                    bw.write("\n");


                }
                bw.flush();
                bw.close();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            if(customer.getUsername().equals("anonymous")){
                Default_page.default_page();
            }else{
                save_card_option(customer, card_number, card_password);

            }
        });

        cancel.addActionListener(e ->{
            customer.cancel("src/main/resources/Cancel", "Customer Cancelled");
            customer.clearShoppingCart();
            countDownProgressBar.stop();
            card_processing_page.dispose();
            Default_page.default_page();
        });


        scroll_pane.setViewportView(text_pane);
        card_processing_page.setVisible(true);
    }
}
