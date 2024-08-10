package model;

import java.util.ArrayList;
import java.util.List;

import model.carte.Carta;

/**
 * classe che rappresenta il giocatore
 * può essere un utente reale oppure un bot
 */
public class Giocatore 
{
	/**
	 * nome del giocatore
	 */
	protected String username;
	
	/**
	 * lista di carte utilizzata durante la partita
	 */
	private List<Carta> carte;
	
	public Giocatore()
	{
		if(!(this instanceof UtenteGiocante))
		{
			username = "bot";  
		}
		
		carte = new ArrayList<>();
	}
	
	/**
	 * metodo per aggiungere una carta alla lista di carte del giocatore
	 * @param carta la carta da aggiugere
	 */
	public void addCarta(Carta carta) 
	{
		carte.add(carta);
		System.out.println("Giocatore: " + username + ", carta: " + carta.getImmagine());  
	}

}
