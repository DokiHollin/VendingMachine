package GUI.Useful_functions;

import Generated.Generated;

import javax.swing.*;
@Generated
public class J_constructor {

    public static JFrame frame_constructor(String title, int width, int height){
        JFrame frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    public static JButton button( JFrame frame,String name, int x, int y, int width, int height){
        JButton button = new JButton(name);
        button.setBounds(x, y, width, height);
        frame.add(button);
        return button;
    }

    public static JLabel label( JFrame frame,String name, int x, int y, int width, int height){
        JLabel label = new JLabel(name);
        label.setBounds(x, y, width, height);
        frame.add(label);
        return label;
    }

    public static JTextField text_field(JFrame frame, int x, int y, int width, int height){
        JTextField text_field = new JTextField();
        text_field.setBounds(x, y, width, height);
        frame.add(text_field);
        return text_field;
    }

    public static JPasswordField password_field( JFrame frame, int x, int y, int width, int height){
        JPasswordField password_field = new JPasswordField();
        password_field.setBounds(x, y, width, height);
        frame.add(password_field);
        return password_field;
    }

    public static JTextArea text_area( JFrame frame, int x, int y, int width, int height){
        JTextArea text_area = new JTextArea();
        text_area.setBounds(x, y, width, height);
        text_area.setEditable(false);
        frame.add(text_area);
        return text_area;
    }

    public static JTable table( JFrame frame, int x, int y, int width, int height, int row, int column){
        JTable table = new JTable(row, column);
        table.setBounds(x, y, width, height);
        // set table uneditable
        table.setDefaultEditor(Object.class, null);

        frame.add(table);
        return table;
    }

    public static JComboBox<String> combo_box(JFrame frame, String[] items, int x, int y, int width, int height){
        JComboBox<String> combo_box = new JComboBox<>(items);
        combo_box.setBounds(x, y, width, height);
        frame.add(combo_box);
        return combo_box;
    }

    public static JScrollPane scroll_pane(JFrame frame, int x, int y, int width, int height){
        JScrollPane scroll_pane = new JScrollPane();
        scroll_pane.setBounds(x, y, width, height);
        frame.add(scroll_pane);
        return scroll_pane;
    }


    public static void beautiful_ui(){
        try{
            for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()){
                if("Nimbus".equals(info.getName())){
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
