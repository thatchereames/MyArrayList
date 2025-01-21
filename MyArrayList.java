/**
 * Name: Thatcher Eames
 * Email: theames@ucsd.edu
 * PID: A17284279
 * Sources Used: write-up, 
 * 
 * This file does x...
 * 
 */



public class MyArrayList<E> implements MyList<E> {

    Object[] data;
    int size;

    /**
     * This is a no arg contructor that creates instances of MyArrayList
     */
    public MyArrayList() {
        this.data = new Object[5];
        this.size = 0;
    }

    /**
     * Constructor that creates instances of MyArrayList if only one int 
     * parameter is given
     * 
     * @param initialCapacity is an int representing the maximum capacity of the
     * instance of MyArrayList created
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        this.data = new Object[initialCapacity];
    }

    /**
     * Constructor that creates instances of MyArrayList
     * 
     * @param arr An array that the values are passed into the MyArrayList
     */
    public MyArrayList(E[] arr) {
        if (arr == null) {
            this.data = new Object[5];
            this.size = 0;
        } else {
            this.data = new Object[arr.length];
            for (int i = 0; i < arr.length; i++) {
                this.data[i] = arr[i];
                size++;
            }
        }
    }

    private static final String INVALID_CAPACITY_MSG =   
    "Specified capacity is less than current capacity";
    private static final String INVALID_INDEX_MSG = "Index is out of bounds";
    

    /**
     * Expands the capacity of the instance of MyArrayList to either 2 * the
     * current capacity or to the user specified required capacity, whichever is
     * value is greater. 
     * 
     * @param requiredCapacity Intrepresents the needed capacity of the user
     */
    @Override
    public void expandCapacity(int requiredCapacity) 
    throws IllegalArgumentException {
        // Determines which capacity is larger and sets the capacity
        if (data.length > requiredCapacity) {
            throw new IllegalArgumentException(INVALID_CAPACITY_MSG);
        } else if (data.length == 0 && requiredCapacity <= 5){
            data = new Object[5];
        } else if (data.length * 2 >= requiredCapacity) {
            Object[] newData = new Object[data.length * 2];
            for (int i = 0 ; i < data.length ; i++) {
                newData[i] = data[i];
            }
            data = newData;
        } else {
            Object[] newData = new Object[requiredCapacity];
            for (int i = 0; i < newData.length ; i++) {
                newData[i] = data[i];
            }
        }
    }

    @Override
    public int getCapacity() {
        return data.length;
    }
    
    /**
     * Adds a new element at a specified index. If MyArrayList is at capacity,  
     * expands the capacity of the MyArrayList
     * 
     * @param index int representing the desired index of the element
     * @param element the element to be added to the MyArray List
     */
    @Override
    public void insert(int index, E element) throws IndexOutOfBoundsException {
        //Throws an error if the index user provides is out of bounds
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(INVALID_INDEX_MSG);
        }
        //Expands the capacity of the data array if it is insuffient
        if (size == data.length) {
            expandCapacity(size + 1);
        } 
        //Moves the elements after the index of new element to one index greater
        for (int i = size ; i > index ; i-- ) {
            data[i] = data[i - 1];
        } 
        data[index] = element;
        size++;
    }

    /**
     * Adds an element to the end of MyArrayList
     * 
     * @param element 
     */
    @Override
    public void append(E element) {
        if (size == data.length) {
            expandCapacity(size + 1);
        }
        data[size] = element;
        size++;
    }
    /**
     * Adds an element to the beginning of MyArrayList
     * 
     * @param element An object of type E to be added to MyArrayList
     */
    @Override 
    public void prepend(E element) {
        if (size == data.length) {
            expandCapacity(size + 1);
        }
        for (int i = size ; i > 0 ; i--) {
            data[i] = data[i - 1];
        } 
        data[0] = element;
        size++;
    }
    /**
     * Retrieves an element E from the MyArrayList at a specified index
     * 
     * @param index An int representing the index to be retreived
     */
    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) data[index];
    }
    /**
     * Changes the element at the specified index to a new element and returns
     * the overwritten element
     * 
     * @param index An int representing the index of the element you want to 
     * replace
     * @param element An object of type E to replace the old element
     * @return An object of type E that was replaced
     */
    @Override
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        E overwrittenElement = (E) data[index];
        data[index] = element;
        return overwrittenElement;
    }

    /**
     * Removes an element from the MyArrayList at a specified element
     * 
     * @param index An int representing the index of the element
     * @return The object of type E that was removed from the list
     */
    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(INVALID_INDEX_MSG);
        }
        E removedElement = (E) data[index];
        for (int i = index ; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        return removedElement;
    }

    /**
     * Returns the number of elements that are currently held in the MyArrayList
     * 
     * @return An int value representing the number of elements
     */
    @Override
    public int size() {
        return size;
    }
    /**
     * Rotates all of the items in the list, such that each item decreases in 
     * index by 1 and the first item becomes the last one.
     * 
     * @param numPositions An int representing the number of times the 
     * MyArrayList will be rotated
     */
    @Override
    @SuppressWarnings("unchecked")
    public void rotate(int numPositions) throws IndexOutOfBoundsException {
        if (numPositions < 0 || numPositions >= size) {
            throw new IndexOutOfBoundsException(INVALID_INDEX_MSG);
        }
        for (int i = 0 ; i < numPositions ; i++) {
            E lastElement = (E) data[size - 1];
            for (int j = size - 1; j > 0; j--) {
                data[j] = data[j - 1];
            }
            data[0] = lastElement;
        }
    }

    @Override
    public int find(E element) {
        for (int i = size - 1 ; i > 0 ; i--) {
            if (data[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

}