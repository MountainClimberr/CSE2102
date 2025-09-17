package c;

public class carRunner {
  public static void main(String[] args){
    hybrid hybrid = new hybrid(80, 75);

    hybrid.setMilesFromGas(120);
    hybrid.setGallonsFromGas(6);

    hybrid.setElectricMiles(300);
    hybrid.setTotalKWH(70);

    double gasMPG = hybrid.calcGasMPG();
    double electricMPGe = hybrid.calcMPGe();
    double averageMPG = (gasMPG + electricMPGe)/ 2;

    System.out.println("Gas MPG: " + gasMPG);
    System.out.println("Electric MPGe: " + electricMPGe);
    System.out.println("Average MPG: " + averageMPG);
  }
}