package simple_rmi_project;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {

    // You can use static fields to store Global State
    public static void main(String[] args) {
        try {
            // Create and export a remotable object
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

// Exmaple with multiple services:
/*
    public static void main(String[] args) {
        try {
            // Create and export remotable objects
            AdderImpl adder = new AdderImpl();
            SubtractorImpl subtractor = new SubtractorImpl();
            MultiplierImpl multiplier = new MultiplierImpl();
            DividerImpl divider = new DividerImpl();

            // Start the RMI registry on port 1099
            LocateRegistry.createRegistry(1099);

            // Bind the remote objects' stubs in the registry
            Naming.rebind("rmi://localhost/AdderService", adder);
            Naming.rebind("rmi://localhost/SubtractorService", subtractor);
            Naming.rebind("rmi://localhost/MultiplierService", multiplier);
            Naming.rebind("rmi://localhost/DividerService", divider);

            System.out.println("Service registered and ready for use.");
        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
 */

 /*
  If you want to modify the Server State from the Client, you can define methods in the Server class which will be called by the remote objects. For example:

  public class Server {
    private static int operationCount = 0;

    // (This one) - Synchronized method to increment the operation count
    public static synchronized void incrementOperationCount() {
        operationCount++;
    }

    // (This one) - Synchronized method to get the current operation count
    public static synchronized int getOperationCount() {
        return operationCount;
    }

    public static void main(String[] args) {
        try {
            // Create and export remotable objects
            AdderImpl adder = new AdderImpl();
            SubtractorImpl subtractor = new SubtractorImpl();
            MultiplierImpl multiplier = new MultiplierImpl();
            DividerImpl divider = new DividerImpl();

            // Start the RMI registry on port 1099
            LocateRegistry.createRegistry(1099);

            // Bind the remote objects' stubs in the registry
            Naming.rebind("rmi://localhost/AdderService", adder);
            Naming.rebind("rmi://localhost/SubtractorService", subtractor);
            Naming.rebind("rmi://localhost/MultiplierService", multiplier);
            Naming.rebind("rmi://localhost/DividerService", divider);

            System.out.println("Service registered and ready for use.");
        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
  }
-------------------------------------------------------------------------------
  Now, inside the add, subtract, multiply, and divide methods of the AdderImpl, SubtractorImpl, MultiplierImpl, and DividerImpl classes, you can call the incrementOperationCount method of the Server class to increment the operation count. You can also define a getOperationCount method in the Server class to get the current operation count.

  Example:
  
  public class AdderImpl extends UnicastRemoteObject implements Adder {
    
    protected AdderImpl() throws RemoteException {
        super();
    }

    public int add(int x, int y) {
        Server.incrementOperationCount(); // <-- Modifies the Server State
        return x + y;
    }

}
  */
