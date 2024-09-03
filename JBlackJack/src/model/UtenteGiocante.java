package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import model.partita.Giocatore;

/**
 * classe che rappresenta l'utente che utilizza l'applicazione
 * che è anche un giocatore
 * 
 * principalmente questa classe serve a differenziare l'utente (del quale devo salvare i dati)
 * e gli altri giocatori che sono bot 
 */
public class UtenteGiocante extends Giocatore
{
	//CAMPI
	private static UtenteGiocante instance;
	
	/**
	 * path del file che contiente i dati dell'utente
	 */
	private String filePath;
	
	/**
	 * numero di chips possedute dall'utente
	 */
	private int chips;
	
	/**
	 * numero di mani giocate dall'utente
	 */
	private int maniGiocate;
	
	/**
	 * numero di mani vinte dall'utente
	 */
	private int maniVinte;
	
	/**
	 * numero di mani perse dall'utente
	 */
	private int maniPerse;
	
	/**
	 * livello di esperienza dell'utente
	 */
	private int livello;
	
	/**
	 * numero di partite giocate
	 */
	private int partite;
	
	//COSTRUTTORE
	private UtenteGiocante(){}
	
	/**
	 * metodo per ottenere/creare l'istanza dell'utente
	 * @return l'istanza dell'utente
	 */
	public static UtenteGiocante getInstance()
	{
		if (instance == null) instance = new UtenteGiocante();
		return instance;
	}
	
	//GETTERS E SETTERS
	public String getFilePath() 
	{
		return filePath;
	}
	public void setFilePath(String file)
	{
		this.filePath = file;
	}

	public int getChips() {
		return chips;
	}
	public void setChips(int chips)
	{
		this.chips = chips;
	}

	public int getManiGiocate() 
	{
		return maniGiocate;
	}
	public void setManiGiocate(int maniGiocate) 
	{
		this.maniGiocate = maniGiocate;
		FileUtils.aggiornaCampoFile(getFilePath(), "maniGiocate", Integer.toString(maniGiocate));
	}

	public int getManiVinte() 
	{
		return maniVinte;
	}
	public void setManiVinte(int maniVinte) 
	{
		this.maniVinte = maniVinte;
	}

	public int getManiPerse() 
	{
		return maniPerse;
	}
	public void setManiPerse(int maniPerse)
	{
		this.maniPerse = maniPerse;
	}

	public int getLivello()
	{
		return livello;
	}
	public void setLivello(int livello) 
	{
		this.livello = livello;
	}
	
	//SET UTENTE
	/**
	 * metodo che aggiorna i dati dell’utente prendendoli dal file passato in input
	 * e scrive l'username dell'utente nel file "ultimo_utente.txt"
	 * @param path nome del file che contiene i dati dell'utente
	 */
	public void setDati(String path)
	{
        setFilePath(path);
        List<String> lines = FileUtils.leggiFile(path);

        for (String line : lines) {
            String[] keyValue = line.split(":");
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();

            switch (key) {
                case "username":
                    setUsername(value);
                    break;
                case "chips":
                    setChips(Integer.parseInt(value));
                    break;
                case "maniGiocate":
                    setManiGiocate(Integer.parseInt(value));
                    break;
                case "maniVinte":
                    setManiVinte(Integer.parseInt(value));
                    break;
                case "maniPerse":
                    setManiPerse(Integer.parseInt(value));
                    break;
                case "livello":
                    setLivello(Integer.parseInt(value));
                    break;
            }
        }

        // Scrivi l'username nel file ultimo_utente.txt
        FileUtils.scriviFile("src/resources/data/ultimo_utente.txt", getUsername(), false);
	}
	
	//CREAZIONE UTENTE
	/**
	 * metodo per creare un utente
	 * prima controlla che l'username inserito non sia gia stato salvato in precedenza
	 * poi salva l'username inserito nel file "utenti.txt"
	 * infine crea il file dedicato all'utente appena creato
	 * @return true se la creazione va a buon fine, altrimenti false
	 */
	public boolean creaUtente(String username)
	{
		//se il file degli utenti contiene gia l'username inserito
		if (FileUtils.leggiFile("src/resources/data/utenti.txt").contains(username)) return false;
        
		//salva l'username
		FileUtils.scriviFile("src/resources/data/utenti.txt", username, true);
		
		//crea il file dell'utente e ritorna true
        creaFileUtente(username);
        return true;
	} 	
	/**
	 * metodo privato che crea il file del nuovo utente inserendo i dati necessari al suo interno"
	 * @param filename nome del file passato in input
	 * @param username l'username passato in input
	 * @return se l'operazione di creazione del file e di inserimento dati va a buon fine restituisce true altrimenti false
	 */
	private void creaFileUtente(String username) 
	{
		String filePath = "src/resources/data/dati_utenti/" + username + "_dati.txt";
        List<String> contenuto = new ArrayList<>();
        contenuto.add("username:" + username);
        contenuto.add("chips:1000");
        contenuto.add("maniGiocate:0");
        contenuto.add("maniVinte:0");
        contenuto.add("maniPerse:0");
        contenuto.add("livello:0");

        FileUtils.scriviFile(filePath, contenuto, false);
	}
	
	//ELIMINAZIONE UTENTE
	/**
	 * metodo per eliminare l'utente 
	 * elimina l'utente dal file degli utenti 
	 * elimina il file dell'utente
	 * @param username l'username dell'utente da eliminare
	 */
	public void eliminaUtente(String username)
	{
		List<String> utenti = new ArrayList<>(FileUtils.leggiFile("src/resources/data/utenti.txt"));
        utenti.remove(username);
   
        //sovrascrivo il file con la lista di utenti aggiornata
        FileUtils.scriviFile("src/resources/data/utenti.txt", utenti, false);
        
        //elimino il file legato all'utente
        new File("src/resources/data/dati_utenti/" + username + "_dati.txt").delete();
	}
}
