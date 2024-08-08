package TypewiseAlert;

public class PrintOutput implements FormatOutput{

	@Override
	public void printf(String input, Object... args) {
		System.out.printf(input, args);
	}

	@Override
	public void println(String input) {
		System.out.println(input);
	}

}
