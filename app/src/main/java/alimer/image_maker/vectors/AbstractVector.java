package alimer.image_maker.vectors;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractVector
{

    protected ArrayList<Double> arrayList;

    //------------//
    //Constructors//
    //------------//

    /**
     * Creates a vector with given size and default number of 0.
     *
     * @param  vectorLength size of the vector
     */
    protected AbstractVector(int vectorLength)
    {
        arrayList = new ArrayList<>(vectorLength);
        replaceAll(0);
    }

    /**
     * Creates a vector with given size and given default number.
     *
     * @param  vectorLength size of the vector
     * @param  defaultNum default number to populate the Vector
     */
    protected AbstractVector(int vectorLength, double defaultNum)
    {
        arrayList = new ArrayList<>(vectorLength);
        replaceAll(defaultNum);
    }

    /**
     * Creates a vector the same size and numbers as the array.
     *
     * @param  array size of the vector
     */
    protected AbstractVector(double[] array)
    {
        arrayList = new ArrayList<>(array.length);
        for(int i=0;i<array.length;i++)
        {
            set(i,array[i]);
        }
    }

    /**
     * Creates a vector the same size and numbers as the list.
     *
     * @param  list size of the vector
     */
    protected AbstractVector(List<Double> list)
    {
        arrayList = new ArrayList<Double>(list);
    }

    /**
     * Creates a vector the same size and numbers as the Vector.
     *
     * @param  list size of the vector
     */
    protected AbstractVector(AbstractVector vector)
    {
        arrayList = new ArrayList<>(vector.arrayList);
    }

    //------------------//
    //General Operations//
    //------------------//

    /**
     * Replaces every number in the Vector with the given number.
     *
     * @param  num number
     */
    private void replaceAll(double num)
    {
        for(int i=0; i<arrayList.size(); i++)
        {
            arrayList.set(i,Double.valueOf(num));
        }
    }

    /**
     * Return the size of the Vector.
     *
     * @return  size of the vector
     */
    public int size()
    {
        return arrayList.size();
    }

    /**
     * Returns the value at the given index.
     *
     * @param  index index used to get the value
     * @return value at the given index
     */
    public double get(int index)
    {
        return arrayList.get(index);
    }

    /**
     * Sets the value in the Vector.
     *
     * @param  index
     * @param  value
     */
    public void set(int index, double value)
    {
        arrayList.set(index,Double.valueOf(value));
    }

    //----------------//
    //Basic arithmetic//
    //----------------//

    //--------//
    //Addition//
    //--------//

    /**
     * Add the given value to each element of the Vector.
     *
     * @param  d value to be added
     * @return Returns this instance of the Vector
     */
    public AbstractVector add(double d)
    {
        for(int i = 0;i < this.size();i++)
        {
            this.set(i,get(i) + d);
        }
        return this;
    }

    /**
     * Adds each element from the given Vector to this Vector.
     *
     * @param  v given Vector
     * @return Returns this instance of the Vector
     */
    public AbstractVector add(AbstractVector v)
    {
        int maxLength = Math.max(size(), v.size());

        for(int i = 0; i<maxLength; i++)
        {
            this.set(i, get(i) + v.get(i));
        }

        return this;
    }

    /**
     * Adds every value from the v0 to v1 and creates a new Vector for the result.
     *
     * @param  v0 first Vector of the addition
     * @param  v1 second Vector of the addition
     * @return Returns a new Vector as a result
     */
    protected static AbstractVector add(AbstractVector v0, AbstractVector v1)
    {
        int maxLength = Math.max(v0.size(), v1.size());
        AbstractVector v2 = new VectorDynamic(maxLength,0);
        v2.add(v0);
        v2.add(v1);
        return v2;
    }

    //--------------//
    //Multiplication//
    //--------------//

    /**
     * Multiply the given value to each element of the Vector.
     *
     * @param  d value to be multiplied
     * @return Returns this instance of the Vector
     */
    public AbstractVector mult(double d)
    {
        for(int i = 0;i < this.size();i++)
        {
            this.set(i,get(i) * d);
        }
        return this;
    }

    /**
     * Multiplies each element from the given Vector to this Vector.
     *
     * @param  v given Vector
     * @return Returns this instance of the Vector
     */
    public AbstractVector mult(AbstractVector v)
    {
        int maxLength = Math.max(size(), v.size());

        for(int i = 0; i<maxLength; i++)
        {
            this.set(i, get(i) * v.get(i));
        }

        return this;
    }

    /**
     * Multiplies every value from the v0 to v1 and creates a new Vector for the result.
     *
     * @param  v0 first Vector of the multiplication
     * @param  v1 second Vector of the multiplication
     * @return Returns a new Vector as a result
     */
    protected static AbstractVector mult(AbstractVector v0, AbstractVector v1)
    {
        int maxLength = Math.max(v0.size(), v1.size());
        AbstractVector v2 = new VectorDynamic(maxLength,1);
        v2.mult(v0);
        v2.mult(v1);
        return v2;
    }

    //-----//
    //Other//
    //-----//

    /**
     * Adds all of the value of the Vector and returns it.
     * @return total value of the Vector
     */
    public double sum()
    {
        double total = 0;
        for(int i = 0;i<size();i++)
        {
            total =+ get(i);
        }
        return total;
    }

}
