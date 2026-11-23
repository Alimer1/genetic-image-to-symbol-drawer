package alimer.image_maker;

import java.util.ArrayList;
import java.util.Arrays;
import java.awt.image.*;

import alimer.image_maker.vectors.*;

import alimer.image_maker.symbols.Symbol;

public class Canvas 
{
    private ArrayList<Symbol> symbolList;

    private int symbolLimit = 100;

    private BufferedImage image;

    private int height;
    private int width;

    public Canvas(ArrayList<Symbol> list)
    {
        this(new Vector2D(700,700),list);
    }

    public Canvas(Vector2D dim, ArrayList<Symbol> list)
    {
        height = (int) dim.y();
        width = (int) dim.x();
        image = new BufferedImage(width , height, BufferedImage.TYPE_3BYTE_BGR);
        symbolList = list;
    }

    public void setPixel(Vector2D pos, Vector3D color, double opacity)
    {

        int x = (int) Math.round(pos.x());
        int y = (int) Math.round(pos.y());

        //SECURITY!!!

        if((x < 0)      || (y < 0))       return;
        if((x >= width) || (y >= height)) return;



        //Making y+ go upwards as normally y+ points downwards.
        y = height - y;

        WritableRaster raster = image.getRaster();

        int[] array = raster.getPixel(x, y, new int[3]);

        //CHANGE THIS LATER
        //Thanks to that one stackoverflow dude
        Vector3D targetColor = new Vector3D(Arrays.stream(array).asDoubleStream().toArray());

        //Calculating opacity

        Vector3D finalColor = new Vector3D(color);

        finalColor.mult(255);

        finalColor.mult(opacity);

        targetColor.mult(1 - opacity);

        finalColor.add(targetColor);

        double[] colorArray = finalColor.toArray();

        raster.setPixel(x,y,colorArray);
    }

    /**
     * TEMP TEMP TEMP
     */
    public BufferedImage getImage()
    {
        return image;
    }

    /**
     * TEMP TEMP TEMP
     */
    public void drawAll()
    {
        for(int i=0; i<symbolList.size(); i++)
        {
            symbolList.get(i).drawOnCanvas(this);
        }
    }

    public void clear()
    {

    }
}
