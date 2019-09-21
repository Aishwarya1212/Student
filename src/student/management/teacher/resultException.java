package student.management.teacher;

import javax.swing.*;

public class resultException extends Throwable
{
    public resultException(int due1) {
        JOptionPane.showMessageDialog(null,"Total Can't Be Greater than 300! Each Subject has 100 Marks","Exception",JOptionPane.ERROR_MESSAGE);
    }
}
