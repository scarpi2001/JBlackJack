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
        setForeground(builder.foreground);
        setFont(builder.font);
    }

    public static class Builder 
    {
        private String text;
        private Color foreground; 
        private Font font; 

        public Builder() 
        {
        	foreground = Color.WHITE;
        	font = new Font("Arial", Font.BOLD, 18);
        }

        public Builder text(String text)
        {
            this.text = text;
            return this;
        }

        public Builder foreground(Color foreground)
        {
            this.foreground = foreground;
            return this;
        }

        public Builder font(Font font) 
        {
            this.font = font;
            return this;
        }

        public MyJLabel build() 
        {
            return new MyJLabel(this);
        }
    }
}
