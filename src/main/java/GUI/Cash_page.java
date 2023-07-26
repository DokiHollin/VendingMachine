package GUI;

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
import java.util.HashMap;
import java.util.Map;

import static GUI.Useful_functions.J_constructor.button;
import static GUI.MainPages.Default_page.default_page;
import static Roles.Cashier.get_store_total_balance;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
@Generated
public class Cash_page {
    public static void cash_page(Store store,  Cashier cashier, Customer customer, double total_price, Order order) {
        JFrame cash_page = J_constructor.frame_constructor("Cash", 800, 500);
        CountDownProgressBar countDownProgressBar = new CountDownProgressBar(cash_page, customer);

        JButton cancel_button = button( cash_page,"Cancel", 720, 0, 80, 30);
        cancel_button.addActionListener(e -> {
            countDownProgressBar.stop();
            customer.clearShoppingCart();
            customer.cancel("src/main/resources/Cancel","Customer Cancelled");
            cash_page.dispose();
            default_page();
        });


        JLabel label = J_constructor.label(cash_page,"amount to pay: ", 50, 50, 200, 50);
        JTextField text_field = J_constructor.text_field(cash_page, 50, 100, 200, 50);
        JButton button = J_constructor.button(cash_page, "Confirm", 50, 200, 100, 50);
        button.addActionListener(e -> {
            countDownProgressBar.restart();
            if(Double.parseDouble(text_field.getText()) > customer.getBalance()){
                JOptionPane.showMessageDialog(cash_page,"You don't have enough money");
                countDownProgressBar.restart();

            }
            else if (text_field.getText().equals("")) {
                JOptionPane.showMessageDialog(cash_page, "Please enter the amount of money you want to pay");
                countDownProgressBar.restart();

            }else {
                // if the amount of money is not enough, then error
                if (Double.parseDouble(text_field.getText()) < total_price) {
                    JOptionPane.showMessageDialog(cash_page, "The amount of money you entered is not enough");
                    countDownProgressBar.restart();
                } else {
                // if the amount of money is enough, then go on
                    countDownProgressBar.restart();

                    double amount = Double.parseDouble(text_field.getText());
                    cashier.getCash("src/main/resources/RemainCash");

                    int total_balance = get_store_total_balance();
                    if(amount > total_balance){
                        JOptionPane.showMessageDialog(cash_page, "The store doesn't have enough money");
                        customer.cancel("src/main/resources/Cancel", "CHANGE NOT ENOUGH");
                        countDownProgressBar.stop();
                        cash_page.dispose();
                        default_page();
                        return;
                    }
                    Map<Double, Integer> change = cashier.the_change_amount(amount, total_price);


                    System.out.println("the amount of change is: "+ change);
                    System.out.println("update cash: "+cashier.updateCash(change,"src/main/resources/RemainCash"));

                    //try to write the whole transaction detail into the file
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    File file = new File("src/main/resources/TransactionHistory");
                    try {
                        FileWriter fw = new FileWriter(file, true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        for(Map.Entry<Snacks, Integer> each: order.getItems().entrySet()) {
                            bw.write(dtf.format(now));
                            bw.write("!");
                            bw.write(each.getValue()+ " " + each.getKey().getName());
                            bw.write("!");
                            bw.write(each.getValue() * each.getKey().getPrice() + "");
                            bw.write("!");

//                            bw.write(change.entrySet().toString());
                            HashMap<Double,Integer> temp = new HashMap<>();
                            for(Map.Entry<Double, Integer> te: change.entrySet()){
                                if(te.getValue() != 0){
                                    temp.put(te.getKey(),te.getValue());
                                }
                            }
                            bw.write(temp.entrySet().toString());
                            bw.write("!");
                            bw.write("Cash");
                            bw.write("!");
                            bw.write(customer.getUsername());
                            bw.write("!");
                            bw.write(each.getKey().getId()+"");

                            bw.write("\n");
                        }
                        bw.flush();
                        bw.close();
                    } catch (IOException | NullPointerException es) {
                        es.printStackTrace();
                    }

                    Map<Double, Integer> money = cashier.change_amount_to_map(amount);

                    Store.complete_an_order_cash(store, customer, cashier, money, total_price);
                    if (cashier.change_give_to_customer(change) == null) {
                        JOptionPane.showMessageDialog(cash_page, "Purchase successfully. No change received");
                    } else {
                        JOptionPane.showMessageDialog(cash_page, "Purchase successfully, you will get " + cashier.change_give_to_customer(change) + " change");
                    }
//                    JOptionPane.showMessageDialog(cash_page, "Purchase successfully, you will get " + cashier.change_give_to_customer(change) + " change");
//                    Seller.addAndUpdate(order, "src/main/resources/SellerReport");
                    countDownProgressBar.stop();
                    cash_page.dispose();

                    customer.clearShoppingCart();


                    default_page();

                }
            }
        });





        cash_page.setVisible(true);
    }
}
