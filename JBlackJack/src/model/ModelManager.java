package model;
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
	private static Utente utente;
	
	/**
	 * il costruttore del model, che istanzia l'utente
	 */
	private ModelManager()
	{
		ModelManager.utente = Utente.getInstance();
	}
	
	public static ModelManager getInstance()
	{
		if (instance == null) instance = new ModelManager();
		return instance;
	}
	
	/**
	 * metodo che aggiorna i dati dell’utente prendendoli dal file passato in input
	 * e notifica gli osservatori
	 * @param nomeFile nome del file che contiene i dati dell'utente
	 */
	public void setUser(String nomeFile)
	{
		setChanged();
		notifyObservers();
	}
}

