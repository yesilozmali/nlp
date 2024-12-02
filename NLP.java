import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NLP {
    private static final int Before_The_Process = 1;
    private static final int After_The_Process = 2; 
    public static void main(String[] args) {
        
        double total_time;
        String []files={("Bilim_is_basinda"),("Bozkirda.txt"),("DENEMELER.txt"),("Degisim.txt"),("grimms-fairy-tales_P1.txt")};
        for(String file:files){
                total_time=0;
            for(int i=1;i<=3;i++){
                System.out.println("\nFile name: "+ file+"\n");
                total_time+=process(file, i);
            }
            System.out.println("\nTotal time =  "+ total_time+" second");
        }      
    }

    public static boolean hasPunctuation(String sentence) {
        
        String[] words  = sentence.split("\\s+");

        for(int i=0;i<words.length-1;i++){
            String word = words[i];
            if (word.endsWith(".") || word.endsWith("?") || word.endsWith("!")|| word.endsWith(":")|| word.endsWith(";")) {
                return true;
            }
        }
        return false; // No punctuation marks next to any pair of words
    }

    public static<K> void incrementValue(Map<K, Integer> map, K key)
    {
        // get the value of the specified key
        Integer count = map.get(key);
        // if the map contains no mapping for the key,map the key with a value of 1
        if (count == null)  map.put(key, 1);

        // else increment the found value by 1
        else  map.put(key, count + 1);
        
    }
   
    public static HashMap<String,Integer> ngrams(int n, String[] str) {
        
        HashMap<String,Integer> ngrams = new HashMap<String, Integer>();
    
        for (int i = 0; i <   str.length - n + 1; i++){
        String group  = str[i];

        if(n!=1){
            for(int j=1;j<n;j++){
                group +=" "+str[i+j];
            }
        }
        if(hasPunctuation(group )&&i>1){
            continue;
        }
        group =replace_words(group , After_The_Process);
        incrementValue(ngrams,group );

      }
    return ngrams;
    }
    public static double process(String file_name,int grams_n){
        
        System.out.println(grams_n+"-grams");
        
        long startTime = System.currentTimeMillis();
        
        String text=readFromFile(file_name);
        text=text.toLowerCase();
        text=replace_words(text, Before_The_Process);
        
        String[] words = text.split("\\s+");
        HashMap<String,Integer> ngrams = ngrams(grams_n, words);
        List<Map.Entry<String, Integer>> list = new LinkedList<>(ngrams.entrySet());

        Collections.sort(list,  Collections.reverseOrder(Map.Entry.comparingByValue()));
        int count=1;
        for (Map.Entry<String, Integer> entry : list) {
            if (count <= 20) {
                System.out.println(" key: '" +entry.getKey() + "'  value: " + entry.getValue());
                count++;
            } 
            else break;
            
        }
        long endTime = System.currentTimeMillis();

        long elapsedTime = endTime - startTime;
        double elapsedTimeSeconds = (double) elapsedTime / 1000;
        System.out.println("running time =  "+elapsedTimeSeconds+" second");
        return elapsedTimeSeconds;
   
    }
  
    private static String replace_words(String text,int state) {
    
        String removeChars = ",’‘-»«";
        String punctuationMarks = ".:;?!";

        String newText = text;
        String charsToReplace = (state == Before_The_Process) ? removeChars : punctuationMarks;

        for (char chr : charsToReplace.toCharArray()) {
            newText = newText.replace(String.valueOf(chr), "");
        }
        return newText;
    }
    public static String readFromFile(String filename) {
        File file = new File(filename);
        FileReader fileReader;
        String readText="";
        try {
            if("grimms-fairy-tales_P1.txt".equals(filename)){
                fileReader = new FileReader(file,Charset.forName("Windows-1252"));
            }
            else{
                fileReader = new FileReader(file,Charset.forName("ISO-8859-9"));
            }
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    readLine = filterBracketContent(readLine);
                    readText += readLine + "\n";
                }
            }
            bufferedReader.close();
        
        } catch (FileNotFoundException ex) {
           ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return readText;

    }
    private static String filterBracketContent(String line) {
        
        StringBuilder filteredLine = new StringBuilder();
        int bracketCount = 0;

        for (char c : line.toCharArray()) {
            if (c == '(') {
                bracketCount++;
            } else if (c == ')') {
                bracketCount--;
            } else if (bracketCount == 0) {
                filteredLine.append(c);
            }
        }
        return filteredLine.toString();
    }
  
}
