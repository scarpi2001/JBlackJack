package controller.actionListeners.partita;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ModelManager;
import view.View;

public class BackActionListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) 
	{
    	ModelManager.getInstance().clearGiocatori();
    	View.getInstance().showMenuPanel(); 
	}

}
