import java.util.*;
import java.lang.Math;

public class StatTool {
	
	// calculate the sample mean of a given data set
	public static double sampleMean(ArrayList<Double> c) {
		int size = c.size();
		double total = 0;
		for(double i : c) {
			total += i;
		}
		double mean = total / size;
		return mean;
	}
	
	// calculate the sample variance of a given data set
	public static double sampleVariance(ArrayList<Double> c) {
		double size = c.size();
		double mean = sampleMean(c);
		double total = 0;
		double deviation;
		for(double i : c) {
			deviation = i - mean;
			total += Math.pow(deviation, 2);
		}
		double variance = (1 / (size - 1)) * total;
		return variance;
	}
	
	// calculate the standard deviation of a given data set
	public static double sampleStdDev(ArrayList<Double> c) {
		return Math.sqrt(sampleVariance(c));
	}
	
	// calculate the sum of least squares of two sets of data
	public static double leastSquares(ArrayList<Double> dataX, ArrayList<Double> dataY) {
		double deviationX;
		double deviationY;
		double meanX = sampleMean(dataX);
		double meanY = sampleMean(dataY);
		double total = 0;
		for(int i=0; i<dataX.size(); i++) {
			deviationX = dataX.get(i) - meanX;
			deviationY = dataY.get(i) - meanY;
			total += deviationX * deviationY;
		}
		return total;
	}
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		String line; // buffer for input
		boolean y = false; // do we have a data set Y?
		
		ArrayList<Double> dataX = new ArrayList<Double>(); // data set X
		ArrayList<Double> dataY = new ArrayList<Double>(); // data set Y
		
		System.out.println();
		System.out.println("Enter data for set X (ENTER to finish):");
		while(true) {
			line = in.nextLine();
			if(line.equals("")) {
				break;
			}
			dataX.add(Double.parseDouble(line));
		}
		
		System.out.println("Enter data for set Y (ENTER to skip or finish):");
		while(true) {
			line = in.nextLine();
			if(line.equals("")) {
				break;
			}
			dataY.add(Double.parseDouble(line));
		}
		if(!dataY.isEmpty()) {
			y = true;
		}
		
		if(y) {
			if(dataX.size() != dataY.size()) {
				System.err.println("Fatal error: data sets X and Y are not of equal size.");
				System.exit(1);
			}
		}
		
		System.out.println("Sample mean of data set X: " + sampleMean(dataX));
		System.out.println("Sample variance of data set X: " + sampleVariance(dataX));
		System.out.println("Sample standard deviation of data set X: " + sampleStdDev(dataX));
		System.out.println();
		if(y) {
			System.out.println("Sample mean of data set Y: " + sampleMean(dataY));
			System.out.println("Sample variance of data set Y: " + sampleVariance(dataY));
			System.out.println("Sample standard deviation of data set Y: " + sampleStdDev(dataY));
			System.out.println();
			System.out.println("Sum of least squares of X and Y:" + leastSquares(dataX, dataY));
		}
		System.out.println();
	}
}