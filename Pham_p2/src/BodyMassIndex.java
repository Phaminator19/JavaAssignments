import java.lang.Math;

public class BodyMassIndex {
    private final double height;
    private  final double weight;

    public BodyMassIndex(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }
    public static double BMIScore(double inches, double pounds) {
        double newInches = Math.pow(inches,2);
        return (703 * pounds) / newInches;
    }

    public static String BMICategory(double inches, double pound) {
        String s = "";
        if (BMIScore(inches, pound) < 18.5) {
             s = "You are Underweight. Keep going.";
        } else if (BMIScore(inches, pound) >= 18.5 && BMIScore(inches, pound) <= 24.9) {
             s = "You are Normal Weight. Nice!";
        } else if (BMIScore(inches, pound)>= 25 && BMIScore(inches, pound) <= 29.9) {
            s = "You are overweight. Do an exercise!";
        } else if (BMIScore(inches, pound) >= 30) {
            s = "You are Obese! Stop eating and do exercise now.";
        }
        return s;
    }
    public double GetBMIHeight() {
        return height;
    }
    public double GetBMIWeight() {
        return weight;
    }
    public double GetBMIScore() {
        return BMIScore(height, weight);
    }
    public String GetBMICategory() {
        return BMICategory(height, weight);
    }
}

