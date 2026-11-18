package alimer.image_maker.symbols;

import java.util.random.*;

abstract class AbstractDrawingSymbol
{
    //Determines the layer of the symbol in the stack.
    private double weight;
    //Transparency value 0 = opaque 255 = invisible
    private int alpha;

    public AbstractDrawingSymbol()
    {
        this.alpha = 255;
        this.weight = 0;
    }

}
