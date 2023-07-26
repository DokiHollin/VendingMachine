package GUI.Useful_functions;

import GUI.MainPages.Default_page;
import Generated.Generated;
import Roles.Customer;

import javax.swing.*;
@Generated
public class Save_card_option {
    public static void save_card_option(Customer customer, String card_number, String card_password) {
        JFrame save_card_option = J_constructor.frame_constructor("Save card option", 800, 500);
        CountDownProgressBar countDownProgressBar = new CountDownProgressBar(save_card_option, customer);
        JLabel save_card_label = J_constructor.label(save_card_option, "Do you want to save your card?", 100, 100, 200, 30);
        JButton yes_button = J_constructor.button(save_card_option, "Yes", 100, 200, 100, 30);
        JButton no_button = J_constructor.button(save_card_option, "No", 300, 200, 100, 30);
        yes_button.addActionListener(e -> {
            countDownProgressBar.stop();
            customer.addCardInfo("src/main/resources/CustomerInfo", card_number, card_password);

            save_card_option.dispose();
            JOptionPane.showMessageDialog(null, "Card saved!");
            Default_page.default_page();
        });

        no_button.addActionListener(e -> {
            countDownProgressBar.stop();
            save_card_option.dispose();
            JOptionPane.showMessageDialog(null, "See you!");
            Default_page.default_page();
        });

        save_card_option.setVisible(true);


    }

}
