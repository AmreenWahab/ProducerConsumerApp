package com.consumer.consumer.util;


import com.consumer.consumer.model.Target;

import java.util.Random;

public class Utility {

    private static Random random = new Random();
    private static Random randomForTarget = new Random();
    private static Random randomForMaxRecords = new Random();

    public static int getRandom() {
        return random.ints(1,Integer.MAX_VALUE).findFirst().getAsInt();
    }

    //a random target value is chosen from the given set of target enum
    public static int getRandomForTarget() {
        return randomForTarget.ints(1, Target.values().length+1).findFirst().getAsInt();
    }

    // maximum limit for records to be randomly added is set as 200, can be changed to any desired value.
    public static int getRandomForMaxRecords() {
        return randomForTarget.ints(1, 2000).findFirst().getAsInt();
    }
}
