import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RoofTopSolarEnergy {

    public static void main(String[] args) {

        List<Double> panelWattages = new ArrayList<>();

        panelWattages.add(400.0);
        panelWattages.add(450.0);
        panelWattages.add(350.0);
        panelWattages.add(500.0);
        panelWattages.add(450.0);

        System.out.println("Roof Top Solar Energy System");
        System.out.println("--------------------------------");
        System.out.println("Panel Wattages: " + panelWattages);
        double totalWattage = panelWattages.stream()
                .mapToDouble(w -> w)
                .sum();

        System.out.println("Total Panel Wattage: " + totalWattage + " W");
        Predicate<Double> highPowerPanel = wattage -> wattage >= 450;
        List<Double> highPowerPanels = panelWattages.stream()
                .filter(highPowerPanel)
                .collect(Collectors.toList());

        System.out.println("High Power Panels (>= 450 W): " + highPowerPanels);
        List<Double> sortedPanels = panelWattages.stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Sorted Panel Wattages: " + sortedPanels);

        double averageWattage = panelWattages.stream()
                .mapToDouble(w -> w)
                .average()
                .orElse(0);

        System.out.println("Average Panel Wattage: "
                + averageWattage + " W");
        double sunlightHours = 5.0;

        double dailyEnergy = panelWattages.stream()
                .mapToDouble(w -> w)
                .sum() * sunlightHours / 1000;

        System.out.println("Sunlight Hours: " + sunlightHours);
        System.out.println("Estimated Daily Energy: "
                + dailyEnergy + " kWh");

        long panelCount = panelWattages.stream().count();

        System.out.println("Number of Solar Panels: " + panelCount);
    }
}
