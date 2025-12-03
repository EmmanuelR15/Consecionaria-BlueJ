package diseno;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Implementa una cola FIFO genérica respaldada por {@link LinkedList}.
 *
 * @param <T> tipo de dato almacenado en la cola.
 */
public class Cola<T> {

    private final LinkedList<T> elementos;

    public Cola() {
        this.elementos = new LinkedList<>();
    }

    /**
     * Inserta un nuevo elemento al final de la cola.
     *
     * @param elemento elemento a encolar.
     */
    public void encolar(T elemento) {
        elementos.addLast(elemento);
    }

    /**
     * Extrae y retorna el primer elemento de la cola.
     *
     * @return el elemento al frente de la cola.
     * @throws NoSuchElementException si la cola está vacía.
     */
    public T desencolar() {
        if (estaVacia()) {
            throw new NoSuchElementException("La cola está vacía");
        }
        return elementos.removeFirst();
    }

    /**
     * Consulta el primer elemento sin retirarlo.
     *
     * @return el elemento al frente o null si la cola está vacía.
     */
    public T verPrimero() {
        return elementos.peekFirst();
    }

    public boolean estaVacia() {
        return elementos.isEmpty();
    }

    public int tamanio() {
        return elementos.size();
    }

    @Override
    public String toString() {
        return "Cola" + elementos.toString();
    }
}

