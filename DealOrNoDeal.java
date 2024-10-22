import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;
import java.util.Arrays;



public class DealOrNoDeal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] cases = new int[26];
        List<Integer> remainingCases = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 26; i++) {
            cases[i] = (i + 1) * 1000;
            remainingCases.add(i + 1);
        }
        Collections.shuffle(Arrays.asList(cases));

        System.out.println("Welcome to Deal or No Deal!");
        System.out.println("Please choose your case (1 to 26):");
        int playerCase = scanner.nextInt();
        System.out.println("You have chosen case #" + playerCase + ". Keep it safe!");

        remainingCases.remove((Integer) playerCase);

        int bankerOffer = 0;
        while (remainingCases.size() > 1) {
            System.out.println("Remaining cases: " + remainingCases);
            System.out.println("Select a case to open (choose from remaining cases):");
            int chosenCase = scanner.nextInt();

            if (remainingCases.contains(chosenCase)) {
                int caseValue = cases[chosenCase - 1];
                System.out.println("Case #" + chosenCase + " contains: $" + caseValue);
                remainingCases.remove((Integer) chosenCase);
            } else {
                System.out.println("Invalid choice. Please choose a remaining case.");
                continue;
            }

            if (remainingCases.size() % 5 == 0) {
                bankerOffer = calculateBankerOffer(remainingCases, cases);
                System.out.println("Banker offers you: $" + bankerOffer);
                System.out.println("Deal or No Deal? (type '1' for Deal, '2' for No Deal)");
                int dealChoice = scanner.nextInt();

                if (dealChoice == 1) {
                    System.out.println("You took the deal! You won: $" + bankerOffer);
                    break;
                }
            }
        }

        if (remainingCases.size() == 1) {
            System.out.println("Only one case left! Your case was #" + playerCase);
            System.out.println("Would you like to swap it with the last remaining case? (1 for Yes, 2 for No)");
            int swapChoice = scanner.nextInt();

            if (swapChoice == 1) {
                playerCase = remainingCases.get(0);
            }

            System.out.println("Your final case contains: $" + cases[playerCase - 1]);
        }

        scanner.close();
    }

    public static int calculateBankerOffer(List<Integer> remainingCases, int[] cases) {
        int totalValue = 0;
        for (int caseNum : remainingCases) {
            totalValue += cases[caseNum - 1];
        }
        return totalValue / remainingCases.size();
    }
}

