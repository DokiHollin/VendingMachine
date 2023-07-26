package GUI.login_register_anonymous;

import GUI.MainPages.*;
import GUI.Useful_functions.J_constructor;
import Generated.Generated;
import Login.Login;
import Roles.Cashier;
import Roles.Customer;
import Roles.Owner;
import Roles.Roles;
import Roles.Seller;
import Store.Store;
import org.checkerframework.checker.units.qual.C;

import static GUI.Useful_functions.J_constructor.beautiful_ui;
import static GUI.Useful_functions.J_constructor.button;

import javax.swing.*;
import java.awt.*;

@Generated

public class Login_page {
    @Generated
    public static void Login_frame() {
        beautiful_ui();
        JFrame login_frame = J_constructor.frame_constructor("Login", 800, 500);
        JButton cancel_button = button( login_frame,"Cancel", 720, 0, 80, 30);
        cancel_button.addActionListener(e -> {
            Default_page.default_page();
            login_frame.dispose();
        });

        JLabel login_label = J_constructor.label(login_frame, "Login with your existing account", 200, 100, 200, 30);
        JLabel username_label = J_constructor.label(login_frame, "Username", 200, 130, 100, 30);
        JLabel password_label = J_constructor.label(login_frame, "Password", 200, 160, 100, 30);

        JTextField username_text_field = J_constructor.text_field(login_frame, 300, 130, 100, 30);
        JPasswordField password_text_field = J_constructor.password_field(login_frame, 300, 160, 100, 30);


        JLabel show_password = J_constructor.label(login_frame, "", 430, 150, 50, 50);
        show_password.setIcon(new ImageIcon("src/main/resources/show_password.png"));
        JButton login_button = button(login_frame, "Login", 300, 220, 100, 30);
        login_label.setForeground(Color.CYAN);
        username_label.setForeground(Color.CYAN);
        password_label.setForeground(Color.CYAN);

        show_password.addMouseListener(new java.awt.event.MouseAdapter() {
            @Generated
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                password_text_field.setEchoChar((char) 0);
            }
            @Generated
            public void mouseExited(java.awt.event.MouseEvent evt) {
                password_text_field.setEchoChar('*');
            }
        });


        login_button.addActionListener(e -> {
            String username = username_text_field.getText();
            String password_text = String.valueOf(password_text_field.getPassword());

            Login login = new Login("src/main/resources/Account");
            String roles = login.checkLogin(username, password_text);
            try {
                switch (roles) {
                    case "Customer" -> {
                        Customer customer = new Customer(100000000, 1, username, password_text);
                        Customer_page.customer_page(new Store(), customer);
                        login_frame.dispose();
                    }
                    case "Seller" -> {
                        Seller_page.seller_page(new Store());
                        login_frame.dispose();
                    }
                    case "Cashier" -> {
                        Cashier cashier = new Cashier(username, password_text);
                        Cashier_page.cashier_page(cashier);
                        login_frame.dispose();
                    }
                    case "Owner" -> {
                        for (Roles roles1: Roles.roles) {
                            if (roles1.getUsername().equals(username)) {
                                Owner_page.owner_page((Owner) roles1);
                            }
                        }
                        login_frame.dispose();
                    }
                }
            } catch (NullPointerException ee) {
                JOptionPane.showMessageDialog(null, "Wrong username or password");
            }

            // if username or password is empty, pop up a message
            if (username.equals("") || password_text.equals("")){
                JOptionPane.showMessageDialog(login_frame, "Please fill in the username and password");
            }

        });
        JButton register_button = button(login_frame, "Register", 300, 260, 100, 30);
        register_button.addActionListener(e -> {
            Register_page.register_page();
            login_frame.dispose();
        });

        JButton anonymous_button = button(login_frame, "Continue as anonymous", 300, 300, 200, 30);
        anonymous_button.addActionListener(e -> {
            Customer customer = new Customer(100000000, 1, "anonymous", "anonymous");
            Customer_page.customer_page(new Store(), customer);
            login_frame.dispose();
        });




        Default_page.setIkun(login_frame);
        login_frame.setVisible(true);
    }
}
