import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        compute();
    }

    public static List<Double> compute() throws FileNotFoundException {

        /* Find the file and create a scanner */
        File file = new File("data");
        Scanner scanner = new Scanner(file);

        List<Double> numbers = inputReading(scanner); // read input
        double mean = meanComputing(numbers); // compute the mean
        double std = standardDev(numbers, mean); // compute the standard deviation
        List<Double> normalized = normalization(numbers, mean, std); // compute the normalization
        return outputSpreading(normalized, "output"); // output the normalization in a file and return the normalization
    }

    private static List<Double> inputReading(Scanner scanner){
        List<Double> numbers = new ArrayList<>();
        while (scanner.hasNextDouble()) {
            double number = scanner.nextDouble();
            numbers.add(number);
        }
        scanner.close();
        return numbers;
    }

    private static double meanComputing(List<Double> numbers){
        double sum = 0;
        for (double f : numbers) {
            sum += f;
        }
        return sum / numbers.size();
    }

    private static double standardDev(List<Double> numbers, double mean){
        double sumSquare = 0;
        for (double f : numbers) {
            double diff = f - mean;
            sumSquare += diff * diff;
        }
        return Math.sqrt(sumSquare / numbers.size());
    }

    private static List<Double> normalization(List<Double> numbers, double mean, double std){
        List<Double> normalized = new ArrayList<>();
        for (double f : numbers) {
            normalized.add((f - mean) / std);
        }
        return normalized;
    }

    private static List<Double> outputSpreading(List<Double> normalized, String fileName){
        System.out.println(normalized);
        try {
            FileWriter fw = new FileWriter(fileName);
            for (double n : normalized) {
                fw.write(Double.toString(n));
                fw.write("\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Error writing output file");
        }
        System.out.println("Wrote output file.");
        return normalized;
    }
}
