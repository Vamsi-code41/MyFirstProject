package Revision;

import Revision.managers.StudentManager;
import Revision.exceptions.StudentAlreadyExistsException;
import Revision.exceptions.StudentNotFoundException;

import java.util.Scanner;

public class Main{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    StudentManager studentManager = new StudentManager(2);
    boolean isrunning = true;
    while(isrunning){
    System.out.print("\n-----Student Record-------\n");
    System.out.println("1. Add Student");
    System.out.println("2. View all Students");
    System.out.println("3. Delete Student");
    System.out.println("4. Exit");
    int n = 0;
    try{
    System.out.print("Please select one of these Options-[1/2/3/4]: ");
    n = Integer.parseInt(sc.nextLine());
    validate(n);
    }
    catch(NumberFormatException e){
        System.out.println("Please select a valid option");
    }
      switch(n){
        case 1 -> {
          if(!studentManager.addStudent(sc)){
            continue;
          }
        }
        case 2 -> {
          System.out.println();
          studentManager.viewStudents();
        }
        case 3 -> {
          while(true){
            int rollNumber = StudentManager.getValidatedRollNumber(sc,"Enter the RollNumber of the Student: ");
            char x = Main.getValidatedYesNo(sc, String.format("Are you sure you would like to delete Student of RollNumber %d? [y/n]: ", rollNumber));
            if(Character.toLowerCase(x)== 'y'){
              try{
              studentManager.deleteStudent(rollNumber);
              System.out.println("The student has been successfully deleted.");
              break;
              }
              catch(StudentNotFoundException e){
                System.out.println(e.getMessage());
              }
            }
          }
        } 
        case 4 ->{
          System.out.println("Exited out successfully");
          isrunning = false;
          break;
        }
      }
    }
    sc.close();
  }
  public static void validate(int n) throws NumberFormatException{
    if(n>4){
      throw new NumberFormatException();
    }
  }

  public static char getValidatedYesNo(Scanner sc, String prompt) {
    while (true) {
        System.out.print(prompt);
        String input = sc.nextLine().trim();
        if (input.isEmpty()) continue;
        char c = Character.toLowerCase(input.charAt(0));
        if (c == 'y' || c == 'n') return c;
        System.out.println("Invalid input. Please enter 'y' or 'n'.");
    }
  }


}
