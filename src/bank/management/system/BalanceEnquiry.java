
package bank.management.system;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import java.util.*;

public class BalanceEnquiry extends JFrame  implements ActionListener{
      
    JButton b1, b2, b3;
    JLabel l1, l2, l3;
    String pinnumber;
    BalanceEnquiry(String pinnumber)
    {
        this.pinnumber=pinnumber;
         setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);
        
        b1 = new JButton("BACK");
        b1.setBounds(355, 520, 150, 30);
        image.add(b1);
        b1.addActionListener(this);
        
        int balance = 0;
        try{
            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("select * from bank where pin = '"+pinnumber+"'");
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
        }catch(Exception e){}
        
        l1 = new JLabel();
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));
        l1.setText("Your Current Account Balance is Rs "+balance);
        l1.setBounds(170, 300, 400, 30);
        image.add(l1);

        
        
        
        
        
        
        
        setSize(900, 900);
        //setUndecorated(true);
        setLocation(300, 0);
        setVisible(true);
    }
   
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(pinnumber).setVisible(true);
    }
    public static void main(String args[]) {
        // TODO code application logic here
         new BalanceEnquiry("").setVisible(true);
    }
}
