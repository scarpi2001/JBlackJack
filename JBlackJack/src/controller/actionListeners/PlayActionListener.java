package controller.actionListeners;

import java.awt.event.*;
import view.View;

/**
 * classe che definisce l'evento di avvio partita al click di un componente swing
 */
public class PlayActionListener implements ActionListener
{
    @Override
	public void actionPerformed(ActionEvent e) 
	{      
    	View view = View.getInstance();
    	view.showGamePanel();
	}
}
