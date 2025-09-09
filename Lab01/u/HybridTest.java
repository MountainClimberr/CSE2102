package u;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import c.hybrid;

public class HybridTest {
    private hybrid hybrid;

    @Before
    public void setUp() {
        hybrid = new hybrid(50, 30);
    }

    @Test
    public void testConstructorSetsInitialValues() {
        assertEquals(50, hybrid.getBatteryLevel());
        assertEquals(30, hybrid.getFuelLevel());
    }

    @Test
    public void testChargeBatterySetsBatteryTo100AndPrintsMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        hybrid.chargeBattery();
        assertEquals(100, hybrid.getBatteryLevel());
        assertTrue(outContent.toString().trim().contains("Battery fully charged."));
        System.setOut(System.out);
    }

    @Test
    public void testGetBatteryLevelReturnsCorrectValue() {
        assertEquals(50, hybrid.getBatteryLevel());
        hybrid.chargeBattery();
        assertEquals(100, hybrid.getBatteryLevel());
    }

    @Test
    public void testRefuelSetsFuelTo100AndPrintsMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        hybrid.refuel();
        assertEquals(100, hybrid.getFuelLevel());
        assertTrue(outContent.toString().trim().contains("Fuel tank full."));
        System.setOut(System.out);
    }

    @Test
    public void testGetFuelLevelReturnsCorrectValue() {
        assertEquals(30, hybrid.getFuelLevel());
        hybrid.refuel();
        assertEquals(100, hybrid.getFuelLevel()); 
    }

    @Test
    public void testCalcGasMPG() {
        hybrid.setMilesFromGas(120);
        hybrid.setGallonsFromGas(6);
        assertEquals(20.0, hybrid.calcGasMPG(), 0.01);
    }

    @Test
    public void testCalcMPGe() {
        hybrid.setElectricMiles(300);
        hybrid.setTotalKWH(70);
        assertEquals(144.43, hybrid.calcMPGe(), 0.1);
    }

    @Test
    public void testSetMilesFromGas() {
        hybrid.setMilesFromGas(150);
        hybrid.setGallonsFromGas(5);
        assertEquals(30.0, hybrid.calcGasMPG(), 0.01);
    }

    @Test
    public void testSetElectricMiles() {
        hybrid.setElectricMiles(200);
        hybrid.setTotalKWH(50);
        assertEquals(134.8, hybrid.calcMPGe(), 0.1);
    }
}