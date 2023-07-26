package GUI;

import GUI.MainPages.Owner_page;
import GUI.Useful_functions.J_constructor;
import Generated.Generated;
import Roles.Owner;

import javax.swing.*;
@Generated
public class Remove_roles {
    public static void remove_roles(Owner owner) {
        JFrame remove_roles = J_constructor.frame_constructor("Remove seller", 800, 500);
        JButton cancel_button = J_constructor.button( remove_roles,"Cancel", 720, 0, 80, 30);
        cancel_button.addActionListener(e -> {
            remove_roles.dispose();
            Owner_page.owner_page(owner);
        });

        JLabel roles_name = J_constructor.label(remove_roles, "Roles name", 0, 0, 100, 30);
        JTextField roles_name_text = J_constructor.text_field(remove_roles, 0, 30, 100, 30);

        JButton remove_button = J_constructor.button(remove_roles, "Remove", 0, 60, 100, 30);
        remove_button.addActionListener(e -> {
            String name = roles_name_text.getText();
            owner.removeRole(name,"src/main/resources/Account");
            JOptionPane.showMessageDialog(remove_roles, "Role removed");
            remove_roles.dispose();
            Owner_page.owner_page(owner);
        });
        remove_roles.setVisible(true);
    }
}
