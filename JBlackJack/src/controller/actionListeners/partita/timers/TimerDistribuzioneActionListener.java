package controller.actionListeners.partita.timers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import controller.Controller;
import model.ModelManager;
import model.giocatore.Giocatore;

public class TimerDistribuzioneActionListener implements ActionListener
{

	private int index = 1;
    private int giro = 1;
    
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		ModelManager model = ModelManager.getInstance();
		if (giro == 1)
        {
            if (index < model.getGiocatoriPartita().size())
            {
                Giocatore giocatore = model.getGiocatoriPartita().get(index);
                giocatore.hit();
                index++;
            } 
            else //passo al secondo giro
            {        
                index = 0;
                giro = 2;
            }
        } 
        else if (giro == 2) 
        {
            if (index < model.getGiocatoriPartita().size()) 
            {
                Giocatore giocatore = model.getGiocatoriPartita().get(index);
                giocatore.hit();
                
                //ricontrollo se ho terminato la mano (per un eventuale bj)
                if (giocatore.isManoTerminata())
                {
                    ((Timer)e.getSource()).stop();
                    Controller.getInstance().gameloop();  
                    return;
                }
                
                index++;
            } 
            else
            {
                //fermo il timer dopo il secondo giro
                ((Timer)e.getSource()).stop();
            }
        }
    }
}