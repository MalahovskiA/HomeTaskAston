package by.malahovski;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Собственная реализация двусвязного списка (Linked List),
 * наследующего интерфейс {@link MyList}.
 *
 * @param <T> тип элементов списка
 */
public class MyLinkedList<T> implements MyList<T>, Iterable<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Внутренний класс, представляющий узел списка.
     *
     * @param <T> тип данных, хранящихся в узле
     */
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        /**
         * Конструктор для создания узла.
         *
         * @param data данные, хранящиеся в узле
         */
        Node(T data) {
            this.data = data;
        }
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element элемент для добавления
     */
    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (size == 0) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    /**
     * Добавляет элемент на указанную позицию.
     *
     * @param index индекс, по которому будет добавлен элемент
     * @param element элемент для добавления
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        if (index == size) {
            add(element);
            return;
        }
        Node<T> newNode = new Node<>(element);
        if (index == 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else {
            Node<T> current = getNode(index);
            Node<T> prev = current.prev;
            newNode.next = current;
            newNode.prev = prev;
            if (prev != null) {
                prev.next = newNode;
            }
            current.prev = newNode;
        }
        size++;
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index индекс элемента
     * @return элемент, расположенный по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */
    @Override
    public T get(int index) {
        Node<T> node = getNode(index);
        return node.data;
    }

    /**
     * Заменяет элемент по указанному индексу новым значением.
     *
     * @param index индекс заменяемого элемента
     * @param element новое значение
     * @return старое значение, которое было заменено
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */
    @Override
    public T set(int index, T element) {
        Node<T> node = getNode(index);
        T oldData = node.data;
        node.data = element;
        return oldData;
    }

    /**
     * Удаляет элемент по указанному индексу.
     *
     * @param index индекс удаляемого элемента
     * @return удалённый элемент
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */
    @Override
    public T remove(int index) {
        Node<T> node = getNode(index);
        T removedData = node.data;

        Node<T> prev = node.prev;
        Node<T> next = node.next;

        if (prev != null) {
            prev.next = next;
        } else {
            head = next;
        }

        if (next != null) {
            next.prev = prev;
        } else {
            tail = prev;
        }

        size--;
        return removedData;
    }

    /**
     * Удаляет первый найденный элемент с указанным значением.
     *
     * @param element элемент для удаления
     * @return {@code true}, если элемент был найден и удалён, иначе {@code false}
     */
    @Override
    public boolean remove(T element) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(element)) {
                Node<T> prev = current.prev;
                Node<T> next = current.next;

                if (prev != null) {
                    prev.next = next;
                } else {
                    head = next;
                }

                if (next != null) {
                    next.prev = prev;
                } else {
                    tail = prev;
                }

                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Возвращает размер списка.
     *
     * @return количество элементов в списке
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Проверяет, пуст ли список.
     *
     * @return {@code true}, если список пуст, иначе {@code false}
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Возвращает строковое представление списка.
     *
     * @return строковое представление списка
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Реализует итератор для обхода элементов списка.
     *
     * @return итератор, позволяющий последовательно проходить по элементам списка
     */
    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }

    /**
     * Итератор для MyLinkedList.
     */
    private class MyLinkedListIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    }

    /**
     * Возвращает узел по указанному индексу.
     *
     * @param index индекс узла
     * @return узел списка
     * @throws IndexOutOfBoundsException если индекс выходит за пределы списка
     */
    private Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        Node<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }
}