package alimer.image_maker.symbols;

import alimer.image_maker.vectors.*;
import alimer.image_maker.Canvas;

public class SymbolRectangle extends Symbol
{

    private Vector2D dim;
    private Vector2D pos;

    public SymbolRectangle()
    {
        super(0.5,0.5,new Vector3D());
        System.out.println("The constructor for this class has not been implimented!!!!!");
    }

    public void drawOnCanvas(Canvas canvas)
    {

        int width = (int) dim.x() + 1;
        int height = (int) dim.y() + 1;

        double x = pos.x();
        double y = pos.y();

        for(int i = 0;i < height; i++)
        {
            for(int j = 0;j < width; j++)
            {
                canvas.setPixel(new Vector2D(x+j,y+i),color(),opacity());
            }
        }
    }
}
