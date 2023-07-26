package GUI.MainPages;

import GUI.Add_roles;
import GUI.Modify_item_seller;
import GUI.Remove_roles;
import GUI.Report_page;
import GUI.Useful_functions.J_constructor;
import Generated.Generated;
import Roles.Owner;
import Store.Store;

import javax.swing.*;

import static GUI.Useful_functions.J_constructor.beautiful_ui;
import static GUI.Useful_functions.J_constructor.button;
import static GUI.MainPages.Default_page.default_page;
@Generated
public class Owner_page {
    public static void owner_page(Owner owner) {
        beautiful_ui();
        JFrame owner_page = J_constructor.frame_constructor("Owner page", 800, 500);
        JButton cancel_button = button( owner_page,"Cancel", 720, 0, 80, 30);
        cancel_button.addActionListener(e -> {
            owner_page.dispose();
            default_page();
        });
        JButton add_roles_button = button(owner_page, "Add Role", 0, 0, 150, 30);
        JButton remove_roles_button = button(owner_page, "Remove Role", 0, 30, 150, 30);
        add_roles_button.addActionListener(e -> {
            owner_page.dispose();
            Add_roles.add_roles(owner);
        });

        remove_roles_button.addActionListener(e -> {
            owner_page.dispose();
            Remove_roles.remove_roles(owner);
        });

        JButton report_button = J_constructor.button(owner_page, "Report", 0, 60, 150, 30);
        report_button.addActionListener(e -> {
            owner_page.dispose();
            Report_page.report_page(owner);
        });

        JButton modify_button = J_constructor.button( owner_page,"Modify", 0, 90,150,30);
        modify_button.addActionListener(e -> {
            owner_page.dispose();
            Modify_item_seller.modify_item_seller(new Store());
        });











        owner_page.setVisible(true);
    }
}

