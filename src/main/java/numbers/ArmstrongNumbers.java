package numbers;

public class ArmstrongNumbers {


    public boolean isArmstrongNumber(int number) {
        String[] numbers = numberToString(number).split("");
        double sum = 0.0;
        for (String actual : numbers){
           sum += getPower(stringToNumber(actual),numbers.length);
        }
        return sum == (double) number;
    }


    private double getPower(double number, double power){
        return  Math.pow((double) number,(double) power);
    }

    private String numberToString(int number){
        return Integer.toString(number);
    }

    private double stringToNumber(String numberString){
        return Double.parseDouble(numberString);
    }
}
