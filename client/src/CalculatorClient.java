import calculator.CalculatorService;

public class CalculatorClient {
	public static void main (String[] argv) {
		calculator.Calculator service = new CalculatorService().getCalculatorPort();

		System.out.printf("2 + 3 = %f\n", service.add(2, 3));
		System.out.printf("2 - 3 = %f\n", service.sub(2, 3));
		System.out.printf("2 * 3 = %f\n", service.mul(2, 3));
		System.out.printf("2 / 3 = %f\n", service.div(2, 3));
	}
}
