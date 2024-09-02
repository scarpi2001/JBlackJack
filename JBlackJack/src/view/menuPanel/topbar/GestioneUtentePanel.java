package view.menuPanel.topbar;

import javax.swing.*;
import java.util.List;

import controller.actionListeners.menu.utente.CreateUserActionListener;
import controller.actionListeners.menu.utente.DeleteUserActionListener;
import controller.actionListeners.menu.utente.SetUserActionListener;

import java.awt.*;
import java.awt.event.ActionListener;

import model.FileUtils;
import model.ModelManager;


/**
 * panel che rappresenta la topBar del menu
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
		buttonCreateUser.addActionListener(new CreateUserActionListener());
		add(buttonCreateUser);
		
		//bottone elimina utente
		buttonDeleteUser = new JButton("elimina utente");
		buttonDeleteUser.addActionListener(new DeleteUserActionListener());
		add(buttonDeleteUser);
		
		//select utenti
        comboBoxUtenti = new JComboBox<>(FileUtils.leggiFile("src/resources/data/utenti.txt").toArray(new String[0]));
        setUserActionListener = new SetUserActionListener(comboBoxUtenti);
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
		List<String> utenti = FileUtils.leggiFile("src/resources/data/utenti.txt");
		for (String utente : utenti) 
		{
			comboBoxUtenti.addItem(utente);
		}
		
		//mostro l'utente selezionato nella combobox (sarebbe il comportamento di default, ma dato che svuoto e riempio, devo farlo a mano)
		comboBoxUtenti.setSelectedItem(model.getUtenteUsername());

		comboBoxUtenti.addActionListener(setUserActionListener);
	}
}