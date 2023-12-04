import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            try {
                System.out.println(calc(scanner.nextLine()));
            } catch (Exception e) {
                System.out.println("throws Exception");
                break;
            }
        }

        scanner.close();
    }
    public static String calc(String input) throws IOException {
        String [] romanNumerals = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};

        int numberOne = 0;
        int numberTwo = 0;
        int deductionResult = 0;
        boolean flag = false;
        String[] arrayNumbers = input.split("[+\\-*/]");

        for (int i = 0; i < romanNumerals.length; i++) {
            if(arrayNumbers[0].trim().toUpperCase().equals(romanNumerals[i])) {
                numberOne = i + 1;
            }

            if(arrayNumbers[1].trim().toUpperCase().equals(romanNumerals[i])) {
                numberTwo = i + 1;
            }
        }

        if (numberOne == 0 && numberTwo == 0) {
            numberOne = Integer.parseInt(arrayNumbers[0].trim());
            numberTwo = Integer.parseInt(arrayNumbers[1].trim());
            flag = true;
        }

        if (numberOne >= 1 && numberTwo >= 1 && numberOne <= 10 && numberTwo <= 10) {
            if (input.contains("+")) {
                deductionResult = numberOne + numberTwo;
            } else if (input.contains("-")) {
                deductionResult = numberOne - numberTwo;
            } else if (input.contains("*")) {
                deductionResult = numberOne * numberTwo;
            } else if (input.contains("/")) {
                deductionResult = numberOne / numberTwo;
            }
        } else {
            throw new IOException();
        }

        if (flag == false) {
            int backToRome = deductionResult / 10;
            if (backToRome < 1) {
                int i = deductionResult - 1;
                input = romanNumerals[i];
            }

            if (backToRome >= 1) {
                int firstNumber = (deductionResult / 10) + 8;
                int secondNumber = (deductionResult % 10) - 1;

                if (secondNumber <= 0) {
                    input = romanNumerals[firstNumber];
                } else {
                    input = romanNumerals[firstNumber] + romanNumerals[secondNumber];
                }
            }
        } else {
            input = Integer.toString(deductionResult);
        }

        return input;
    }
}