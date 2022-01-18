package csc2a.Files;

import java.io.*;

public class BinaryFile {
    public  static String readerBin(File fileHandler){
        String Level = null;
        DataInputStream dataInputStream =null;
        try{
            FileInputStream fileInputStream = new FileInputStream(fileHandler);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            dataInputStream = new DataInputStream(bufferedInputStream);
            Level= dataInputStream.readUTF();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(dataInputStream!=null){
                try {
                    dataInputStream.close();
                }catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        }
        return Level;
    }
}
