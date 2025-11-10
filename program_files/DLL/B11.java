import java.util.Scanner;

public class B11 {
    static {
        System.loadLibrary("B11"); 
    }

    
    public native int add1(int a, int b);
    public native int sub1(int a, int b);
    public native int multi(int a, int b);
    public native int divi(int a, int b);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        B11 obj = new B11();

        System.out.print("Enter value of a: ");
        int a = sc.nextInt();

        System.out.print("Enter value of b: ");
        int b = sc.nextInt();

        while (true) {
            System.out.println("\nENTER YOUR CHOICE:");
            System.out.println("1. Add");
            System.out.println("2. Subtract");
            System.out.println("3. Multiply");
            System.out.println("4. Divide");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            int result;

            switch (choice) {
                case 1:
                    result = obj.add1(a, b);
                    System.out.println("Addition result: " + result);
                    break;
                case 2:
                    result = obj.sub1(a, b);
                    System.out.println("Subtraction result: " + result);
                    break;
                case 3:
                    result = obj.multi(a, b);
                    System.out.println("Multiplication result: " + result);
                    break;
                case 4:
                    if (b == 0) {
                        System.out.println("Division by zero is not allowed.");
                    } else {
                        result = obj.divi(a, b);
                        System.out.println("Division result: " + result);
                    }
                    break;
                case 5:
                    System.out.println("Exiting program.");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
