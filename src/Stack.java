/**
 * Einfache Implementierung eines Stacks mit generischem Datentyp.
 *
 * @param <T> Datentyp des Stacks.
 * @author akubf
 */
public class Stack<T> {
    private StackNode<T> topElement; // Pointer auf den Kopf des Stacks.
    private int counter; // Anzahl Elemente im Stack.

    /**
     * Legt einen leeren Stack an.
     */
    public Stack() {
        this.topElement = null;
        this.counter = 0;
    }

    /**
     * Legt ein neues Element auf den Stack.
     * POST: das neue Element ist das oberste Element, Anzahl Elemente um eins erhöht.
     *
     * @param t der Wert des Elements.
     */
    public void push(T t) {
        StackNode<T> newElement = new StackNode<>(t);
        newElement.next = this.topElement;
        this.topElement = newElement;
        this.counter++;
    }

    /**
     * Entfernt das oberste Stackelement.
     * PRE: Stack ist nicht leer.
     * POST: oberstes Element entfernt, Anzahl Elemente um eins verringert.
     */
    public void pop() {
        if (!this.isEmpty()) {
            topElement = topElement.next;
            counter--;
        }
    }

    /**
     * Liefert das oberste Stackelement.
     *
     * @return den Wert des obersten Elements.
     */
    public T top() {
        return topElement.data;
    }

    /**
     * Testet, ob der Stack leer ist.
     *
     * @return genau dann true, wenn der Stack leer ist.
     */
    public boolean isEmpty() {
        return topElement == null;
    }

    /**
     * Gibt die aktuelle Anzahl Elemente auf dem Stack zurück.
     *
     * @return die aktuelle Größe des Stacks.
     */
    public int size() {
        return counter;

    }
}
