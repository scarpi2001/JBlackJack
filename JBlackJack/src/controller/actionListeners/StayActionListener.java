package controller.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ModelManager;

/**
 * classe che definisce l'evento di "stay" (cioè la richiesta al dealer, di fermarsi e non ricevere piu carte) che deve accadere al click di un componente swing
 */
public class StayActionListener implements ActionListener 
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		ModelManager model = ModelManager.getInstance();
		model.stay();
	}

}
