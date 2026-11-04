package alimer.image_maker;

import javax.swing.*;

import java.awt.image.*;

public class ImageWindow
{

    private JFrame mainFrame;

    //Test image frame thingy
    private BufferedImage pureImage;

    //Constructor
    public ImageWindow()
    {
        createImage();
        createWindow();
    }

    private void createImage()
    {
        pureImage = new BufferedImage(100, 100, BufferedImage.TYPE_3BYTE_BGR);
        pureImage.setRGB(50, 50, 0x3d724300);
    }

    private void createWindow()
    {
        // JFrame [  ]
        // JPanel  ()

        ImageIcon img = new ImageIcon(pureImage);

        JLabel imgLabel = new JLabel(img);

        JPanel imagePanel = new JPanel();

        imagePanel.add(imgLabel);

        mainFrame = new JFrame("Zeynep");

        mainFrame.add(imagePanel);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

}