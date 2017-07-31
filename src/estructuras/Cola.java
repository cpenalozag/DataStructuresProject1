package estructuras;

import java.util.NoSuchElementException;

/**
 *  The {@code Queue} class represents a first-in-first-out (FIFO)
 *  queue of generic items.
 *  It supports the usual <em>enqueue</em> and <em>dequeue</em>
 *  */
public class Cola<T extends IdentificadoUnicamente> extends ListaSencillamenteEncadenada<T>
{
	/**
	 * Initializes an empty queue.
	 */
	public Cola()
	{
		primero = null;
		size = super.size;
	}
	
	/**
	 * Adds the item to this queue
	 * @param elemento The item to add
	 */

	public void enqueue (T elemento)
	{
		this.add(elemento);
	}
	
	 /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     */
	
	public T dequeue ()
	{
		return (T) this.removerPrimero();
	}
	

    /**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue	
     * @throws NoSuchElementException if this queue is empty
     */
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return (T) primero.elemento;
    }
	
}