# BMI Calculator with JNI

A console-based BMI Calculator built with Java and C++ using JNI. Java handles the menu, user input, and result display, while C++ handles the BMI calculation, BMI category classification, and health advice through a native DLL.

## Project Files

- `BMICalculator.java` - Main Java program with the console menu and user input handling.
- `BMICalculator.h` - JNI header file that connects Java native methods to C++ functions.
- `bmi_calculator.cpp` - C++ source code containing the BMI calculation logic.
- `bmi.dll` - Compiled native C++ library used by the Java program.
- `BMICalculator.class` - Compiled Java class file.

## Features

- Calculate BMI using weight and height.
- Identify BMI category from a BMI value.
- Provide simple health advice based on BMI.
- Demonstrate Java and C++ interoperability using JNI.

## Requirements

- Java JDK installed.
- Windows operating system.
- `bmi.dll` must be in the same folder as the Java program, or available in the system library path.

## How to Run

1. Open Command Prompt in this project folder.

2. Compile the Java file if needed:

```bash
javac BMICalculator.java
```

3. Run the program:

```bash
java -Djava.library.path=. BMICalculator
```

## How to Use

When the program starts, choose one of the menu options:

1. Calculate BMI by entering weight in kilograms and height in centimeters.
2. Enter a BMI value to check the BMI category.
3. Enter a BMI value to get health advice.
4. Exit the program.

## Example

```text
Please enter your choice: 1
Please enter your weight (kg): 70
Please enter your height (cm): 170
BMI Value: 24.22
```

## Notes

- Enter numeric values only.
- Weight and height must be positive values.
- The health advice is general guidance and should not replace professional medical advice.
