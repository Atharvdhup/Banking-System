
package bank.management.system;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class PinChange extends JFrame implements ActionListener{
    JLabel l1,l2,l3;
     JPasswordField t1,t2;
     JButton b1,b2;     
     String pinnumber;
    
    PinChange(String pinnumber)
    {  this.pinnumber=pinnumber;
        ImageIcon i1= new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2=i1.getImage().getScaledInstance(900,900 ,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);
        
        l1 = new JLabel("CHANGE YOUR PIN");
        l1.setFont(new Font("System", Font.BOLD, 16));
        l1.setForeground(Color.WHITE);
        l1.setBounds(250,280,500,35);
        image.add(l1);
        
        l2 = new JLabel("New PIN:");
        l2.setFont(new Font("System", Font.BOLD, 16));
        l2.setForeground(Color.WHITE);
        l2.setBounds(165,320,180,25);
        image.add(l2);
        
        l3 = new JLabel("Re-Enter New PIN:");
        l3.setFont(new Font("System", Font.BOLD, 16));
        l3.setForeground(Color.WHITE);
        l3.setBounds(165,360,180,25);
        image.add(l3);
        
        t1 = new JPasswordField();
        t1.setFont(new Font("Raleway", Font.BOLD, 25));
        
        t2 = new JPasswordField();
        t2.setFont(new Font("Raleway", Font.BOLD, 25));
        
        t1.setBounds(330,320,180,25);
        image.add(t1);
        
        t2.setBounds(330,360,180,25);
        image.add(t2);
        
        b1 = new JButton("CHANGE");
        b2 = new JButton("BACK");
        
        b1.setBounds(355,485,150,30);
        image.add(b1);
        
        b2.setBounds(355,520,150,30);
        image.add(b2);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
         setLayout(null);
        
        setSize(900,900);
        setLocation(300,0);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==b1)
        {
           try{
            String npin = t1.getText();
            String rpin = t2.getText();
            
            if(!npin.equals(rpin)){
                JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                return;
            }
            if (t1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please Enter New PIN");
                }
                if (t2.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please Re-Enter new PIN");
                }
                 Conn c1 = new Conn();
                String q1 = "update bank set pin = '"+rpin+"' where pin = '"+pinnumber+"' ";
                String q2 = "update login set pin = '"+rpin+"' where pin = '"+pinnumber+"' ";
                String q3 = "update signupthree set pin = '"+rpin+"' where pin = '"+pinnumber+"' ";

                c1.s.executeUpdate(q1);
                c1.s.executeUpdate(q2);
                c1.s.executeUpdate(q3);

                JOptionPane.showMessageDialog(null, "PIN changed successfully");
                setVisible(false);
                new Transactions(rpin).setVisible(true);     
                
            
         }catch(Exception e)
         {
             System.out.print(e);
         } 
        }else
        {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
        
             
    }
    
    public static void main(String args[]) {
        // TODO code application logic here
        new PinChange("").setVisible(true);
    }
}
