import javax.swing.*;
public class Frame extends JFrame {
    public Frame() {
        initComponents();
    }
    private void initComponents() {
        JLabel jLabel1 = new JLabel("N1");
        JLabel jLabel2 = new JLabel("N2");
        JLabel jLabel3 = new JLabel("Result");
        JButton jButton1 = new JButton("Add");
        JTextField jTextField1 = new JTextField(10);
        JTextField jTextField2 = new JTextField(10);
        JTextField jTextField3 = new JTextField(10);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jButton1.addActionListener(evt -> {
            try {
                int a = Integer.parseInt(jTextField1.getText());
                int b = Integer.parseInt(jTextField2.getText());
                int c = a + b;
                jTextField3.setText(String.valueOf(c));
            } catch (NumberFormatException e) {
                jTextField3.setText("Invalid Input");
            }
        });
        setLayout(new java.awt.FlowLayout());
        add(jLabel1);
        add(jTextField1);
        add(jLabel2);
        add(jTextField2);
        add(jButton1);
        add(jLabel3);
        add(jTextField3);
        pack();
    }
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new Frame().setVisible(true);
        });
    }
}
