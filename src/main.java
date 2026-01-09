import java.nio.file.FileStore;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class main {
    public static void main(String[] args) {
        int port = 5001;
        //UdpUnicastServer server = new UdpUnicastServer(port);
        //UdpUnicastClient client = new UdpUnicastClient(port);
        //ExecutorService executorService = Executors.newFixedThreadPool(1);
        //executorService.submit(client);
        //executorService.submit(server);

        saveAPI saveAPI= new saveAPI();



    }
}
