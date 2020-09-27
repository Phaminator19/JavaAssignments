
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    private static double getUserWeight() {
        Scanner UserWeight = new Scanner(System.in);
        System.out.println("Please enter your weight in pounds: "); //read the weight double input
        double weight = UserWeight.nextDouble();
        boolean badInput = true;
        while (badInput) {
            if (weight < 0) {
                System.out.println("Your value isn't acceptable. Please input a positive number.");
                badInput = false;
            }
            else {
                return weight;
            }
        }
        return weight;
    }

    private static double getUserHeight() {
        Scanner UserHeight = new Scanner(System.in);
        System.out.println("Please enter your height in inches: "); //read the height double input
        double height = UserHeight.nextDouble();
        boolean badInput = true;
        while (badInput) {
            if (height < 0) {
                System.out.println("Your value isn't acceptable. Please input a positive number.");
                badInput = false;
            }
            else {
                return height;
            }
        }
        return height;
    }

    private static void displayBmiInfo(BodyMassIndex bmiScore) {
        System.out.printf("Your BMi Score is: %f%n", bmiScore.GetBMIScore());
        System.out.printf("%s%n", bmiScore.GetBMICategory());
        System.out.printf("%n");
        System.out.println("Underweight < 18.5");
        System.out.println("Normal Weight = 18.5-24.9");
        System.out.println("Overweight = 25-29.9");
        System.out.println("Obesity >= 30\n");
    }

    public static void displayBmiStatistics(ArrayList<BodyMassIndex> BMiArray) {
        int j = BMiArray.size();
        double total = 0;
        for (int i = 0; i < j; i++) {
            //this next line will do loop to get each element inside the array list and
            // pull out the height and the weight that we need.
            double w = BodyMassIndex.BMIScore(BMiArray.get(i).GetBMIHeight(),BMiArray.get(i).GetBMIWeight());
            total += w;
        }
        double result = Math.round(total/j);
        System.out.println("The Average of BMi data are: " + result);
    }


    private static boolean moreInput() {
        Scanner in = new Scanner (System.in);
        System.out.println("Let's do a Body Mass Index Test! Type Y to continue or Type N to stop");
        String answer = in.nextLine();
        if (answer.equals("Y")) {

            return true;
        }
        else if (answer.equals("y")) {
            return true;
        }
        return answer.equals("yes");
    }

    public static void main(String[] args) {
        ArrayList<BodyMassIndex> bmiData = new ArrayList<BodyMassIndex>();

        while (moreInput()) {
            double height = getUserHeight();
            double weight = getUserWeight();

            BodyMassIndex bmi = new BodyMassIndex(height, weight);
            bmiData.add(bmi);

            displayBmiInfo(bmi);
        }

        displayBmiStatistics(bmiData);
    }
}
