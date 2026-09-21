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
        
        int month1Units = 320; 
        int month2Units = 300; 
        int month3Units = 340; 
        int month4Units = 310; 
        int month5Units = 360; 
        int month6Units = 330; 
        
        double slab1Rate = 3.50; 
        double slab2Rate = 5.20; 
        double slab3Rate = 7.10; 
        
        double totalInstalledCapacityW = panelWattage * numPanels; 
        double totalInstalledCapacityKW = totalInstalledCapacityW / 1000; 
        double dailyEnergyKWh = (totalInstalledCapacityKW * sunlightHours) * efficiency; 
        double monthlyEnergyKWh = dailyEnergyKWh * 30; 
        double annualEnergyKWh = dailyEnergyKWh * 365; 
        
        double totalUnits = month1Units + month2Units + month3Units + month4Units + month5Units + month6Units; 
        double avgMonthlyConsumption = totalUnits / 6; 
        
        boolean meetsDemand = monthlyEnergyKWh >= avgMonthlyConsumption; 
        double surplusUnits = meetsDemand ? (monthlyEnergyKWh - avgMonthlyConsumption) : 0; 
        double deficitUnits = !meetsDemand ? (avgMonthlyConsumption - monthlyEnergyKWh) : 0; 
        
        boolean eligibleForNetMetering = isGridConnected && meetsDemand; 
        boolean needsBatteryBackup = (!isGridConnected) || (!meetsDemand); 
        
        double savingsPerMonth = 0.0; 
        savingsPerMonth += surplusUnits * slab2Rate; 
        
        double systemCost = 55000 * numPanels; 
        systemCost -= 20000; 
        
        double paybackYears = savingsPerMonth > 0 ? systemCost / (savingsPerMonth * 12) : Double.POSITIVE_INFINITY; 
        String performanceStatus = meetsDemand ? "Surplus" : "Deficit"; 
        
        System.out.println("ROOFTOP SOLAR SYSTEM REPORT - " + city); 
        System.out.printf("Panel Model : %s%n", panelBrand); 
        System.out.printf("Number of Panels : %d%n", numPanels); 
        System.out.printf("Total Capacity : %.2f kW%n", totalInstalledCapacityKW); 
        System.out.printf("Daily Generation : %.2f kWh%n", dailyEnergyKWh); 
        System.out.printf("Monthly Generation : %.2f kWh%n", monthlyEnergyKWh); 
        System.out.printf("Annual Generation : %.2f kWh%n", annualEnergyKWh); 
        System.out.printf("Avg Monthly Usage : %.2f kWh%n", avgMonthlyConsumption); 
        System.out.printf("Status : %s%n", performanceStatus); 
        System.out.printf("Surplus Units : %.2f kWh%n", surplusUnits); 
        System.out.printf("Deficit Units : %.2f kWh%n", deficitUnits); 
        System.out.printf("Grid Connected : %b%n", isGridConnected); 
        System.out.printf("Net Metering Eligible: %b%n", eligibleForNetMetering); 
        System.out.printf("Needs Battery Backup : %b%n", needsBatteryBackup); 
        System.out.printf("Est. Monthly Savings : Rs.%.2f%n", savingsPerMonth); 
        System.out.printf("System Cost : Rs.%.2f%n", systemCost); 
        System.out.printf("Est. Payback Period : %.1f years%n", paybackYears); 
        System.out.printf("Slab1 Rate Ref : %.2f, Slab3 Rate Ref: %.2f%n", slab1Rate, slab3Rate); 
        System.out.printf("Location Coordinates : %.4f, %.4f%n", latitude, longitude); 
    } 
}