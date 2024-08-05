package view.menuPanel;

import javax.swing.*;
import java.awt.*;

import model.ModelManager;


/**
 * panel che rappresenta la bottomBar del menu
 */
public class BottomBar extends JPanel 
{
	private JButton buttonGioca;

	public BottomBar() 
	{
		ModelManager model = ModelManager.getInstance();
	    
		setLayout(new BorderLayout());
		setBackground(new Color(0, 0, 0, 0));
		
		//bottone play
		buttonGioca = new JButton("GIOCA");
		add(buttonGioca, BorderLayout.LINE_END);
	}
}