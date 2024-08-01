package model;
import java.io.*;
import java.util.ArrayList;
import java.util.Observable;

/**
 * classe che rappresenta il model
 */
public class ModelManager extends Observable 
{
	private static ModelManager instance;
	
	/**
	 * utente che sta utilizzando l'applicazione
	 */
	private Utente utente;
	
	/**
	 * il costruttore del model, che istanzia l'utente
	 */
	private ModelManager()
	{
		utente = Utente.getInstance();
	}
	
	public static ModelManager getInstance()
	{
		if (instance == null) instance = new ModelManager();
		return instance;
	}
	
	//INIZIO METODI UTENTE
	public String getUtenteUsername()
	{
		return utente.getUsername();
	}
	
	
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
	public String getUltimoUtenteUsername(String path) {
        String username = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String riga;
            while ((riga = reader.readLine()) != null) {
                username = riga;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return username;
    }
	//FINE METODI UTENTE
	
}

