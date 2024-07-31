package controller.actionListeners;

import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import model.ModelManager;

/**
 * classe che contiene la logica di creazione dell'utente
 */
public class CreateUserActionListener implements ActionListener
{
	private String username;
	
	public CreateUserActionListener(String username) {
        this.username = username;
    }
	
    @Override
	public void actionPerformed(ActionEvent e) 
	{           
		ModelManager model = ModelManager.getInstance();
		
		//controlla se c'è un utente con lo stesso nome
		if (controllaUsername(username)) {
			JOptionPane.showMessageDialog(null,"Questo username è già stato preso.", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
		
		//salva utente in utenti.txt e crea file per il nuovo utente
		salvaUsername(username);
        creaFileUtente(username);
        
        //setta l'utente appena creato
		model.setUtente("src/resources/data/dati_utenti/" + username + "_dati.txt");
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
	private boolean salvaUsername(String username) 
	{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/data/utenti.txt", true))) 
        {
            writer.write(username);
            writer.newLine();
            return true;
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return false;
    }
    	
	/**
	 * metodo privato che crea il file del nuovo utente inserendo i dati necessari al suo interno"
	 * @param filename nome del file passato in input
	 * @param username l'username passato in input
	 * @return se l'operazione di creazione del file e di inserimento dati va a buon fine restituisce true altrimenti false
	 */
	private boolean creaFileUtente(String username) 
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
            
            return true;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return false;
	}
   
}