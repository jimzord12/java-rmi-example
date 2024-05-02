package simple_rmi_project;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            // Create and export a remote object
            AdderImpl adder = new AdderImpl();
            // Start the RMI registry on port 1099
            LocateRegistry.createRegistry(1099);
            // Bind the remote object's stub in the registry
            Naming.rebind("rmi://localhost/AdderService", adder);
            System.out.println("Service registered and ready for use.");
        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
