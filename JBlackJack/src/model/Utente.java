package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * classe che rappresenta l'utente che utilizza l'applicazione
 */
public class Utente 
{
	private static Utente instance;
	
	/**
	 * nome dell'utente
	 */
	private String username;
	
	/**
	 * stringa del file che contiente i dati dell'utente
	 */
	private String file;
	
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
	
	
	private Utente()
	{
		
	}
	
	public static Utente getInstance()
	{
		if (instance == null) instance = new Utente();
		return instance;
	}

	
	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getFile() 
	{
		return file;
	}

	public void setFile(String file)
	{
		this.file = file;
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
	
	/**
	 * metodo che aggiorna i dati dell’utente prendendoli dal file passato in input
	 * @param nomeFile nome del file che contiene i dati dell'utente
	 */
	public void setDati(String nomeFile)
	{
		setFile(nomeFile);
		try (BufferedReader reader = new BufferedReader(new FileReader(nomeFile))) {
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
	}
	
}
