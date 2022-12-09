package com.example.myjavafxdemo2.demo.somedemo;

import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GetMouseAction {
    public static void main(String[] args) {
        Csk.main(args);
    }
}

class Csk extends Frame
{
    private static final long serialVersionUID = 1L;
    public static void main ( String[] args )
    {
        Csk csk = new Csk ();
        csk.setTitle ("Csk");
        csk.addMouseListener (new MouseAdapter ()
        {
            @Override
            public void mousePressed ( MouseEvent e )
            {
                System.out.println ("down");
            }
            @Override
            public void mouseReleased ( MouseEvent e )
            {
                System.out.println ("up");
            }
        });
        csk.addWindowListener (new WindowAdapter ()
        {
            @Override
            public void windowClosing ( WindowEvent e )
            {
                System.exit (0);
            }
        });
        csk.setSize (399, 399);
        csk.setLocationRelativeTo (null);
        csk.setVisible (true);
    }
}