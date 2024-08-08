package controller.actionListeners;

import java.awt.event.*;
import view.View;
import model.ModelManager;

/**
 * classe che definisce l'evento di avvio partita al click di un componente swing
 */
public class PlayActionListener implements ActionListener
{
    @Override
	public void actionPerformed(ActionEvent e) 
	{      
    	ModelManager model = ModelManager.getInstance();
    	View view = View.getInstance();
    	view.showGamePanel();
    	
    	model.initMazzo();
	}
}
