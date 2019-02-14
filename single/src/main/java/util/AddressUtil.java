package util;

import sun.security.util.Length;

public class AddressUtil {


    public static void main(String[] args) {
        String s="255.0.0.1";
        System.out.println(LegalHost(s));
    }

    public static boolean LegalHost(String host){
        //特殊符号 .
        String[] hosts = host.split("\\.");
        if(hosts.length!=4){
            return false;
        }
        for(String s:hosts){
            Integer value = Integer.valueOf(s);
            if(value<0||(value>>>8)>0){
                return false;
            }
        }
        return true;
    }

}
