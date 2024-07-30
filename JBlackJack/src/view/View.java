package view;

import java.util.Observer;
import java.util.ArrayList;
import java.util.Observable;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import javax.swing.*;

import controller.CreateUserActionListener;
import model.ModelManager;
import model.Utente;

/**
 * classe che rappresenta la view, implementata tramite un JFrame
 */
public class View extends JFrame implements Observer 
{
	public static final String TITOLO = "JBlackJack";
	private static View instance;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private JPanel cardPanel;
    private CardLayout cardLayout;
	
	private View()
	{
		setTitle(TITOLO);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(850, 650);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        menuPanel = new MenuPanel();
        gamePanel = new GamePanel();
        
        cardPanel.add(menuPanel, "Menu");
        cardPanel.add(gamePanel, "Game");
        
        add(cardPanel, BorderLayout.CENTER);
        
		setVisible(true);
	}
	
	public static View getInstance()
	{
		if (instance == null) instance = new View();
		return instance;
	}
	
	@Override
	public void update(Observable o, Object arg) 
	{
		System.out.println("update ricevuto");
		menuPanel.repaint();
		gamePanel.repaint();
	}
	
	/**
	 * metodo per mostrare il panel del menu
	 * controlla anche che ci sia gia un utente creato, se non c'è chiede di crearlo
	 */
	public void showMenuPanel() 
	{
        cardLayout.show(cardPanel, "Menu");
        
        if(leggiUtentiDaFile("src/resources/data/utenti.txt").length == 0)
        {        	
			String username = JOptionPane.showInputDialog(null, "Inserire un username", "Creazione primo utente", JOptionPane.PLAIN_MESSAGE);

			//se clicco su OK
			if (username != null) {
				//controlla la validita dell'input (se il campo non è vuoto fai l'actionPerformed del CreateUserActionListener, altrimenti manda un errore)
				if (!username.isEmpty()) {
	                new CreateUserActionListener(username.trim()).actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
	            } else {	
	                JOptionPane.showMessageDialog(null, "l'username non può essere vuoto!", "Errore", JOptionPane.ERROR_MESSAGE);
	            }
	        } 
        }
    }

	/**
	 * metodo per mostrare il panel della partita
	 */
    public void showGamePanel() 
    {
        cardLayout.show(cardPanel, "Game");
    }
    
    /**
     * metodo per leggere gli utenti dal file degli utenti
     * @param path path del file
     * @return la lista degli utenti
     */
    public static String[] leggiUtentiDaFile(String path) 
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
}
