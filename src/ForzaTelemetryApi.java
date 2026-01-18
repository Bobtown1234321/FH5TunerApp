import java.nio.ByteBuffer;
import java.nio.ByteOrder;

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
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ForzaTelemetryApi{");
        sb.append("isRaceOn=").append(isRaceOn);
        sb.append(", carOrdinal=").append(carOrdinal);
        sb.append(", carClass=").append(carClass);
        sb.append(", carPerformanceIndex=").append(carPerformanceIndex);
        sb.append(", driveType=").append(driveType);
        sb.append(", numCyclinders=").append(numCyclinders);
        sb.append(", carType=").append(carType);
        sb.append(", timeMs=").append(timeMs);
        sb.append(", objectHit=").append(objectHit);
        sb.append(", engineRPM=");
        if (engineRPM == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < engineRPM.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(engineRPM[i]);
            sb.append(']');
        }
        sb.append(", carAccler=");
        if (carAccler == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < carAccler.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(carAccler[i]);
            sb.append(']');
        }
        sb.append(", velocity=");
        if (velocity == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < velocity.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(velocity[i]);
            sb.append(']');
        }
        sb.append(", angularVelocity=");
        if (angularVelocity == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < angularVelocity.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(angularVelocity[i]);
            sb.append(']');
        }
        sb.append(", angularPosition=");
        if (angularPosition == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < angularPosition.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(angularPosition[i]);
            sb.append(']');
        }
        sb.append(", normSusTravel=");
        if (normSusTravel == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < normSusTravel.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(normSusTravel[i]);
            sb.append(']');
        }
        sb.append(", normSlipRatio=");
        if (normSlipRatio == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < normSlipRatio.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(normSlipRatio[i]);
            sb.append(']');
        }
        sb.append(", wheelRotSpd=");
        if (wheelRotSpd == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < wheelRotSpd.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(wheelRotSpd[i]);
            sb.append(']');
        }
        sb.append(", onRumbleStrip=");
        if (onRumbleStrip == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < onRumbleStrip.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(onRumbleStrip[i]);
            sb.append(']');
        }
        sb.append(", puddleDepth=");
        if (puddleDepth == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < puddleDepth.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(puddleDepth[i]);
            sb.append(']');
        }
        sb.append(", surfaceRumble=");
        if (surfaceRumble == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < surfaceRumble.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(surfaceRumble[i]);
            sb.append(']');
        }
        sb.append(", normSlipAngle=");
        if (normSlipAngle == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < normSlipAngle.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(normSlipAngle[i]);
            sb.append(']');
        }
        sb.append(", normCombinedSlip=");
        if (normCombinedSlip == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < normCombinedSlip.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(normCombinedSlip[i]);
            sb.append(']');
        }
        sb.append(", actSusTravel=");
        if (actSusTravel == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < actSusTravel.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(actSusTravel[i]);
            sb.append(']');
        }
        sb.append(", position=");
        if (position == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < position.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(position[i]);
            sb.append(']');
        }
        sb.append(", tireTemp=");
        if (tireTemp == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < tireTemp.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(tireTemp[i]);
            sb.append(']');
        }
        sb.append(", speed=").append(speed);
        sb.append(", power=").append(power);
        sb.append(", torque=").append(torque);
        sb.append(", boost=").append(boost);
        sb.append(", fuel=").append(fuel);
        sb.append(", distTraveled=").append(distTraveled);
        sb.append(", bestLap=").append(bestLap);
        sb.append(", lastLap=").append(lastLap);
        sb.append(", currLap=").append(currLap);
        sb.append(", currRaceTime=").append(currRaceTime);
        sb.append(", lapNum=").append(lapNum);
        sb.append(", racePos=").append(racePos);
        sb.append(", throttle=").append(throttle);
        sb.append(", brake=").append(brake);
        sb.append(", clutch=").append(clutch);
        sb.append(", handBrake=").append(handBrake);
        sb.append(", gear=").append(gear);
        sb.append(", steer=").append(steer);
        sb.append(", normDriveLine=").append(normDriveLine);
        sb.append(", normAiBrakeDiff=").append(normAiBrakeDiff);
        sb.append('}');
        return sb.toString();
    }
}
