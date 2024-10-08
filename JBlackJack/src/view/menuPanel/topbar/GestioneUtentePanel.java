package view.menuPanel.topbar;

import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

import controller.actionListeners.menu.utente.CreaUtenteActionListener;
import controller.actionListeners.menu.utente.EliminaUtenteActionListener;
import controller.actionListeners.menu.utente.SetUtenteActionListener;

import model.ModelManager;


/**
 * panello per la gestione dell'utente nel menu,
 * al suo interno ci sono i bottoni per la creazione e l'eliminazione di un utente
 * e la combobox per selezionare l'utente
 */
public class GestioneUtentePanel extends JPanel 
{
	private JButton buttonDeleteUser;
	private JButton buttonCreateUser;
	private JComboBox<String> comboBoxUtenti;
	private ActionListener setUserActionListener;
	
	public GestioneUtentePanel() 
	{
		ModelManager model = ModelManager.getInstance();
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		setOpaque(false);
		
		//bottone crea utente
		buttonCreateUser = new JButton("Crea nuovo utente");
		buttonCreateUser.addActionListener(new CreaUtenteActionListener());
		add(buttonCreateUser);
		
		//bottone elimina utente
		buttonDeleteUser = new JButton("elimina utente");
		buttonDeleteUser.addActionListener(new EliminaUtenteActionListener());
		add(buttonDeleteUser);
		
		//select utenti
        comboBoxUtenti = new JComboBox<>(model.getUtenti("src/resources/data/utenti.txt").toArray(new String[0]));
        setUserActionListener = new SetUtenteActionListener(comboBoxUtenti);
        comboBoxUtenti.addActionListener(setUserActionListener);
        add(comboBoxUtenti);
	}
	
	/**
	 * metodo per aggiornare la comboBox 
	 */
	public void aggiornaComboBox() 
	{
		/*problema: comboBoxUtenti.removeAllItems() mi triggera l'evento dell'action listener, che prova a settare un utente senza l'utente
		 *quindi tolgo l'action listener e lo rimetto alla fine dell'operazione di aggiornamento  
		 */
		ModelManager model = ModelManager.getInstance();
		comboBoxUtenti.removeActionListener(setUserActionListener);
		
		//svuoto e riempio le options guardando le modifiche al file utenti
		comboBoxUtenti.removeAllItems();
		List<String> utenti = model.getUtenti("src/resources/data/utenti.txt");
		for (String utente : utenti) 
		{
			comboBoxUtenti.addItem(utente);
		}
		
		//mostro l'utente selezionato nella combobox (sarebbe il comportamento di default, ma dato che svuoto e riempio, devo farlo a mano)
		comboBoxUtenti.setSelectedItem(model.getUsernameUtente());

		comboBoxUtenti.addActionListener(setUserActionListener);
	}
}