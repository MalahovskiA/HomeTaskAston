package by.malahovski;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

    private MyLinkedList<String> list;

    @BeforeEach
    void setUp() {
        list = new MyLinkedList<>();
    }

    @Test
    void testAddToEnd() {
        list.add("A");
        list.add("B");
        list.add("C");
        assertEquals("[A, B, C]", list.toString(), "Элементы должны добавляться в конец списка");
    }

    @Test
    void testAddAtIndex() {
        list.add("A");
        list.add("C");
        list.add(1, "B");
        assertEquals("[A, B, C]", list.toString(), "Элемент должен быть добавлен по указанному индексу");
    }

    @Test
    void testAddAtIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, "A"), "Должно выбрасываться исключение при добавлении за пределами списка");
    }

    @Test
    void testGet() {
        list.add("A");
        list.add("B");
        list.add("C");
        assertEquals("B", list.get(1), "Метод get должен возвращать элемент по указанному индексу");
    }

    @Test
    void testGetOutOfBounds() {
        list.add("A");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1), "Должно выбрасываться исключение при получении элемента за пределами списка");
    }

    @Test
    void testSet() {
        list.add("A");
        list.add("B");
        list.add("C");
        assertEquals("B", list.set(1, "D"), "Метод set должен возвращать старое значение элемента");
        assertEquals("[A, D, C]", list.toString(), "Элемент должен быть заменён на указанное значение");
    }

    @Test
    void testSetOutOfBounds() {
        list.add("A");
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(1, "B"), "Должно выбрасываться исключение при замене элемента за пределами списка");
    }

    @Test
    void testRemoveByIndex() {
        list.add("A");
        list.add("B");
        list.add("C");
        assertEquals("B", list.remove(1), "Метод remove должен возвращать удалённый элемент");
        assertEquals("[A, C]", list.toString(), "Элемент должен быть удалён по указанному индексу");
    }

    @Test
    void testRemoveByIndexOutOfBounds() {
        list.add("A");
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1), "Должно выбрасываться исключение при удалении элемента за пределами списка");
    }

    @Test
    void testRemoveByElement() {
        list.add("A");
        list.add("B");
        list.add("C");
        assertTrue(list.remove("B"), "Метод remove должен возвращать true, если элемент найден и удалён");
        assertEquals("[A, C]", list.toString(), "Список должен корректно обновляться после удаления элемента");
        assertFalse(list.remove("D"), "Метод remove должен возвращать false, если элемент не найден");
    }

    @Test
    void testIsEmpty() {
        assertTrue(list.isEmpty(), "Список должен быть пустым после создания");
        list.add("A");
        assertFalse(list.isEmpty(), "Список не должен быть пустым после добавления элемента");
    }

    @Test
    void testSize() {
        assertEquals(0, list.size(), "Размер нового списка должен быть 0");
        list.add("A");
        list.add("B");
        assertEquals(2, list.size(), "Размер списка должен корректно обновляться при добавлении элементов");
    }

    @Test
    void testIterator() {
        list.add("A");
        list.add("B");
        list.add("C");

        StringBuilder result = new StringBuilder();
        for (String value : list) {
            result.append(value).append(" ");
        }
        assertEquals("A B C ", result.toString(), "Итератор должен возвращать элементы в правильном порядке");
    }

    @Test
    void testToStringEmptyList() {
        assertEquals("[]", list.toString(), "Метод toString должен возвращать [] для пустого списка");
    }

    @Test
    void testToStringNonEmptyList() {
        list.add("A");
        list.add("B");
        assertEquals("[A, B]", list.toString(), "Метод toString должен корректно отображать элементы списка");
    }
}