package controller.actionListeners.partita;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import controller.Controller;
import model.Giocatore;
import model.ModelManager;

/**
 * classe che definisce l'evento di "hit" (cioè la richiesta di una carta al dealer) che deve accadere al click di un componente swing
 */
public class HitActionListener implements ActionListener
{
	@Override
	/**
	 * aggiunge una carta al giocatore giusto in base al turno della partita
	 */
	public void actionPerformed(ActionEvent e) 
	{
		Controller controller = Controller.getInstance();
		controller.hit();
	}
}
