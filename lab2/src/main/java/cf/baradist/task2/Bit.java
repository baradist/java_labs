package cf.baradist.task2;

public class Bit implements IBit {
    private int[] array;

    public Bit() {
        this.array = new int[128];
    }

    private static void assertIndex(int n) {
        if (n < 0 || n >= 128 * 32) {
            throw new IllegalArgumentException("n should be from 0 to 4095");
        }
    }

    /**
     * For test purposes
     */
    void setElement(int i, int value) {
        array[i] = value;
    }

    /**
     * For test purposes
     */
    int getElement(int i) {
        return array[i];
    }

    @Override
    public boolean checkBit(int n) throws Exception {
        assertIndex(n);
        int element = array[n / 32];
        int offset = n % 32;
        return (element & (1 << offset)) != 0;
    }

    @Override
    public boolean turnOnBit(int n) throws Exception {
        boolean result = !checkBit(n);
        int i = n / 32;
        int offset = n % 32;
        array[i] = array[i] | (1 << offset);
        return result;
    }

    @Override
    public boolean turnOffBit(int n) throws Exception {
        boolean result = checkBit(n);
        int i = n / 32;
        int offset = n % 32;
        array[i] = array[i] & ~(1 << offset);
        return result;
    }

    @Override
    public boolean invertBit(int n) throws Exception {
        assertIndex(n);
        int i = n / 32;
        int offset = n % 32;
        array[i] = array[i] ^ (1 << offset);
        return true;
    }
}
