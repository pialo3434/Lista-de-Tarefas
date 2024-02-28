import java.awt.Component;
import java.awt.Container;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class metodos_gerais {
		
	  //------------------------------------------------------------------------------------------
	  //Caixas de dialogo 
	
	  public static void showDialogError(String title ,String msg) {
		  JOptionPane.showMessageDialog(null, title, msg, JOptionPane.ERROR_MESSAGE);
	  }
	  
	  public static void showDialogInfo(String title ,String msg) {
		  JOptionPane.showMessageDialog(null, title, msg, JOptionPane.INFORMATION_MESSAGE);
	  }
	  
	  public static void showDialogWarning(String title ,String msg) {
		  JOptionPane.showMessageDialog(null, title, msg, JOptionPane.WARNING_MESSAGE);
	  }
	  
	 //------------------------------------------------------------------------------------------
	  
	  //Metodo para limpar text fields e resetar as combobox
	  public void clearTextComponents(Object obj) {
		    if (obj instanceof JTextField) {
		        ((JTextField) obj).setText("");
		    } else if (obj instanceof JTextArea) {
		        ((JTextArea) obj).setText("");
		    } else if (obj instanceof JComboBox) {
		        ((JComboBox<?>) obj).setSelectedIndex(0);
		    }
		    if (obj instanceof Container) {
		        for (Component c : ((Container) obj).getComponents()) {
		            clearTextComponents(c);
		        }
		    }
		}
	  
	  //Limpar as rows que estao na tabela
	  public void clearTableRows(JTable table) {
		    DefaultTableModel model = (DefaultTableModel) table.getModel();
		    model.setRowCount(0);
		}

}
