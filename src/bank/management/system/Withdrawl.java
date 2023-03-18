
package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Withdrawl extends JFrame implements ActionListener{
    JLabel text,l2,l3;
    JTextField amount,t2;
    JButton Withdraw,back,b3;
    String pinnumber;
    
    Withdrawl(String pinnumber)
      {
          this.pinnumber=pinnumber;
          setLayout(null);
          
          
         ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
         Image i2=i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
         ImageIcon i3=new ImageIcon(i2);
         JLabel image=new JLabel(i3);
         image.setBounds(0,0,900,900);
         add(image);
         
        text = new JLabel("Enter the amount you want to Withdraw");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(170,300,400,20);
        image.add(text);
        
        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(170,350,320,25);
        image.add(amount);
        
        Withdraw = new JButton("Withdraw");
        Withdraw.setBounds(355,485,150,30);
        Withdraw.addActionListener(this);
        image.add(Withdraw);
        
        back = new JButton("Back");
        back.setBounds(355,520,150,30);
        back.addActionListener(this);
        image.add(back);
        

         
          setSize(900,900);
          setLocation(300,0);
          setVisible(true);
      }
    
    public void actionPerformed(ActionEvent ae){
        try{        
            String number = amount.getText();
            Date date = new Date();
            if(ae.getSource()==Withdraw){
                if(amount.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Withdraw");
                }else{
                    Conn c1 = new Conn();
                    c1.s.executeUpdate("insert into bank values('"+pinnumber+"', '"+date+"', 'Withdrawl', '"+number+"')");
                    JOptionPane.showMessageDialog(null, "Rs. "+number+" Withdraw Successfully");
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                }
            }else if(ae.getSource()==back){
                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
            
    }
    
    public static void main(String args[]) {
        // TODO code application logic here
        new Withdrawl("");
    }
}
