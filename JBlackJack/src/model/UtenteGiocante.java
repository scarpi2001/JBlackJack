package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import view.View;

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
		//e cambia maniGiocate nel file
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
	 * @param nomeFile nome del file che contiene i dati dell'utente
	 */
	public void setDati(String nomeFile)
	{
		setFilePath(nomeFile);
		try (BufferedReader reader = new BufferedReader(new FileReader(nomeFile))) 
		{
	        String riga;
	        while ((riga = reader.readLine()) != null) 
	        {
	            String[] keyValue = riga.split(":");
	            String key = keyValue[0].trim();
                String value = keyValue[1].trim();

                switch (key) 
                {
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
	    } 
		catch (IOException e) 
		{
	        e.printStackTrace();
	    }
		
		//scrivo l'username nel file ultimo_utente.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/data/ultimo_utente.txt"))) 
        {
            writer.write(getUsername());
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
	}
	
	//CREAZIONE UTENTE
	/**
	 * metodo per creare un utente
	 * prima controlla che l'username inserito non sia gia stato salvato in precedenza
	 * poi salva l'username inserito nel file "utenti.txt"
	 * infine crea il file dedicato all'utente appena creato
	 */
	public void creaUtente(String username)
	{
		if (controllaUsername(username)) 
		{
			View.showError("Questo username è già stato preso.");
            return;
        }
		salvaUsername(username);
        creaFileUtente(username);
	}
	
	/**
     * metodo privato che controlla l'eventuale presenza dell'username passato in input nel file "utenti.txt"
     * @param username l'username passato in input
     * @return se l'username è presente nel file restituisce true altrimenti false
     */
	private boolean controllaUsername(String username) 
	{
        try (BufferedReader reader = new BufferedReader(new FileReader("src/resources/data/utenti.txt"))) 
        {
            String riga;
            while ((riga = reader.readLine()) != null)
            {
                if (riga.trim().equals(username)) 
                {
                    return true;
                }
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return false;
    }
	
    /**
     * metodo privato che salva l'username passato in input nel file "utenti.txt"
     * @param username l'username passato in input
     * @return se l'operazione di inserimento va a buon fine restituisce true altrimenti false
     */
	private void salvaUsername(String username) 
	{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/data/utenti.txt", true))) 
        {
            writer.write(username);
            writer.newLine();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    	
	/**
	 * metodo privato che crea il file del nuovo utente inserendo i dati necessari al suo interno"
	 * @param filename nome del file passato in input
	 * @param username l'username passato in input
	 * @return se l'operazione di creazione del file e di inserimento dati va a buon fine restituisce true altrimenti false
	 */
	private void creaFileUtente(String username) 
	{
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/data/dati_utenti/" + username + "_dati.txt")))
		{
			writer.write("username:" + username);
            writer.newLine();
            
            writer.write("chips:1000");
            writer.newLine();
            
            writer.write("maniGiocate:0");
            writer.newLine();
            
            writer.write("maniVinte:0");
            writer.newLine();
            
            writer.write("maniPerse:0");
            writer.newLine();
            
            writer.write("livello:0");
            writer.newLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
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
		ModelManager model = ModelManager.getInstance();
		List<String> utenti = new ArrayList<>(Arrays.asList(model.getUtenti("src/resources/data/utenti.txt")));
        utenti.remove(username);
   
        //sovrascrivo il file con la lista di utenti aggiornata
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/data/utenti.txt"))) 
        {
            for (String utente : utenti) 
            {
            	writer.write(utente);
            	writer.newLine();
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
        //elimino il file legato all'utente
        new File("src/resources/data/dati_utenti/" + username + "_dati.txt").delete();

	}
}
