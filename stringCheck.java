/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagement;

import javax.swing.JOptionPane;

/**
 *
 * @author mallikarjuna
 */
public class stringCheck {
    /*
    * the selection made the user is inputted
    * The appropriate method is called based on the selection
    */
    public stringCheck(){
        String data = search.input.getText();
        
        
        String input = search.selection;
        if (input.equals ("Book Title")){
            new titlesearch();
            
            }
        if (input.equals ("Author")){
            new authorsearch();
        }
        if (input.equals ("BookID")){
            new IDsearch();
        }
        if (input.equals ("Publisher")){
            new Publishersearch();
        }

    }
}

