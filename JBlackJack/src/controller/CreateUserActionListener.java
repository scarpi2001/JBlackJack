package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ModelManager;

public class CreateUserActionListener implements ActionListener
{
	public void actionPerformed(ActionEvent e) 
	{
		ModelManager model = ModelManager.getInstance();
		model.setUser("a");
	}
}
