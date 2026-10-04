//QUAH YU WEI 1211110756
//Explaination 
#include <jni.h>
#include <string>
#include <sstream>
#include <iomanip>
#include "BMICalculator.h"

// BMI Calculator Class
class BMI {
    private:
        double weight;      // This in kg
        double height;      // This in cm
        double bmiValue;
    
    public:
        BMI(double w, double h) : weight(w), height(h), bmiValue(0.0) {}
        
        // Constructor to directly set BMI value
        BMI(double bmi) : weight(0.0), height(0.0), bmiValue(bmi) {}
    
        // This is Calculate BMI 
        double calculate() {
            
            // This is Question requirement which specific height from cm to meters format
            double heightInMeters = height / 100.0;
        
            // After that this is calculation of BMI = weight(kg) / [height(m)]^2
            bmiValue = weight / (heightInMeters * heightInMeters);
        
            return bmiValue;
        }
    
        // This is the BMI category, help to identify the BMI Category
        std::string BMICategory() const {
            if (bmiValue < 16.0) {
                return "Severe Thinness";
            } else if (bmiValue >= 16.0 && bmiValue < 17.0) {
                return "Moderate Thinness";
            } else if (bmiValue >= 17.0 && bmiValue < 18.5) {
                return "Mild Thinness";
            } else if (bmiValue >= 18.5 && bmiValue < 25.0) {
                return "Normal";
            } else if (bmiValue >= 25.0 && bmiValue < 30.0) {
                return "Overweight";
            } else if (bmiValue >= 30.0 && bmiValue < 35.0) {
                return "Obese Class I";
            } else if (bmiValue >= 35.0 && bmiValue < 40.0) {
                return "Obese Class II";
            } else {
                return "Obese Class III";
        }   
    }
    
        // This is use in health advice based on BMI value
        std::string HealthAdvice() const {
            std::string advice;
            
            if (bmiValue < 16.0) {

                // Severe Thinness category
                advice = "- You are severely underweight. Please consult a doctor.\n";
                advice += "- Eat more nutritious, calorie-rich foods.\n";
                
            } else if (bmiValue >= 16.0 && bmiValue < 17.0) {

                // Moderate Thinness category
                advice = "- You are moderately underweight.\n";
                advice += "- Increase your calorie intake with healthy foods.\n";
                
            } else if (bmiValue >= 17.0 && bmiValue < 18.5) {

                // Mild Thinness category
                advice = "- You are slightly underweight.\n";
                advice += "- Focus on balanced, nutritious meals.\n";
                
            } else if (bmiValue >= 18.5 && bmiValue < 25.0) {

                // Normal category
                advice = "- Great job! You have a healthy weight.\n";
                advice += "- Maintain your current diet and exercise routine.\n";
                
            } else if (bmiValue >= 25.0 && bmiValue < 30.0) {

                // Overweight category
                advice = "- You are slightly overweight.\n";
                advice += "- Focus on portion control and healthy eating.\n";
                
            } else if (bmiValue >= 30.0 && bmiValue < 35.0) {

                // Obese Class I category
                advice = "- You are in the obese category.\n";
                advice += "- Consult a healthcare provider for guidance.\n";
                
            } else if (bmiValue >= 35.0 && bmiValue < 40.0) {

                // Obese Class II category
                advice = "- You have severe obesity. Seek medical advice.\n";
                advice += "- Work with a doctor on a weight loss plan.\n";
                
            } else {

                // Obese Class III category
                advice = "- You have very severe obesity. Medical help is important.\n";
                advice += "- Please consult a healthcare professional urgently.\n";
            }
            
            return advice;
        }
    
        // This is use to get the user BMI value
        double getValue() const {
            return bmiValue;
        }
    };

    
    jstring cppStringToJavaString(JNIEnv *env, const std::string& str) {
        return env->NewStringUTF(str.c_str());
    }

    // This is the JNI function for Calculate BMI
    extern "C" JNIEXPORT jdouble JNICALL Java_BMICalculator_calculateBMI
        (JNIEnv *env, jobject obj, jdouble weight, jdouble height) {
    
        try {

            // This is use to create BMI object using C++ class
            BMI bmi(weight, height);
        
            // Calculate and return BMI
            return bmi.calculate();
        
        } catch (const std::exception& e) {
            // Handle any exceptions
            return -1.0;
        }
    }

    // This is the JNI function for BMI Category
    extern "C" JNIEXPORT jstring JNICALL Java_BMICalculator_BMICategory
        (JNIEnv *env, jobject obj, jdouble bmi) {
    
        try {

            BMI bmiCalculator(bmi);
        
            // This is use to get the BMI category using C++ string
            std::string category = bmiCalculator.BMICategory();
        
            return cppStringToJavaString(env, category);
        
        } catch (const std::exception& e) {
            return cppStringToJavaString(env, "Unknown");
        }
    }

    // This is the JNI function for health advice
    extern "C" JNIEXPORT jstring JNICALL Java_BMICalculator_HealthAdvice
        (JNIEnv *env, jobject obj, jdouble bmi) {
    
        try {
            // Create BMI object with the BMI value
            BMI bmiCalculator(bmi);
        
            // Get health advice using C++ string
            std::string advice = bmiCalculator.HealthAdvice();
        
            return cppStringToJavaString(env, advice);
        
        } catch (const std::exception& e) {
            return cppStringToJavaString(env, "Unable to provide advice.");
        }
    }