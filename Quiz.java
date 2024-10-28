import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Timer;

class Question {
    String question;
    String[] options;
    int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class Quiz {
    private ArrayList<Question> questions;
    private int score;
    private int timeLimit = 10; // 10 seconds for each question
    private Timer timer;
    private boolean isTimeUp;

    public Quiz() {
        questions = new ArrayList<>();
        score = 0;
        initializeQuestions();
    }

    // Initialize questions, options, and correct answers
    private void initializeQuestions() {
        questions.add(new Question("What is the capital of France?", new String[]{"1. Berlin", "2. Paris", "3. Madrid", "4. Rome"}, 2));
        questions.add(new Question("Who developed Java?", new String[]{"1. James Gosling", "2. Guido van Rossum", "3. Bjarne Stroustrup", "4. Dennis Ritchie"}, 1));
        questions.add(new Question("What is 2 + 2?", new String[]{"1. 3", "2. 4", "3. 5", "4. 6"}, 2));
        // Add more questions as needed
    }

    // Start the quiz
    public void start() {
        Scanner scanner = new Scanner(System.in);
        printWelcomeMessage();

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            isTimeUp = false;

            System.out.println("\nQuestion " + (i + 1) + ": " + question.question);
            for (String option : question.options) {
                System.out.println(option);
            }

            // Start timer
            startTimer();

            int answer = -1;
            while (!isTimeUp) {
                try {
                    if (scanner.hasNextInt()) {
                        answer = scanner.nextInt();
                        break;
                    } else {
                        System.out.println("Please enter a valid option number.");
                        scanner.next(); // Clear invalid input
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.next(); // Clear invalid input
                }
            }

            timer.cancel(); // Stop timer after submission or timeout

            if (!isTimeUp && answer == question.correctAnswer) {
                System.out.println("Correct!");
                score++;
            } else if (isTimeUp) {
                System.out.println("Time's up! Moving to the next question.");
            } else {
                System.out.println("Incorrect.");
            }
        }

        // Display final score
        displayResult();
    }

    // Timer method
    private void startTimer() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            int timeLeft = timeLimit;

            @Override
            public void run() {
                if (timeLeft > 0) {
                    System.out.println("Time left: " + timeLeft + " seconds");
                    timeLeft--;
                } else {
                    isTimeUp = true;
                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    // Display final result
    private void displayResult() {
        System.out.println("\nQuiz Completed!");
        System.out.println("Your Score: " + score + "/" + questions.size());
    }

    // Print welcome message with ASCII art
    private void printWelcomeMessage() {
        System.out.println("=====================================");
        System.out.println("          WELCOME TO THE QUIZ       ");
        System.out.println("=====================================");
        System.out.println("   Test your knowledge and have fun! ");
        System.out.println("=====================================");
    }

    // Display main menu
    private void showMenu() {
        System.out.println("1. Start Quiz");
        System.out.println("2. Quit");
        System.out.print("Choose an option: ");
    }

    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            quiz.showMenu();
            int choice = -1;

            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        quiz.start();
                        break;
                    case 2:
                        System.out.println("Thank you for playing! Goodbye!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }
        scanner.close(); // Close scanner when quitting
    }
}
