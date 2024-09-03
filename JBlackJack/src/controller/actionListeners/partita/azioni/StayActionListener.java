package controller.actionListeners.partita.azioni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.Controller;
import view.View;

/**
 * classe che definisce l'evento di "stay" (cioè la richiesta al dealer, di fermarsi e non ricevere piu carte) che deve accadere al click di un componente swing
 */
public class StayActionListener implements ActionListener 
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Controller.getInstance().stay();
	}
}
