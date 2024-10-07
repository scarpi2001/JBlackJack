package controller.actionListeners.partita.timers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import controller.Controller;
import model.ModelManager;
import model.giocatore.Giocatore;

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
		//model.setDistribuzionePartita(true);
        if(giocatore.isManoTerminata()) giocatore.manoSuccessiva();
    	
        if(model.isPartitaFinita()) model.finePartita();
        else Controller.getInstance().gameloop();

        ((Timer) e.getSource()).stop();
       // model.setDistribuzionePartita(false);
    }
}
