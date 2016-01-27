import java.io.*;
import java.util.*;

public class System {
    
    private Scanner sc;
    private String[] args;
    private int people = Integer.parseInt(args[0]);
    private int[] tiempo = new int[people];
    private int[] arriba = new int[people];
    private int[] abajo = new int[people];
    
    public void openFile(){
        try{
            sc = new Scanner(new File("SortedSequences.txt"));
        }
        catch(Exception e){
            System.out.print("Could not find file!");
        }
    }
    public void readFile(){
        while(sc.hasNext()){
            int time = sc.nextInt();
            int floorFrom = sc.nextInt();
            int floorTo = sc.nextInt();
            for(int i = 0; i < people; i++){
                tiempo[i] = time;
                arriba[i] = floorFrom;
                abajo[i] = floorTo;
            }
        }
    }
    public void closeFile(){
        sc.close();
    }
    
    public static void main(String[] args){
        
        System l = new System();
        l.openFile();
        l.readFile();
        l.closeFile();
        
        Algorithm1 first = new Algorithm1();
        
        
    }
}
