package com.company;

import java.io.*;
import java.net.Socket;
import java.security.Timestamp;
import java.util.Date;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {

    public static Socket connect() throws IOException {

        int port = 4040;
        String host = "localhost";
        Socket myClient;

        System.out.println("SocketClient initialized");

        try {
            myClient = new Socket(host, port);

            return myClient;
        }
        catch (IOException f) {
            System.out.println("IOException: " + f);
        }
        catch (Exception g) {
            System.out.println("Exception: " + g);
        }
        return null;
    }

    public static void run(Socket Client) throws IOException {
        Socket myClient = Client;

        BufferedOutputStream bos = new BufferedOutputStream(myClient.getOutputStream());
        OutputStreamWriter osw = new OutputStreamWriter(bos, "US-ASCII");

        StringBuffer instr = new StringBuffer();
        String TimeStamp = new Date().toString();
        String LAT = "15";
        String LON = "10";
        String LATITUDE = "LATITUDE:" + LAT +(char) 13;
        String LONGITUDE = "LONGITUDE:" + LON + (char) 13;

        osw.write(LATITUDE);
        osw.write(LONGITUDE);
        osw.flush();

        /** Instantiate a BufferedInputStream object for reading
        /** Instantiate a BufferedInputStream object for reading
        * incoming socket streams.
        */

        BufferedInputStream bis = new BufferedInputStream(myClient.getInputStream());
        /**Instantiate an InputStreamReader with the optional
         * character encoding.
         */

        InputStreamReader isr = new InputStreamReader(bis, "US-ASCII");

        /**Read the socket's InputStream and append to a StringBuffer */
        int c;
        while ( (c = isr.read()) != 13)
            instr.append( (char) c);

            System.out.println(instr);

    }


    public static void main(String[] args) throws IOException, InterruptedException {
        // write your code here
        Socket myClient = connect();

        //this code sucks a lot, so fucking much


        while(true){
            sleep(5);
            run(myClient);
            sleep(3000);
        }

    }
}
