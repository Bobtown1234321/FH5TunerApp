import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class driver {
    public static void main(String[] args) {
        int port = 5001; //Port set in Forza

        ForzaClient client = new ForzaClient(port);

        //saveAPI saveAPI= new saveAPI();
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.submit(client);


    }
}
