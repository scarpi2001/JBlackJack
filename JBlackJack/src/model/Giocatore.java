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
	//CAMPI
	/**
	 * nome del giocatore
	 */
	private String username;
	
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
	
	//COSTRUTTORE
	public Giocatore()
	{
		if(!(this instanceof UtenteGiocante))
		{
			username = "bot";  
		}
		
		carte = new ArrayList<>();
	}
	
	//GETTERS E SETTERS
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
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

	//METODI
	//questa roba potrebbe andare nel controller (checkConteggio) e addCarta dovrebbe servire solo ad aggiungere una carta al mazzo del giocatore
	/**
	 * metodo per aggiungere una carta alla lista di carte del giocatore
	 * riconosce lo stato di "sballato" e di "blackjack"
	 * @param carta la carta da aggiugere
	 */
	public void addCarta(Carta carta) 
	{
		carte.add(carta);		
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
		
		System.out.println(username + ", carta: " + carta.toString() + ", " + conteggio + ", " + "soft hand: " + soft);
		
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
	
	/**
	 * metodo per riconoscere la condizione di split del giocatore
	 * @return true se il giocatore può splittare, false altrimenti
	 */
	public boolean canSplit()
	{
		if (carte.size() == 2) return carte.get(0).getSimbolo() == carte.get(1).getSimbolo();
		return false;
	}
	
	//la lista di carte potrebbe non servire, potrei usare solo il conteggio
	/**
	 * metodo per resettare lo stato del giocatore (svuotare la mano del giocatore dalle carte e reimpostare i flag a false e il conteggio a 0)
	 */
	public void resetStato() 
	{
		carte.clear();
		setConteggio(0);
		sballato = false;
		blackjack = false;
		soft = false;
	}
}
