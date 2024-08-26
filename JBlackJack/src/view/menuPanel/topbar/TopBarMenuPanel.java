package view.menuPanel.topbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import model.ModelManager;


/**
 * panel che rappresenta la topBar del menu
 */
public class TopBarMenuPanel extends JPanel 
{
	private NumeroGiocatoriPanel numeroGiocatoriPanel;
	private GestioneUtentePanel gestioneUtentePanel;
	
	public TopBarMenuPanel() 
	{
		setLayout(new GridBagLayout());
		setOpaque(false);
		
		GridBagConstraints gbc = new GridBagConstraints();
        
        //panel per gestione utente
        gestioneUtentePanel = new GestioneUtentePanel();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(gestioneUtentePanel, gbc);

        //panel numero giocatori
        numeroGiocatoriPanel = new NumeroGiocatoriPanel();
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(numeroGiocatoriPanel, gbc);
	}
	
	/**
	 * metodo per aggiornare la comboBox 
	 */
	public void aggiornaComboBox() 
	{
		gestioneUtentePanel.aggiornaComboBox();
	}
}