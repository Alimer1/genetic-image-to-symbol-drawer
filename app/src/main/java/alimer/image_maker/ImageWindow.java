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
        drawLine(30,30,30,31,new Color(255,0,0));
        drawLine(20,20,21,20,new Color(0,255,0));
        drawTriangle(10,10,40,80,90,30,new Color(255,0,0));
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

        int deltaX = x2 - x1 + 1;
        int deltaY = y2 - y1 + 1;

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
                    raster.setPixel(minX + j, minY + i, colorArray);
                    raster.setPixel(minX - j, minY + i, colorArray);
                    raster.setPixel(minX + j, minY - i, colorArray);
                    raster.setPixel(minX - j, minY - i, colorArray);
                }
            }
        }
    }

    private void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3, Color color)
    {

        boolean sorted = false;

        //Worst sorting algorthim a human has ever written.
        //Use vector2 and arrays and make it nicer later.
        while(!sorted)
        {
            sorted = true;
            if(y1 < y2)
            {
                sorted = false;
                int temp = y2;
                y2 = y1;
                y1 = temp;
                temp = x2;
                x2 = x1;
                x1 = temp;
            }
            if(y2 < y3)
            {
                sorted = false;
                int temp = y3;
                y3 = y2;
                y2 = temp;
                temp = x3;
                x3 = x2;
                x2 = temp;
            }
        }

        //Straight Line
        if((y1 == y2) && (y2 == y3))
        {
            System.out.println("Drew a line");
            drawLine(Math.min(Math.min(x1,x2),x3), y1,Math.max(Math.max(x1,x2),x3), y1, color);
        }
        //Down Triangle
        else if((y1 == y2) && (y2 != y3))
        {
            System.out.println("Drew a down triangle");
            if(x1 < x2)
            {
                drawSimpleTriangle(x1, y1, x3, y3, x2, y2, color);
            }
            else
            {
                drawSimpleTriangle(x2, y2, x3, y3, x1, y1, color);
            }
        }
        //Up Triangle
        else if((y1 != y2) && (y2 == y3))
        {
            System.out.println("Drew an up triangle");
            if(x2 < x3)
            {
                drawSimpleTriangle(x2, y2, x1, y1, x3, y3, color);
            }
            else
            {
                drawSimpleTriangle(x3, y3, x1, y1, x2, y2, color);
            }
        }
        //Split Triangle
        else
        {
            System.out.println("Drew a split triangle");
            int deltaX = x1 - x3;
            int deltaY = y1 - y3;

            float gradient = (float) deltaY / deltaX;
            float constant = y1 - gradient * x1;

            int newX = Math.round((y2 - constant) / gradient);

            if(newX < x2)
            {
                int temp = x2;
                x2 = newX;
                newX = temp;
            }
            //Up Triangle
            drawSimpleTriangle(x2,y2,x1,y1,newX,y2,color);
            //Down Triangle
            drawSimpleTriangle(x2,y2,x3,y3,newX,y2,color);
        }

    }

    //x1,y1 = first point
    //x2,y2 = up/down point
    //x3,y3 = down point
    private void drawSimpleTriangle(int x1,int y1, int x2, int y2, int x3, int y3, Color color)
    {
        WritableRaster raster = pureImage.getRaster();

        int[] colorArray = {color.getRed(), color.getGreen(), color.getBlue()};

        //Turning a down triangle to an up triangle.

        int flip = 1;
        if(y1 > y2)
        {
            flip = -1;
            y2 = y2 + Math.abs(y1 - y2) * 2;
        }

        int deltaXA = x2 - x1;
        int deltaYA = y2 - y1;

        float gradientA = (float) deltaYA / deltaXA;
        float constantA = y1 - gradientA * x1;


        int deltaXB = x3 - x2;
        int deltaYB = y3 - y2;

        float gradientB = (float) deltaYB / deltaXB;
        float constantB = y3 - gradientB * x3;


        int minX = Math.min(x1,x2);
        int minY = y1;


        int width = Math.max(x2,x3) - Math.min(x1,x2) + 1;
        int height = y2 - y1 + 1;

        /*
        
         Up         | Down      | Leaning Right | Leaning Left
             2      | 1-------3 |             2 | 2
            / \     |  \     /  |            /  |  \
           /   \    |   \   /   |           /   |   \
          /     \   |    \ /    |          /    |    \
         1-------3  |     2     | 1-------3     |     1-------3

        */

        boolean leaningLeft = x1 > x2;
        boolean leaningRight = x2 > x3;

        for(int i = 0;i < height;i++)
        {
            for(int j = 0;j < width;j++)
            {
                //Checks if the point is above the first line.
                if(minY + i > (minX+j)*gradientA + constantA)
                {
                    if(!leaningLeft) continue;

                }
                else
                {
                    if(leaningLeft) continue;
                }

                //Checks if the point is above the second line.
                if(minY + i > (minX+j)*gradientB + constantB)
                {
                    if(!leaningRight) continue;
                }
                else
                {
                    if(leaningRight) continue;
                }

                raster.setPixel(minX + j, minY + i * flip, colorArray);
            }
        }
    }
}