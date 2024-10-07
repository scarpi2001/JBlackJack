package controller.actionListeners.partita.timers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import controller.Controller;
import model.ModelManager;
import model.giocatore.Giocatore;

/**
 * si occupa di gestire il controllo dello stato della mano del giocatore corrente e della partita (che deve avvenire alla fine di un timer),
 * per poi proseguire con il gameloop o terminarlo se necessario
 */
public class TimerGameLoopActionListener implements ActionListener
{
	private Giocatore giocatore;
	
	public TimerGameLoopActionListener(Giocatore giocatore)
	{
		this.giocatore = giocatore;
	}
	
	@Override
    public void actionPerformed(ActionEvent e) 
    { 		
		ModelManager model = ModelManager.getInstance();

        if(giocatore.isManoTerminata()) giocatore.manoSuccessiva();
    	
        if(model.isPartitaFinita()) model.finePartita();
        else Controller.getInstance().gameloop();

        ((Timer) e.getSource()).stop();
    }
}
