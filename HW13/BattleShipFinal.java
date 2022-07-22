
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.net.*;

import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class BattleShipFinal
{
    BufferedReader readFrom;
    PrintWriter writeTo;
    ServerSocket   aServerSocket;

    String ocean;
    String hostName;
    int port;
    boolean first = false;
    boolean second = false;

    String WIDTH  			= "width";
    String HEIGHT 			= "height";
    String WATER 			= "w";
    int WATER_VALUE 			= -1;
    int WATER_HIT_VALUE 		= -2;
    int HIT_VALUE 			= -3;
    String  lineDelimiter		= "#";

    int[][] battleField 		= null;		// This is the field shown to the player
    int[][] originalBattleField 	= null;		// will be initialized once
    String  battleFieldString 	= null;		// will be initialized once
    int battleFieldWidth 		= 0;
    int battleFieldHeight 		= 0;
    Scanner battleFieldParser 	= null;
    char hit 			= 'x';
    String fileName 			= null;

    static String readData;


    void parseArgs(String[] args ) {

        port = Integer.parseInt(args[0]);
        ocean = args[1];
        hostName = args[2];
        if (args[1].equals("ocean1")){
            first = true;
        }
        else {
            second = true;
        }
    }

    private void removeAllPartsOfBoat(int column,int row)	{
        int boatId = originalBattleField[column][row];
        for ( int rows = 0; rows < battleFieldHeight; rows ++ )	{
            for ( int columns = 0; columns < battleFieldWidth; columns ++ )	{
                if ( originalBattleField[columns][rows] == boatId )
                    battleField[columns][rows] = HIT_VALUE;
            }
        }
    }
    private void printBattleField(int[][] battleField) {
        System.out.println();
        for ( int rows = 0; rows < battleFieldHeight; rows ++ )	{
            for ( int columns = 0; columns < battleFieldWidth; columns ++ )	{
                if ( battleField[columns][rows] == WATER_VALUE )
                    System.out.print(  "w" + " " );
                else
                    System.out.print(  battleField[columns][rows] + " " );
            }
            System.out.println();
        }
        System.out.println();
    }
    private void printBattleFieldForPlayer(int[][] battleField) {
        System.out.println();
        System.out.println("x indicates a hit.");
        System.out.println("w indicates a miss, but you know now there is water.");
        System.out.println(". indicates boat oder water.\n");
        System.out.print( "   " );
        for ( int columns = 0; columns < battleFieldWidth; columns ++ )	{
            System.out.print(  " " + columns );
        }
        System.out.println(" ---> columns");
        for ( int rows = 0; rows < battleFieldHeight; rows ++ )	{
            System.out.print(rows + ": " );
            for ( int columns = 0; columns < battleFieldWidth; columns ++ )	{
                if ( battleField[columns][rows] == WATER_HIT_VALUE )
                    System.out.print(  " " + "w" );
                else if ( battleField[columns][rows] == HIT_VALUE )
                    System.out.print(  " " + "x" );
                else
                    System.out.print(  " " + "." );
            }
            System.out.println();
        }
        System.out.println();
    }
    private boolean isThereAboatLeft() {
        boolean rValue = false;
        for ( int rows = 0; ! rValue && rows < battleFieldHeight; rows ++ )	{
            for ( int columns = 0; ! rValue && columns < battleFieldWidth; columns ++ )	{
                if  ( battleField[columns][rows] >= 0 )
                    rValue = true;
            }
        }
        return rValue;
    }
    private boolean allWater() {
        for ( int column = 0; column < battleFieldWidth; column ++ )	{
            for ( int row = 0; row < battleFieldWidth; row ++ )	{
                if (  battleField[column][row] != WATER_VALUE  )
                    return false;
            }
        }
        return true;
    }
    private void readHeightWidth() {
        for (int index = 0; index < 2; index ++ )	{
            if ( battleFieldParser.hasNextLine() )	{
                String[] oneDimension = battleFieldParser.nextLine().split("\\s+");
                if ( oneDimension[0].equals(WIDTH) )
                    battleFieldWidth = Integer.parseInt(oneDimension[1]);
                else
                    battleFieldHeight = Integer.parseInt(oneDimension[1]);
            }
        }
    }
    private void createBattleField() {
        battleField             = new int[battleFieldWidth][battleFieldHeight];
        originalBattleField	= new int[battleFieldWidth][battleFieldHeight];
        for ( int columns = 0; columns < battleFieldWidth; columns ++ )	{
            for ( int rows = 0; rows < battleFieldHeight; rows ++ )	{
                battleField[columns][rows] = WATER_VALUE;
                originalBattleField[columns][rows] = WATER_VALUE;
            }
        }
    }
    private void updateBattleField() {
        for ( int columns = 0; columns < battleFieldWidth; columns ++ )	{
            for ( int rows = 0; rows < battleFieldHeight; rows ++ )	{
                if ( originalBattleField[columns][rows] >= 0 )
                    battleField[columns][rows] = 0;
            }
        }
    }
    private void readBattleFieldScenario() {
        for ( int index = 0; index <   battleFieldHeight; index ++ )	{
            if ( battleFieldParser.hasNextLine() )	{
                String[] oneRow = battleFieldParser.nextLine().split("\\s+");
                for ( int xPosition = 1; xPosition < battleFieldWidth + 1; xPosition ++ )	{
                    if (! oneRow[xPosition].equals(WATER) ) {
                        String id = oneRow[xPosition].substring(1, oneRow[xPosition].length() );
                        originalBattleField[xPosition-1][index] = Integer.parseInt(id);
                    }
                }
            }
        }
    }
    private void readBattleFieldFile(String fileName) {		// new
        if ( fileName.equals("exit")) {
            System.exit(0);
        }
        int column = 0;
        try {
            battleFieldString = "";
            battleFieldParser = new Scanner(new File(fileName));
            while ( battleFieldParser.hasNextLine() )	{
                battleFieldString += battleFieldParser.nextLine() + lineDelimiter ;
            }

        } catch (FileNotFoundException e) {
            System.out.println("Can't find that file! Try Again.");
        }

    }
    // new
    private void readBattleFieldFromString(String theBattleFieldInStringForm) {

        int column = 0;
        theBattleFieldInStringForm = theBattleFieldInStringForm.replaceAll(lineDelimiter, "\n");
        battleFieldParser = new Scanner(theBattleFieldInStringForm);
        readHeightWidth();
        createBattleField();
        readBattleFieldScenario();
        updateBattleField();

    }
    private int readOneIntValue(Scanner readUserInput, String text) {
        System.out.print(text);
        if ( readUserInput.hasNextInt()	)
            return readUserInput.nextInt();
        else	{
            System.out.println("Can't read next integer - RIP");
            System.exit(1);
        }
        return -1;
    }
    private boolean checkRange(int minValue, int value, int maxValue, String errorMessage) {
        if ( ( minValue <= value) && ( value < maxValue ) )	{
            return true;
        } else	{
            System.out.println("Error: " + errorMessage );
            return false;
        }
    }
    private void readTheOceans() {				// new
        if ( first ) 		{
            sendData(battleFieldString);

            String theOtherOnes =  readData();
            readBattleFieldFromString(theOtherOnes);
        } else	{
            String theOtherOnes =  readData();
            readBattleFieldFromString(theOtherOnes);

            sendData(battleFieldString);
        }
    }
    private void play() {
        readTheOceans();		// new
        Scanner readUserInput  = new Scanner( System.in);
        int row = 0;
        int column = 0;
        int soManyTries = 0;
        while ( isThereAboatLeft()	)	{
            soManyTries++;
            printBattleFieldForPlayer(battleField);
            column = readOneIntValue(readUserInput,
                    "column coordinate (0 <= column < " + battleFieldWidth + "): ");
            row	= readOneIntValue(readUserInput,
                    "row	coordinate (0 <= row	< " + battleFieldHeight + "): ");
            if ( checkRange(0, column, battleFieldWidth, "Column out of range. " + column)	&&
                    checkRange(0, row, battleFieldHeight, "Row out of range. " + row )          )
                if ( originalBattleField[column][row] == WATER_VALUE )	{
                    battleField[column][row] = WATER_HIT_VALUE;
                } else {
                    System.out.println("HIT");
                    removeAllPartsOfBoat(column,row);
                }
            printBattleFieldForPlayer(battleField);
        }
        printBattleFieldForPlayer(battleField);
    }

    private  void sendData(String theData) {		// new
        System.out.println("sendData: ");
        try {
            writeTo.println(theData);
        } catch ( Exception e )	{
            e.printStackTrace();
        }
    }
    private  String  readData() {				// new
        System.out.println("readData: ");
        String rValue = "";
        try {
            rValue = readData;
        } catch ( Exception e )	{
            e.printStackTrace();
        }
        return rValue;
    }
    private void setUpIO()	{

        if ( first )	{
            try {
                byte[] buf = new byte[256];
                DatagramSocket socket = new DatagramSocket(port);


                String sendThis = battleFieldString;
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                while (packet == null) {
                    socket.receive(packet);
                }
                readData = new String(packet.getData());
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                buf = sendThis.getBytes();
                packet = new DatagramPacket(buf, buf.length, address, port);
                System.out.println("Sending to port: " + port );
                System.out.println("Sending    data: " + new String(buf) );
                socket.send(packet);

                socket.close();

            } catch ( Exception e )	{
                e.printStackTrace();
            }
        } else {
            try {

                byte[] buf = new byte[256];
                String sendThis = battleFieldString;
                buf = sendThis.getBytes();
                InetAddress aInetAddress = InetAddress.getByName(hostName);
                DatagramPacket dp = new DatagramPacket(buf, buf.length);
                DatagramSocket socket = new DatagramSocket();
                DatagramPacket packet = new DatagramPacket(buf,
                        buf.length, aInetAddress, port);
                socket.send(packet);

                System.out.println("host: " +  hostName );
                System.out.println("port: " +  port );
                System.out.println("after creation");
                socket.receive(dp);
                System.out.println("received: -" +
                        new String(dp.getData() ) + "-"  );
                socket.close();

            } catch ( Exception e )	{
                e.printStackTrace();
            }
        }
    }
    private void playTheGame(String[] args)	{
        parseArgs(args);
        readBattleFieldFile(ocean);			// new
        setUpIO();
        play();
    }
    public static void main(String[] args) {
        new BattleShipFinal().playTheGame(args);
    }
}