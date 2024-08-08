package TypewiseAlert;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import TypewiseAlert.Constants.BreachType;
import TypewiseAlert.Constants.CoolingType;
import TypewiseAlert.BreachManager;


public class BreachManagerTest {

	@Test
	public void testClassifyTemperatureBreachPassiveCooling() {
		assertEquals(BreachType.TOO_LOW, BreachManager.classifyTemperatureBreach(CoolingType.PASSIVE_COOLING, -1));
		assertEquals(BreachType.NORMAL, BreachManager.classifyTemperatureBreach(CoolingType.PASSIVE_COOLING, 20));
		assertEquals(BreachType.TOO_HIGH, BreachManager.classifyTemperatureBreach(CoolingType.PASSIVE_COOLING, 36));
	}

	@Test
	public void testClassifyTemperatureBreachHiActiveCooling() {
		assertEquals(BreachType.TOO_LOW, BreachManager.classifyTemperatureBreach(CoolingType.HI_ACTIVE_COOLING, -1));
		assertEquals(BreachType.NORMAL, BreachManager.classifyTemperatureBreach(CoolingType.HI_ACTIVE_COOLING, 30));
		assertEquals(BreachType.TOO_HIGH, BreachManager.classifyTemperatureBreach(CoolingType.HI_ACTIVE_COOLING, 46));
	}

	@Test
	public void testClassifyTemperatureBreachMedActiveCooling() {
		assertEquals(BreachType.TOO_LOW, BreachManager.classifyTemperatureBreach(CoolingType.MED_ACTIVE_COOLING, -1));
		assertEquals(BreachType.NORMAL, BreachManager.classifyTemperatureBreach(CoolingType.MED_ACTIVE_COOLING, 30));
		assertEquals(BreachType.TOO_HIGH, BreachManager.classifyTemperatureBreach(CoolingType.MED_ACTIVE_COOLING, 41));
	}

}
