
package proof;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

/**
 * This class reads and writes files
 */
public class FileHandler {

    /**
     * Reads from file
     * @param filename
     * @return
     * @throws IOException
     */
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

    /**
     * Puts a file in an array, where every row is a line
     * @param filename
     * @return array of the lines
     * @throws IOException
     */
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

    /**
     * Puts a file in an list, where each node is a line
     * @param filename
     * @return list of the lines
     * @throws IOException
     */
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

    /**
     * Writes into given file the content
     * @param filename
     * @param content
     * @throws IOException
     */
    public static void writeFile (String filename,String content) throws IOException{
            FileWriter fstream = new FileWriter(filename);
            BufferedWriter fileBuffer = new BufferedWriter(fstream);
            fileBuffer.write(content);
            fileBuffer.close();

    }

    /**
     * Writes into file. Gets an array and for each row creates a new line
     * @param filename
     * @param content
     * @throws IOException
     */
        public static void writeFile (String filename,String[] content) throws IOException{
            FileWriter fstream = new FileWriter(filename);
            BufferedWriter fileBuffer = new BufferedWriter(fstream);
            for(int i=0;i<content.length;i++){
                fileBuffer.append(content[i]);
                fileBuffer.newLine();
           }

            fileBuffer.close();

    }
    /**
     * Writes into file. Gets a list and for each node creates a line
     * @param filename
     * @param content
     * @throws IOException
     */
        public static void writeFile (String filename,List<String> content) throws IOException{
        FileWriter fstream = new FileWriter(filename);
        BufferedWriter fileBuffer = new BufferedWriter(fstream);
        for(int i=0;i<content.size();i++){
            fileBuffer.append(content.get(i));
            fileBuffer.newLine();
       }

        fileBuffer.close();

    }


        /**
         * Append new content to file.
         * @param filename
         * @param content
         * @throws IOException
         */
    public static void appendFile (String filename,String content) throws IOException{
        FileWriter fstream = new FileWriter(filename,true);
        BufferedWriter fileBuffer = new BufferedWriter(fstream);
        fstream.append(content);
        fileBuffer.newLine();
        fileBuffer.close();
    }



}
