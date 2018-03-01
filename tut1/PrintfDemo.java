import java.util.*;

public class PrintfDemo {
  public static void main( String args[] ) 
  {
    System.out.printf("Two decimal digits: %.2f\n", 10.0/3.0);
    System.out.printf("Use group separators: %,.2f\n\n",
                      1546456.87);
    System.out.printf("%10s %10s %10s\n",
                      "Value", "Root", "Square");

    for(double i=1.0; i < 10.0; i++)
      System.out.printf("%10.2f %10.2f %10.2f\n",
                        i, Math.sqrt(i), i*i);

    System.out.println();
    Calendar cal = Calendar.getInstance();
    System.out.printf("Current time and date: %tc\n", cal);
  }
}
