package controller;

/**
 * è il punto di ingresso dell'applicazione,
 * contiene il metodo main() che avvia il programma e inizializza il menu principale del gioco tramite il controller.
 */
public class JBlackJack 
{
	public static void main(String[] args)
	{
        Controller.getInstance().initMenu();	
	}
}
