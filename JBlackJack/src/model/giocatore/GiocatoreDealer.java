package model.giocatore;

/**
 * rappresenta il dealer, che a sua volta è un bot
 */
public class GiocatoreDealer extends GiocatoreBot
{
	private static GiocatoreDealer instance;

	private GiocatoreDealer(){}
	public static GiocatoreDealer getInstance()
	{
		if (instance == null) instance = new GiocatoreDealer();
		return instance;
	}
}
