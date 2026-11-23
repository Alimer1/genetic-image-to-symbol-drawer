package alimer.image_maker;

import javax.swing.*;

import alimer.image_maker.symbols.Symbol;
import alimer.image_maker.symbols.SymbolTriangle;

import java.util.ArrayList;

public class ImageWindow
{

    private JFrame mainFrame;

    //Test image frame thingy
    private Canvas canvas;

    //Constructor
    public ImageWindow()
    {

        ArrayList<Symbol> symbolList = new ArrayList<Symbol>();

        for(int i=0;i<120;i++)
        {
            SymbolTriangle tri = new SymbolTriangle();
            tri.TempRandom(700, 700);
            symbolList.add(tri);
        }

        canvas = new Canvas(symbolList);
        
        createWindow();
    }

    private void createWindow()
    {
        // JFrame [  ]
        // JPanel  ()

        ImageIcon img = new ImageIcon(canvas.getImage());

        JLabel imgLabel = new JLabel(img);

        JPanel imagePanel = new JPanel();

        imagePanel.add(imgLabel);

        mainFrame = new JFrame("Zeynep");

        mainFrame.add(imagePanel);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);

        Timer renderTime = new Timer(16, e -> {
            imgLabel.repaint();
        });
        renderTime.start();

        canvas.drawAll();
    }



}