package controller.actionListeners.partita.timers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import controller.Controller;
import model.ModelManager;
import model.giocatore.Giocatore;
import model.giocatore.GiocatoreDealer;
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
	 * rappresenta il giro di distribuzione, 
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
                
                //ricontrollo se l'utente ha terminato la mano 
                //questo controllo è necessario farlo dentro il timer perchè è asincrono
                //di norma ci penserebbe il gameloop ma se lo chiamo subito questa distribuzione asincrona
                //i controlli necessari vengono fatti prima che la distribuzione finisce 
                //e l'utente non passa alla mano successiva se fa bj             
                if (giocatore instanceof GiocatoreDealer && GiocatoreUtente.getInstance().isManoTerminata())
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