/**
 * @authur M Menze 220034828
 * @version Practical X 2020
 */
package csc2a.Files;


import csc2a.models.GameLevels;

import java.io.*;
import java.util.ArrayList;


public class TextualFile {
   public  static ArrayList<String> readerControls(String fileName){
      ArrayList<String> controls=new ArrayList<>();
       try{
           File file = new File(fileName);
           BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
           String line;
           while ((line=bufferedReader.readLine())!=null){
               controls.add(line);
           }
       }catch (IOException e){
           e.printStackTrace();
       }
       return  controls;
   }
   public static void saveStats(String lineSave){
       try {
           FileWriter writer = new FileWriter("data/game_save.txt");
           writer.write(lineSave+"\n");
           writer.close();
       }catch (IOException e){
           e.printStackTrace();
       }
   }
}
