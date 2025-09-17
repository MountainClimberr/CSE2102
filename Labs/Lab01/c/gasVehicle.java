package c;

public interface gasVehicle {
  void refuel();
  int getFuelLevel();
  double calcGasMPG();
  void setMilesFromGas(double miles);
  void setGallonsFromGas(double gallons);
}