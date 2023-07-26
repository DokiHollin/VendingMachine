package GUI;

import Generated.Generated;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

@Generated
public class ShowTransactionHistory {
    public static void show(JFrame cashier_page){
        File file = new File("src/main/resources/TransactionHistory");
        Scanner scan;
        int count = 1;
        JTable table = new JTable(100,5);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setRowHeight(30);

        // set the name of the table
        table.setValueAt("Date",0,0);
        table.setValueAt("Item",0,1);
        table.setValueAt("Amount Paid",0,2);
        table.setValueAt("Change",0,3);
        table.setValueAt("Payment Method",0,4);
        try {
            scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String tmp = scan.nextLine();

                String[] content = tmp.split("!");
                System.out.println(Arrays.toString(content));
                table.setValueAt(content[0],count,0);
                table.setValueAt(content[1],count,1);
                table.setValueAt(content[2],count,2);
                table.setValueAt(content[3],count,3);
                table.setValueAt(content[4],count,4);

                count++;
            }
        } catch (FileNotFoundException es) {
            throw new RuntimeException(es);
        }
        table.getColumnModel().getColumn(0).setPreferredWidth(300);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);

        scrollPane.setPreferredSize(new Dimension(500, 500));

        cashier_page.add(scrollPane);
        JOptionPane.showMessageDialog(cashier_page,scrollPane,"History",JOptionPane.PLAIN_MESSAGE);
    }
}
