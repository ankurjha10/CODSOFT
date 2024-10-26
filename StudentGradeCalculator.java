import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the Marks of your Subject");
        System.out.print("Enter marks of Subject 1 : ");
        int Subject1 = sc.nextInt();
        System.out.print("Enter marks of Subject 2: ");
        int Subject2 = sc.nextInt();
        System.out.print("Enter marks of Subject 3: ");
        int Subject3 = sc.nextInt();
        System.out.print("Enter marks of Subject 4: ");
        int Subject4 = sc.nextInt();
        System.out.print("Enter marks of Subject 5: ");
        int Subject5 = sc.nextInt();
        System.out.print("Enter marks of Subject 6: ");
        int Subject6 = sc.nextInt();

        int TotalMarks = Subject1+Subject2+Subject3+Subject4+Subject5+Subject6;
        System.out.println("Total Marks: " + TotalMarks);

        int AvgPercentage = TotalMarks/6;
        System.out.println("Average Percentage: " + AvgPercentage + "%");

        char Grades;
        if (AvgPercentage >= 90) {
            Grades = 'A';
        } else if (AvgPercentage >= 80) {
            Grades = 'B';
        } else if (AvgPercentage >= 70) {
            Grades = 'C';
        } else if (AvgPercentage >= 60) {
            Grades = 'D';
        } else {
            Grades = 'F';
        }

        System.out.println("Grades: " + Grades);
    }
}
