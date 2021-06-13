package javapackage;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    // get 3 standards for evaluating test case
    float freezingThreshold = getStandards("freezing threshold: ");
    float boilingThreshold = getStandards("boiling threshold: ");
    float fluctuation = Math.abs(getStandards("fluctuation: "));
    // get test case
    String[] inputs = getUserInput();
    // process test case
    processInput(freezingThreshold, boilingThreshold, fluctuation, inputs);
  }

  public static float getStandards(String value) {
    Scanner scanner = new Scanner(System.in);
    System.out.print(value);
    while(!scanner.hasNextFloat()) {
      System.out.print("please enter digit: ");
      scanner.next();
    }
    float number = scanner.nextFloat();
    return number;
  }

  public static String[] getUserInput() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("temperature digits with spaces in between: ");
    String[] numbers = scanner.nextLine().split("\\s");
    if (!containsAllIntegers(numbers))
      System.out.print("please enter digits: ");
      numbers = scanner.nextLine().split("\\s");
    return numbers;
 }

  public static boolean containsAllIntegers(String[] numbers){
    for (String number : numbers){
      if (!isDigit(number)){
        return false;
      }   
    }   
    return true;
  }

  public static boolean isDigit(String number) {
    try { 
        Float.parseFloat(number); 
    } catch(NumberFormatException e) { 
        return false; 
    }
    return true;
  }

 public static ArrayList<String> processInput(float freezingThreshold, float boilingThreshold, float fluctuation, String [] inputs) {
    ArrayList<String> tempInputs = new ArrayList<String>();
    
    String prevStatus = "";

    for (int i = 0; i < inputs.length; i++) {
      ArrayList<String> curStatus = new ArrayList<String>();
      tempInputs.add(inputs[i]);
      if (prevStatus == "freezing") {
        if (Float.parseFloat(inputs[i]) > (freezingThreshold + fluctuation)) {
          curStatus.add("unfreezing");
        }
      } else if (prevStatus == "boiling") {
        if (Float.parseFloat(inputs[i]) < (boilingThreshold - fluctuation)) {
          curStatus.add("unboiling");
        }
      }
      if (Float.parseFloat(inputs[i]) <= freezingThreshold && prevStatus != "freezing") {
        curStatus.add("freezing");
      }
      if (Float.parseFloat(inputs[i]) >= boilingThreshold && prevStatus != "boiling") {
        curStatus.add("boiling");
      }
      if (curStatus.size() > 0) {
        prevStatus = curStatus.get(curStatus.size() - 1);
        for (int j = 0; j < curStatus.size(); j++) {
          tempInputs.add(curStatus.get(j));
        }
      }
    }
    System.out.print("Output value: " + " " + tempInputs);
    return tempInputs;
 }
}