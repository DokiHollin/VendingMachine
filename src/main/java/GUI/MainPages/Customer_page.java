package GUI.MainPages;

import GUI.Useful_functions.J_constructor;
import Generated.Generated;
import Roles.Cashier;
import GUI.Useful_functions.CountDownProgressBar;
import Roles.Customer;
import Roles.Seller;
import ShoppingCart.ShoppingCart;
import Snacks.Snacks;
import Store.Store;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static GUI.Checkout_page.checkout_page;
import static GUI.History_page.history_page;
import static GUI.Useful_functions.J_constructor.beautiful_ui;
import static GUI.Useful_functions.J_constructor.button;
import static GUI.MainPages.Default_page.default_page;
import static RecentFiveItems.Items.show;

@Generated
public class Customer_page {
    public static void customer_page(Store store,   Customer customer) {
        beautiful_ui();
        JFrame customer_page = J_constructor.frame_constructor("Customer page", 830, 520);
        JButton cancel_button = button( customer_page,"Cancel", 720, 0, 80, 30);
        CountDownProgressBar countDownProgressBar = new CountDownProgressBar(customer_page, customer);


        cancel_button.addActionListener(e -> {
            countDownProgressBar.stop();
            customer.cancel("src/main/resources/Cancel", "Customer Cancel");
            customer_page.dispose();
            default_page();
            customer.clearShoppingCart();
        });









        JTable table = J_constructor.table(customer_page, 250, 40, 450, 400,16, 5);
        // set the size of the table
        table.setRowHeight(25);
        // set the first row width
        table.getColumnModel().getColumn(1).setPreferredWidth(150);

        // set the first row of the table clickable


        // set the first row of the table
        table.setValueAt("Id", 0, 0);
        table.setValueAt("Product", 0, 1);
        table.setValueAt("Type", 0, 2);
        table.setValueAt("Price", 0, 3);
        table.setValueAt("Amount", 0, 4);


        // add icon to the frame
//        ImageIcon icon = new ImageIcon("src/main/resources/images/checkout.png");


        JButton drinks = J_constructor.button(customer_page, "Drinks", 0, 0, 100, 30);
        JButton chocolates = J_constructor.button(customer_page, "Chocolates", 100, 0, 100, 30);
        JButton chips = J_constructor.button(customer_page, "Chips", 200, 0, 100, 30);
        JButton candies = J_constructor.button(customer_page, "Candies", 300, 0, 100, 30);



        // add the action listener to the buttons
        List<JButton> buttons = List.of(drinks, chocolates, chips, candies);
        buttons.forEach(button -> button.addActionListener(e -> {
            countDownProgressBar.restart();
            String type = button.getText();
            for(int i = 0; i < 15; i ++){

                if(i < store.get_items(type).size()){
                    if(customer.getShoppingCart().hasName(store.get_items(type).get(i).getName())) {
                        table.setValueAt(store.get_items(type).get(i).getId(), i + 1, 0);
                        table.setValueAt(store.get_items(type).get(i).getName(), i + 1, 1);
                        table.setValueAt(store.get_items(type).get(i).getType(), i + 1, 2);
                        table.setValueAt(store.get_items(type).get(i).getPrice(), i + 1, 3);
                        table.setValueAt(store.get_items(type).get(i).getAmount() - customer.getShoppingCart().get_amount(store.get_items(type).get(i).getName()), i + 1, 4);
                    }else {
                        table.setValueAt(store.get_items(type).get(i).getId(), i + 1, 0);
                        table.setValueAt(store.get_items(type).get(i).getName(), i + 1, 1);
                        table.setValueAt(store.get_items(type).get(i).getType(), i + 1, 2);
                        table.setValueAt(store.get_items(type).get(i).getPrice(), i + 1, 3);
                    table.setValueAt(store.get_items(type).get(i).getAmount(), i + 1, 4);
                    }

                } else{
                    table.setValueAt("", i + 1, 0);
                    table.setValueAt("", i + 1, 1);
                    table.setValueAt("", i + 1, 2);
                    table.setValueAt("", i + 1, 3);
                    table.setValueAt("", i + 1, 4);
                }
            }
        }));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 40, 240, 300);
        customer_page.add(scrollPane);

        JTable cart_table = J_constructor.table(customer_page, 0, 30, 240, 300,1,3);

        cart_table.setValueAt("Product", 0, 0);
        cart_table.setValueAt("Amount", 0, 1);
        cart_table.setValueAt("Price", 0, 2);
        cart_table.setRowHeight(25);
        String[] column_names = {"Product", "Amount", "Price"};
        scrollPane.setViewportView(cart_table);

        JButton add_to_cart = J_constructor.button(customer_page, "Add", 700, 30, 100, 30);
        JButton remove_from_cart = J_constructor.button(customer_page, "Remove", 700, 70, 100, 30);


        List<String> list = new ArrayList<>();
        // add the action listener to the add to cart button
        add_to_cart.addActionListener(e -> {
            countDownProgressBar.restart();
            if(table.getSelectedRow() > 0) {
                int row = table.getSelectedRow();
                if (row == -1 ) {
                    JOptionPane.showMessageDialog(customer_page, "Please select a product");
                    countDownProgressBar.restart();
                }
                else {
                    try {
                        Snacks snacks = store.get_items(table.getValueAt(row, 2).toString()).get(row - 1);
                        String product = (String) table.getValueAt(row, 1);
                        int amount = Integer.parseInt(JOptionPane.showInputDialog("Enter the amount of " + product + " you want to buy"));
                        Double price = (Double) table.getValueAt(row, 3);
                        String type = (String) table.getValueAt(row, 2);

                        if (amount <= 0) {
                            JOptionPane.showMessageDialog(customer_page, "Please enter a valid amount");
                            countDownProgressBar.restart();

                        } else {
                            countDownProgressBar.restart();

                            if (!list.contains(product)) {

                                if (amount > snacks.getAmount()) {
                                    JOptionPane.showMessageDialog(customer_page, "The amount you entered is greater than the amount of the product in the store");
                                    countDownProgressBar.restart();
                                } else {
                                    table.setValueAt(Integer.parseInt(table.getValueAt(row, 4).toString()) - amount, row, 4);
                                    customer.addToShoppingCart(snacks, amount);
                                    list.add(product);
                                    DefaultTableModel model = (DefaultTableModel) cart_table.getModel();
                                    model.addRow(new Object[]{null, null, null});
                                    cart_table.setValueAt(product, cart_table.getRowCount() - 1, 0);
                                    cart_table.setValueAt(amount, cart_table.getRowCount() - 1, 1);
                                    cart_table.setValueAt(price, cart_table.getRowCount() - 1, 2);

                                }

                            } else { // list contain the product TODO: 处理负数
                                int old_amount = (int) cart_table.getValueAt(cart_table.getRowCount() - 1, 1);
                                if (amount > snacks.getAmount()) {
                                    JOptionPane.showMessageDialog(customer_page, "The amount you entered is greater than the amount of the product in the store");
                                    countDownProgressBar.restart();

                                } else if (old_amount + amount > snacks.getAmount()) {
                                    JOptionPane.showMessageDialog(customer_page, "The amount you entered is greater than the amount of the product in the store");
                                    countDownProgressBar.restart();
                                } else {
                                    cart_table.setValueAt(old_amount + amount, cart_table.getRowCount() - 1, 1);
                                    table.setValueAt(Integer.parseInt(table.getValueAt(row, 4).toString()) - customer.getShoppingCart().get_amount(product), row, 4);
                                    customer.addToShoppingCart(snacks, amount);
                                }


                            }
                        }
                    }catch (Exception exception){
                        JOptionPane.showMessageDialog(customer_page, "Please do the selection again");
                        countDownProgressBar.restart();
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(customer_page, "Please select a product");
                countDownProgressBar.restart();
            }
            System.out.println(customer.getShoppingCart());

        });

        remove_from_cart.addActionListener(e -> {
            countDownProgressBar.restart();
            if(cart_table.getSelectedRow() > 0) {
                int row = cart_table.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(customer_page, "Please select a product");
                    countDownProgressBar.restart();
                } else {
                    try {
                        countDownProgressBar.restart();
                        Snacks snacks = store.get_item(cart_table.getValueAt(row, 0).toString());
                        String product = (String) cart_table.getValueAt(row, 0);
                        Double price = (Double) cart_table.getValueAt(row, 2);
                        int amount = (int) cart_table.getValueAt(row, 1);
                        int amount_remove = Integer.parseInt(JOptionPane.showInputDialog("Enter the amount of " + product + " you want to remove"));
                        countDownProgressBar.restart();

                        if (amount_remove > amount) {
                            JOptionPane.showMessageDialog(customer_page, "The amount you entered is greater than the amount of the product in the cart");
                            countDownProgressBar.restart();
                        } else {
                            if (amount - amount_remove == 0) {
                                DefaultTableModel model = (DefaultTableModel) cart_table.getModel();
                                model.removeRow(row);
//                            customer.getShoppingCart().remove(snacks);//TODO
//                            snacks.setAmount(snacks.getAmount() + amount);
//                            table.setValueAt(snacks.getAmount(), row, 4);
                                table.setValueAt(Integer.parseInt(table.getValueAt(row, 4).toString()) + amount_remove, row, 4);
                                customer.getShoppingCart().remove(snacks);
                                list.remove(product);
                                countDownProgressBar.restart();

                            } else {
                                cart_table.setValueAt(amount - amount_remove, row, 1);
//                            snacks.setAmount(snacks.getAmount() + amount_remove);
                                table.setValueAt(Integer.parseInt(table.getValueAt(row, 4).toString()) + amount_remove, row, 4);
                                customer.removeFromShoppingCart(snacks, amount_remove);
                                countDownProgressBar.restart();


                            }
                        }
                    }catch (Exception exception){
                        JOptionPane.showMessageDialog(customer_page, "Please do the selection again");
                        countDownProgressBar.restart();
                    }

                }

            }
            else{
                JOptionPane.showMessageDialog(customer_page, "Please select a product");
                countDownProgressBar.restart();
            }
        });

        JButton remove_cart = J_constructor.button(customer_page, "remove all", 700, 110, 100, 30);
        remove_cart.addActionListener(e -> {
            countDownProgressBar.restart();
            DefaultTableModel model = (DefaultTableModel) cart_table.getModel();
            model.setRowCount(0);
            customer.clearShoppingCart();

            customer_page.dispose();
            customer_page(store, customer);
        });

        // get the products from the cart_table and convert them to a list of snacks
//        List<Snacks> snacks_list = new ArrayList<>();

        ShoppingCart shopping_cart = customer.getShoppingCart();



        JButton checkout = J_constructor.button(customer_page, "checkout", 700, 150, 100, 30);
        checkout.addActionListener(e -> {
            // if the cart is empty, show a message
            if(shopping_cart.isEmpty()){
                JOptionPane.showMessageDialog(customer_page, "Your cart is empty");
                countDownProgressBar.restart();
            }else{
                countDownProgressBar.stop();
                int total_price = 0;
                for(int i = 1; i < cart_table.getRowCount(); i++){
                    total_price += (int) cart_table.getValueAt(i, 1) * (double) cart_table.getValueAt(i, 2);
                }

                customer_page.dispose();
                checkout_page(store, customer);
            }


        });

        JButton history = J_constructor.button(customer_page, "history", 700, 190, 100, 30);
        history.addActionListener(e -> {

            countDownProgressBar.stop();
            customer_page.dispose();
            history_page(store,new Cashier("T","1"), customer);
        });
        JLabel recent_items = J_constructor.label(customer_page, "Recent items", 0, 350, 100, 30);
        JTable five_items = J_constructor.table(customer_page, 0, 380, 240, 100, 5,2);
        List<String[]> five = show(customer.getUsername(),"src/main/resources/TransactionHistory");

        for(int i = 0; i < five.size(); i++){
            five_items.setValueAt(five.get(i)[0], i, 0);
            five_items.setValueAt(five.get(i)[1], i, 1);
        }


        customer_page.setVisible(true);
    }

}