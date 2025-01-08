package by.malahovski;

public interface MyList<T> {

    void add(T element);

    void add(int index, T element);

    T get(int index);

    T set(int index, T element);

    T remove(int index);

    boolean remove(T element);

    int size();

    boolean isEmpty();
}