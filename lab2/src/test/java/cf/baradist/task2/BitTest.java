package cf.baradist.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitTest {

    private Bit bit;

    private static void printBinary(int value) {
        System.out.println(Integer.toBinaryString(value));
    }

    @BeforeEach
    void setUp() {
        bit = new Bit();
    }

    @Test
    void checkBit() throws Exception {
        bit.setElement(2, -1);
        assertTrue(bit.checkBit(64));
        assertFalse(bit.checkBit(63));
        printBinary(bit.getElement(2));
    }

    @Test
    void turnOnBit() throws Exception {
        assertTrue(bit.turnOnBit(64));
        assertFalse(bit.turnOnBit(64));
        printBinary(bit.getElement(2));
        assertEquals(1, bit.getElement(2));
        bit.turnOnBit(65);
        printBinary(bit.getElement(2));
        assertEquals(3, bit.getElement(2));
    }

    @Test
    void turnOffBit() throws Exception {
        bit.setElement(2, 3);
        printBinary(bit.getElement(2));
        assertTrue(bit.turnOffBit(65));
        printBinary(bit.getElement(2));
        assertFalse(bit.turnOffBit(65));
        printBinary(bit.getElement(2));
        assertEquals(1, bit.getElement(2));
        bit.turnOffBit(64);
        printBinary(bit.getElement(2));
        assertEquals(0, bit.getElement(2));
    }

    @Test
    void invertBit() throws Exception {
        bit.invertBit(64);
        printBinary(bit.getElement(2));
        assertEquals(1, bit.getElement(2));
        bit.invertBit(65);
        printBinary(bit.getElement(2));
        assertEquals(3, bit.getElement(2));
        bit.invertBit(64);
        printBinary(bit.getElement(2));
        assertEquals(2, bit.getElement(2));
        bit.invertBit(65);
        printBinary(bit.getElement(2));
        assertEquals(0, bit.getElement(2));
    }
}
