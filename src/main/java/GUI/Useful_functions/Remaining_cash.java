package GUI.Useful_functions;

import Generated.Generated;
import Roles.Cashier;

import javax.swing.*;
import java.util.Map;
@Generated
public class Remaining_cash {
    public static void remaining_cash(JFrame frame, Map<Double,Integer> map) {

        JTable table = new JTable(12,2);
        // set the name of the table
        table.setValueAt("Unit",0,0);
        table.setValueAt("Quantity",0,1);
        for(int i = 1; i < 12; i++){
            table.setValueAt(map.keySet().toArray()[i-1],i,0);
            table.setValueAt(map.values().toArray()[i-1],i,1);

        }
        frame.add(table);
        JOptionPane.showMessageDialog(frame,table,"Remaining Cash",JOptionPane.PLAIN_MESSAGE);
    }
}
