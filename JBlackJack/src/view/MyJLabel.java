package view;

import javax.swing.*;
import java.awt.*;

/**
 * classe per le label personalizzate, utilizza il builder pattern, permettendo di costruire oggetti di questa classe in modo più flessibile
 */
public class MyJLabel extends JLabel 
{

    private MyJLabel(Builder builder)
    {
        super(builder.text);
        setForeground(builder.background);
        setFont(builder.font);
    }

    /**
     * builder della classe MyJLabel
     */
    public static class Builder 
    {
        private String text;
        private Color background; 
        private Font font; 

        public Builder() 
        {
        	background = Color.WHITE;
        	font = new Font("Arial", Font.BOLD, 18);
        }

        /**
         * modifica il testo
         * @param text testo
         * @return istanza del builder con campo modificato
         */
        public Builder text(String text)
        {
            this.text = text;
            return this;
        }

        /**
         * modifica il background
         * @param background background
         * @return istanza del builder con campo modificato
         */
        public Builder background(Color background)
        {
            this.background = background;
            return this;
        }

        /**
         * modifica il font
         * @param font font
         * @return istanza del builder con campo modificato
         */
        public Builder font(Font font) 
        {
            this.font = font;
            return this;
        }

        /**
         * ritorna l'istanza del builder
         * @return l'istanza del builder
         */
        public MyJLabel build() 
        {
            return new MyJLabel(this);
        }
    }
}
