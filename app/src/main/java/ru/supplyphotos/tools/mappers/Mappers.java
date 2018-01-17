package ru.supplyphotos.tools.mappers;


import ru.supplyphotos.data.answers.start_login.DeviceToken;
import ru.supplyphotos.data.answers.start_login.StartToken;

/**
 * @author libgo on 14.01.2018.
 */

public class Mappers {

     public static DeviceToken mapDeviceToken(StartToken startToken){
         return startToken.getDeviceToken();
     }

}
