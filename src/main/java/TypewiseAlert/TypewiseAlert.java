package TypewiseAlert;

import TypewiseAlert.Constants.AlertTarget;
import TypewiseAlert.Constants.BreachType;
import TypewiseAlert.Constants.CoolingType;

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
	
	private static void sendToController(BreachType breachType) {
		int header = 0xfeed;
		output.printf("%x : %s\n", header, breachType);
	}
	
	private static void sendToEmail(BreachType breachType) {
		String recepient = "a.b@c.com";
		if(breachType.equals(BreachType.TOO_LOW)) {
			output.printf("To: %s\n", recepient);
			output.println("Hi, the temperature is too low\n");
		} else if(breachType.equals(BreachType.TOO_HIGH)) {
			output.printf("To: %s\n", recepient);
			output.println("Hi, the temperature is too high\n");
		}
	}
}
