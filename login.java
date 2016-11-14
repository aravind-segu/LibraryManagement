
package librarymanagement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import static librarymanagement.LibraryManagement.frame;

public class login extends LibraryManagement implements ActionListener {
    /*
    * Variables required for connection is defined
    * two booleans are defined for future checking
    * Two textboxes are defined
    * The container, panel and JFrame and defined for future use
    */
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    static boolean newUser;
    static boolean loginCorrect = false;
    static JPasswordField passEntry;
    static JTextField nameEntry;
    Container contentPane;
    JPanel myPanel;
    JFrame loginFrame;
    
    login () {
        loginMembers ();
        }
    
    public void loginMembers () {
        /*
            *  The frame is initialized
            * The Close option is removed
            * Title is set
            * Size is set to 400x400
            * visibility defined to be true 
            * It is placed at the center of the screen
          */              
        
        loginFrame = new JFrame ();
        
        loginFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        loginFrame.setTitle ("Login");
        loginFrame.setSize (800,600);
        loginFrame.setVisible(true);
        loginFrame.setLocationRelativeTo(null);
        /*
        * Three panels are made
        */
        contentPane = loginFrame.getContentPane();
        JPanel bottom = new JPanel();
        JPanel top = new JPanel();
        JPanel info = new JPanel();
        myPanel = new JPanel ();
        myPanel.setLayout(new BorderLayout());
        /*
        * The top panel is given a GridBagLayout
        * The title is printed
        * Directions are printed
        */
        top.setLayout(new GridBagLayout());
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.insets = new Insets(1,1,1,1);
        JLabel welcome = new JLabel ("Welcome to Library Management System!!");
        welcome.setFont(new Font ("Arial",Font.BOLD , 24));
        top.add (welcome, gbc1);
        
        
       gbc1.gridx = 0;
       gbc1.gridy = 1;
        JLabel inst = new JLabel ("Login: Please Enter your Username and Password");
        inst.setFont(new Font ("Arial",Font.BOLD , 18));
        top.add (inst, gbc1);
        /*
        * The info panel is also given a gridBagLayout
        * The username label is printed and a textbox beside the label
        * The password lable is pringted and a textbox beside the label is also printed
        * A login button is given
        */
        info.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(1,1,1,1);
        
        JLabel username = new JLabel ("Username:");
        
        info.add (username , gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        nameEntry = new JTextField (20);
        info.add (nameEntry , gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel password = new JLabel ("Password:");
        info.add (password , gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        passEntry = new JPasswordField (20);
        passEntry.setEchoChar('*');
        info.add (passEntry , gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        JButton login = new JButton ("Login");
        login.addActionListener(this);
        info.add (login , gbc);
        
        /*
        * The bottom banel is given a Border Layout
        * A New User button is inserted
        * All three panels are added to the main panel
        */
       bottom.setLayout(new BorderLayout ()); 
       JButton newUserLogin = new JButton ("New User? Register Here");
       newUserLogin.addActionListener (this);
       bottom.add(newUserLogin, BorderLayout.CENTER);
       
            
       myPanel.add (top , BorderLayout.NORTH);
       myPanel .add (info, BorderLayout.CENTER);
       myPanel .add (bottom, BorderLayout.SOUTH);
       contentPane.add (myPanel);
       loginFrame.validate ();
        
    }

   
    @Override
    public void actionPerformed(ActionEvent e) {
        String event = e.getActionCommand();
        /*
        * If the new user button is pressed
        * The GuiNewUser object is created
        */
        if (event.equals ("New User? Register Here")){
            try {
               new GuiNewUser ();
               loginFrame.dispose();
            } catch (Exception f) {
                System.out.println(" Sorry, Error Occured");
            }
        }
         /*
        * If the login button is pressed
        * The login method is called
        */
       if (event.equals ("Login")){
            try {
                loginCheck ();
            } catch (Exception f) {
                System.out.println(" Sorry, Error Occured");
            }
        }
    }
    
    public void loginCheck (){
        /*
        * Two booleans are defined
        * The Username and password entered by the user are retrieved
        */
        boolean userCorrect = false;
        boolean passCorrect = false;
        String Username = nameEntry.getText();
        char [] Pass = passEntry.getPassword();
        String Password = new String (Pass);
        String userInput = "";
        String passInput = "";
        String firstName = "";
        
        
        try {
            /*
            * The Connection is established with UserDetails
            * The table is iterated
            * If the username matches the entered username 
                * boolean userCorrect is true and firstName is stored
            * If the password is correct
                * passcorrect boolean is true
            * If both variables are true
                * The login Correct Boolean is true
            *All connections are closed
            */
			connection = DriverManager.getConnection("jdbc:ucanaccess://library.mdb");
			statement = connection.createStatement();
			//resultSet = statement.executeQuery("SELECT * FROM UserDetails WHERE Username = " + Username);
                        resultSet = statement.executeQuery("SELECT ID,FirstName,LastName,Username,Password FROM UserDetails");
			while (resultSet.next()) {
                                int id = resultSet.getInt("ID");
                                String first = resultSet.getString("FirstName");
                                String last = resultSet.getString("LastName");
                                userInput = resultSet.getString("Username");
				passInput = resultSet.getString("Password");
                                
                                if (userInput.equals (Username)){
                                        userCorrect = true;
                                        firstName = first;
                                        
                                  }
                                if (passInput.equals (Password)){
                                       passCorrect = true;
                                  }
                                if (userCorrect == true && passCorrect == true){
                                     loginCorrect = true;
                              } 
			                        
                        }
                        resultSet.close();
			statement.close();
			connection.close();
		}
		catch (SQLException SQLe) {
			System.out.println("UserDetails.java\n" + SQLe.toString());
		}
        
        /*
        * If loginCorrec is true
        * Welcome message is printed
        * The textboxes are cleared
        * A new MainWindow object is created
        * The current frame is disposed
        */
        if (loginCorrect == true){
            JOptionPane.showMessageDialog(null, "Welcome " + firstName);
            nameEntry.setText("");
            passEntry.setText("");
            new MainWindow();
            loginFrame.dispose();
        }
        /*
        * If the boolean is false
        * Try Again Message is printed
        * The textboxes are cleared
        */
        if (loginCorrect == false){
            JOptionPane.showMessageDialog(null, "Sorry, Wrong Username or Password. Please Try Again");
            nameEntry.setText("");
            passEntry.setText("");
        }

  
    /*
        if (loginCorrect == true){
            //new MainWindow ();
            new center();
                    }
            */
}
 
}

