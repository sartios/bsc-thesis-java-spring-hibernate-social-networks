
package proof;
import java.util.ArrayList;
import java.util.List;
import java.io.*;


public class FileHandler {

 
    public static String readFile (String filename) throws IOException{

           FileInputStream file = new FileInputStream(filename);
           BufferedReader br = new BufferedReader(new InputStreamReader(file));
           String strLine,content="";

           while ((strLine = br.readLine()) != null)   {
                content=content+strLine;

            }

            file.close();
        return content;
    }
    public static String[] getTableFromFile(String filename) throws IOException{
           FileInputStream file = new FileInputStream(filename);
           BufferedReader br = new BufferedReader(new InputStreamReader(file));
           String strLine;
           
           List<String> lineList = new ArrayList();

           while ((strLine = br.readLine()) != null)   {
               lineList.add(strLine);

            }
          String[] lineTable = lineList.toArray(new String[0]);
           return lineTable;
    }
        public static List<String> getListFromFile(String filename) throws IOException{
           FileInputStream file = new FileInputStream(filename);
           BufferedReader br = new BufferedReader(new InputStreamReader(file));
           String strLine;

           List<String> lineList = new ArrayList();

           while ((strLine = br.readLine()) != null)   {
               lineList.add(strLine);

            }

           return lineList;
    }


    public static void writeFile (String filename,String content) throws IOException{
            FileWriter fstream = new FileWriter(filename);
            BufferedWriter fileBuffer = new BufferedWriter(fstream);
            fileBuffer.write(content);
            fileBuffer.close();

    }
        public static void writeFile (String filename,String[] content) throws IOException{
            FileWriter fstream = new FileWriter(filename);
            BufferedWriter fileBuffer = new BufferedWriter(fstream);
           for(int i=0;i<content.length;i++){
            fileBuffer.append(content[i]);
            fileBuffer.newLine();
           }

            fileBuffer.close();

    }
            public static void writeFile (String filename,List<String> content) throws IOException{
            FileWriter fstream = new FileWriter(filename);
            BufferedWriter fileBuffer = new BufferedWriter(fstream);
           for(int i=0;i<content.size();i++){
            fileBuffer.append(content.get(i));
            fileBuffer.newLine();
           }

            fileBuffer.close();

    }


          
    public static void appendFile (String filename,String content) throws IOException{
            FileWriter fstream = new FileWriter(filename,true);
            BufferedWriter fileBuffer = new BufferedWriter(fstream);
           fstream.append(content);
            fileBuffer.newLine();
                    
            fileBuffer.close();

    }



}
