package student.management.accountant;

import javax.swing.*;

public class feeException extends Throwable
{
    public feeException()
    {
        JOptionPane.showMessageDialog(null,"You have paid extra fees","Exception",JOptionPane.ERROR_MESSAGE);
    }
}
