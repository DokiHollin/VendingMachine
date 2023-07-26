package GUI.MainPages;

import GUI.Useful_functions.J_constructor;
import GUI.login_register_anonymous.Login_page;
import Generated.Generated;
import Roles.Cashier;
import Roles.Customer;
import Roles.Seller;
import Store.Store;

import javax.swing.*;

import java.awt.*;
import java.net.URL;

import static App.App.store;
import static GUI.Useful_functions.J_constructor.beautiful_ui;

@Generated
public class Default_page {
    public static void default_page() {
        beautiful_ui();
        JFrame default_page = J_constructor.frame_constructor("Default page", 800, 500);

        JButton login_button = J_constructor.button(default_page,"LOGIN", 0, 0, 100, 50);
        login_button.addActionListener(e -> {
            Login_page.Login_frame();
            default_page.dispose();
        });


        JScrollPane drinks_scrollPane = J_constructor.scroll_pane(default_page, 140, 0, 300 , 200);
        JTable drinks_table = J_constructor.table(default_page, 200 + 130 , 0, 100 , 100,15,2);
        drinks_table.getColumnModel().getColumn(0).setHeaderValue("Drinks");
        drinks_table.getColumnModel().getColumn(1).setHeaderValue("Price");
        drinks_scrollPane.setViewportView(drinks_table);
        for(int i = 0; i < store.store.get(Store.items[0]).size(); i++){
            drinks_table.setValueAt(store.store.get(Store.items[0]).get(i).getName(), i , 0);
            drinks_table.setValueAt(store.store.get(Store.items[0]).get(i).getPrice(), i , 1);
        }


        JScrollPane chocolate_scrollPane = J_constructor.scroll_pane(default_page, 460, 0, 300 , 200);
        JTable chocolate_table = J_constructor.table(default_page, 200 + 130 , 0, 100 , 100,15,2);
        chocolate_table.getColumnModel().getColumn(0).setHeaderValue("Chocolates");
        chocolate_table.getColumnModel().getColumn(1).setHeaderValue("Price");
        chocolate_scrollPane.setViewportView(chocolate_table);
        for(int i = 0; i < store.store.get(Store.items[1]).size(); i++){
            chocolate_table.setValueAt(store.store.get(Store.items[1]).get(i).getName(), i , 0);
            chocolate_table.setValueAt(store.store.get(Store.items[1]).get(i).getPrice(), i, 1);
        }

        JScrollPane chips_scrollPane = J_constructor.scroll_pane(default_page, 140, 230, 300 , 200);
        JTable chips_table = J_constructor.table(default_page, 200 + 130 , 0, 100 , 100,15,2);
        chips_table.getColumnModel().getColumn(0).setHeaderValue("Chips");
        chips_table.getColumnModel().getColumn(1).setHeaderValue("Price");
        chips_scrollPane.setViewportView(chips_table);
        for(int i = 0; i < store.store.get(Store.items[2]).size(); i++){
            chips_table.setValueAt(store.store.get(Store.items[2]).get(i).getName(), i , 0);
            chips_table.setValueAt(store.store.get(Store.items[2]).get(i).getPrice(), i, 1);
        }

        JScrollPane candy_scrollPane = J_constructor.scroll_pane(default_page, 460, 230, 300 , 200);
        JTable candy_table = J_constructor.table(default_page, 200 + 130 , 0, 100 , 100,15,2);
        candy_table.getColumnModel().getColumn(0).setHeaderValue("Candy");
        candy_table.getColumnModel().getColumn(1).setHeaderValue("Price");
        candy_scrollPane.setViewportView(candy_table);
        for(int i = 0; i < store.store.get(Store.items[3]).size(); i++){
            candy_table.setValueAt(store.store.get(Store.items[3]).get(i).getName(), i , 0);
            candy_table.setValueAt(store.store.get(Store.items[3]).get(i).getPrice(), i, 1);
        }
        setIkun(default_page);
        default_page.setVisible(true);


    }

    public static void setIkun(JFrame jFrame) {
        ImageIcon icon = new ImageIcon("src/main/resources/ikun.gif");
        Image img = icon.getImage();
        Image tmp = img.getScaledInstance(800, 500, Image.SCALE_DEFAULT);
        icon = new ImageIcon(tmp);

        JLabel b = new JLabel(icon);
        b.setLayout(null);
        b.setBounds(0, 0, 800, 500);
        jFrame.getContentPane().add(b);
    }

}
