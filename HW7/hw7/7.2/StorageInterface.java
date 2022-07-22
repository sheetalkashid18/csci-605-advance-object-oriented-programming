package hw7;

public interface StorageInterface<T> {
	boolean add(T x);               // true if it was successfully added, false otherwise
    boolean find(T x);           // true if x could be found, false otherwise
    boolean includesNull();      // true, if the storage include a null element, false otherwise
    boolean delete(T x);         // true if it was successfully deleted, false otherwise
}
