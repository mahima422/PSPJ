import java.util.Scanner;

public class RooftopSolarEnergy {

    private double roofArea;
    private double panelEfficiency;
    private int numberOfPanels;
    private double sunlightHours;

    public RooftopSolarEnergy(double roofArea, double panelEfficiency,
                              int numberOfPanels, double sunlightHours) {
        this.roofArea = roofArea;
        this.panelEfficiency = panelEfficiency;
        this.numberOfPanels = numberOfPanels;
        this.sunlightHours = sunlightHours;
    }

    public double getRoofArea() {
        return roofArea;
    }

    public double getPanelEfficiency() {
        return panelEfficiency;
    }

    public int getNumberOfPanels() {
        return numberOfPanels;
    }

    public double getSunlightHours() {
        return sunlightHours;
    }

    public double calculateEnergy() {

        if (roofArea <= 0) {
            throw new IllegalArgumentException(
                    "Roof area must be greater than zero.");
        }

        if (panelEfficiency <= 0 || panelEfficiency > 1) {
            throw new IllegalArgumentException(
                    "Panel efficiency must be between 0 and 1.");
        }

        if (numberOfPanels <= 0) {
            throw new IllegalArgumentException(
                    "Number of panels must be greater than zero.");
        }

        if (sunlightHours <= 0) {
            throw new IllegalArgumentException(
                    "Sunlight hours must be greater than zero.");
        }

        return roofArea * panelEfficiency
                * numberOfPanels * sunlightHours;
    }

    public void displayDetails() {

        System.out.println("==========================================");
        System.out.println("       ROOFTOP SOLAR ENERGY SYSTEM");
        System.out.println("==========================================");

        System.out.println("Roof Area        : "
                + roofArea + " sq.m");

        System.out.println("Panel Efficiency : "
                + (panelEfficiency * 100) + "%");

        System.out.println("Number of Panels : "
                + numberOfPanels);

        System.out.println("Sunlight Hours   : "
                + sunlightHours + " hours/day");

        System.out.println("==========================================");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            double roofArea = 120.0;
            double panelEfficiency = 0.20;
            int numberOfPanels = 12;
            double sunlightHours = 5.5;

            RooftopSolarEnergy solar =
                    new RooftopSolarEnergy(
                            roofArea,
                            panelEfficiency,
                            numberOfPanels,
                            sunlightHours);

            solar.displayDetails();

            double energy = solar.calculateEnergy();

            System.out.println("Estimated Energy Generated : "
                    + energy + " units/day");

            System.out.println("==========================================");
            System.out.println("The rooftop solar system is successfully");
            System.out.println("generating clean and renewable energy.");
            System.out.println("==========================================");

        }
        catch (IllegalArgumentException e) {

            System.out.println("Error: " + e.getMessage());

        }
        catch (Exception e) {

            System.out.println("Unexpected error occurred.");
            System.out.println("Please check the program.");

        }
        finally {

            sc.close();

            
        }
    }
}