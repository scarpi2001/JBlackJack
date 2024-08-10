package model;

import model.carte.Carta;
import model.carte.Mazzo;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * classe che rappresenta il model
 */
public class ModelManager extends Observable 
{
	//CAMPI
	/**
	 * istanza del model
	 */
	private static ModelManager instance;
	
	/**
	 * utente che sta utilizzando l'applicazione
	 */
	private UtenteGiocante utente;
	
	/**
	 * numero di giocatori che partecipano alla partita
	 */
	private int numeroGiocatori;
	
	/**
	 * lista dei giocatori che partecipano alla partita
	 */
	private List<Giocatore> giocatori;
	
	/**
	 * mazzo di carte utilizzato in partita
	 */
	private Mazzo mazzo;
	
	/**
	 * variabile utilizzata per stabilire quale giocatore deve giocare
	 */
	private int turno;
	
	//COSTRUTTORE
	/**
	 * il costruttore del model, che istanzia l'utente
	 */
	private ModelManager()
	{
		utente = UtenteGiocante.getInstance();
		giocatori = new ArrayList<>();
		giocatori.add(utente);
	}
	
	/**
	 * metodo per ottenere/creare l'istanza del model
	 * @return l'istanza del model
	 */
	public static ModelManager getInstance()
	{
		if (instance == null) instance = new ModelManager();
		return instance;
	}
	
	//GETTERS E SETTERS
	public void setGiocatori(int numeroGiocatori) 
	{		
		this.numeroGiocatori = numeroGiocatori;
	}
	
	//GETTERS E SETTERS DELL'UTENTE
	public String getUtenteUsername()
	{
		return utente.getUsername();
	}
	
	public String getUtenteFilePath()
	{
		return utente.getFilePath();
	}
	
	public int getUtenteChips()
	{
		return utente.getChips();
	}
	
	public int getUtenteManiGiocate()
	{
		return utente.getManiGiocate();
	}
	
	public int getUtenteManiVinte()
	{
		return utente.getManiVinte();
	}
	
	public int getUtenteManiPerse()
	{
		return utente.getManiPerse();
	}
	
	public int getUtenteLivello()
	{
		return utente.getLivello();
	}
	
	//METODI UTENTE
	/**
	 * metodo che aggiorna i dati dell’utente prendendoli dal file passato in input,
	 * e notifica gli osservatori
	 * @param nomeFile nome del file che contiene i dati dell'utente
	 */
	public void setUtente(String nomeFile)
	{
		utente.setDati(nomeFile);        
		setChanged();
		notifyObservers();
	}
	
	/**
	 * metodo per creare l'utente 
	 * crea l'utente e lo setta
	 * @param username l'username dell'utente
	 */
	public void creaUtente(String username)
	{
		utente.creaUtente(username);
		setUtente("src/resources/data/dati_utenti/" + username + "_dati.txt");
	}
	
	/**
	 * metodo per eliminare l'utente 
	 * elimina l'utente e setta il primo della select degli utenti 
	 * @param username l'username dell'utente da eliminare
	 */
	public void eliminaUtente(String username)
	{
		utente.eliminaUtente(username);
		//setto il primo utente della lista di utenti 
		setUtente("src/resources/data/dati_utenti/" + getUtenti("src/resources/data/utenti.txt")[0] + "_dati.txt");
	}
	
	/**
	 * metodo per leggere gli utenti da un file di utenti 
	 * @param path path del file che contiene gli utenti
	 * @return la lista degli utenti
	 */
	public String[] getUtenti(String path) 
	{
		ArrayList<String> utenti = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) 
		{
			String riga;
			while ((riga = reader.readLine()) != null)
			{
				utenti.add(riga.trim());
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return utenti.toArray(new String[0]);
	}
	
	/**
	 * metodo per leggere l'utente da un file contenente l'ultimo utente selezionato
	 * @param path path del file che contiene i dati dell'utente
	 */
	public String getUltimoUtente(String path) 
	{
        String username = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) 
        {
            String riga;
            while ((riga = reader.readLine()) != null) 
            {
                username = riga;
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return username;
    }
	
	//METODI PARTITA
	//(questi aspetti della partita potrebbero dover essere trasferiti nel controller)
	/**
	 * metodo che inizializza la partita
	 */
	public void initPartita()
	{
		initMazzo();
		initGiocatori();
		
		//per ogni giocatore devo distribuire due carte
		int n = 0;
        for(Giocatore giocatore : giocatori)
        {
        	Carta carta1 = mazzo.carta();
        	Carta carta2 = mazzo.carta();
        			
        	n++;
        	giocatore.addCarta(carta1);	
        	giocatore.addCarta(carta2);
 	
        	System.out.println("");  
        }
	}
	
	/**
	 * metodo che inizializza il mazzo
	 */
	private void initMazzo()
	{
		mazzo = new Mazzo();
		mazzo.mix();
		
		/*
		boolean finito = false;
        while (finito == false)
        {
            Carta carta = mazzo.hit();
            if(carta == null)
            {
            	System.out.println(""); 
                System.out.println("Mazzo finito");
                
                finito = true;
            }
            else
            {
                System.out.println(carta.getImmagine());   
            }
        }
        */
	}
	
	/**
	 * metodo che inizializza la lista di giocatori che partecipano alla partita
	 */
	private void initGiocatori()
	{
		for(int i = 0; i < numeroGiocatori - 1; i++)
		{	
			giocatori.add(new Giocatore());
		}	
	}
	
	/**
	 * metodo che aggiunge una carta al giocatore giusto in base al turno della partita
	 */
	public void hit()
	{
		giocatori.get(turno).addCarta(mazzo.carta());
	}
}