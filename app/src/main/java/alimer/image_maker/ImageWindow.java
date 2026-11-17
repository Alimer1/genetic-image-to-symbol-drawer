package alimer.image_maker;

import javax.swing.*;

import java.awt.image.*;

import java.awt.*;

public class ImageWindow
{

    private JFrame mainFrame;

    //Test image frame thingy
    private BufferedImage pureImage;

    //Constructor
    public ImageWindow()
    {
        createImage();
        drawRect(10, 10, 40, 40, new Color(60,224,68));
        drawCircle(70,50,15, new Color(91,56,22));
        drawLine(50,50,90,60,new Color(255,255,255));
        drawLine(50,50,60,90,new Color(0,255,255));
        drawLine(50,50,10,90,new Color(255,0,0));
        drawLine(40,90,50,50,new Color(0,255,0));
        createWindow();
    }

    private void createImage()
    {
        pureImage = new BufferedImage(100, 100, BufferedImage.TYPE_3BYTE_BGR);
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
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void drawRect(int x1, int y1, int x2, int y2, Color color)
    {
        WritableRaster raster = pureImage.getRaster();

        int[] colorArray = {color.getRed(), color.getGreen(), color.getBlue()};

        int minX = Math.min(x1,x2);
        int minY = Math.min(y1,y2);
        int difX = Math.abs(x1 - x2) + 1;
        int difY = Math.abs(y1 - y2) + 1;

        for(int i = 0;i < difY; i++)
        {
            for(int j = 0;j < difX; j++)
            {
                raster.setPixel(minX + j, minY + i, colorArray);
            }
        }
    }

    private void drawLine(int x1, int y1, int x2, int y2, Color color)
    {
        WritableRaster raster = pureImage.getRaster();

        int[] colorArray = {color.getRed(), color.getGreen(), color.getBlue()};

        int deltaX = x2 - x1;
        int deltaY = y2 - y1;

        //LOGIC TIME

        int flipX = 1;
        if(deltaX < 0)
        {
            flipX = -1;
            deltaX = deltaX * -1;
        }

        int flipY = 1;
        if(deltaY < 0)
        {
            flipY = -1;
            deltaY = deltaY * -1;
        }

        boolean swap  = false;
        if(deltaX < deltaY)
        {
            swap = true;
            int temp = deltaX;
            deltaX = deltaY;
            deltaY = temp;
        }

        //TRUTH LINE ---------------

        float gradient = (float) deltaY / deltaX;
        int currentY = 0;

        for(int i = 0; i < deltaX; i++)
        {
            float targetY = i * gradient;
            float error = currentY - targetY;
            if(error < -0.5)
            {
                currentY++;
            }
            //CONVERT BACK TO NORMALCY

            if(swap)
            {
                raster.setPixel(
                    x1 + currentY * flipX,
                    y1 + i * flipY,
                    colorArray
                );
            }
            else
            {
                raster.setPixel(
                    x1 + i * flipX,
                    y1 + currentY * flipY,
                    colorArray
                );
            }

        }

    }

    private void drawCircle(int x, int y, int r, Color color)
    {
        WritableRaster raster = pureImage.getRaster();

        int[] colorArray = {color.getRed(), color.getGreen(), color.getBlue()};

        int minX = x;
        int minY = y;
        int difX = r;
        int difY = r;

        for(int i = 0;i < difY; i++)
        {
            for(int j = 0;j < difX; j++)
            {
                if(i*i <= r*r - j*j)
                {
                    //NORMAL
                    raster.setPixel(minX + j, minY + i, colorArray);
                    //X FLIPPED
                    raster.setPixel(minX - j, minY + i, colorArray);
                    //Y FLIPPED
                    raster.setPixel(minX + j, minY - i, colorArray);
                    //X & Y FLIPPED
                    raster.setPixel(minX - j, minY - i, colorArray);
                }
            }
        }
    }

    private void drawTriangle(int x1,int y1, int x2, int y2, int x3, int y3, Color color)
    {
        
    }
}