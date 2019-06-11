import java.util.*;

public class Recursion {

    public static String removeXs(String str){
        return removeR(str, 0, "");
    }

    public static String removeR (String str, int start){
        if(str.length() == 0) return "";
        if(str.charAt(start) == 'x'){
            str = str.substring(0, start)
        }
        return removeXs(str.substring(1, str.length()-1));
    }


    public static void main(String[] args){
        //int[] num = new int[;3];
        //num[0] = 1;
        //num[1] = 2;
        //num[2] = 1;
        //num[3] = 1;
        System.out.print(matched("((a))"));
    }

}
