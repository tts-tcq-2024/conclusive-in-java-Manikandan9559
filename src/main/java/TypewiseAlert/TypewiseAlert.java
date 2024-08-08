package TypewiseAlert;

public class TypewiseAlert 
{
    private static FormatOutput output = new PrintOutput();

	public class BatteryCharacter {
		public CoolingType coolingType;
	}
	
	public static void checkAndAlert(AlertTarget alertTarget, BatteryCharacter batteryChar, double temperatureInC) {
		BreachType breachType = BreachManager.classifyTemperatureBreach(batteryChar.coolingType, temperatureInC);
		if(alertTarget.equals(AlertTarget.TO_CONTROLLER)) {
			sendToController(breachType);
		} else if(alertTarget.equals(AlertTarget.TO_EMAIL)) {
			sendToEmail(breachType);
		}
	}
	
	public static void sendToController(BreachType breachType) {
		int header = 0xfeed;
		output.printf("%x : %s\n", header, breachType);
	}
	
	public static void sendToEmail(BreachType breachType) {
		String recepient = "a.b@c.com";
		if(breachType.equals(BreachType.TOO_LOW)) {
			output.printf("To: %s\n", recepient);
			output.println("Hi, the temperature is too low\n");
		} else if(breachType.equals(BreachType.TOO_HIGH)) {
			output.printf("To: %s\n", recepient);
			output.println("Hi, the temperature is too high\n");
		}
	}
	
	public static void main(String args[]) {
		TypewiseAlert alert = new TypewiseAlert();
		BatteryCharacter battery = alert.new BatteryCharacter();
		battery.coolingType = CoolingType.HI_ACTIVE_COOLING;
		checkAndAlert(AlertTarget.TO_EMAIL, battery, 50);
	}
}
