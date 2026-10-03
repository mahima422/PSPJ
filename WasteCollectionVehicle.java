import java.util.Scanner;

public class WasteCollectionVehicle {

    public static double calculateTotalWaste(double point1Waste, double point2Waste) {
        return point1Waste + point2Waste;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int vehicleNumber = 4218;
        double wasteCollectedKg = 687.0;
        int collectionPoints = 15;
        char vehicleStatus = 'R';

        System.out.println("--- Waste Collection Vehicle Details ---");
        System.out.println("Vehicle Number             = " + vehicleNumber);
        System.out.println("Waste Collected (in kg)    = " + wasteCollectedKg + " kg");
        System.out.println("Number of Collection Points= " + collectionPoints);
        System.out.println("Vehicle Status             = " + vehicleStatus);

        if (wasteCollectedKg >= 100.0) {
            System.out.println("Collection Target Achieved");
        } else {
            System.out.println("More Waste Collection Required");
        }

        System.out.print("Enter waste collected at Collection 34 (kg): ");
        double point1 = scanner.nextDouble();
        System.out.print("Enter waste collected at Collection 12 (kg): ");
        double point2 = scanner.nextDouble();

        double totalWaste = calculateTotalWaste(34, 12);
        System.out.println("Total waste collected: " + totalWaste + " kg");

        scanner.close();
    }
}
