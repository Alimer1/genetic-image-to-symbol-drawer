package alimer.image_maker.symbols;

import alimer.image_maker.vectors.*;
import alimer.image_maker.Canvas;

public abstract class Symbol
{
    //Determines the layer of the symbol in the stack.
    private double weight;
    //Transparency value 0 = invisible , 1 = opaque
    private double opacity;
    //Color vector of the symbol (RGB)
    private Vector3D color;

    protected Symbol(double weight,double opacity,Vector3D color)
    {
        this.weight = weight;
        this.opacity = opacity;
        this.color = color;
    }

    public Vector3D color()
    {
        return color;
    }

    public double opacity()
    {
        return opacity;
    }

    public void setOpacity(double opacity)
    {
        this.opacity = opacity;
    }

    public double weight()
    {
        return weight;
    }

    /**
     * Calls on this symbol to be drawn on the given canvas
     * @param canvas
     */
    public abstract void drawOnCanvas(Canvas canvas);

}
