//QUAH YU WEI 1211110756
//Explaination 

import java.util.Scanner;

    public class BMICalculator {

        // This is use to load the native library
        static {
            try {
                System.loadLibrary("bmi");
                libraryLoaded = true;
            } catch (UnsatisfiedLinkError e) {
                libraryLoaded = false;
            }
        }

        private static boolean libraryLoaded = false;
    
        // This is use for Native method declarations part
        public native double calculateBMI(double weight, double height);
        public native String BMICategory(double bmi);
        public native String HealthAdvice(double bmi);  // NEW: Health advice method
    
        private static void cout(String message) {
            System.out.println(message);
        }
    
        // Helper method for cout-style print
        private static void CountNewline(String message) {
            System.out.print(message);
        }
    
        public static void main(String[] args) {
            BMICalculator calculator = new BMICalculator();
            Scanner scanner = new Scanner(System.in);
        
            printHeader();
        
            boolean running = true;
        
            while (running) {

                //This is the Menu Selection which will display 4 functions
                cout("--This is the Group 4 TCP Part B Question 1--");
                cout("\n--BMI Calculator--");
                cout("\nSelect an option:\n");
                cout("1. Calculate Your BMI Value, insert your weight and height");
                cout("2. Analysis your BMI category from your BMI value");
                cout("3. Get Health Advice based on your BMI");  // NEW OPTION
                cout("4. Exit the program");
                CountNewline("\nPlease enter your choice (use 1, 2, 3, or 4): ");
            
            try {
                int choice = scanner.nextInt();
                
                if (choice == 1) {
                    // This funtion represent Option 1 which isCalculate BMI using weight and height
                    CalculateBMIWeightHeight(calculator, scanner);
                    
                } else if (choice == 2) {
                    // This funtion represent Option 2 which get the BMI category from BMI value
                    CategoryBMI(calculator, scanner);
                    
                } else if (choice == 3) {
                    // NEW: This function represent Option 3 which is get health advice
                    HealthAdvice(calculator, scanner);
                    
                } else if (choice == 4) {
                    // This function represent Option 4 which is Exit the program
                    cout("\nThank you for using BMI Calculator, See you next time and Stay Healthy. Goodbye!");
                    running = false;
                    
                    //invalid number input will display error message
                } else {
                    cout("\nThis is invalid choice! Come on please enter 1, 2, 3, or 4!");
                }

                //invalid alphabet input will display error message
            } catch (Exception e) {
                cout("\nThis is invalid input! Please enter number 1,2,3,4 only.");
                scanner.nextLine(); // This clear the invalid input
            }
        }
        
        scanner.close();
    }
    
    // This is Function 1: Calculate BMI Value using weight and height
        private static void CalculateBMIWeightHeight(BMICalculator calculator, Scanner scanner) {
            cout("\n--------------------------------------");
            cout("   CALCULATE BMI FROM WEIGHT & HEIGHT");
            cout("--------------------------------------\n");
        
            try {
                // This is use to get the user input which are weight and height
                CountNewline("Please enter your weight (kg): ");
                double weight = scanner.nextDouble();
            
                CountNewline("Please enter your height (cm): ");
                double height = scanner.nextDouble();
            
                // This is use to set the Valid rules
                if (weight <= 0 || height <= 0) { //no one is 0 weight and height
                    cout("\nThis is error value! Come on how can your weight and height are 0?"); //error message
                    return;
                }
            
                if (weight > 500 || height > 300) {//no one is more than 500kg weight and more than 300cm height
                    cout("\nThis is error value! Come on how can you more than 500kg and higher than 300cm? You are not human bruh");
                    return;
                }
            
                // This is use to calculate BMI using native method
                double bmi = calculator.calculateBMI(weight, height);
            
                // This is the display BMI Value result interface
                cout("\n----------------------------------------");
                cout("         YOUR BMI VALUE RESULT");
                cout("----------------------------------------");
                cout(String.format("Weight:        %.2f kg", weight));
                cout(String.format("Height:        %.2f cm", height));
                cout(String.format("BMI Value:     %.2f", bmi));
                cout("----------------------------------------");
            
                cout("\nYour BMI Value has been calculated: " + String.format("%.2f", bmi));
                cout("Select your function! You can insert 2 to get your BMI category, insert 3 for health advice, and insert 4 to exit program!");
            
                //Invalid input insert
            } catch (Exception e) {
                cout("\nThis is the invalid input! Please enter correct or numeric values.");
            }
    }
    
        // This is Function 2: Get category from BMI value
        private static void CategoryBMI(BMICalculator calculator, Scanner scanner) {
            cout("\n----------------------------------------");
            cout("     GET BMI CATEGORY FROM BMI VALUE");
            cout("----------------------------------------\n");
        
            try {
                //User need to insert their BMI Value to get the BMI categories from user
                CountNewline("Please enter your BMI value, Be Honest: ");
                double bmi = scanner.nextDouble();
            
                // The Validate rules input
                if (bmi <= 0) {
                    cout("\nThis is error message. The BMI must be a positive value!");
                    return;
                }
            
                if (bmi > 100) {
                    cout("\nThis is error message. Please enter a correct BMI value!");
                    return;
                }
            
                // This is use to get BMI category using native method
                String category = calculator.BMICategory(bmi);
            
                // This is use to display BMI results
                cout("\n----------------------------------------");
                cout("            YOUR BMI RESULTS");
                cout("------------------------------------------");
                cout(String.format("BMI Value:     %.2f", bmi));
                cout(String.format("Category:      %s", category));
                cout("------------------------------------------");
            
                printBMIChart(bmi);
            
            //This is the invalid input and display error message
            } catch (Exception e) {
                cout("\nThis is error message: you insert an invalid input! Please enter a correct value.");
            }
    }
    
        // NEW: Function 3: Get health advice based on BMI
        private static void HealthAdvice(BMICalculator calculator, Scanner scanner) {
            cout("\n----------------------------------------");
            cout("       GET HEALTH ADVICE FROM BMI");
            cout("----------------------------------------\n");
        
            try {
                // User needs to insert their BMI Value to get health advice
                CountNewline("Please enter your BMI value: ");
                double bmi = scanner.nextDouble();
            
                // Validate input
                if (bmi <= 0) {
                    cout("\nThis is error message. The BMI must be a positive value!");
                    return;
                }
            
                if (bmi > 100) {
                    cout("\nThis is error message. Please enter a correct BMI value!");
                    return;
                }
            
                // Get BMI category and health advice using native methods
                String category = calculator.BMICategory(bmi);
                String advice = calculator.HealthAdvice(bmi);
            
                // Display health advice results
                cout("\n========================================");
                cout("          YOUR HEALTH ADVICE");
                cout("========================================");
                cout(String.format("BMI Value:     %.2f", bmi));
                cout(String.format("Category:      %s", category));
                cout("----------------------------------------");
                cout("Health Advice:");
                cout(advice);
                cout("========================================");

                cout("\nThis is general advice. Please consult a healthcare professional for personalized guidance");
            
            // This is the invalid input and display error message
            } catch (Exception e) {
                cout("\nThis is error message: you insert an invalid input! Please enter a correct value.");
            }
        }
    
        private static void printHeader() {

            cout("\n-------------------------------------------");
            cout("          BMI CALCULATOR with JNI");
            cout("-------------------------------------------\n");
        }
    
        //This is the BMI Categories Reference Table
        private static void printBMIChart(double bmi) {

            cout("\nThis is the BMI REFERENCE CHART:");
            cout("+---------------------+------------------+");
            cout("|     Category        |      BMI Range   |");
            cout("+---------------------+------------------+");
        
            String[] categories = {
                "Severe Thinness", "Moderate Thinness", "Mild Thinness", 
                "Normal", "Overweight", "Obese Class I", 
                "Obese Class II", "Obese Class III"
            };

            String[] ranges = {
                "< 16", "16 - 17", "17 - 18.5", 
                "18.5 - 25", "25 - 30", "30 - 35", 
                "35 - 40", "> 40"
            };
        
            for (int i = 0; i < categories.length; i++) {
                boolean inRange = false;
                if (i == 0 && bmi < 16) inRange = true;
                else if (i == 1 && bmi >= 16 && bmi < 17) inRange = true;
                else if (i == 2 && bmi >= 17 && bmi < 18.5) inRange = true;
                else if (i == 3 && bmi >= 18.5 && bmi < 25) inRange = true;
                else if (i == 4 && bmi >= 25 && bmi < 30) inRange = true;
                else if (i == 5 && bmi >= 30 && bmi < 35) inRange = true;
                else if (i == 6 && bmi >= 35 && bmi < 40) inRange = true;
                else if (i == 7 && bmi >= 40) inRange = true;
            
                //this is use for navigation icon
                String marker = inRange ? ">> " : "   ";
                cout(String.format("| %s%-18s| %-16s |", marker, categories[i], ranges[i]));
            }

            cout("+---------------------+---------------x-+");
        }
}