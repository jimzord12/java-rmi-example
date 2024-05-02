package simple_rmi_project;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AdderImpl extends UnicastRemoteObject implements Adder {

    protected AdderImpl() throws RemoteException {
        super();
    }

    public int add(int x, int y) {
        return x + y;
    }
}
