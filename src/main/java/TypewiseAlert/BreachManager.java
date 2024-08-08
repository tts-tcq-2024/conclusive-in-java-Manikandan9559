package TypewiseAlert;

import TypewiseAlert.Constants.BreachType;
import TypewiseAlert.Constants.CoolingType;

public class BreachManager {

	private static BreachType inferBreach(double value, double lowerLimit, double upperLimit) {
		if(value < lowerLimit) {
			return BreachType.TOO_LOW;
		}
		if(value > upperLimit) {
			return BreachType.TOO_HIGH;
		}
		return BreachType.NORMAL;
	}

	public static BreachType classifyTemperatureBreach(CoolingType coolingType, double temperatureInC) {
		int lowerLimit = 0;
		int upperLimit = 0;
		if(coolingType.equals(CoolingType.PASSIVE_COOLING)) {
			lowerLimit = 0;
			upperLimit = 35;
		} else if(coolingType.equals(CoolingType.HI_ACTIVE_COOLING)) {
			lowerLimit = 0;
			upperLimit = 45;
		} else {
			lowerLimit = 0;
			upperLimit = 40;
		}
		return inferBreach(temperatureInC, lowerLimit, upperLimit);
	}

}
