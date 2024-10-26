import java.util.Scanner;
import java.util.Random;

public class NumberGame {
    public static void main(String[] args) {
        // Generating Random Number
        Random rd = new Random();
        int random = rd.nextInt(100) + 1; // Range from 1 to 100

        Scanner sc = new Scanner(System.in);
        int input, options, count;
        int score = 100;

        System.out.println("███╗   ██╗██╗   ██╗███╗   ███╗██████╗ ███████╗██████╗      ██████╗  █████╗ ███╗   ███╗███████╗");
        System.out.println("████╗  ██║██║   ██║████╗ ████║██╔══██╗██╔════╝██╔══██╗    ██╔════╝ ██╔══██╗████╗ ████║██╔════╝");
        System.out.println("██╔██╗ ██║██║   ██║██╔████╔██║██║████║█████╗  ██████╔╝    ██║  ███╗███████║██╔████╔██║█████╗  ");
        System.out.println("██║╚██╗██║██║   ██║██║╚██╔╝██║██║  ██║██╔══╝  ██╔══██╗    ██║   ██║██╔══██║██║╚██╔╝██║██╔══╝  ");
        System.out.println("██║ ╚████║╚██████╔╝██║ ╚═╝ ██║██████╔╝███████╗██║  ██║    ╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗");
        System.out.println("╚═╝  ╚═══╝ ╚═════╝ ╚═╝     ╚═╝╚═════╝ ╚══════╝╚═╝  ╚═╝     ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝");
        System.out.println("****************************************");
        System.out.println("*                                      *");
        System.out.println("*      WELCOME TO THE NUMBER GAME      *");
        System.out.println("*                                      *");
        System.out.println("****************************************");

        mainLoop: while (true) {  // Main loop for menu
            System.out.println("\nSelect Options:");
            System.out.println("1) START GAME");
            System.out.println("2) INSTRUCTIONS");
            System.out.println("3) EXIT");

            options = sc.nextInt();

            switch (options) {
                case 1:
                    count = 0;
                    System.out.println("Guess the number between 1 and 100!");
                    do {
                        System.out.print("Enter your guess: ");
                        input = sc.nextInt();
                        if (input > random) {
                            System.out.println("Too High...");
                            score -= 5;
                        } else if (input < random) {
                            System.out.println("Too Low...");
                            score -= 5;
                        } else {
                            System.out.println("Correct!! You've guessed it right.");
                            System.out.println("Your Score is: " + score);
                        }
                        count++;
                    } while (input != random);
                    System.out.println("Congratulations! You guessed the number in " + count + " attempts.");
                    break;

                case 2:
                    System.out.println("Instructions:");
                    System.out.println("1. The game will generate a random number between 1 and 100.");
                    System.out.println("2. Your goal is to guess the correct number.");
                    System.out.println("3. After each guess, you will receive feedback:");
                    System.out.println("   - If your guess is too high, you'll be asked to try a lower number.");
                    System.out.println("   - If your guess is too low, you'll be asked to try a higher number.");
                    System.out.println("4. The game continues until you guess the correct number.");
                    System.out.println("5. The total number of attempts will be shown at the end.");
                    break;

                case 3:
                    System.out.println("Exiting...");
                    break mainLoop;

                default:
                    System.out.println("Select Options between 1, 2, or 3 only!!");
            }
        }
        sc.close();
    }
}
