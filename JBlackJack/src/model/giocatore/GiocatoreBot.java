package model.giocatore;

import model.ModelManager;
import model.carte.Mano;

/**
 * classe che rappresenta un giocatore bot,
 * non essendo comandato dall'utente gioca "a modo suo"
 */
public class GiocatoreBot extends Giocatore
{
	/**
	 * sovrascrive il metodo gioca,
	 * implementando la logica che utilizza un dealer nel blackjack
	 * (chiede carta fino a che non arriva a 17)
	 */
	@Override
    public void gioca() 
	{	
        while (getManoCorrente().getConteggio() < 17)
        {
        	hit(); 
        }
        stay();
    }
	
	/**
	 * sovrascrive il metodo confrontaManoConDealer,
	 * confronta la singola mano del bot con quella del dealer
	 * e aggiorna solo lo stato della mano
	 * @param mano La mano del giocatore da confrontare
	 * @param manoDealer La mano del dealer
	 */
	@Override
	public void confrontaManoConDealer(Mano mano, Mano manoDealer)
    {
    	int conteggioDealer = manoDealer.getConteggio();
        int conteggioMano = mano.getConteggio();

        if (manoDealer.isBusted() || conteggioMano > conteggioDealer) mano.setStato(Mano.Stato.VINTA);
        else if (conteggioMano < conteggioDealer)mano.setStato(Mano.Stato.PERSA); 
        else mano.setStato(Mano.Stato.PAREGGIATA);      
    }
}
