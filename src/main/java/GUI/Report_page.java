package GUI;

import GUI.MainPages.Owner_page;
import GUI.Useful_functions.J_constructor;
import Generated.Generated;
import Roles.Owner;

import javax.swing.*;
import java.util.List;
@Generated
public class Report_page {
    public static void report_page(Owner owner) {

        JFrame report_page = J_constructor.frame_constructor("Report", 800, 500);
        JButton cancel_button = J_constructor.button(report_page, "Cancel", 720, 0, 80, 30);
        cancel_button.addActionListener(e -> {
            report_page.dispose();
            Owner_page.owner_page(owner);
        });

        JButton report_button = J_constructor.button(report_page, "Report", 0, 0, 100, 30);
        report_button.addActionListener(e -> {
            report_page.dispose();
            report_details(owner);
        });



        report_page.setVisible(true);
    }
    public static void report_details(Owner owner){
        JFrame report_details = J_constructor.frame_constructor("Report details", 800, 500);
        JButton cancel_button = J_constructor.button(report_details, "Cancel", 720, 0, 80, 30);
        cancel_button.addActionListener(e -> {
            report_details.dispose();
            report_page(owner);
        });
        JScrollPane scrollPane = J_constructor.scroll_pane(report_details, 50, 50, 700, 400);
        JTable table = J_constructor.table(report_details, 50,50,700,400,100, 3 );
        table.getColumnModel().getColumn(0).setHeaderValue("Date");
        table.getColumnModel().getColumn(1).setHeaderValue("Name");
        table.getColumnModel().getColumn(2).setHeaderValue("Reason");
        scrollPane.setViewportView(table);
        List<String[]> report_file = owner.getCanceled("src/main/resources/Cancel");
        // set the report file to the table
        for (int i = 0; i < report_file.size(); i++) {
            table.setValueAt(report_file.get(i)[0], i, 0);
            table.setValueAt(report_file.get(i)[1], i, 1);
            table.setValueAt(report_file.get(i)[2], i, 2);

        }

        report_details.setVisible(true);

    }
}
