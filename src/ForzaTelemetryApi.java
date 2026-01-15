import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ForzaTelemetryApi {
    ByteBuffer buffer;

    int isRaceOn;
    int carPerformanceIndex;
    public ForzaTelemetryApi(byte[] data){
        buffer = ByteBuffer.wrap(data);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        decode();
    }

    private void decode(){
        isRaceOn = buffer.getInt();
        System.out.println(buffer.isDirect());
        System.out.println(buffer.remaining());
        System.out.println(buffer.position());
        carPerformanceIndex = buffer.getInt(220); //The starting bit location
        System.out.println(buffer.remaining());
        System.out.println(buffer.position());
    }

    @Override
    public String toString(){
        return "isRaceOn: " + isRaceOn + "\n"
                + "Car Performance Index: " + carPerformanceIndex + "\n";
    }
}
