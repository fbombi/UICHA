package utilities;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Data {

    public static Map<String, String> data = new HashMap<String, String>();
    public static Map<String, String> xpaths = new HashMap<String, String>();

    public static void dataRead(String path,int option) {
        File file = new File(path);
        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] parts = line.split("~");
            if (parts.length != 0) {
                if (option == 1) {
                    data.put(parts[0], parts[1]);
                }
                else{
                    xpaths.put(parts[0], parts[1]);

                }

            }
        }
    }
}

