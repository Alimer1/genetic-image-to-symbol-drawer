package alimer.image_maker.vectors;

import java.util.List;

public class Vector2D extends AbstractVector
{

    static final int SIZELIMIT = 2;

    //------------//
    //Constructors//
    //------------//

    /**
     * Creates the vector with default value 0.
     */
    public Vector2D()
    {
        super(SIZELIMIT);
    }

    /**
     * Creates the vector with the given x,y variables.
     * @param x
     * @param y
     */
    public Vector2D(double x, double y)
    {
        super(SIZELIMIT);
        this.set(0,x);
        this.set(1,y);
    }

    /**
     * Creates the vector with the given arrays first two values.
     * @param array double array
     */
    public Vector2D(double[] array)
    {
        super(SIZELIMIT);
        set(0,array[0]);
        set(1,array[1]);
    }

    /**
     * Creates the vector with the given lists first two values.
     * @param list Double list
     */
    public Vector2D(List<Double> list)
    {
        super(SIZELIMIT);
        set(0,list.get(0));
        set(1,list.get(1));
    }

    /**
     * Creates a new vector with the given vectors first two values.
     * @param vector any vector
     */
    public Vector2D(AbstractVector vector)
    {
        super(SIZELIMIT);
        set(0,vector.get(0));
        set(1,vector.get(1));
    }


    public double getX()
    {
        return get(0);
    }

    public void setX(double val)
    {
        set(0,val);
    }

    public double getY()
    {
        return get(1);
    }

    public void setY(double val)
    {
        set(1,val);
    }

}
