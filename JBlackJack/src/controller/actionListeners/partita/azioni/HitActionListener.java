package controller.actionListeners.partita.azioniUtente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.Controller;
import model.ModelManager;

/**
 * definisce l'evento di "hit" (cioè la richiesta di una carta) che deve accadere al click di un componente swing
 */
public class HitActionListener implements ActionListener
{
	@Override
	/**
	 * aggiunge una carta al giocatore giusto in base al turno della partita
	 */
	public void actionPerformed(ActionEvent e) 
	{		
		ModelManager.getInstance().getGiocatoreCorrente().hit();
		Controller.getInstance().gameloop();
	}
}
