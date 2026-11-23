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
        arrayList = new ArrayList<Double>(vectorLength);
        populate(0, vectorLength);
    }

    /**
     * Creates a vector with given size and given default number.
     *
     * @param  vectorLength size of the vector
     * @param  defaultNum default number to populate the Vector
     */
    protected AbstractVector(int vectorLength, double defaultNum)
    {
        arrayList = new ArrayList<Double>(vectorLength);
        populate(defaultNum, vectorLength);
    }

    /**
     * Creates a vector the same size and numbers as the array.
     *
     * @param  array size of the vector
     */
    protected AbstractVector(double[] array)
    {
        arrayList = new ArrayList<Double>(array.length);
        for(int i=0;i<array.length;i++)
        {
            arrayList.add(i,array[i]);
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
     * @param  vector
     */
    protected AbstractVector(AbstractVector vector)
    {
        arrayList = new ArrayList<Double>(vector.arrayList);
    }

    //------------------//
    //General Operations//
    //------------------//

    /**
     * Replaces every number in the Vector with the given number.
     *
     * @param  num number
     * @param  desiredSize size of the Vector
     */
    private void populate(double num, int desiredSize)
    {
        for(int i=0; i<desiredSize; i++)
        {
            arrayList.add(i,Double.valueOf(num));
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

    /**
     * Returns the values in the vector as new array.
     *
     * @return vector in the form of an array
     */
    public double[] toArray()
    {
        double[] result = new double[size()];
        for(int i =0;i<size();i++)
        {
            result[i] = get(i);
        }
        return result;
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
     */
    public void add(double d)
    {
        for(int i = 0;i < this.size();i++)
        {
            this.set(i,get(i) + d);
        }
    }

    /**
     * Adds each element from the given Vector to this Vector.
     *
     * @param  v given Vector
     */
    public void add(AbstractVector v)
    {
        int maxLength = Math.max(size(), v.size());

        for(int i = 0; i<maxLength; i++)
        {
            this.set(i, get(i) + v.get(i));
        }
    }

    //-----------//
    //Subtraction//
    //-----------//

    /**
     * Subtracts the given value to each element of the Vector.
     *
     * @param  d value to be subtracted
     */
    public void sub(double d)
    {
        for(int i = 0;i < this.size();i++)
        {
            this.set(i,get(i) - d);
        }
    }

    /**
     * Subtracts each element from the given Vector to this Vector.
     *
     * @param  v given Vector
     */
    public void sub(AbstractVector v)
    {
        int maxLength = Math.max(size(), v.size());

        for(int i = 0; i<maxLength; i++)
        {
            this.set(i, get(i) - v.get(i));
        }
    }

    //--------------//
    //Multiplication//
    //--------------//

    /**
     * Multiply the given value to each element of the Vector.
     *
     * @param  d value to be multiplied
     */
    public void mult(double d)
    {
        for(int i = 0;i < this.size();i++)
        {
            this.set(i,get(i) * d);
        }
    }

    /**
     * Multiplies each element from the given Vector to this Vector.
     *
     * @param  v given Vector
     */
    public void mult(AbstractVector v)
    {
        int maxLength = Math.max(size(), v.size());

        for(int i = 0; i<maxLength; i++)
        {
            this.set(i, get(i) * v.get(i));
        }
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

    /**
     * Converts each value to either +1 or -1 (0 will count as positive)
     * 
     */
    public void toSignBinary()
    {
        for(int i=0;i<size();i++)
        {
            set(i,get(i) > 0 ? 1 : -1);
        }
    }

    /**
     * Flips the sign of every value.
     * 
     */
    public void flipSign()
    {
        for(int i=0;i<size();i++)
        {
            set(i,get(i) * -1);
        }
    }

}
