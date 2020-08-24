/**
 * Your implementation of an ArrayList.
 *
 * @author Noah Dorfman
 * @version 1.0
 * @userid ndorfman6
 * @GTID 903440327
 * <p>
 * Collaborators: N/A
 * <p>
 * Resources: ArrayListCharlieTest (Charles Jenkins),
 * ArrayListKTest (Mija Kennedy), ArrayListJUnitTestCode (Vincent Davies),
 * ArrayListTestYuri (Yoo Jhang)
 */
public class ArrayList<T> {

    /**
     * The initial capacity of the ArrayList.
     * <p>
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 9;

    // Do not add new instance variables or modify existing ones.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     * <p>
     * Java does not allow for regular generic array creation, so you will have
     * to cast an Object[] to a T[] to get the generic typing.
     */
    public ArrayList() {
        this.size = 0;
        this.backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Adds the element to the specified index.
     * <p>
     * Remember that this add may require elements to be shifted.
     * <p>
     * Must be amortized O(1) for index size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > this.size) {
            throw new java.lang.IndexOutOfBoundsException("You cannot add data outside of ArrayList bounds.");
        }

        if (data == null) {
            throw new java.lang.IllegalArgumentException("You cannot add null data to ArrayList.");
        }

        /*
         * Checking if adding at index will overflow array.
         * If so, make an array of double capacity.
         */
        if (this.size + 1 > this.backingArray.length) {
            T[] newArray = (T[]) new Object[this.backingArray.length * 2];

            // Shifting of items as copying occurs to leave room for new item.
            for (int i = this.size - 1; i >= index; i--) {
                newArray[i + 1] = this.backingArray[i];
            }

            newArray[index] = data;

            for (int i = index - 1; i >= 0; i--) {
                newArray[i] = this.backingArray[i];
            }

            // Replace old backingArray with newArray.
            this.backingArray = newArray;
        } else {
            // Shifting items to back of array to make room for new item.
            for (int i = this.size - 1; i >= index; i--) {
                this.backingArray[i + 1] = this.backingArray[i];
            }

            this.backingArray[index] = data;
        }

        // Increase size to account for new item.
        this.size += 1;
        return;
    }

    /**
     * Adds the element to the front of the list.
     * <p>
     * Remember that this add may require elements to be shifted.
     * <p>
     * Must be O(n).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("You cannot add null data to ArrayList.");
        }

        /*
         * Checking if adding at index will overflow array.
         * If so, make an array of double capacity.
         */
        if (this.size + 1 > this.backingArray.length) {
            T[] newArray = (T[]) new Object[this.backingArray.length * 2];

            // Shifting of items as copying occurs to leave room for new item.
            for (int i = this.size - 1; i >= 0; i--) {
                newArray[i + 1] = this.backingArray[i];
            }

            newArray[0] = data;

            // Replace old backingArray with newArray.
            this.backingArray = newArray;
        } else {
            // Shifting items to back of array to make room for new item.
            for (int i = this.size - 1; i >= 0; i--) {
                this.backingArray[i + 1] = this.backingArray[i];
            }

            this.backingArray[0] = data;
        }

        // Increase size to account for new item.
        this.size += 1;
    }

    /**
     * Adds the element to the back of the list.
     * <p>
     * Must be amortized O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("You cannot add null data to ArrayList.");
        }

        /*
         * Checking if adding at index will overflow array.
         * If so, make an array of double capacity.
         */
        if (this.size + 1 > this.backingArray.length) {
            T[] newArray = (T[]) new Object[this.backingArray.length * 2];

            // Copying backingArray to newArray.
            for (int i = 0; i < this.size; i++) {
                newArray[i] = backingArray[i];
            }

            newArray[this.size] = data;

            // Replace old backingArray with newArray.
            this.backingArray = newArray;
        } else {
            // Simply add the new item to the back.
            this.backingArray[this.size] = data;
        }

        // Increase size to account for new item.
        this.size += 1;
    }

    /**
     * Removes and returns the element at the specified index.
     * <p>
     * Remember that this remove may require elements to be shifted.
     * <p>
     * Must be O(1) for index size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new java.lang.IndexOutOfBoundsException("You cannot remove data outside of ArrayList bounds.");
        }

        // Saving item to be removed for later return.
        T removedItem = this.backingArray[index];

        // Shifting items over top of where the removed item was.
        for (int i = index + 1; i < this.size; i++) {
            this.backingArray[i - 1] = this.backingArray[i];
        }

        // Removing the old last item of the list (duplicate).
        this.backingArray[this.size - 1] = null;

        // Decrease size to account for removed item.
        size -= 1;
        return removedItem;
    }

    /**
     * Removes and returns the first element of the list.
     * <p>
     * Remember that this remove may require elements to be shifted.
     * <p>
     * Must be O(n).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (this.size == 0) {
            throw new java.util.NoSuchElementException("The ArrayList is empty.");
        }

        // Saving item to be removed for later return.
        T removedItem = this.backingArray[0];

        // Shifting items over top of where the removed item was.
        for (int i = 1; i < this.size; i++) {
            this.backingArray[i - 1] = this.backingArray[i];
        }

        // Removing the old last item of the list (duplicate).
        this.backingArray[this.size - 1] = null;

        // Decrease size to account for removed item.
        size -= 1;
        return removedItem;
    }

    /**
     * Removes and returns the last element of the list.
     * <p>
     * Must be O(1).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (this.size == 0) {
            throw new java.util.NoSuchElementException("The ArrayList is empty.");
        }

        // Saving item to be removed for later return.
        T removedItem = this.backingArray[this.size - 1];

        // Simply remove the last item of the list.
        this.backingArray[this.size - 1] = null;

        // Decrease size to account for removed item.
        size -= 1;
        return removedItem;
    }

    /**
     * Returns the element at the specified index.
     * <p>
     * Must be O(1).
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new java.lang.IndexOutOfBoundsException("You cannot access data outside of ArrayList bounds.");
        }

        return backingArray[index];
    }

    /**
     * Returns whether or not the list is empty.
     * <p>
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        boolean result = false;

        if (this.size == 0) {
            result = true;
        }

        return result;
    }

    /**
     * Clears the list.
     * <p>
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     * <p>
     * Must be O(1).
     */
    public void clear() {
        this.size = 0;
        this.backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Returns the backing array of the list.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the list.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
