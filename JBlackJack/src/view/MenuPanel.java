package view;

import java.awt.*;
import javax.swing.*;


import controller.CreateUserActionListener;

public class MenuPanel extends JPanel
{
private JButton buttonCreateUser;
	
	public MenuPanel()
	{
		//setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		
		buttonCreateUser = new JButton("+");	
		buttonCreateUser.addActionListener(new CreateUserActionListener());
		add(buttonCreateUser,BorderLayout.NORTH);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
				
		this.setForeground(Color.BLACK);
		g2.drawString("ciao", 50, 50);
	}
}
