package view;

import javax.swing.*;
import java.awt.*;

public class MyJLabel extends JLabel {

    public MyJLabel() {
        super();
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 18));
    }

    public MyJLabel(String text) {
        super(text);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 18)); 
    }
}
