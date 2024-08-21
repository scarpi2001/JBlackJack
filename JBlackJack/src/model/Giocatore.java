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
	
	/**
	 * booleano che indica se il giocatore ha "sballato" o no
	 */
	private boolean sballato;
	
	/**
	 * conteggio dei punti delle carte del giocatore
	 */
	private int conteggio;
	
	public Giocatore()
	{
		if(!(this instanceof UtenteGiocante))
		{
			username = "bot";  
		}
		
		carte = new ArrayList<>();
	}
	
	public boolean isBusted()
	{
		return sballato;
	}
	
	public int getConteggio()
	{
		return conteggio;
	}
	
	public void setConteggio(int conteggio)
	{
		this.conteggio = conteggio;
	}
	
	/**
	 * metodo per aggiungere una carta alla lista di carte del giocatore
	 * e per riconoscere lo stato di "sballato"
	 * @param carta la carta da aggiugere
	 */
	//questa roba potrebbe andare nel main e addCarta dovrebbe servire solo ad aggiungere una carta al mazzo del giocatore
	public void addCarta(Carta carta) 
	{
		carte.add(carta);
		System.out.println("Giocatore: " + username + ", carta: " + carta.toString());  
		
		if(conteggio <= 10) conteggio += carta.getValore();
		else conteggio += carta.getValoreAlternativo();
		
		if(conteggio == 21)
		{
			System.out.println("Blackjack!");
		}
		if(conteggio > 21)
		{
			System.out.println("Sballato!");
			sballato = true;
		}
	}
	
	//la lista di carte potrebbe non servire, potrei usare solo il conteggio
	public void svuotaCarte() 
	{
		carte.removeAll(carte);
		setConteggio(0);
		sballato = false;
	}

}
