package estructuras;
import java.util.NoSuchElementException;

/**
 *  The <tt>Stack</tt> class represents a last-in-first-out (LIFO) stack of generic items.
 *  It supports the usual <em>push</em> and <em>pop</em> operations, along with methods
 */
public class Pila<T extends IdentificadoUnicamente> extends ListaSencillamenteEncadenada<T> {
	
	/**
	 * Initializes an empty stack.
	 */
	public Pila() {
		primero = null;
		size = super.size;
	}

	/**
	 * Adds the item to this stack.
	 *
	 * @param  item the item to add
	 */
	public void push(T item) {
		NodoSencillo<IdentificadoUnicamente>anteriorPrimero = (ListaSencillamenteEncadenada<T>.NodoSencillo<IdentificadoUnicamente>) primero;
		primero = (ListaSencillamenteEncadenada<T>.NodoSencillo<T>) new NodoSencillo<IdentificadoUnicamente>(null);
		primero.elemento = item;
		primero.siguiente = (ListaSencillamenteEncadenada<T>.NodoSencillo<T>) anteriorPrimero;
		size++;
	}

	/**
	 * Removes and returns the item most recently added to this stack.
	 *
	 * @return the item most recently added
	 * @throws NoSuchElementException if this stack is empty
	 */
	public T pop() {
		return (T) this.removerPrimero();
	}


	/**
	 * Returns (but does not remove) the item most recently added to this stack.
	 *
	 * @return the item most recently added to this stack
	 * @throws NoSuchElementException if this stack is empty
	 */
	public T peek() {
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		return (T) darPrimero().elemento;
	}
}
