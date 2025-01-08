package by.malahovski;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Реализация собственного списка на основе массива
 * с единственным конструктором устанавливающий начальный рзмер списка.
 * наследующего интерфейс {@link MyList}.
 *
 * @param <T> тип элементов, которые будут храниться в списке
 */
public class MyArrayList<T> implements MyList<T>, Iterable<T> {

    /**
     * Начальная ёмкость массива по умолчанию.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Массив для хранения элементов списка.
     */
    private Object[] elements;

    /**
     * Текущий размер списка.
     */
    private int size;

    /**
     * Конструктор по умолчанию. Создаёт список с начальной ёмкостью.
     */
    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Увеличивает ёмкость массива в 1,5 раза, если он заполнен
     */
    private void ensureCapacity() {
        if (size >= elements.length) {
            int newCapacity = elements.length + (elements.length >> 1);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    /**
     * Проверяет, что индекс находится в пределах допустимых значений списка.
     *
     * @param index индекс для проверки
     * @throws IndexOutOfBoundsException если индекс выходит за границы списка
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element элемент, который нужно добавить
     */
    @Override
    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    /**
     * Добавляет элемент в заданную позицию списка.
     *
     * @param index   индекс, куда нужно вставить элемент
     * @param element элемент, который нужно вставить
     * @throws IndexOutOfBoundsException если индекс выходит за пределы [0, size]
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Возвращает элемент по индексу.
     *
     * @param index индекс элемента
     * @return элемент на заданной позиции
     * @throws IndexOutOfBoundsException если индекс выходит за границы
     * @SuppressWarnings("unchecked") используется для подавления предупреждений о приведении Object к типу T,
     * так как массив элементов хранится как Object[], а тип T определяется во время компиляции.
     */
    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    /**
     * Заменяет элемент в указанной позиции списка на новый элемент.
     * Возвращает старый элемент, который находился в этой позиции.
     *
     * @param index   индекс элемента, который нужно заменить (должен быть в диапазоне от 0 до size - 1)
     * @param element новый элемент, который будет установлен на указанную позицию
     * @return старый элемент, находившийся на указанной позиции
     * @throws IndexOutOfBoundsException если указанный индекс выходит за пределы допустимого диапазона
     * @SuppressWarnings("unchecked") используется для подавления предупреждений о приведении Object к типу T,
     * так как массив элементов хранится как Object[], а тип T определяется во время компиляции.
     */
    @SuppressWarnings("unchecked")
    @Override
    public T set(int index, T element) {
        checkIndex(index);
        T oldElement = (T) elements[index];
        elements[index] = element;
        return oldElement;
    }

    /**
     * Удаляет элемент по индексу.
     *
     * @param index индекс элемента, который нужно удалить
     * @return удалённый элемент
     * @throws IndexOutOfBoundsException если индекс выходит за границы
     * @SuppressWarnings("unchecked") используется для подавления предупреждений о приведении Object к типу T,
     * так как массив элементов хранится как Object[], а тип T определяется во время компиляции.
     */
    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = (T) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
        return removedElement;
    }

    /**
     * Удаляет первый найденный элемент, равный указанному.
     *
     * @param element элемент, который нужно удалить
     * @return true, если элемент был найден и удалён, иначе false
     */
    @Override
    public boolean remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Возвращает подсписок из текущего списка.
     * Подсписок включает элементы с индекса fromIndex (включительно) до toIndex (исключительно).
     *
     * @param fromIndex начальный индекс (включительно)
     * @param toIndex   конечный индекс (исключительно)
     * @return новый список, содержащий элементы из указанного диапазона
     * @throws IndexOutOfBoundsException если индексы выходят за пределы списка
     * @throws IllegalArgumentException  если fromIndex больше toIndex
     */
    public MyArrayList<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("Некорректные индексы: fromIndex=" + fromIndex + ", toIndex=" + toIndex);
        }

        MyArrayList<T> subList = new MyArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(this.get(i));
        }
        return subList;
    }

    /**
     * Возвращает текущий размер списка.
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
     * @return true, если список пуст, иначе возвращает false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Возвращает строковое представление списка.
     *
     * @return строка с элементами списка
     */
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elements, size));
    }

    /**
     * Возвращает итератор для обхода элементов списка.
     *
     * @return итератор для списка
     */
    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

    /**
     * Внутренний класс для реализации итератора.
     */
    private class MyArrayListIterator implements Iterator<T> {
        private int currentIndex = 0;

        /**
         * Проверяет, есть ли следующий элемент.
         *
         * @return true, если следующий элемент существует, иначе false
         */
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        /**
         * Возвращает следующий элемент.
         *
         * @return следующий элемент
         * @throws java.util.NoSuchElementException если элементов больше нет
         * @SuppressWarnings("unchecked") используется для подавления предупреждений о приведении Object к типу T,
         * так как массив элементов хранится как Object[], а тип T определяется во время компиляции.
         */
        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return (T) elements[currentIndex++];
        }
    }
}