
package librarymanagement;

import java.io.*;

import java.awt.*;
/**
 *
 * @author mallikarjuna
 */
public class help {
    public help () {
        
    if (Desktop.isDesktopSupported()) {
    try {
        File myFile = new File("help.pdf");
        Desktop.getDesktop().open(myFile);
    } catch (IOException ex) {
        // no application registered for PDFs
    }
}
}
}
