package com.example.swing;

import java.awt.*;
import javax.swing.*;

public class ScrollBar extends JScrollBar{

    public ScrollBar() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(5,5));
        setBackground(new Color(240,240,240));
        setUnitIncrement(40);
    }
    
}
