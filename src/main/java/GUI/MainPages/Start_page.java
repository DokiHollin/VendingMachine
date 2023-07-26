package GUI.MainPages;

import GUI.Useful_functions.J_constructor;
import GUI.login_register_anonymous.Login_page;
import GUI.login_register_anonymous.Register_page;
import Generated.Generated;
import Roles.Cashier;
import Roles.Customer;
import Roles.Seller;

import javax.swing.*;

import static GUI.Useful_functions.J_constructor.beautiful_ui;
@Generated
public class Start_page {
    public static void start_page(Seller seller, Cashier cashier, Customer customer) {
        beautiful_ui();
        JFrame start_page = J_constructor.frame_constructor("Start page", 800, 500);
        JButton login_button = J_constructor.button(start_page, "Login", 200, 200, 100, 30);
        start_page.add(login_button);
        JButton register_button = J_constructor.button(start_page, "Register", 350, 200, 100, 30);
        start_page.add(register_button);
        JButton anonymous_button = J_constructor.button(start_page, "Anonymous", 500, 200, 100, 30);
        start_page.add(anonymous_button);

        login_button.addActionListener(e -> {
            start_page.dispose();
            Login_page.Login_frame();
        });

        register_button.addActionListener(e ->{
            start_page.dispose();
            Register_page.register_page();
        });

        anonymous_button.addActionListener(e -> {
            start_page.dispose();
            Default_page.default_page();
        });

        start_page.setVisible(true);

    }
}
