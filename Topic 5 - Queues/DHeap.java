//Filip Lingefelt, fili8261
package alda.heap;
// Klassen i denna fil måste döpas om till DHeap för att testerna ska fungera.

//DHeap class
//
//CONSTRUCTION: with optional capacity (that defaults to 100)
//            or an array containing initial items
//
//******************PUBLIC OPERATIONS*********************
//void insert( x )       --> Insert x
//Comparable deleteMin( )--> Return and remove smallest item
//Comparable findMin( )  --> Return smallest item
//boolean isEmpty( )     --> Return true if empty; else false
//void makeEmpty( )      --> Remove all items
//******************ERRORS********************************
//Throws UnderflowException as appropriate



/**
 * Implements a binary heap.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class DHeap<T extends Comparable<? super T>>
{

    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;      // Number of elements in heap
    private T [ ] array; // The heap array
    private int dim = 2;
    /**
     * Construct the binary heap.
     */
    public DHeap( )
    {
        this( 2);
    }

    public DHeap(int d)
    {
        this( DEFAULT_CAPACITY, d);
    }

    /**
     * Construct the binary heap.
     * @param capacity the capacity of the binary heap.
     */
    public DHeap( int capacity, int dim)
    {
        if(dim <= 1){
            throw new IllegalArgumentException();
        }
        this.dim = dim;
        currentSize = 0;
        array = (T[]) new Comparable[ capacity + 1 ];
    }

    /**
     * Construct the binary heap given an array of items.
     */
    public DHeap( T [ ] items )
    {
        currentSize = items.length;
        array = (T[]) new Comparable[ ( currentSize + 2 ) * 11 / 10 ];

        int i = 1;
        for( T item : items )
            array[ i++ ] = item;
        buildHeap( );
    }

    /**
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed.
     * @param x the item to insert.
     */

    public void insert( T x )
    {
        if( currentSize == array.length - 1 )
            enlargeArray( array.length * 2 + 1 );

        // Percolate up
        int hole = ++currentSize;
        for( array[0] = x; hole != 1 && x.compareTo(array[parentIndex(hole)]) < 0; hole = parentIndex(hole) )
            array[ hole ] = array[parentIndex(hole)];
        array[ hole ] = x;
    }

    private void enlargeArray( int newSize )
    {
        T [] old = array;
        array = (T []) new Comparable[ newSize ];
        for( int i = 0; i < old.length; i++ )
            array[ i ] = old[ i ];
    }

    /**
     * Find the smallest item in the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public T findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return array[ 1 ];
    }

    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public T deleteMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );

        T minItem = findMin( );
        array[ 1 ] = array[ currentSize-- ];
        percolateDown( 1 );

        return minItem;
    }

    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */
    private void buildHeap( )
    {
        for( int i = currentSize / 2; i > 0; i-- )
            percolateDown( i );
    }

    /**
     * Test if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return currentSize == 0;
    }

    /**
     * Make the priority queue logically empty.
     */
    public void makeEmpty( )
    {
        currentSize = 0;
    }

    /**
     * Internal method to percolate down in the heap.
     * @param hole the index at which the percolate begins.
     */

    private void percolateDown( int hole )
    {
        int child;
        T tmp = array[ hole ];
        int tempChild;

        for( ; firstChildIndex(hole) <= currentSize; hole = child )
        {
            child = firstChildIndex(hole);
            tempChild = child;

            for(int i = 0; i < dim; i++){
                tempChild = firstChildIndex(hole) + i;
                if( tempChild <= currentSize && array[tempChild].compareTo( array[ child ] ) < 0 )
                    child = tempChild;

            }


            if( array[ child ].compareTo( tmp ) < 0 )
                array[ hole ] = array[ child ];
            else
                break;
        }
        array[ hole ] = tmp;
    }


    public int parentIndex(int child){
        if(child <= 1){
            throw new IllegalArgumentException();
        }
        int parentIndex;
        parentIndex = ((dim-2)+child)/dim;
        return parentIndex;
    }

    public int firstChildIndex(int parent){
        if(parent <= 0){
            throw new IllegalArgumentException();
        }
        int firstChildIndex;
        if(parent == 1){
            firstChildIndex = 2;
        }else{
            firstChildIndex = dim*(parent-1)+2;
        }
        return firstChildIndex;
    }

    public int size() {
        return currentSize;
    }
    public T get(int index){
        return array[index];
    }


    // Test program
    public static void main( String [ ] args )
    {
        int numItems = 10000;
        DHeap<Integer> h = new DHeap<>( );
        int i = 37;

        for( i = 37; i != 0; i = ( i + 37 ) % numItems )
            h.insert( i );
        for( i = 1; i < numItems; i++ )
            if( h.deleteMin( ) != i )
                System.out.println( "Oops! " + i );
    }
}
