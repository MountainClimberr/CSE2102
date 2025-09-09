package c;

public class hybrid implements electricVehicle, gasVehicle {
    private int batteryLevel;
    private int fuelLevel;
    private int milesTraveled;
    private double milesFromGas;
    private double gallonsFromGas;
    private double electricMiles;
    private double totalKWH;

    public hybrid(int batteryLevel, int fuelLevel) {
        this.batteryLevel = batteryLevel;
        this.fuelLevel = fuelLevel;
    }

    public void setMilesTraveled(int miles) {
        this.milesTraveled = miles;
    }

    public int getMilesTraveled() {
        return milesTraveled;
    }

    public int getMilesPerGallon() {
        //return milesTraveled / gallonsUsed();
        if (gallonsFromGas > 0){
          return (int)(milesFromGas / gallonsFromGas);
        }
        return 0;
    }

    @Override
    public void chargeBattery() {
        batteryLevel = 100;
        System.out.println("Battery fully charged.");
    }

    @Override
    public int getBatteryLevel() {
        return batteryLevel;
    }

    @Override
    public void refuel() {
        fuelLevel = 100;
        System.out.println("Fuel tank full.");
    }

    @Override
    public int getFuelLevel() {
        return fuelLevel;
    }

    @Override
    public double calcGasMPG(){
      if (gallonsFromGas > 0){
        return milesFromGas / gallonsFromGas;
      }
      return 0;
    }

    @Override
    public void setMilesFromGas(double miles){
      this.milesFromGas = miles;
    }

    @Override
    public void setGallonsFromGas(double gallons){
      this.gallonsFromGas = gallons;
    }

    @Override
    public double calcMPGe(){
      if (totalKWH > 0){
        return (electricMiles / totalKWH) * 33.7;
      }
      return 0;
    }

    @Override
    public void setElectricMiles(double totalElectricMiles){
      this.electricMiles = totalElectricMiles;
    }

    @Override
    public void setTotalKWH(double totalKWH){
      this.totalKWH = totalKWH;
    }
}