package model.giocatore;

import java.util.List;

import model.ModelManager;
import model.carte.Mano;

/**
 * rappresenta un giocatore bot,
 * segue determinate regole di gioco
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
		if(getManoCorrente().getConteggio() < 17) hit();		
		else stay();
	}
	
    /**
     * confronta le mani del giocatore con quella del dealer,
     * e aggiorna lo stato delle mani
     */
	public void aggiornaStats()
	{
		ModelManager model = ModelManager.getInstance(); 
		List<Giocatore> giocatori = model.getGiocatoriPartita();
    	Giocatore dealer = giocatori.get(giocatori.size() - 1);
    	Mano manoDealer = dealer.getMani().get(0);
    	
		for (Mano mano : getMani())
        {
            if (mano.getStato() == Mano.Stato.IN_CORSO) confrontaManoConDealer(mano, manoDealer);                     
        }
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
