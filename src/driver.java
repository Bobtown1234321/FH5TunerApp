import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.DecimalFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class driver {
    public static void main(String[] args) {
        //saveAPI saveAPI= new saveAPI();

        byte[] test = {102, 3, 0, 0, -122, -33, -7, 62};
        //byte[] maxRpm = {-78, 77, 54, 57,};
        //ByteBuffer buffer1 = ByteBuffer.wrap(maxRpm);
        //buffer1.order(ByteOrder.LITTLE_ENDIAN);

        ByteBuffer buffer = ByteBuffer.wrap(test);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        int index = buffer.getInt();
        float rpm = buffer.getFloat();
        DecimalFormat test1 = new DecimalFormat("0.00");

        System.out.println(index);
        System.out.println(test1.format(rpm));
    }

    protected void client(){
        int port = 5001; //Port set in Forza
        ForzaClient client = new ForzaClient(port);

        ExecutorService service = Executors.newFixedThreadPool(1);
        service.submit(client);
    }
}
