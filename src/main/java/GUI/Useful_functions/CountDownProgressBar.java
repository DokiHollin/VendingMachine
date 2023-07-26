package GUI.Useful_functions;

import Generated.Generated;
import Roles.Cashier;
import Roles.Customer;
import Roles.Seller;

import java.awt.event.*;
import javax.swing.*;

import static GUI.MainPages.Default_page.default_page;
@Generated
public class CountDownProgressBar {

    JLabel label;

    Timer timer;
    JProgressBar progressBar;

    public CountDownProgressBar(JFrame frame, Customer customer) {
        label = J_constructor.label(frame, "Time left: ", 430, 445, 800, 30);
        progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 120);
        progressBar.setValue(12000);

        progressBar.setString("120");
        progressBar.setStringPainted(true);
        // let progress bar show the time left every second
        progressBar.setBounds(500, 450, 300, 20);



        frame.add(progressBar);

        timer = new Timer(1000, new ActionListener() {
            @Generated
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = progressBar.getValue();
                progressBar.setString(String.valueOf(progressBar.getValue()));
                if (value > 1) {
                    progressBar.setValue(value - 1);
                } else {
                    timer.stop();
                    frame.dispose();
                    customer.clearShoppingCart();
                    customer.cancel("src/main/resources/Cancel", "TIMEOUT");
                    default_page();


                }
            }
        });

        timer.start();


    }
    // stop the timer
    public void stop() {
        timer.stop();
    }

    // start a new timer
    public void restart() {
        progressBar.setValue(12000);
        timer.start();
    }



}

