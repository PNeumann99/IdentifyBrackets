/**
 * Einfache Implementierung eines Stackelements mit generischem Datentyp
 * für die Stack Klasse.
 *
 * @param <T> Der Datentyp des Stackelements.
 * @author akubf
 */
public class StackNode<T> {
    public T data; // Der Wert des Elements
    public StackNode<T> next; // Der Pointer auf das nächste Element (unter diesem)

    /**
     * Erzeugt eine neue StackNode.
     *
     * @param data Der Wert des neuen Elements.
     */
    public StackNode(T data) {
        this.data = data;
        this.next = null;
    }
}
