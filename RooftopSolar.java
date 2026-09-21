public class RooftopSolarCalculator {

    public static void main(String[] args) {

        String panelBrand = "SunMax 450W";
        int panelWattage = 450;
        int numPanels = 12;
        double efficiency = 0.85;
        double sunlightHours = 5.5;
        boolean isGridConnected = true;

        String city = "Hyderabad";
        double latitude = 17.385;
        double longitude = 78.4867;

        int[] monthlyBillUnits = {320, 300, 340, 310, 360, 330};
        double[] tariffRates = {3.50, 5.20, 7.10};

        double totalInstalledCapacityKW = calculateCapacityKW(panelWattage, numPanels);
        double dailyEnergyKWh = calculateDailyEnergy(totalInstalledCapacityKW, sunlightHours, efficiency);
        double monthlyEnergyKWh = dailyEnergyKWh * 30;
        double annualEnergyKWh = dailyEnergyKWh * 365;

        double avgMonthlyConsumption = calculateAverage(monthlyBillUnits);

        boolean meetsDemand = monthlyEnergyKWh >= avgMonthlyConsumption;
        double surplusUnits = meetsDemand ? (monthlyEnergyKWh - avgMonthlyConsumption) : 0;
        double deficitUnits = !meetsDemand ? (avgMonthlyConsumption - monthlyEnergyKWh) : 0;

        boolean eligibleForNetMetering = isGridConnected && meetsDemand;
        boolean needsBatteryBackup = (!isGridConnected) || (!meetsDemand);

        double applicableRate = getApplicableRate(tariffRates, avgMonthlyConsumption);

        double savingsPerMonth = calculateSavings(surplusUnits, applicableRate);

        double systemCost = calculateSystemCost(numPanels);

        double paybackYears = calculatePayback(systemCost, savingsPerMonth);

        String performanceStatus = meetsDemand ? "Surplus" : "Deficit";

        printReport(city, panelBrand, numPanels, totalInstalledCapacityKW, dailyEnergyKWh,
                monthlyEnergyKWh, annualEnergyKWh, avgMonthlyConsumption, performanceStatus,
                surplusUnits, deficitUnits, isGridConnected, eligibleForNetMetering,
                needsBatteryBackup, savingsPerMonth, systemCost, paybackYears,
                applicableRate, latitude, longitude);
    }

    static double calculateCapacityKW(int wattage, int panels) {
        double totalW = wattage * panels;
        return totalW / 1000;
    }

    static double calculateDailyEnergy(double capacityKW, double sunHours, double eff) {
        return (capacityKW * sunHours) * eff;
    }

    static double calculateAverage(int[] units) {
        double sum = 0;
        for (int u : units) {
            sum += u;
        }
        return sum / units.length;
    }

    static double getApplicableRate(double[] rates, double avgUnits) {
        int index = avgUnits <= 100 ? 0 : (avgUnits <= 300 ? 1 : 2);
        return rates[index];
    }

    static double calculateSavings(double surplus, double rate) {
        double savings = 0.0;
        savings += surplus * rate;
        return savings;
    }

    static double calculateSystemCost(int panels) {
        double cost = 55000 * panels;
        cost -= 20000;
        return cost;
    }

    static double calculatePayback(double cost, double monthlySavings) {
        return monthlySavings > 0 ? cost / (monthlySavings * 12) : Double.POSITIVE_INFINITY;
    }

    static void printReport(String city, String panelBrand, int numPanels, double capacityKW,
                             double dailyEnergy, double monthlyEnergy, double annualEnergy,
                             double avgConsumption, String status, double surplus, double deficit,
                             boolean gridConnected, boolean netMetering, boolean batteryBackup,
                             double savings, double cost, double payback, double rate,
                             double lat, double lon) {

        System.out.println("ROOFTOP SOLAR SYSTEM REPORT - " + city);
        System.out.printf("Panel Model          : %s%n", panelBrand);
        System.out.printf("Number of Panels     : %d%n", numPanels);
        System.out.printf("Total Capacity       : %.2f kW%n", capacityKW);
        System.out.printf("Daily Generation     : %.2f kWh%n", dailyEnergy);
        System.out.printf("Monthly Generation   : %.2f kWh%n", monthlyEnergy);
        System.out.printf("Annual Generation    : %.2f kWh%n", annualEnergy);
        System.out.printf("Avg Monthly Usage    : %.2f kWh%n", avgConsumption);
        System.out.printf("Applicable Rate      : Rs.%.2f/unit%n", rate);
        System.out.printf("Status               : %s%n", status);
        System.out.printf("Surplus Units        : %.2f kWh%n", surplus);
        System.out.printf("Deficit Units        : %.2f kWh%n", deficit);
        System.out.printf("Grid Connected       : %b%n", gridConnected);
        System.out.printf("Net Metering Eligible: %b%n", netMetering);
        System.out.printf("Needs Battery Backup : %b%n", batteryBackup);
        System.out.printf("Est. Monthly Savings : Rs.%.2f%n", savings);
        System.out.printf("System Cost          : Rs.%.2f%n", cost);
        System.out.printf("Est. Payback Period  : %.1f years%n", payback);
        System.out.printf("Location Coordinates : %.4f, %.4f%n", lat, lon);
    }
}
