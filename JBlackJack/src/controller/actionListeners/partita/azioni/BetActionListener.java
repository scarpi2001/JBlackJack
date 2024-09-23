package controller.actionListeners.partita.azioni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.Controller;
import model.ModelManager;

/**
 * classe che definisce l'evento di "bet" che deve accadere al click di un componente swing
 */
public class BetActionListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		ModelManager.getInstance().getGiocatoreCorrente().bet(); //come argomento la somma che ho bettato (il valore della tb che sarà sotto)
		Controller.getInstance().distribuisciCarte();
		Controller.getInstance().gameloop();
	}
}
