package controller.actionListeners.partita.azioni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import controller.Controller;
import model.ModelManager;
import view.View;

/**
 * definisce l'evento di scommessa che deve accadere al click di un componente swing
 */
public class ScommettiActionListener implements ActionListener
{
	private JTextField fieldScommessa;
	
	public ScommettiActionListener(JTextField fieldScommessa)
	{
		this.fieldScommessa = fieldScommessa;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		ModelManager model = ModelManager.getInstance();
		String scommessaString = fieldScommessa.getText();
		
		try 
		{
			//provo a convertire la stringa in intero
            int scommessa = Integer.parseInt(scommessaString);

            if (model.getChipsUtente() - scommessa < 0) 
            {
            	fieldScommessa.setText("");
            	View.showError("non hai abbastanza chips da scommettere, torna al menu per ricaricarle");
            	return;
            }
            	
            if(scommessa <= 0) 
            {
            	fieldScommessa.setText("");
                View.showError("La scommessa non può essere negativa o nulla");
                return;
            }

            model.getGiocatoreCorrente().scommetti(scommessa);  
        	Controller.getInstance().distribuisciCarte();
        	Controller.getInstance().gameloop();    	                    
        } 
        catch (NumberFormatException ex) 
        {
        	//se la conversione fallisce
        	fieldScommessa.setText("");
            View.showError("La scommessa deve essere un numero");
        }	
	}
}
