package GUI;

import GUI.MainPages.Seller_page;
import GUI.Useful_functions.J_constructor;
import Generated.Generated;
import Store.Store;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static Roles.Seller.generateReport;
@Generated
public class Seller_history {
    public static void seller_history(Store store){
        JFrame seller_history = J_constructor.frame_constructor("Seller history", 800, 500);
        JButton cancel_button = J_constructor.button(seller_history,"Cancel", 720, 0, 80, 30);
        cancel_button.addActionListener(e -> {
            seller_history.dispose();
            Seller_page.seller_page(store);
        });
        JLabel show_history = J_constructor.label(seller_history,"Show the history", 50, 50, 200, 50);
        JButton show_history_button = J_constructor.button(seller_history, "Show", 50, 100, 100, 30);
        show_history_button.addActionListener(e -> {
//            seller_history.dispose();
            show_history_details(store);


        });
        seller_history.setVisible(true);
    }

//    public static void show_history_details(Store store){
//        JFrame show_history_details = J_constructor.frame_constructor("Show history details", 800, 500);
//        JButton cancel_button = J_constructor.button(show_history_details,"Cancel", 720, 0, 80, 30);
//        cancel_button.addActionListener(e -> {
//            show_history_details.dispose();
//            seller_history(store);
//        });
//
//        JScrollPane scrollPane = J_constructor.scroll_pane(show_history_details, 50, 50, 700, 400);
//        JTable table = J_constructor.table(show_history_details,50,50,700,400,100, 3 );
//        table.getColumnModel().getColumn(0).setHeaderValue("Code");
//        table.getColumnModel().getColumn(1).setHeaderValue("Name");
//        table.getColumnModel().getColumn(2).setHeaderValue("Amount");
//        scrollPane.setViewportView(table);
//        List<String[]> report =  generateReport("src/main/resources/TransactionHistory");
//
//
////
//
//        for (int i = 0; i < report.size(); i++) {
//
//            String name = report.get(i)[1];
//            for(int j = 0; j < table.getRowCount(); j++){
//                if(table.getValueAt(j, 1) == name){
//                    int amount = Integer.parseInt((String) table.getValueAt(j, 2));
//                    amount += Integer.parseInt(report.get(i)[2]);
//                    table.setValueAt(amount/2, j, 2);
//                    break;
//                }else{
//                    table.setValueAt(report.get(i)[0], i, 0);
//                    table.setValueAt(report.get(i)[1], i, 1);
//                    table.setValueAt(report.get(i)[2], i, 2);
//                }
//            }
//
//        }
//
//        show_history_details.setVisible(true);
//    }
    public static void show_history_details(Store store){
        JFrame show_history_details = J_constructor.frame_constructor("Show history details", 800, 500);
        JButton cancel_button = J_constructor.button(show_history_details,"Cancel", 720, 0, 80, 30);
        cancel_button.addActionListener(e -> {
            show_history_details.dispose();
//            seller_history(store);
        });
        File file = new File("src/main/resources/TransactionHistory");
        Scanner scan;
        int count = 1;
        JTable table = new JTable(100,3);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setRowHeight(30);

        // set the name of the table
        table.setValueAt("Code",0,0);
        table.setValueAt("Item Name",0,1);
        table.setValueAt("Amount",0,2);

        try {
            scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String tmp = scan.nextLine();

                String[] content = tmp.split("!");
                System.out.println(Arrays.toString(content));
                table.setValueAt(content[6],count,0);
                String[] temp = content[1].split(" ");
                table.setValueAt(temp[0],count,2);
                table.setValueAt(temp[1],count,1);

                count++;
            }
        } catch (FileNotFoundException es) {
            throw new RuntimeException(es);
        }
        table.getColumnModel().getColumn(0).setPreferredWidth(300);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);

        scrollPane.setPreferredSize(new Dimension(500, 500));

        show_history_details.add(scrollPane);
        JOptionPane.showMessageDialog(show_history_details,scrollPane,"History",JOptionPane.PLAIN_MESSAGE);
    }

}
