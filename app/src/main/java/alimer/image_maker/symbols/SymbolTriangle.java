package alimer.image_maker.symbols;

import alimer.image_maker.vectors.*;
import alimer.image_maker.Canvas;

import java.util.Random;

public class SymbolTriangle extends Symbol
{

    private Vector2D pos0;
    private Vector2D pos1;
    private Vector2D pos2;

    public SymbolTriangle()
    {
        super(0.5,0.8,new Vector3D());
        pos0 = new Vector2D();
        pos1 = new Vector2D();
        pos2 = new Vector2D();
    }

    public SymbolTriangle(Vector2D[] positions,Vector3D color)
    {
        super(0.5,0.8,color);
        pos0 = positions[0];
        pos1 = positions[1];
        pos2 = positions[2];
    }

    public void TempRandom(int h, int w)
    {
        Random rand = new Random();

        pos0.setX(rand.nextDouble() * w);
        pos0.setY(rand.nextDouble() * h);

        pos1.setX(rand.nextDouble() * w);
        pos1.setY(rand.nextDouble() * h);

        pos2.setX(rand.nextDouble() * w);
        pos2.setY(rand.nextDouble() * h);

        color().setX(rand.nextDouble());
        color().setY(rand.nextDouble());
        color().setZ(rand.nextDouble());

        setOpacity(rand.nextDouble());

    }

    public void drawOnCanvas(Canvas canvas)
    {
        Vector2D top = pos0;
        Vector2D mid = pos1;
        Vector2D bot = pos2;
        Vector2D temp;

        if(top.y() < mid.y()) {temp = top; top = mid; mid = temp;}
        if(mid.y() < bot.y()) {temp = mid; mid = bot; bot = temp;}
        if(top.y() < mid.y()) {temp = top; top = mid; mid = temp;}

        //Straight Line (IMPLIMENT LATER)

        if((top.y() == mid.y()) && (mid.y() == bot.y()))
        {}

        //Up Triangle

        else if((top.y() != mid.y()) && (mid.y() == bot.y()))
        {
            if(mid.x() < bot.x()) simpleTriangle(canvas, mid, top, bot);
            else simpleTriangle(canvas, bot, top, mid);
        }

        //Down Triangle

        else if((top.y() == mid.y()) && (mid.y() != bot.y()))
        {
            if(top.x() < mid.x()) simpleTriangle(canvas, top, bot, mid);
            else simpleTriangle(canvas, mid, bot, top);
        }

        //Split Triangle

        else
        {
            Vector2D delta = new Vector2D
            (
                top.x() - bot.x(),
                top.y() - bot.y()
            );
            
            double gradient = delta.y() / delta.x();
            double constant = top.y() - gradient * top.x();

            Vector2D split = new Vector2D((mid.y() - constant)/gradient,mid.y());

            if(split.x() < mid.x()) {temp = mid; mid = split; split = temp;}

            //Up Triangle
            simpleTriangle(canvas,mid,top,split,true);
            //Down Triangle
            simpleTriangle(canvas,mid,bot,split);
        }

    }


    //baseL = first point
    //topPeak = up/down point
    //baseR = down point
    private void simpleTriangle(Canvas canvas,Vector2D baseL, Vector2D topPeak, Vector2D baseR)
    {
        simpleTriangle(canvas, baseL, topPeak, baseR, false);
    }

    //baseL = first point
    //topPeak = up/down point
    //baseR = down point
    private void simpleTriangle(Canvas canvas,Vector2D baseL, Vector2D topPeak, Vector2D baseR, boolean skipBase)
    {

        Vector2D peak = new Vector2D(topPeak);

        //Turning the down triangle upside down
        int flip = baseL.y() < peak.y() ? 0 : -2;
        if(flip != 0) {peak.setY(peak.y() + Math.abs(baseL.y() - peak.y()) * 2);}

        //LINE A

        Vector2D deltaA = new Vector2D(peak);
        deltaA.sub(baseL);

        double gradientA = deltaA.y() / deltaA.x();
        double constantA = peak.y() - gradientA * peak.x();

        //LINE B

        Vector2D deltaB = new Vector2D(baseR);
        deltaB.sub(peak);

        double gradientB = deltaB.y() / deltaB.x();
        double constantB = peak.y() - gradientB * peak.x();

        //BOUND

        Vector2D corner = new Vector2D(Math.min(baseL.x(), peak.x()), baseL.y());

        int width =  (int) Math.round(Math.max(peak.x(),baseR.x()) - corner.x() + 1);
        int height = (int) Math.round(peak.y()                     - baseL.y()  + 1);


        /**
         * Up         | Down      | Leaning Right | Leaning Left
         *     2      | 1-------3 |             2 | 2
         *    / \     |  \     /  |            /  |  \
         *   /   \    |   \   /   |           /   |   \
         *  /     \   |    \ /    |          /    |    \
         * 1-------3  |     2     | 1-------3     |     1-------3
         */

        boolean leaningLeft = baseL.x() > peak.x();
        boolean leaningRight = peak.x() > baseR.x();

        Vector2D target = new Vector2D();

        for(int i = skipBase ? 1 : 0; i < height; i++)
        {

            for(int j = 0; j < width; j++)
            {
                target.setX(corner.x() + j);
                target.setY(corner.y() + i);
                
                if(target.y() > target.x()*gradientA + constantA)
                {
                    if(!leaningLeft) continue;
                }
                else
                {
                    if(leaningLeft) continue;
                }
                if(target.y() > target.x()*gradientB + constantB)
                {
                    if(!leaningRight) continue;
                }
                else
                {
                    if(leaningRight) continue;
                }

                //Flipping the stuff
                target.setY(target.y() + i * flip);

                canvas.setPixel(target, color(), opacity());
            }
        }

    }
}
