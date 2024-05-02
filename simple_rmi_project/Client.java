package simple_rmi_project;
import java.rmi.Naming;

public class Client {
    public static void main(String[] args) {
        try {
            // Look up the remote object in the RMI registry
            Adder adder = (Adder) Naming.lookup("rmi://localhost/AdderService");
            // Call the remote method and print the result
            System.out.println("Result of add(5, 10): " + adder.add(5, 10));
        } catch (Exception e) {
            System.out.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
