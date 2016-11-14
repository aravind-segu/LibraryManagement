package librarymanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.util.Date;
import org.apache.commons.lang.time.DateUtils;

public class renewViewCheck extends LibraryManagement implements ActionListener {
        /*
    * Connection variables are defined
    * A textbox is created for the book, name, duedate, date
    * A JFrame and Container are used for GUI 
    * Booleans are used for checking purposes
    */
    int rowLocal = viewCheck.row;
    int memLocal = viewCheck.mem;
    
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    JFrame renewFrame;
    Container contentPane;
    JPanel myPanel;
    JTextField book;
    JTextField name;
    JTextField date;
    JTextField duedate;
    DateFormat currentdate = DateFormat.getDateInstance();
    Date today = new Date();
    String todayDate = currentdate.format(today);
    boolean foundbook = false;
    boolean foundmem = false;
    String booktitle;
    int copiesrem;
    String first;
    
    public renewViewCheck (){
        GUI();
    }
    
    public void GUI (){
        /*
        * BookID and MemberID are retrieved from the selected row
        */
        String bkid = viewCheck.checkoutData[rowLocal][0];
        String mid = String.valueOf(memLocal);
               /*
        * The frame is initialized
        * The Close option is removed
        * Title is set
        * Size is set to 400x400
        * visibility defined to be true 
        * It is placed at the center of the screen
        */
         renewFrame = new JFrame();
         renewFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
         renewFrame.setTitle ("Check-Out");
         renewFrame.setSize (400,400);
         renewFrame.setVisible(true);
         renewFrame.setLocationRelativeTo(null);
                  /*
         * Three panels bottom, top and info are are defined
         */
        contentPane =  renewFrame.getContentPane();
        JPanel bottom = new JPanel();
        JPanel top = new JPanel();
        JPanel info = new JPanel();
        myPanel = new JPanel ();
        myPanel.setLayout(new BorderLayout());
                /*
        * The top panel is given a GridBagLayout
        * The Title is added
        * Description is Added
        */
      
        top.setLayout(new GridBagLayout());
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.insets = new Insets(1,1,1,1);
        JLabel welcome = new JLabel ("Renew Book");
        welcome.setFont(new Font ("Arial",Font.BOLD , 18));
        top.add (welcome, gbc1);
                
        gbc1.gridx = 0;
        gbc1.gridy = 2;
        JLabel inst = new JLabel ("Please Enter the information and press Enter");
        inst.setFont(new Font ("Arial",Font.BOLD , 12));
        top.add (inst, gbc1);
        
                /*
        * The info panel is given a GridBagLayout
        * Book ID label and textbox is added
        * MemberID label and textbox is added
        */
        info.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(1,1,1,1);
        JLabel Booktitle = new JLabel ("Book ID:");
                info.add (Booktitle , gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        book = new JTextField (20);
        book.setText(bkid);
        info.add (book , gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel author = new JLabel ("Member ID:");
        info.add (author , gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        name = new JTextField (20);
        name.setText(mid);
        info.add (name , gbc);
        
                      /*
        * The back and renew buttons are added to bottom panel
        * All panels are added to the main panel
        */ 
                
       bottom.setLayout(new BorderLayout ()); 
       JButton back = new JButton ("Back To Main");
       back.addActionListener(this);
              bottom.add(back, BorderLayout.WEST);
       
       JButton add = new JButton ("Renew");
        add.addActionListener(this);
       //newUserLogin.addActionListener (this);
       bottom.add(add, BorderLayout.EAST);
       
            
       myPanel.add (top , BorderLayout.NORTH);
       myPanel .add (info, BorderLayout.CENTER);
       myPanel .add (bottom, BorderLayout.SOUTH);
       contentPane.add (myPanel);
       renewFrame.validate ();
    }
    @Override
 public void actionPerformed (ActionEvent e){
        String event = e.getActionCommand();
        /*
        * If event is Renew, the check method is called
        */
        if (event.equals ("Renew")){
            try {
                check();
                 renewFrame.dispose();
                
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
               /*
        * If event is Back to Main, an object of MainWindow is created 
        */
        if (event.equals ("Back To Main")){
            try {
                new manageMembers ();
                renewFrame.dispose();
                
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
}
 public void check (){
     try {
         /*
         * The text in the textboxes is retrieved and stored in local variables
         */
         String idInput = book.getText();
         String idmem = name.getText();
         String lastname = "";
         String btitle = "";
 
                        connection = DriverManager.getConnection("jdbc:ucanaccess://library.mdb");
			statement = connection.createStatement (resultSet.TYPE_FORWARD_ONLY, resultSet.CONCUR_UPDATABLE);
			
                        resultSet = statement.executeQuery("SELECT ID,MemberID,MemberLastName,BookID,BookTitle,DateBorrowed,ReturnDate,TimesRenewed FROM memCheckouts");
                         /*
                        * Each row is iterated
                            * If the BookID is equal to idInput
                                * found book is true
                                    * if idLocal is equal to idmem
                                        * found mem is true
                                        * While loop is broken
                        * Connections are closed
                        */
			while (resultSet.next()) {
                                int id = resultSet.getInt("ID");
                                String idlocal = resultSet.getString ("MemberID");
                                lastname = resultSet.getString ("MemberLastName");
                                String bID = resultSet.getString ("BookID");
                                btitle = resultSet.getString ("BookTitle");
                                String bdate = resultSet.getString ("DateBorrowed");
                                String rdate = resultSet.getString ("ReturnDate");
                                String trenew = resultSet.getString ("TimesRenewed");
                                
                                if (bID.equals(idInput)){
                                foundbook = true;
                                if (idlocal.equals(idmem)){
                                foundmem = true;
                                break;
                                }
                                }
                                
                                
                        }
                        connection.close();
                        statement.close();
                        resultSet.close();
                        
                       /*
                        * The messages are printed to reflect the booleans
                        */
                        if (foundmem == true & foundbook == true){
                            renewItem();
                        }
                          if (foundmem == true & foundbook == false){
                            JOptionPane.showMessageDialog(null, "Book ID: "+ idInput + " not Found");
                            new renew ();
                            renewFrame.dispose();
                        }
                        if (foundmem == false & foundbook == true){
                            JOptionPane.showMessageDialog(null, "MemberID: " + idmem + " not found." );
                            new renew ();
                            renewFrame.dispose();
                        }
                        if (foundmem == false & foundbook == false){
                            JOptionPane.showMessageDialog(null, "MemberID: " + idmem + " and \n BookID: " + idInput + " not found." );
                            new renew();
                            renewFrame.dispose();
                        }
     }catch (Exception a){
         System.out.println (a);
     }
 }
  public void renewItem (){
     try{
                  // Data entered is stored in local variables
         String idInput = book.getText();
         String idmem = name.getText();
 // Connection is established with memCheckouts
                        connection = DriverManager.getConnection("jdbc:ucanaccess://library.mdb");
			statement = connection.createStatement (resultSet.TYPE_FORWARD_ONLY, resultSet.CONCUR_UPDATABLE);
			
                        resultSet = statement.executeQuery("SELECT ID,MemberID,MemberLastName,BookID,BookTitle,DateBorrowed,ReturnDate,TimesRenewed FROM memCheckouts");
                          /*
                        * Every row is iterated
                            * The data is stored in local variables
                            */
			while (resultSet.next()) {
                                int id = resultSet.getInt("ID");
                                String idlocal = resultSet.getString ("MemberID");
                                String lastname = resultSet.getString ("MemberLastName");
                                String bID = resultSet.getString ("BookID");
                                String btitle = resultSet.getString ("BookTitle");
                                String bdate = resultSet.getString ("DateBorrowed");
                                String rdate = resultSet.getString ("ReturnDate");
                                String trenew = resultSet.getString ("TimesRenewed");
                                  /*
                                * If book id is equal to entered bookid
                                    * If memeberid is equal to local memberiD
                                        * The current date is taken, incremented by 21 days and stored in string
                                        * if the times renewed is less than 4
                                            *The renew is completed accourdingly and database is updated
                                            * Success message is printed
                                        * else
                                            * Renewal limit reached is printed
                                */
                                if (bID.equals(idInput)){
                                    if (idlocal.equals(idmem)){
                                        Date current = new Date();
                                        Date increment = DateUtils.addDays(current, 21);
                                        String reportDate = currentdate.format(increment);
                                        
                                        int times = Integer.parseInt(trenew);
                                        times++;
                                        if (times < 4){
                                        String ntimes = String.valueOf(times);
                                        resultSet.updateString("MemberID", idlocal);
                                        resultSet.updateString("MemberLastName", lastname);
                                        resultSet.updateString("BookID", bID);
                                        resultSet.updateString("BookTitle", btitle);
                                        resultSet.updateString("DateBorrowed",bdate);
                                        resultSet.updateString("ReturnDate",reportDate);
                                        resultSet.updateString("TimesRenewed",ntimes);
                                        
                                        resultSet.updateRow();
                                        JOptionPane.showMessageDialog(null, "Successfully renewed " + btitle + ".\n New due date: " + reportDate + "\n Times Renewed: " + ntimes );
                                         connection.close();
                                            statement.close();
                                            resultSet.close();
                                            break;
                                        }else {
                                            JOptionPane.showMessageDialog(null, "Sorry Renewal Limit Reached" );
                                            break;
                                        }
                                    }
                                }
                                    
         
                                
                        
 }
                        new renew();
                        renewFrame.dispose();
     }catch (Exception exa){
                     System.out.println (exa);
                     }

}
}
