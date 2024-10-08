package controller.actionListeners.partita.azioniUtente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.Controller;
import model.ModelManager;
import view.View;

/**
 * definisce l'evento di "split" che deve accadere al click di un componente swing
 */
public class SplitActionListener implements ActionListener
{
	@Override
	/**
	 * aggiunge una carta al giocatore giusto in base al turno della partita
	 */
	public void actionPerformed(ActionEvent e) 
	{	
		ModelManager model = ModelManager.getInstance();
		
		if (model.getChipsUtente() - model.getScommessaUtentePartita() < 0) 
        {
        	View.showError("non hai abbastanza chips per splittare");
        	return;
        }
				
		model.getGiocatoreCorrente().split();
		Controller.getInstance().gameloop();
	}
}
