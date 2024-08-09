package controller.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import model.ModelManager;

/**
 * classe che definisce l'evento di set del numero di giocatori che deve accadere al click di un componente swing
 */
public class SetGiocatoriActionListener implements ActionListener 
{

	private JComboBox<Integer> comboBox;
	
    public SetGiocatoriActionListener(JComboBox<Integer> comboBox) 
    {
        this.comboBox = comboBox;
    }
	
    @Override
	public void actionPerformed(ActionEvent e) 
	{           
		ModelManager model = ModelManager.getInstance();
		model.setGiocatori((int)comboBox.getSelectedItem());
	}

}
