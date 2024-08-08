package TypewiseAlertTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import TypewiseAlert.Constants.BreachType;
import TypewiseAlert.Constants.CoolingType;
import TypewiseAlert.Constants.AlertTarget;
import TypewiseAlert.FormatOutput;
import TypewiseAlert.TypewiseAlert;

public class TypewiseAlertTest {
	
	  private FormatOutput mockOutput;
    private TypewiseAlert.BatteryCharacter batteryCharacter;

    @Before
    public void setUpPrinter() throws Exception {
        mockOutput = mock(FormatOutput.class);
        Field outputField = TypewiseAlert.class.getDeclaredField("output");
        outputField.setAccessible(true);
        outputField.set(null, mockOutput);
        batteryCharacter = new TypewiseAlert().new BatteryCharacter();
    }

    @Test
    public void testSendToController() {
        batteryCharacter.coolingType = CoolingType.PASSIVE_COOLING;
        TypewiseAlert.checkAndAlert(AlertTarget.TO_CONTROLLER, batteryCharacter, 50);
        verify(mockOutput).printf("%x : %s\n", 0xfeed, BreachType.TOO_HIGH);
    }

    @Test
    public void testSendToEmailTooLow() {
        batteryCharacter.coolingType = CoolingType.PASSIVE_COOLING;
        TypewiseAlert.checkAndAlert(AlertTarget.TO_EMAIL, batteryCharacter, -1);
        verify(mockOutput).printf("To: %s\n", "a.b@c.com");
        verify(mockOutput).println("Hi, the temperature is too low\n");
    }

    @Test
    public void testSendToEmailTooHigh() {
        batteryCharacter.coolingType = CoolingType.PASSIVE_COOLING;
        TypewiseAlert.checkAndAlert(AlertTarget.TO_EMAIL, batteryCharacter, 50);
        verify(mockOutput).printf("To: %s\n", "a.b@c.com");
        verify(mockOutput).println("Hi, the temperature is too high\n");
    }

    @Test
    public void testNoEmailForNormalTemperature() {
        batteryCharacter.coolingType = CoolingType.PASSIVE_COOLING;
        TypewiseAlert.checkAndAlert(AlertTarget.TO_EMAIL, batteryCharacter, 25);
        verify(mockOutput, never()).printf(anyString(), any());
        verify(mockOutput, never()).println(anyString());
    }

}
