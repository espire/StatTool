import java.util.*;
import java.lang.Math;

public class StatTool {
	
	// calculate the sample mean of a given data set
	public static double sampleMean(ArrayList<Double> c) {
		int size = c.size();
		double total = 0;
		for(double i : c) total += i;
		double mean = total / size;
		return mean;
	}
	
	// calculate the sample variance with one degree of freedom lost
	public static double sampleVariance(ArrayList<Double> c) {
		return variance(c, 1);
	}
	
	// calculate the variance of a given data set for a given number of degrees of freedom lost
	public static double variance(ArrayList<Double> c, int degreesLost) {
		double size = c.size();
		double mean = sampleMean(c);
		double total = 0;
		double deviation;
		for(double i : c) {
			deviation = i - mean;
			total += Math.pow(deviation, 2);
		}
		double variance = (1 / (size - degreesLost)) * total;
		return variance;
	}
	
	// calculate the sample standard deviation of a given data set for a given number of degrees of freedom lost
	public static double stdDev(ArrayList<Double> c, int degreesLost) {
		return Math.sqrt(variance(c,degreesLost));
	}
	
	// calculate the standard deviation of a given data set
	public static double sampleStdDev(ArrayList<Double> c) {
		return stdDev(c, 1);
	}
	
	// calculate the sum of least squares of two sets of data
	public static double leastSquares(ArrayList<Double> dataX, ArrayList<Double> dataY) {
		double deviationX;
		double deviationY;
		double meanX = sampleMean(dataX);
		double meanY = sampleMean(dataY);
		double total = 0;
		double size = dataX.size();
		double nxy = meanX * meanY * size; // n*Xbar*Ybar
		
		for(int i=0; i<dataX.size(); i++) {
			total += dataX.get(i)*dataY.get(i);
		}
		total -= nxy;
		
		return total;
	}
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		String line; // buffer for input
		boolean y = false; // do we have a data set Y?
		
		ArrayList<Double> dataX = new ArrayList<Double>(); // data set X
		ArrayList<Double> dataY = new ArrayList<Double>(); // data set Y
		
		System.err.println();
		System.err.println("Enter data for set X (ENTER to finish):");
		while(true) {
			line = in.nextLine();
			if(line.equals("")) break; // end of input
			dataX.add(Double.parseDouble(line));
		}
		
		System.err.println("Enter data for set Y (ENTER to skip or finish):");
		while(true) {
			line = in.nextLine();
			if(line.equals("")) break; // end of input
			dataY.add(Double.parseDouble(line));
		}
		if(!dataY.isEmpty()) y = true;
		
		if(y && (dataX.size() != dataY.size())) {
			System.err.println("Fatal error: data sets X and Y are not of equal size.");
			System.exit(1);
		}
		
		System.err.println();
		System.out.println("X:");
		System.out.println("Sample mean of data set X: " + sampleMean(dataX));
		System.out.println("Sample variance of data set X: " + sampleVariance(dataX));
		System.out.println("Sample standard deviation of data set X: " + sampleStdDev(dataX));
		System.out.println("Sum of least squares of X (Sxx): " + leastSquares(dataX, dataX));
		System.out.println();
		if(y) {
			// these statistics are only applicable if we have two sets of data
			System.out.println("Y:");
			System.out.println("Sample mean of data set Y: " + sampleMean(dataY));
			System.out.println("Sample variance of data set Y: " + sampleVariance(dataY));
			System.out.println("Sample standard deviation of data set Y: " + sampleStdDev(dataY));
			System.out.println("Sample standard deviation, -2 degrees of freedom: (totally wrong, man) " + stdDev(dataY,2));
			System.out.println("Sum of least squares of Y and Y (Syy): " + leastSquares(dataY, dataY));
			System.out.println();
			System.out.println("X and Y:");
			System.out.println("Sum of least squares of X and Y (Sxy): " + leastSquares(dataX, dataY));
			System.out.println("Most likely linear coefficient (beta-hat): " + leastSquares(dataX,dataY)/leastSquares(dataX,dataX));
			System.out.println("Most likely constant (alpha-hat): " + (sampleMean(dataY) - (leastSquares(dataX,dataY)/leastSquares(dataX,dataX))*sampleMean(dataX)));
		}
		System.out.println();
	}
}