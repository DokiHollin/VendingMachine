package GUI;


import Card.GetCardInfo;
import GUI.Useful_functions.Card_processing_page;
import GUI.Useful_functions.CountDownProgressBar;
import GUI.Useful_functions.J_constructor;
import Generated.Generated;
import Order.Order;
import Roles.Cashier;
import Roles.Customer;
import Roles.Seller;
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

import static App.App.cardInfo;
import static GUI.Useful_functions.J_constructor.button;
import static GUI.MainPages.Default_page.default_page;
@Generated
public class Card_page {
    public static void card_page(Store store,  Customer customer, double total_price, Order order, String Name, String password){
        JFrame card_page = J_constructor.frame_constructor("Card", 800, 500);
        CountDownProgressBar countDownProgressBar = new CountDownProgressBar(card_page, customer);

        JButton cancel_button = button( card_page,"Cancel", 720, 0, 80, 30);
        cancel_button.addActionListener(e -> {
            countDownProgressBar.stop();
            customer.clearShoppingCart();
            card_page.dispose();
            default_page();
        });

        JLabel card_number_label = J_constructor.label(card_page, "Card number", 100, 100, 100, 30);
        JTextField card_number_field = J_constructor.text_field(card_page, 200, 100, 100, 30);
        JLabel card_password_label = J_constructor.label(card_page, "Card password", 100, 150, 100, 30);
        JPasswordField card_password_field = J_constructor.password_field(card_page, 200, 150, 100, 30);
        if (Name != null) {
            card_number_field.setText(Name);
            card_password_field.setText(password);
        }

        JLabel show_password = J_constructor.label(card_page, "", 330, 150, 50, 50);
        show_password.setIcon(new ImageIcon("src/main/resources/show_password.png"));

        show_password.addMouseListener(new java.awt.event.MouseAdapter() {
            @Generated
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card_password_field.setEchoChar((char) 0);
            }
            @Generated
            public void mouseExited(java.awt.event.MouseEvent evt) {
                card_password_field.setEchoChar('*');
            }
        });
        JButton login_button = J_constructor.button(card_page, "Login", 100, 200, 100, 30);
        login_button.addActionListener(e -> {
            countDownProgressBar.restart();
            String card_number = card_number_field.getText();
            String card_password = card_password_field.getText();


            if (card_number_field.getText().equals("") || card_password_field.getText().equals("")) {
                JOptionPane.showMessageDialog(card_page, "Please enter your card number and password");
                countDownProgressBar.restart();
            }else{
                // check cardInfo contains the card number and password
                if( customer.checkCard(card_number, card_password)) {
                    System.out.println("Card number and password are correct");

                    if(total_price > customer.getBalance()) {
                        JOptionPane.showMessageDialog(card_page, "Your balance is not enough");
                        countDownProgressBar.restart();
                    }else {
                        countDownProgressBar.stop();

//                        Seller.addAndUpdate(order, "src/main/resources/SellerReport");
                        card_page.dispose();
                        Card_processing_page.card_processing_page(customer, total_price, order, card_number, card_password);
                    }




                }else{
                    JOptionPane.showMessageDialog(card_page, "Card number or password is incorrect");
                    countDownProgressBar.restart();
                }


            }
        });







        card_page.setVisible(true);
    }

}
