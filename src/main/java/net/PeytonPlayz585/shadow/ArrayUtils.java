package net.PeytonPlayz585.shadow;

import java.util.function.Function;
import java.util.function.Supplier;

public class ArrayUtils {
	/**
     * Clones an array or returns {@code null}.
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param array the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static boolean[] clone(final boolean[] array) {
        return array != null ? array.clone() : null;
    }

    /**
     * Clones an array or returns {@code null}.
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param array the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static byte[] clone(final byte[] array) {
        return array != null ? array.clone() : null;
    }

    /**
     * Clones an array or returns {@code null}.
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param array the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static char[] clone(final char[] array) {
        return array != null ? array.clone() : null;
    }

    /**
     * Clones an array or returns {@code null}.
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param array the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static double[] clone(final double[] array) {
        return array != null ? array.clone() : null;
    }

    /**
     * Clones an array or returns {@code null}.
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param array the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static float[] clone(final float[] array) {
        return array != null ? array.clone() : null;
    }

    /**
     * Clones an array or returns {@code null}.
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param array the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static int[] clone(final int[] array) {
        return array != null ? array.clone() : null;
    }

    /**
     * Clones an array or returns {@code null}.
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param array the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static long[] clone(final long[] array) {
        return array != null ? array.clone() : null;
    }

    /**
     * Clones an array or returns {@code null}.
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param array the array to clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static short[] clone(final short[] array) {
        return array != null ? array.clone() : null;
    }

    /**
     * Shallow clones an array or returns {@code null}.
     * <p>
     * The objects in the array are not cloned, thus there is no special handling for multi-dimensional arrays.
     * </p>
     * <p>
     * This method returns {@code null} for a {@code null} input array.
     * </p>
     *
     * @param <T>   the component type of the array
     * @param array the array to shallow clone, may be {@code null}
     * @return the cloned array, {@code null} if {@code null} input
     */
    public static <T> T[] clone(final T[] array) {
        return array != null ? array.clone() : null;
    }

    /**
     * A fluent version of {@link System#arraycopy(Object, int, Object, int, int)} that returns the destination array.
     *
     * @param <T>       the type.
     * @param source    the source array.
     * @param sourcePos starting position in the source array.
     * @param destPos   starting position in the destination data.
     * @param length    the number of array elements to be copied.
     * @param allocator allocates the array to populate and return.
     * @return dest
     * @throws IndexOutOfBoundsException if copying would cause access of data outside array bounds.
     * @throws ArrayStoreException       if an element in the <code>src</code> array could not be stored into the <code>dest</code> array because of a type
     *                                   mismatch.
     * @throws NullPointerException      if either <code>src</code> or <code>dest</code> is <code>null</code>.
     * @since 3.15.0
     */
    public static <T> T arraycopy(final T source, final int sourcePos, final int destPos, final int length, final Function<Integer, T> allocator) {
        return arraycopy(source, sourcePos, allocator.apply(length), destPos, length);
    }

    /**
     * A fluent version of {@link System#arraycopy(Object, int, Object, int, int)} that returns the destination array.
     *
     * @param <T>       the type.
     * @param source    the source array.
     * @param sourcePos starting position in the source array.
     * @param destPos   starting position in the destination data.
     * @param length    the number of array elements to be copied.
     * @param allocator allocates the array to populate and return.
     * @return dest
     * @throws IndexOutOfBoundsException if copying would cause access of data outside array bounds.
     * @throws ArrayStoreException       if an element in the <code>src</code> array could not be stored into the <code>dest</code> array because of a type
     *                                   mismatch.
     * @throws NullPointerException      if either <code>src</code> or <code>dest</code> is <code>null</code>.
     * @since 3.15.0
     */
    public static <T> T arraycopy(final T source, final int sourcePos, final int destPos, final int length, final Supplier<T> allocator) {
        return arraycopy(source, sourcePos, allocator.get(), destPos, length);
    }

    /**
     * A fluent version of {@link System#arraycopy(Object, int, Object, int, int)} that returns the destination array.
     *
     * @param <T>       the type
     * @param source    the source array.
     * @param sourcePos starting position in the source array.
     * @param dest      the destination array.
     * @param destPos   starting position in the destination data.
     * @param length    the number of array elements to be copied.
     * @return dest
     * @throws IndexOutOfBoundsException if copying would cause access of data outside array bounds.
     * @throws ArrayStoreException       if an element in the <code>src</code> array could not be stored into the <code>dest</code> array because of a type
     *                                   mismatch.
     * @throws NullPointerException      if either <code>src</code> or <code>dest</code> is <code>null</code>.
     * @since 3.15.0
     */
    public static <T> T arraycopy(final T source, final int sourcePos, final T dest, final int destPos, final int length) {
        System.arraycopy(source, sourcePos, dest, destPos, length);
        return dest;
    }
    
    public static String arrayToString(boolean[] arr, String separator)
    {
        if (arr == null)
        {
            return "";
        }
        else
        {
            StringBuffer stringbuffer = new StringBuffer(arr.length * 5);

            for (int i = 0; i < arr.length; ++i)
            {
                boolean flag = arr[i];

                if (i > 0)
                {
                    stringbuffer.append(separator);
                }

                stringbuffer.append(String.valueOf(flag));
            }

            return stringbuffer.toString();
        }
    }

    public static String arrayToString(float[] arr)
    {
        return arrayToString(arr, ", ");
    }

    public static String arrayToString(float[] arr, String separator)
    {
        if (arr == null)
        {
            return "";
        }
        else
        {
            StringBuffer stringbuffer = new StringBuffer(arr.length * 5);

            for (int i = 0; i < arr.length; ++i)
            {
                float f = arr[i];

                if (i > 0)
                {
                    stringbuffer.append(separator);
                }

                stringbuffer.append(String.valueOf(f));
            }

            return stringbuffer.toString();
        }
    }

    public static String arrayToString(float[] arr, String separator, String format)
    {
        if (arr == null)
        {
            return "";
        }
        else
        {
            StringBuffer stringbuffer = new StringBuffer(arr.length * 5);

            for (int i = 0; i < arr.length; ++i)
            {
                float f = arr[i];

                if (i > 0)
                {
                    stringbuffer.append(separator);
                }

                stringbuffer.append(String.format(format, new Object[] {Float.valueOf(f)}));
            }

            return stringbuffer.toString();
        }
    }

    public static String arrayToString(int[] arr)
    {
        return arrayToString(arr, ", ");
    }

    public static String arrayToString(int[] arr, String separator)
    {
        if (arr == null)
        {
            return "";
        }
        else
        {
            StringBuffer stringbuffer = new StringBuffer(arr.length * 5);

            for (int i = 0; i < arr.length; ++i)
            {
                int j = arr[i];

                if (i > 0)
                {
                    stringbuffer.append(separator);
                }

                stringbuffer.append(String.valueOf(j));
            }

            return stringbuffer.toString();
        }
    }
    
    public static String arrayToHexString(int[] arr, String separator)
    {
        if (arr == null)
        {
            return "";
        }
        else
        {
            StringBuffer stringbuffer = new StringBuffer(arr.length * 5);

            for (int i = 0; i < arr.length; ++i)
            {
                int j = arr[i];

                if (i > 0)
                {
                    stringbuffer.append(separator);
                }

                stringbuffer.append("0x");
                stringbuffer.append(Integer.toHexString(j));
            }

            return stringbuffer.toString();
        }
    }

    public static String arrayToString(Object[] arr)
    {
        return arrayToString(arr, ", ");
    }

    public static String arrayToString(Object[] arr, String separator)
    {
        if (arr == null)
        {
            return "";
        }
        else
        {
            StringBuffer stringbuffer = new StringBuffer(arr.length * 5);

            for (int i = 0; i < arr.length; ++i)
            {
                Object object = arr[i];

                if (i > 0)
                {
                    stringbuffer.append(separator);
                }

                stringbuffer.append(String.valueOf(object));
            }

            return stringbuffer.toString();
        }
    }
}
