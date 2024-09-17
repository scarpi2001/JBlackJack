package model.giocatore;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import model.FileUtils;
import model.carte.Mano;
import model.carte.Mazzo;

/**
 * classe che rappresenta l'utente che utilizza l'applicazione
 * che è anche un giocatore
 * 
 * principalmente questa classe serve a differenziare l'utente (del quale devo salvare i dati)
 * e gli altri giocatori che sono bot 
 */
public class GiocatoreUtente extends Giocatore
{
	//CAMPI
	private static GiocatoreUtente instance;
	
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
	private GiocatoreUtente(){}
	
	public static GiocatoreUtente getInstance()
	{
		if (instance == null) instance = new GiocatoreUtente();
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
	 * @param fileDatiUtentePath path del file che contiene i dati dell'utente
	 * @param fileUltimoUtentePath path del file che conterrà l'username dell'utente selezionato
	 */
	public void setDati(String fileDatiUtentePath, String fileUltimoUtentePath)
	{
        setFilePath(fileDatiUtentePath);
        List<String> lines = FileUtils.leggiFile(fileDatiUtentePath);

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
        FileUtils.scriviFile(fileUltimoUtentePath, getUsername(), false);
	}
	
	//CREAZIONE UTENTE
	/**
	 * metodo per creare un utente
	 * salva l'username inserito nel file fileUtentiPath
	 * e crea il file con il nome fileDatiUtentePath dedicato all'utente appena creato
	 * @param username nome dell'utente
	 * @param fileUtentiPath path del file degli utenti
	 * @param fileDatiUtentePath path del file dell'utente da creare
	 */
	//potrei estrapolare l'username da fileDatiUtentePath
	public void creaUtente(String username, String fileUtentiPath, String fileDatiUtentePath)
	{
		//salva l'username
		FileUtils.scriviFile(fileUtentiPath, username, true);
		
		//crea il file dell'utente
        creaFileUtente(username, fileDatiUtentePath);
	} 	
	/**
	 * metodo privato che crea il file del nuovo utente inserendo i dati necessari al suo interno"
	 * @param username l'username dell'utente legato al file
	 * @param path path del file da creare
	 * @return se l'operazione di creazione del file e di inserimento dati va a buon fine restituisce true altrimenti false
	 */
	private void creaFileUtente(String username, String path) 
	{
        List<String> contenuto = new ArrayList<>();
        contenuto.add("username:" + username);
        contenuto.add("chips:1000");
        contenuto.add("maniGiocate:0");
        contenuto.add("maniVinte:0");
        contenuto.add("maniPerse:0");
        contenuto.add("livello:0");

        FileUtils.scriviFile(path, contenuto, false);
	}
	
	//ELIMINAZIONE UTENTE
	/**
	 * metodo per eliminare l'utente 
	 * elimina l'utente dal file degli utenti 
	 * elimina il file dell'utente
	 * @param username l'username dell'utente da eliminare
	 * @param fileUtentiPath path del file degli utenti
	 * @param fileDatiUtentePath path del file dell'utente da eliminare
	 */
	//potrei estrapolare l'username da fileDatiUtentePath
	public void eliminaUtente(String username, String fileUtentiPath, String fileDatiUtentePath)
	{
		List<String> utenti = new ArrayList<>(FileUtils.leggiFile(fileUtentiPath));
        utenti.remove(username);
   
        //sovrascrivo il file con la lista di utenti aggiornata
        FileUtils.scriviFile(fileUtentiPath, utenti, false);
        
        //elimino il file legato all'utente
        new File(fileDatiUtentePath).delete();
	}
	
	//PARTITA
	@Override
	/**
	 * sovrascrive il metodo gioca,
	 * non facendo nulla, in un certo senso aspetta l'input dell'utente,
	 * perchè l'utente gioca attraverso l'input dei pulsanti
	 */
	public void gioca()
	{    
		
	}
}