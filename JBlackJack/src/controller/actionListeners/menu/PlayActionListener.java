package controller.actionListeners.menu;

import java.awt.event.*;

import model.ModelManager;
import view.View;

/**
 * classe che definisce l'evento di avvio partita al click di un componente swing
 */
public class PlayActionListener implements ActionListener
{
    @Override
	public void actionPerformed(ActionEvent e) 
	{      
    	View.getInstance().showGamePanel();    	
    	ModelManager.getInstance().initPartita();
	}
}
