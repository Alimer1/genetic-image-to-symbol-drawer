package alimer.image_maker.symbols;

import alimer.image_maker.vectors.*;
import alimer.image_maker.Canvas;

public class SymbolLine extends Symbol
{

    private Vector2D posA;
    private Vector2D posB;

    public SymbolLine()
    {
        super(0.5,0.5,new Vector3D());
        System.out.println("The constructor for this class has not been implimented!!!!!");
    }

    public void drawOnCanvas(Canvas canvas)
    {
        //LOGIC TIME (this will be important later)
        Vector2D delta = new Vector2D
        (
            Math.round(posA.x() - posB.x() + 1),
            Math.round(posA.y() - posB.y() + 1)
        );

        Vector2D flip = new Vector2D(delta);
        flip.toSignBinary();
        delta.mult(flip);

        boolean swap = delta.x() < delta.y();
        if(swap) delta.swap();

        //TRUTH LINE

        double gradient = delta.y() / delta.x();
        int y = 0;

        for(int i = 0; i < (int) delta.x(); i++)
        {
            double target = i * gradient;
            double error = y - target;

            if(error < -0.5) y++;

            //CONVERT BACK TO NORMALCY

            Vector2D changeVector = new Vector2D(i,y);
            if(swap) changeVector.swap();
            changeVector.mult(flip);
            changeVector.add(posA);

            canvas.setPixel(changeVector, color(), opacity());
        }
    }
}
