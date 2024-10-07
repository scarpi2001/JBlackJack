package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * classe di utilità per operazioni di lettura e scrittura di file di testo
 */
public class FileUtils
{
	/**
	 * metodo per ottenere il contenuto di un file
	 * restituisce il contenuto sotto forma di lista di stringhe
	 * ogni elemento della lista è una riga del file
	 * @param filePath path del file
	 * @return lista di righe del file
	 */
    public static List<String> leggiFile(String filePath)
    {
        List<String> righe = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String riga;
            while ((riga = reader.readLine()) != null)
            {
                righe.add(riga);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace(); 
        }
        return righe;
    }

    /**
     * scrive una lista di stringhe su un file
     * @param filePath il percorso del file 
     * @param contenuto la lista di stringhe da scrivere
     * @param append se true, aggiunge al file esistente, altrimenti sovrascrive.
     */
    public static void scriviFile(String filePath, List<String> contenuto, boolean append)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append))) 
        {
            for (String riga : contenuto)
            {
                writer.write(riga);
                writer.newLine();
            }
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * metodo per scrivere una singola riga su un file
     * @param filePath il percorso del file
     * @param riga la riga da scrivere
     * @param append se true, aggiunge al file esistente, altrimenti sovrascrive.
     */
    public static void scriviFile(String filePath, String riga, boolean append) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append)))
        {
            writer.write(riga);
            writer.newLine();
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * metodo per aggiornare un campo specifico in un file di testo dove ogni riga è formattata in questo modo -> campo:valore
     * @param filePath path del file
     * @param campo campo da aggiornare
     * @param nuovoValore valore da dare al campo
     */
    public static void aggiornaCampoFile(String filePath, String campo, String nuovoValore)
    {
        List<String> righe = leggiFile(filePath);
        for (int i = 0; i < righe.size(); i++)
        {
            String riga = righe.get(i);
            if (riga.startsWith(campo))
            {
                righe.set(i, campo + ":" + nuovoValore);
                break;
            }
        }
        scriviFile(filePath, righe, false);
    }
}
