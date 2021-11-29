package uhk.hausy.subsystem.core.rule;


import uhk.hausy.subsystem.core.flatBuffer.objects.server.OperatorFB;

import java.util.Arrays;

/**
 * Created by david on 08.11.2016.
 */
public class Evaluer {

    //private float sensorValue;

    public static boolean compareValues(int[] data, int[] values, byte[] operators) {

        for(int i = 0; i < operators.length; i++) {
            if(!compareSingleValue(data[i], values[i], operators[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean compareSingleValue(int sensorValue, int value, byte operator) {
        if (operator == OperatorFB.EQUAL_TO) {
            //System.out.println((sensorValue == value) + ";" + sensorValue + "==" + value);
            return (sensorValue == value);
        }
        if (operator == OperatorFB.NOT_EQUAL_TO) {
            //System.out.println((sensorValue != value) + ";" + sensorValue + "!=" + value);
            return (sensorValue != value);
        }
        if (operator == OperatorFB.OVER) {
            //System.out.println((sensorValue >= value) + ";" + sensorValue + ">=" + value);
            return (sensorValue >= value);
        }
        if (operator == OperatorFB.UNDER) {

            ////System.out.println((sensorValue <= value) + ";" + sensorValue + "<=" + value);
            return (sensorValue <= value);
        } else {
            //System.out.println("package uhk.hausy.subsystem.core.rule.Evaluer.compareValues(): No Match found");
            return false;
        }
    }

    public static boolean compareValues2(float[] data, float[] values, byte operator) {

        float[] sensorValue = data;
        //float[] value = values;
        if (operator == OperatorFB.EQUAL_TO) {
            return Arrays.equals(sensorValue, values);
        }
        if (operator == OperatorFB.NOT_EQUAL_TO) {
            return !Arrays.equals(sensorValue, values);

        }
        if (operator == OperatorFB.OVER) {
            if (sensorValue.length == values.length){
                for(int i=0; i< sensorValue.length; i++ ) {
                    if (sensorValue[i] <= values[i] ){
                        return false;
                    }
                }
                return true;
            } else{ return false; }
        }
        if (operator == OperatorFB.UNDER) {
            if (sensorValue.length == values.length){
                for(int i=0; i< sensorValue.length; i++ ) {
                    if (sensorValue[i] >= values[i] ){
                        return false;
                    }
                }
                return true;
            } else{ return false; }
        }

        else{
            return false;
        }

    }

}
