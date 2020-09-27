package com;

/**
 * @author jie
 */
public class Jtest {
    public static void main(String[] args) {
        String[] jxfs = new String[] { "正接法", "反接法", "自激法" };
        String[] csdy = new String[] { "0.5", "0.6", "0.8", "1.0", "1.5", "2.0", "2.5", "3.0", "3.5", "4.0", "4.5", "5.0", "5.5", "6.0", "6.5", "7.0", "7.5", "8.0", "8.5", "9.0", "9.5", "10.0", "10.5", "11.0", "11.5", "12.0", "0.0" };
        String[] zjcsdy = new String[] { "0.5", "0.6", "0.8", "1.0", "1.5", "2.0", "2.5", "3.0" };

        for (int i=zjcsdy.length-1 ; i >=0; i--) {
            System.out.print("\""+csdy[i]+"\",");

        }


    }
}
