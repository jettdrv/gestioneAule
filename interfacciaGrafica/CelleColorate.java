package interfacciaGrafica;


import javax.swing.table.*;
import javax.swing.*;
import java.awt.*;

/**
 * Classe che si occupa della rappresentazione di ogni cella della tabella. Il suo costruttore colora la cella solo
 * se si trova una prenotazione. Si puo' modificare sia il colore dello sfondo, che il colore del testo.
 */
public class CelleColorate extends DefaultTableCellRenderer{
	
	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (value != null && column != 0) {
            c.setBackground(Color.gray); 
            c.setForeground(Color.WHITE); 
        } else {
            c.setBackground(Color.WHITE); 
            c.setForeground(Color.BLACK); 
        }

        return c;
    }
}
	

