package Vista;
import java.util.InputMismatchException;
import java.util.Scanner;

public class View {
    private Scanner scanner;

    public View() {
        scanner = new Scanner(System.in);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public String getInput(String prompt) {
        System.out.print(prompt + " ");
        return scanner.nextLine();
    }
    public int leerEntero(String prompt) {
        boolean leer;
        int n = 0;
        do {
            leer = false;
            try {
                System.out.print(prompt);
                n = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
            } catch (InputMismatchException e) {
                System.out.println("Valor no valido");
                leer = true;
                scanner.nextLine();
            }
        } while (leer);



        return n;
    }
    public float leerFloat(String prompt) {
        boolean leer;
        float n = 0;
        do {
            leer = false;
            try {
                System.out.print(prompt);
                n = scanner.nextFloat();
                scanner.nextLine(); // Consume the newline character
            } catch (InputMismatchException e) {
                System.out.println("Valor no valido");
                leer = true;
                scanner.nextLine();
            }
        } while (leer);



        return n;
    }


    public static void  cleanConsole(){
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            System.out.println("Error clearing the screen: " + e.getMessage());
        }
    }
}