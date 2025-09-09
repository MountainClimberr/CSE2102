package c;

public interface electricVehicle {
  void chargeBattery();
  int getBatteryLevel();
  double calcMPGe();
  void setElectricMiles(double totalElectricMiles);
  void setTotalKWH(double totalKWH);
}