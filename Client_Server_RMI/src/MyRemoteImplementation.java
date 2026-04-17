import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyRemoteImplementation extends UnicastRemoteObject implements MyRemoteInterface {
    protected MyRemoteImplementation() throws RemoteException{
        super();
    }
    @Override
    public String sayHello (String name) throws RemoteException{
        return "Hello, " + name + "!";
    }
}
