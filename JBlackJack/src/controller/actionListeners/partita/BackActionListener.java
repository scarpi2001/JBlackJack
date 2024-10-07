package controller.actionListeners.partita;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ModelManager;
import view.View;

/**
 * definisce l'evento di tornare al menu al click di un componente swing
 */
public class BackActionListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e) 
	{
    	ModelManager.getInstance().back();
    	View.getInstance().showMenuPanel(); 
	}

}
