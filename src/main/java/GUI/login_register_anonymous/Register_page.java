package GUI.login_register_anonymous;

import GUI.MainPages.Customer_page;
import GUI.MainPages.Default_page;
import GUI.Useful_functions.J_constructor;

import GUI.MainPages.Start_page;
import Generated.Generated;
import Roles.Cashier;
import Roles.Customer;
import Roles.Owner;
import Store.Store;

import javax.swing.*;

import static GUI.Useful_functions.J_constructor.button;
import static Roles.Owner.addRole;

@Generated
public class Register_page {
    @Generated
    public static void register_page(){
        JFrame register_page = J_constructor.frame_constructor("Register page", 800, 500);
        JButton cancel_button = button( register_page,"Cancel", 720, 0, 80, 30);
        cancel_button.addActionListener(e -> {
            register_page.dispose();
            Default_page.default_page();
        });

        JLabel username_label = J_constructor.label(register_page, "Username", 200, 100, 100, 30);
        JLabel password_label = J_constructor.label(register_page, "Password", 200, 150, 100, 30);
        JTextField username_text_field = J_constructor.text_field(register_page, 300, 100, 100, 30);
        JTextField password_text_field = J_constructor.text_field(register_page, 300, 150, 100, 30);
        JButton register_button = button(register_page, "Register", 300, 250, 100, 30);
        register_button.addActionListener(e -> {
            String username = username_text_field.getText();
            String password_text = String.valueOf(password_text_field.getText());
            // if username or password is empty, pop up a message
            if (username.equals("") || password_text.equals("")){
                JOptionPane.showMessageDialog(register_page, "Please fill in the username and password");
            }else{
                try {
                    if (addRole( username,password_text , "Customer","src/main/resources/Account") == 1) {
                        JOptionPane.showMessageDialog(register_page, "Register successfully");
                    } else {
                        JOptionPane.showMessageDialog(register_page, "The username has been exist");
                    }
                    Customer_page.customer_page( new Store(), new Customer(100000000, 1, username, password_text));
                }catch (Exception exception){
                    JOptionPane.showMessageDialog(register_page, "Please fill in the money with number");
                }
            }

        });



        register_page.setVisible(true);
    }
}
