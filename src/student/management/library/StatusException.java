package student.management.library;

import javax.swing.*;

public class StatusException extends Throwable {

    public StatusException() {
        JOptionPane.showMessageDialog(null,"Book is already Issued by student! Collect the 1st issued book","Exception",JOptionPane.ERROR_MESSAGE);
    }
}
