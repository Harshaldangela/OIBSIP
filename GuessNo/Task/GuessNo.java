package Task;

import java.util.Random;
import java.util.Scanner;

public class GuessNo {

    private static int highestScore = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to Guess the Number!");
        System.out.println("---------------------------------------------------------------------------------------------");
        boolean playAgain;
        do {
            System.out.println("\nSelect Difficulty Level:");
            System.out.println("1. Easy (10 attempts)");
            System.out.println("2. Medium (7 attempts)");
            System.out.println("3. Hard (5 attempts)");
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.print("Enter your choice: ");
            int difficulty = scanner.nextInt();
            int maxAttempts = getMaxAttempts(difficulty);

            System.out.print("\nEnter the range (e.g., 1 to 100):\nFrom: ");
            int minRange = scanner.nextInt();
            System.out.print("To: ");
            int maxRange = scanner.nextInt();

            int totalRounds = 3;
            int score = 0;

            for (int round = 1; round <= totalRounds; round++) {
                System.out.println("\nRound " + round);
                int targetNumber = random.nextInt(maxRange - minRange + 1) + minRange;
                int attempts = 0;
                boolean guessed = false;

                while (attempts < maxAttempts) {
                    System.out.print("Enter your guess (" + minRange + " - " + maxRange + "): ");
                    int userGuess = scanner.nextInt();
                    attempts++;

                    if (userGuess == targetNumber) {
                        System.out.println("Congratulations! You've guessed the number.");
                        System.out.println("----------------------------------------------🎉-----------------------------------------------");
                        score += (maxAttempts - attempts + 1) * 10; // Points based on remaining attempts
                        guessed = true;
                        break;
                    } else if (userGuess < targetNumber) {
                        System.out.println("The number is higher than your guess.");
                    } else {
                        System.out.println("The number is lower than your guess.");
                    }

                    if (attempts == 3 && !guessed) {
                        giveHint(targetNumber, minRange, maxRange);
                    }
                }

                if (!guessed) {
                    System.out.println("Sorry, you've used all your attempts. The number was " + targetNumber + ".");
                }

                System.out.println("Score after round " + round + ": " + score);
            }

            if (score > highestScore) {
                highestScore = score;
                System.out.println("New highest score! Your score: " + score);
            } else {
                System.out.println("Your final score is: " + score);
            }

            System.out.println("Highest score to date: " + highestScore);
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("Thanks for playing! Goodbye.");
        System.out.println("---------------------------------------------🎉------------------------------------------------");
        scanner.close();
    }

    private static int getMaxAttempts(int difficulty) {
        switch (difficulty) {
            case 1:
                return 10; // Easy
            case 2:
                return 7;  // Medium
            case 3:
            default:
                return 5;  // Hard
        }
    }

    private static void giveHint(int targetNumber, int minRange, int maxRange) {
        int hintRange = (maxRange - minRange) / 4;
        int lowerBound = Math.max(minRange, targetNumber - hintRange);
        int upperBound = Math.min(maxRange, targetNumber + hintRange);
        System.out.println("Hint: The number is between " + lowerBound + " and " + upperBound + ".");
        System.out.println("------------------------------------------🫰---------------------------------------------------");
    }
}

