package model.giocatore;

/**
 * rappresenta il dealer, che a sua volta è un bot
 * è singleton perchè ci puo essere solo un dealer
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
