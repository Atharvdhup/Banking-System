
package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import javax.swing.*;
import java.sql.*;

public class FastCash extends JFrame implements ActionListener{
JLabel text;
JButton b1,b2,b3,b4,b5,b6,b7;
String pinnumber;
    FastCash(String pinnumber)
    {
        this.pinnumber=pinnumber;
        setLayout(null);
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2=i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);
        
        text = new JLabel("SELECT WITHDRAWL AMOUNT");
         text.setBounds(210,300,700,35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);
        
        b1 = new JButton("RS 100");
        b2 = new JButton("RS 500");
        b3 = new JButton("RS 1000");
        b4 = new JButton("RS 2000");
        b5 = new JButton("RS 5000");
        b6 = new JButton("RS 10000");
        b7 = new JButton("BACK");
        
        
        
        
        b1.setBounds(170,415,150,30);
        image.add(b1);
        
        b2.setBounds(355,415,150,30);
        image.add(b2);
        
        b3.setBounds(170,450,150,30);
        image.add(b3);
        
        b4.setBounds(355,450,150,30);
        image.add(b4);
        
        b5.setBounds(170,485,150,30);
        image.add(b5);
        
        b6.setBounds(355,485,150,30);
        image.add(b6);
        
        b7.setBounds(355,520,150,30);
        image.add(b7);
        
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        
        
        setSize(900,900);
        setLocation(300,0);
      //  setUndecorated(true);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==b7){ 
            this.setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }else{
            String amount = ((JButton)ae.getSource()).getText().substring(3); //k
            Conn c = new Conn();
            try{
            
            ResultSet rs = c.s.executeQuery("select * from bank where pin = '"+pinnumber+"'");
            int balance = 0;
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
            
             if (ae.getSource() != b7 && balance < Integer.parseInt(amount)) {
                JOptionPane.showMessageDialog(null, "Insuffient Balance");
                return;
            }
             
             Date date = new Date();
                c.s.executeUpdate("insert into bank values('"+pinnumber+"', '"+date+"', 'Withdrawl', '"+amount+"')");
                JOptionPane.showMessageDialog(null, "Rs. "+amount+" Debited Successfully");
                    
                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
                
        }catch(Exception e)
        {
            System.out.print(e);
        }
        }
    }
    
    
    public static void main(String args[]) {
        // TODO code application logic here
        new FastCash("");
    }
}
