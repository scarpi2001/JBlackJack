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
	 * flag che indica se il giocatore ha "sballato" o no
	 */
	private boolean sballato;
	
	/**
	 * flag che indica se il giocatore ha fatto blackjack o no
	 */
	private boolean blackjack;
	
	/**
	 * flag per capire se sono in una "soft hand" o no
	 */
	private boolean soft;
	
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
	
	public boolean isBlackJack()
	{
		return blackjack;
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
	 * riconosce lo stato di "sballato" e di "blackjack"
	 * @param carta la carta da aggiugere
	 */
	//questa roba potrebbe andare nel main e addCarta dovrebbe servire solo ad aggiungere una carta al mazzo del giocatore
	public void addCarta(Carta carta) 
	{
		carte.add(carta);		
		System.out.println("Giocatore: " + username + ", carta: " + carta.toString());  
		
		conteggio += carta.getValore();
		
		//se ho beccato il primo asso, lo faccio valere 11 invece che 1, entrando in una "soft hand"
		if(conteggio <= 11 && carta.isAsso()) 
		{
			conteggio += 10;
			soft = true;
		}
		
		//se sono in una "soft hand" e ho "sballato" il primo asso torna a valere 1 invece che 11 ed esco dalla "soft hand" (hard hand)
		if(soft && conteggio > 21)
		{
			soft = false;
			conteggio -=10;
		}
		
		//check se sono sballato oppure se ho fatto blackjack
		if(conteggio == 21)
		{
			System.out.println("Blackjack!");
			System.out.println("");
			blackjack = true;
		}	
		if(conteggio > 21)
		{
			System.out.println("Sballato!");
			System.out.println("");
			sballato = true;
		}
	}
	
	//la lista di carte potrebbe non servire, potrei usare solo il conteggio
	/**
	 * metodo per svuotare la mano del giocatore dalle carte e resettare lo stato
	 */
	public void resetCarte() 
	{
		carte.removeAll(carte);
		setConteggio(0);
		sballato = false;
		blackjack = false;
	}
}
