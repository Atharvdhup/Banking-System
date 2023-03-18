
package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Login extends JFrame implements ActionListener {
      
     JButton login,clear,signup;
     JTextField cardtext;
     JPasswordField pintext;
      Login(){
          setTitle("ATM");
          setLayout(null);
          ImageIcon i1 =new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
          Image i2=i1.getImage().getScaledInstance(100, 100,Image.SCALE_DEFAULT);
          ImageIcon i3=new ImageIcon(i2);
          JLabel labe1=new JLabel(i3);
          labe1.setBounds(70,10,100,100);
          add(labe1);
          
        
          
          JLabel text=new JLabel("WELECOME TO ATM");
          text.setFont(new Font("Osward",Font.BOLD,38));
          text.setBounds(200,40,400,40);
          add(text);
          
          JLabel cardno=new JLabel("Card NO :");
          cardno.setFont(new Font("Raleway",Font.BOLD,28));
          cardno.setBounds(120,150,150,30);
          add(cardno);
          
           cardtext =new JTextField();
          cardtext.setBounds(300,150,230,30);
          cardtext.setFont(new Font("Arial",Font.BOLD,14));
          add(cardtext);
          
          JLabel pin=new JLabel("PIN :");
          pin.setFont(new Font("Raleway",Font.BOLD,28));
          pin.setBounds(120,220,250,30);
          add(pin);
           pintext =new JPasswordField();
          pintext.setBounds(300,220,230,30);
          pintext.setFont(new Font("Arial",Font.BOLD,14));
          
          
          add(pintext);
          
           login = new JButton("SIGN IN");
          login.setBounds(300,300,100,30);
          login.setBackground(Color.BLACK);
          login.setForeground(Color.WHITE);
          login.addActionListener(this);
          add(login);
          
           clear = new JButton("CLEAR");
          clear.setBounds(430,300,100,30);
          clear.setBackground(Color.BLACK);
          clear.setForeground(Color.WHITE);
          clear.addActionListener(this);
          add(clear);
          
           signup = new JButton("SIGN UP");
          signup.setBounds(300,350,230,30);
          signup.setBackground(Color.BLACK);
          signup.setForeground(Color.WHITE);
          signup.addActionListener(this);
          add(signup);
          
          getContentPane().setBackground(Color.WHITE);
          setSize(800,480);
          setVisible(true);
          setLocation(350,200);
      }
      
      public void actionPerformed(ActionEvent ae)
      {
           if(ae.getSource()==clear){
               cardtext.setText("");
               pintext.setText("");
           }else if(ae.getSource()==login)
           {
               Conn conn=new Conn();
               String cardnumber=cardtext.getText();
               String pinnumber=pintext.getText();
               String q  = "select * from login where cardno = '"+cardnumber+"' and pin = '"+pinnumber+"'";
               try{
               ResultSet rs = conn.s.executeQuery(q);
                if(rs.next()){
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
               } catch(Exception e){
                  e.printStackTrace();
             }
               
           }else if (ae.getSource()==signup)
           {
               setVisible(false);
               new SignupOne().setVisible(true);
           }
           
               
      }
              
    
    public static void main(String args[]) {
        new Login ();
    }
}
