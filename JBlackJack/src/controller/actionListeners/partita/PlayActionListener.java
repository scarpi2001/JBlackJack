package controller.actionListeners.partita;

import java.awt.event.*;

import controller.Controller;
import controller.JBlackJack;
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
    	View view = View.getInstance();
    	Controller controller = Controller.getInstance();
    	
    	controller.initPartita();
    	view.showGamePanel();    	
	}
}
