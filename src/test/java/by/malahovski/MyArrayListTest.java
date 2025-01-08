package by.malahovski;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class MyArrayListTest {

    @Test
    void testAddElement() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals(3, list.size(), "Размер списка должен быть 3");
        assertEquals("A", list.get(0), "Первый элемент должен быть 'A'");
        assertEquals("B", list.get(1), "Второй элемент должен быть 'B'");
        assertEquals("C", list.get(2), "Третий элемент должен быть 'C'");
    }

    @Test
    void testAddElementAtIndex() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");
        list.add("C");
        list.add(1, "B");

        assertEquals(3, list.size(), "Размер списка должен быть 3");
        assertEquals("A", list.get(0), "Первый элемент должен быть 'A'");
        assertEquals("B", list.get(1), "Второй элемент должен быть 'B'");
        assertEquals("C", list.get(2), "Третий элемент должен быть 'C'");
    }

    @Test
    void testRemoveElementByIndex() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        String removedElement = list.remove(1);

        assertEquals("B", removedElement, "Удалённый элемент должен быть 'B'");
        assertEquals(2, list.size(), "Размер списка должен быть 2");
        assertEquals("A", list.get(0), "Первый элемент должен быть 'A'");
        assertEquals("C", list.get(1), "Второй элемент должен быть 'C'");
    }

    @Test
    void testSetElement() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        String oldValue = list.set(1, "D");

        assertEquals("B", oldValue, "Старое значение должно быть 'B'");
        assertEquals("D", list.get(1), "Новое значение должно быть 'D'");
        assertEquals(3, list.size(), "Размер списка не должен измениться");
    }

    @Test
    void testRemoveElementByValue() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        boolean isRemoved = list.remove("B");

        assertTrue(isRemoved, "Элемент 'B' должен быть удалён");
        assertEquals(2, list.size(), "Размер списка должен быть 2");
        assertEquals("A", list.get(0), "Первый элемент должен быть 'A'");
        assertEquals("C", list.get(1), "Второй элемент должен быть 'C'");
    }

    @Test
    void testGetElement() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals("A", list.get(0), "Первый элемент должен быть 'A'");
        assertEquals("B", list.get(1), "Второй элемент должен быть 'B'");
        assertEquals("C", list.get(2), "Третий элемент должен быть 'C'");
    }

    @Test
    void testSubList() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        MyArrayList<String> subList = list.subList(1, 3);

        assertEquals(2, subList.size(), "Размер подсписка должен быть 2");
        assertEquals("B", subList.get(0), "Первый элемент подсписка должен быть 'B'");
        assertEquals("C", subList.get(1), "Второй элемент подсписка должен быть 'C'");
    }

    @Test
    void testSizeAndIsEmpty() {
        MyArrayList<String> list = new MyArrayList<>();

        assertTrue(list.isEmpty(), "Список должен быть пустым");
        assertEquals(0, list.size(), "Размер пустого списка должен быть 0");

        list.add("A");

        assertFalse(list.isEmpty(), "Список не должен быть пустым");
        assertEquals(1, list.size(), "Размер списка должен быть 1");
    }

    @Test
    void testAddBeyondInitialCapacity() {
        MyArrayList<String> list = new MyArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add("Element " + i);
        }

        assertEquals(15, list.size(), "Размер списка должен быть 15");
        for (int i = 0; i < 15; i++) {
            assertEquals("Element " + i, list.get(i), "Элемент на позиции " + i + " должен быть 'Element " + i + "'");
        }
    }

    @Test
    void testInvalidIndexAccess() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");
        list.add("B");

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1), "Ожидается исключение для индекса -1");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2), "Ожидается исключение для индекса 2");
    }
}