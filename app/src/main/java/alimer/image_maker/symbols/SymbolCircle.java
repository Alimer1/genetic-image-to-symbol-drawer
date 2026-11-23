package alimer.image_maker.symbols;

import alimer.image_maker.vectors.*;
import alimer.image_maker.Canvas;

public class SymbolCircle extends Symbol
{

    private int r;
    private Vector2D pos;

    public SymbolCircle()
    {
        super(0.5,0.5,new Vector3D());
        System.out.println("The constructor for this class has not been implimented!!!!!");
    }

    public void drawOnCanvas(Canvas canvas)
    {

        double x = pos.x();
        double y = pos.y();

        for(int i = 0;i < r; i++)
        {
            for(int j = 0;j < r; j++)
            {
                if(i*i <= r*r - j*j)
                {
                    canvas.setPixel(new Vector2D(x+j,y+i), color(), opacity());
                    canvas.setPixel(new Vector2D(x-j,y+i), color(), opacity());
                    canvas.setPixel(new Vector2D(x+j,y-i), color(), opacity());
                    canvas.setPixel(new Vector2D(x-j,y-i), color(), opacity());
                }
            }
        }
    }

}
