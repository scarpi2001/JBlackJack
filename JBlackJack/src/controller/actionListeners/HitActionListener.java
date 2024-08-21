package controller.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ModelManager;

/**
 * classe che definisce l'evento di "hit" (cioè la richiesta di una carta al dealer) che deve accadere al click di un componente swing
 */
public class HitActionListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		ModelManager model = ModelManager.getInstance();
		model.hit();
	}

}
