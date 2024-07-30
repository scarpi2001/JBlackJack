package view;

import java.awt.*;
import javax.swing.*;


import controller.CreateUserActionListener;

/**
 * panel che contiene il menu dell'applicazione
 */
public class MenuPanel extends JPanel
{
	private Image backgroundImage;
	private JButton buttonCreateUser;
	
	public MenuPanel()
	{
		setLayout(new BorderLayout());
		backgroundImage = new ImageIcon("src/resources/images/menu_background3.jpg").getImage();
		
		buttonCreateUser = new JButton("Crea nuovo utente");
		//posso usare una lambda, ActionListener è un interfaccia funzionale
		buttonCreateUser.addActionListener(e -> {
			String username = JOptionPane.showInputDialog(null, "Inserire un username", "Creazione utente", JOptionPane.PLAIN_MESSAGE);

			//se clicco su OK
			if (username != null) {
				//controlla la validita dell'input (se il campo non è vuoto fai l'actionPerformed del CreateUserActionListener, altrimenti manda un errore)
				if (!username.isEmpty()) {
	                new CreateUserActionListener(username.trim()).actionPerformed(e);
	            } else {	
	                JOptionPane.showMessageDialog(null, "l'username non può essere vuoto!", "Errore", JOptionPane.ERROR_MESSAGE);
	            }
	        } 
		});
		add(buttonCreateUser,BorderLayout.NORTH);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;

		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	}
}
