import calculator.CalculatorService;

public class CalculatorClient {
	public static void main (String[] argv) {
		calculator.Calculator service = new CalculatorService().getCalculatorPort();
		System.out.printf("2 + 3 = %d", service.add(2, 3));
	}
}
