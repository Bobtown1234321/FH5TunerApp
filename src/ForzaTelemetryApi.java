import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class ForzaTelemetryApi {

    //Constants
    final private int FRONTLEFT = 0;
    final private int FRONTRIGHT = 1;
    final private int REARLEFT = 2;
    final private int REARRIGHT = 3;
    int[] wheel = {FRONTLEFT, FRONTRIGHT, REARLEFT, REARRIGHT};

    final private int XAXIS = 0;
    final private int YAXIS = 1;
    final private int ZAXIS = 2;

    final private int FLOATSIZE = 4;


    ByteBuffer buffer;

    int isRaceOn, carOrdinal, carClass, carPerformanceIndex, driveType, numCyclinders, carType;
    int timeMs; //TODO: Needs to be unsigned
    long objectHit;
    float[] engineRPM, carAccler, velocity, angularVelocity, angularPosition, normSusTravel, normSlipRatio, wheelRotSpd;
    int[] onRumbleStrip;
    float[] puddleDepth, surfaceRumble, normSlipAngle, normCombinedSlip, actSusTravel, position, tireTemp;
    float speed, power, torque, boost, fuel, distTraveled, bestLap, lastLap, currLap, currRaceTime;
    short lapNum;
    byte racePos, throttle, brake, clutch, handBrake, gear, steer, normDriveLine, normAiBrakeDiff;

    public ForzaTelemetryApi(byte[] data) {
        buffer = ByteBuffer.wrap(data);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        decode();
    }

    private float[] makeFloatArray(ByteBuffer bytes, int arrSize) {
        float[] temp = new float[arrSize];
        for (int i = 0; i < arrSize; i++) {
            temp[i] = bytes.getFloat();
        }
        return temp;
    }

    private int[] makeIntArray(ByteBuffer bytes, int arrSize) {
        int[] temp = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            temp[i] = bytes.getInt();
        }
        return temp;
    }

    private void decode() {

        isRaceOn = buffer.getInt();
        timeMs = buffer.getInt();
        engineRPM = makeFloatArray(buffer, 3);
        carAccler = makeFloatArray(buffer, 3);
        velocity = makeFloatArray(buffer, 3);
        angularVelocity = makeFloatArray(buffer, 3);
        angularPosition = makeFloatArray(buffer, 3);
        normSusTravel = makeFloatArray(buffer, 4);
        normSlipRatio = makeFloatArray(buffer, 4);
        wheelRotSpd = makeFloatArray(buffer, 4);
        onRumbleStrip = makeIntArray(buffer, 4);
        puddleDepth = makeFloatArray(buffer, 4);
        surfaceRumble = makeFloatArray(buffer, 4);
        normSlipAngle = makeFloatArray(buffer, 4);
        normCombinedSlip = makeFloatArray(buffer, 4);
        actSusTravel = makeFloatArray(buffer, 4);
        carOrdinal = buffer.getInt();
        carClass = buffer.getInt();
        carPerformanceIndex = buffer.getInt();
        driveType = buffer.getInt();
        numCyclinders = buffer.getInt();
        carType = buffer.getInt();
        objectHit = buffer.getLong();
        position = makeFloatArray(buffer, 3);
        speed = buffer.getFloat();
        power = buffer.getFloat();
        torque = buffer.getFloat();
        tireTemp = makeFloatArray(buffer, 4);
        boost = buffer.getFloat();
        fuel = buffer.getFloat();
        distTraveled = buffer.getFloat();
        bestLap = buffer.getFloat();
        lastLap = buffer.getFloat();
        currLap = buffer.getFloat();
        currRaceTime = buffer.getFloat();
        lapNum = buffer.getShort();
        racePos = buffer.get();
        throttle = buffer.get();
        brake = buffer.get();
        clutch = buffer.get();
        handBrake = buffer.get();
        gear = buffer.get();
        steer = buffer.get();

        normDriveLine = buffer.get();
        normAiBrakeDiff = buffer.get();

        System.out.println("End " + buffer.position());

        System.out.println(Arrays.toString(engineRPM));
        System.out.println(Arrays.toString(normSusTravel));
        //The starting bit location
        gear = buffer.get(319);
        System.out.println(boost);
        System.out.println(gear);
    }


}
