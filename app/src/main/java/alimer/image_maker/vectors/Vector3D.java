package alimer.image_maker.vectors;

import java.util.List;

public class Vector3D extends AbstractVector
{

    static final int SIZELIMIT = 3;

    //------------//
    //Constructors//
    //------------//

    /**
     * Creates the vector with default value 0.
     */
    public Vector3D()
    {
        super(SIZELIMIT);
    }

    /**
     * Creates the vector with the given x,y,z variables.
     * @param x
     * @param y
     * @param z
     */
    public Vector3D(double x, double y, double z)
    {
        super(SIZELIMIT);
        this.set(0,x);
        this.set(1,y);
        this.set(2,z);
    }

    /**
     * Creates the vector with the given arrays first three values.
     * @param array double array
     */
    public Vector3D(double[] array)
    {
        super(SIZELIMIT);
        set(0,array[0]);
        set(1,array[1]);
        set(2,array[2]);
    }

    /**
     * Creates the vector with the given lists first three values.
     * @param list Double list
     */
    public Vector3D(List<Double> list)
    {
        super(SIZELIMIT);
        set(0,list.get(0));
        set(1,list.get(1));
        set(2,list.get(2));
    }

    /**
     * Creates a new vector with the given vectors first three values.
     * @param vector any vector
     */
    public Vector3D(AbstractVector vector)
    {
        super(SIZELIMIT);
        set(0,vector.get(0));
        set(1,vector.get(1));
        set(2,vector.get(2));
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

    public double getZ()
    {
        return get(2);
    }

    public void setZ(double val)
    {
        set(2,val);
    }
}
