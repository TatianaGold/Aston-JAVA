import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Methods.printThreeWords();
        Methods.checkSumSign(2, 7);
        Methods.printColor();
        Methods.compareNumbers(55, 190);
        System.out.println(Methods.checkSum(5, 14));
        Methods.valueNumberVoid(-3);
        System.out.println(Methods.valueNumberNotVoid(0));
        Methods.printString("Привет!", 3);
        System.out.println(Methods.leapYear(2024));
        System.out.println(Arrays.toString(Methods.reverseArray()));
        System.out.println(Arrays.toString(Methods.emptyArray()));
        System.out.println(Arrays.toString(Methods.changeArray()));
        Methods.diagonalArray(4, 4);
        Methods.arrayLength(4, 2);
    }
}
