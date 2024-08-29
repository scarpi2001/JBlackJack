package controller.actionListeners.partita;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.Controller;
import view.View;

public class BackActionListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		View view = View.getInstance();
    	Controller controller = Controller.getInstance();
    	
    	controller.back();
    	view.showMenuPanel(); 
	}

}
