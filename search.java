
package librarymanagement;

 import javax.swing.*;
 import java.awt.*;
 import java.sql.*;
 import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.*;

public class search extends LibraryManagement implements ActionListener {
    /*
    * The JFrames, Container and JPanels are defined
    * The connection variables are defined
    * ArrayLists are created for each column in BookDetails
    */
    JFrame searchFrame;
    Container contentPane;
    JPanel myPanel;
    static JTextField input;
    static String selection;
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    JFrame manageFrame;
    boolean found = false;
    static ArrayList <String> bookArray = new ArrayList<> ();
    static ArrayList <String> authorArray = new ArrayList<> ();
    static ArrayList <String> yearArray = new ArrayList<> ();
    static ArrayList <String> publisherArray = new ArrayList<> ();
    static ArrayList <String> pagesArray = new ArrayList<> ();
    static ArrayList <String> BookIDArray = new ArrayList<> ();
    static ArrayList <String> CopiesArray = new ArrayList<> ();
    static ArrayList <String> ShelfNumberArray = new ArrayList<> ();
    static ArrayList <String> AvailableArray = new ArrayList<> ();
    static ArrayList <String> Numbers = new ArrayList <>();
    String [][] data;
    public search (){
        searchGUI ();
    }
    public void searchGUI(){
        /*
        * Array Lists are cleared
        * The frame is initialized
        * The Close option is removed
        * Title is set
        * Size is set to 400x400
        * visibility defined to be true 
        * It is placed at the center of the screen
        */
                                bookArray.clear();
                                authorArray.clear();
                                yearArray.clear();
                                publisherArray.clear();
                                pagesArray .clear();
                                BookIDArray.clear();
                                CopiesArray.clear();
                                ShelfNumberArray.clear();
                                AvailableArray.clear();
                                Numbers.clear();
                                selection = "";
         searchFrame = new JFrame();
         searchFrame.setUndecorated(true);
         searchFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
         searchFrame.setTitle ("Search");
         searchFrame.setSize (400,400);
         searchFrame.setVisible(true);
         searchFrame.setLocationRelativeTo(null);
        
        contentPane =  searchFrame.getContentPane();
        /*
        * Three Panels are initialized
        */
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
        JLabel welcome = new JLabel ("Search");
        welcome.setFont(new Font ("Arial",Font.BOLD , 18));
        top.add (welcome, gbc1);
                
        gbc1.gridx = 0;
        gbc1.gridy = 2;
        JLabel inst = new JLabel ("Please Enter the information and press Enter");
        inst.setFont(new Font ("Arial",Font.BOLD , 12));
        top.add (inst, gbc1);
       /*
        * The info panel is given a GridBagLayout
        * The KeyWord Label and Textbox are added
        * A combo box with book title, author, bookId and publisher are added
        */
        info.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(1,1,1,1);
        JLabel Booktitle = new JLabel ("Keyword:");
        info.add (Booktitle , gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        input = new JTextField (20);
        info.add (input , gbc);
        
        String [] choices = {"Book Title", "Author" , "BookID", "Publisher"};
        gbc.gridx = 2;
        gbc.gridy = 2;
        JComboBox choice = new JComboBox(choices);
        choice.setSelectedIndex(3);
        choice.addActionListener(new ActionListener (){
            public void actionPerformed (ActionEvent a){
                JComboBox cb = (JComboBox)a.getSource();
        String msg = (String)cb.getSelectedItem();
        
        /*
        * The msg selected is stored in the string selection
        */
        if (msg.equals ("Book Title")){
            try {
                 selection = "Book Title";
                            
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
            if (msg.equals ("Author")){
            try {
                selection = "Author";
                
            } catch (Exception ex) {
                System.out.println("Error");
            }
            }
            if (msg.equals ("BookID")){
            try {
                 selection = "BookID";
                
            } catch (Exception ex) {
                System.out.println("Error");
            }
            }
            if (msg.equals ("Publisher")){
            try {
                 selection = "Publisher";
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
    
    
            }
        });
        info.add (choice, gbc);
               
            /*
        * The back button and is added to the panel and if clicked it opens the MainWindow
        */   
       bottom.setLayout(new BorderLayout ()); 
       JButton back = new JButton ("Back To Main");
       back.addActionListener(new ActionListener() {
           
     public void actionPerformed (ActionEvent ae){
       String event = ae.getActionCommand();
        
        if (event.equals ("Back To Main")){
            try {
                new MainWindow ();
                searchFrame.dispose();
                
            } catch (Exception ex) {
                System.out.println("Error");
            }
                
        }
                }
       });
      /*
       * The search button is added and all panels are added to the main panel
       */
       bottom.add(back, BorderLayout.WEST);
       
         JButton add = new JButton ("Search");
        add.addActionListener(this);
       //newUserLogin.addActionListener (this);
       bottom.add(add, BorderLayout.EAST);

       
            
       myPanel.add (top , BorderLayout.NORTH);
       myPanel .add (info, BorderLayout.CENTER);
       myPanel .add (bottom, BorderLayout.SOUTH);
       contentPane.add (myPanel);
       searchFrame.validate ();
    }
    
    
    @Override
    /*
    * If the search button is pressed an object of StringCheck is made
    */
     public void actionPerformed (ActionEvent e){
         String event = e.getActionCommand();
         if (event.equals ("Search")){
       if (selection.equals ("")){
            try {
                 JOptionPane.showMessageDialog(null, "Please Select an Option" );
                 new search();
                 searchFrame.dispose();
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
             new stringCheck();
             searchFrame.dispose();
    
         }
        
     
        
}
}
    

        