package cf.baradist.task2;

/**
 * ��������� ���������� ������ ������ � ���������� ������ �������������� �������.
 */

public interface IBit {

    /**
     * ����� ��������� �������� ���� � ������� n.
     *
     * @param n ���������� ����� ����. ��������� ����� ������ ������ ������,
     *          ������� � 0.
     * @return �������� true, ���� �������� ���� ����� 1, � false, ���� ��������
     * ���� ����� 0.
     * @throws Exception ������������� � ������, ���� ����� ���� �������������
     *                   ��� ������� �� ������� �������.
     */

    boolean checkBit(int n) throws Exception;

    /**
     * ����� ������������� �������� ���� � ������� n � �������� 1.
     *
     * @param n ���������� ����� ����. ��������� ����� ������ ������ ������,
     *          ������� � 0.
     * @return �������� true, ���� �������� ���� ���� �������� �� 1, � false,
     * ���� �������� ���� ��� ���� ����� 1.
     * @throws Exception ������������� � ������, ���� ����� ���� �������������
     *                   ��� ������� �� ������� �������.
     */

    boolean turnOnBit(int n) throws Exception;

    /**
     * ����� ������������� �������� ���� � ������� n � �������� 0.
     *
     * @param n ���������� ����� ����. ��������� ����� ������ ������ ������,
     *          ������� � 0.
     * @return �������� true, ���� �������� ���� ���� �������� �� 0, � false,
     * ���� �������� ���� ��� ���� ����� 0.
     * @throws Exception ������������� � ������, ���� ����� ���� �������������
     *                   ��� ������� �� ������� �������.
     */

    boolean turnOffBit(int n) throws Exception;

    /**
     * ����� ����������� �������� ����, �.�. �������� 1 �������� �� �������� 0,
     * � �������� 0 - �� �������� 1.
     *
     * @param n ���������� ����� ����. ��������� ����� ������ ������ ������,
     *          ������� � 0.
     * @return �������� true, ���� �������� ���� ���� ��������, � false �
     * ��������� ������.
     * @throws Exception ������������� � ������, ���� ����� ���� �������������
     *                   ��� ������� �� ������� �������.
     */

    boolean invertBit(int n) throws Exception;
}