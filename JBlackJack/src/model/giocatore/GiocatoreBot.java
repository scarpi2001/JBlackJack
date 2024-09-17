package model.giocatore;

/**
 * classe che rappresenta un giocatore bot,
 * non essendo comandato dall'utente gioca "a modo suo"
 */
public class GiocatoreBot extends Giocatore
{
	@Override
	/**
	 * sovrascrive il metodo gioca,
	 * implementando la logica che utilizza un dealer nel blackjack
	 * (chiede carta fino a che non arriva a 17)
	 */
    public void gioca() 
	{	
        while (getManoCorrente().getConteggio() < 17)
        {
        	hit(); 
        }
        stay();
    }
}
