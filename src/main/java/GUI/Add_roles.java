package GUI;

import GUI.MainPages.Default_page;
import GUI.Useful_functions.J_constructor;
import Generated.Generated;
import Roles.Owner;

import javax.swing.*;

import static Roles.Owner.addRole;
@Generated
public class Add_roles {
    public static void add_roles(Owner owner) {
        JFrame add_roles = J_constructor.frame_constructor("Add seller", 800, 500);
        JButton cancel_button = J_constructor.button( add_roles,"Cancel", 720, 0, 80, 30);
        cancel_button.addActionListener(e -> {
            add_roles.dispose();
            Default_page.default_page();
        });
        JLabel name_label = J_constructor.label(add_roles, "Name", 0, 0, 100, 30);
        JTextField name_text_field = J_constructor.text_field(add_roles, 100, 0, 100, 30);
        JLabel user_password_label = J_constructor.label(add_roles, "Password", 0, 30, 100, 30);
        JTextField user_password_text_field = J_constructor.text_field(add_roles, 100, 30, 100, 30);
        JLabel type_label = J_constructor.label(add_roles, "Type", 0, 60, 100, 30);
        JTextField type_text_field = J_constructor.text_field(add_roles, 100, 60, 100, 30);
        JButton add_button = J_constructor.button(add_roles, "Add", 0, 90, 100, 30);
        add_button.addActionListener(e -> {
            String name = name_text_field.getText();
            String password = user_password_text_field.getText();
            String roles = type_text_field.getText();
            roles = roles.toLowerCase();
            // upper the first letter
            roles = roles.substring(0, 1).toUpperCase() + roles.substring(1);

            if(name.equals("") || password.equals("") || roles.equals("")) {
                JOptionPane.showMessageDialog(add_roles, "Please fill all fields");
            }
            if(roles.equals("Seller") || roles.equals("Customer") || roles.equals("Cashier")){
                addRole(name, password, roles,"src/main/resources/Account");
                JOptionPane.showMessageDialog(add_roles, "Role added");
                add_roles.dispose();
                Default_page.default_page();
            } else {
                JOptionPane.showMessageDialog(add_roles, "No role type available");
                add_roles.dispose();
                //reopen the frame
                Add_roles.add_roles(owner);
            }

        });

        add_roles.setVisible(true);




    }
}
