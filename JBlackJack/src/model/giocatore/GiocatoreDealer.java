package model.giocatore;

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
