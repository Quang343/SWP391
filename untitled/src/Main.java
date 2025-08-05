import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] strings = null;
        String LONGEST_STRING = null;
        int stringCount;

        System.out.print("Enter the number of strings: ");
        stringCount = scanner.nextInt();
        scanner.nextLine();

        strings = new String[stringCount];

        for (int i = 0; i < stringCount; i++) {
            System.out.print("Enter string #" + (i + 1) + ":");
            strings[i] = scanner.nextLine();
        }
        for (int i = 0; i <= stringCount; i++) {
            if (strings[i].length() > LONGEST_STRING.length()) {
                LONGEST_STRING = strings[i];
            }
        }
        System.out.println("The longest string is: " + LONGEST_STRING);

    }
}
