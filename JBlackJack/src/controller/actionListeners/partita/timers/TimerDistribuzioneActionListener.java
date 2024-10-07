package controller.actionListeners.partita.timers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import controller.Controller;
import model.ModelManager;
import model.giocatore.Giocatore;
import model.giocatore.GiocatoreUtente;

/**
 * si occupa di gestire la distribuzione iniziale delle carte che deve avvenire alla fine di un timer
 */
public class TimerDistribuzioneActionListener implements ActionListener
{
	/**
	 * rappresenta l'indice del giocatore a cui viene distribuita la carta.
	 */
	private int index = 1;
	
	/**
	 * rappresenta il giro di distribuzione
	 * la distribuzione è suddivisa in due giri
	 */
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
                
                //ricontrollo se ho terminato la mano 
                //questo controllo è necessario perchè il timer è asincrono
                //quindi il passaggio alla mano successiva (che viene fatto nel gameloop) nel caso di un bj verrebbe saltato 
                //perchè nel momento in cui dovrebbe essere invocato, la mano non è ancora bj e quindi non è ancora terminata
                if (giocatore instanceof GiocatoreUtente && giocatore.isManoTerminata())
                {
                	model.setDistribuzionePartita(false);
                    ((Timer)e.getSource()).stop();

                    Controller.getInstance().gameloop();  
                    return;
                }
                
                index++;
            } 
            else
            {
            	model.setDistribuzionePartita(false);
                ((Timer)e.getSource()).stop();
            }
        }
    }
}