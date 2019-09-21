package student.management.utility;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRender extends JButton implements  TableCellRenderer
{
    public ButtonRender()
    {
        setOpaque(true);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focus, int row, int col)
    {
        if(selected)
        {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        }

        else{
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }

        setText( (value ==null) ? "" : value.toString() );
        return this;

    }
}
