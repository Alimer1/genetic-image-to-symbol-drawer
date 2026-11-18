package alimer.image_maker.vectors;

import java.util.List;

public class VectorDynamic extends AbstractVector
{

    //------------//
    //Constructors//
    //------------//

    /**
     * Creates a vector with given size and default number of 0.
     *
     * @param  vectorLength size of the vector
     */
    public VectorDynamic(int vectorLength)
    {
        super(vectorLength);
    }

    /**
     * Creates a vector with given size and given default number.
     *
     * @param  vectorLength size of the vector
     * @param  defaultNum default number to populate the Vector
     */
    public VectorDynamic(int vectorLength, double defaultNum)
    {
        super(vectorLength,defaultNum);
    }

    /**
     * Creates a vector the same size and numbers as the array.
     *
     * @param  array size of the vector
     */
    public VectorDynamic(double[] array)
    {
        super(array);
    }

    /**
     * Creates a vector the same size and numbers as the list.
     *
     * @param  list size of the vector
     */
    public VectorDynamic(List<Double> list)
    {
        super(list);
    }

    /**
     * Creates a vector the same size and numbers as the Vector.
     *
     * @param  list size of the vector
     */
    public VectorDynamic(VectorDynamic vector)
    {
        super(vector);
    }

    //------------------//
    //General Operations//
    //------------------//

    /**
     * Changes the size of the Vector to match.
     * (If expanding the default value is considered 0)
     *
     * @param  newSize new size of the Vector
     */
    public void changeSize(int newSize)
    {
        changeSize(newSize, 0);
    }

    /**
     * Changes the size of the Vector to match.
     *
     * @param  newSize new size of the Vector
     * @param  defaultValue used when expanding the Vector
     */
    public void changeSize(int newSize, double defaultValue)
    {
        //Expanding
        if(newSize > size())
        {
            for(int i=0; i<(newSize-size()); i++)
            {
                arrayList.add(Double.valueOf(defaultValue));
            }
        }
        //Shrinking
        else if(newSize < size())
        {
            for(int i=0; i<(size()-newSize); i++)
            {
                arrayList.removeLast();
            }
        }
    }
}
