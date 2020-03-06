import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;

public class MyMiniSearchEngine {
    // default solution. OK to change.
    // do not change the signature of index()
    private Map<String, List<List<Integer>>> indexes;
    private Map<String, Map<Integer, List<Integer>>> indexes2;
    private int counter;

    // disable default constructor
    private MyMiniSearchEngine() {
    }

    public MyMiniSearchEngine(List<String> documents) {
        counter = documents.size();
        index(documents);
    }

    // each item in the List is considered a document.
    // assume documents only contain alphabetical words separated by white spaces.
    private void index(List<String> texts) {
        indexes2 = new HashMap<String, Map<Integer, List<Integer>>>();
        for(int f = 0; f < texts.size(); f++){
            Map<String, List<Integer>> catagories = new HashMap<String, List<Integer>>();
            String temp[] = texts.get(f).split(" ");

            List<List<Integer>> input = new ArrayList<List<Integer>>();
            for(int i = 0; i < temp.length; i++){
                List<Integer> holder = new ArrayList<Integer>();
                if(!(catagories.containsKey(temp[i]))){
                    catagories.put(temp[i], new ArrayList<Integer>());
                }
                if(!(indexes2.containsKey(temp[i]))){
                    indexes2.put(temp[i], new HashMap<Integer, List<Integer>>());
                }
                if(catagories.containsKey(temp[i])){
                    catagories.get(temp[i]).add(i);
                }
            }

            for(int i = 0; i < temp.length; i++){
                if(indexes2.containsKey(temp[i])){
                    indexes2.get(temp[i]).put(f,catagories.get(temp[i]));
                }
                //System.out.println(catagories.get(temp[i]) + ":" + temp[i]);
            }
            //System.out.println("---");
        }

        //System.out.println(indexes2.entrySet());
        /*for(Map.Entry entry : indexes2.entrySet()){
        System.out.print(entry.getKey() + ":");
        Map<Integer, List<Integer>> temp = (Map)entry.getValue();
        for(Map.Entry entry2 : temp.entrySet()){
        System.out.println(entry2.getKey()+ ":" + entry2.getValue());
        }
        }*/
    }

    // search(key) return all the document ids where the given key phrase appears.
    // key phrase can have one or two words in English alphabetic characters.
    // return an empty list if search() finds no match in all documents.
    public List<Integer> search(String keyPhrase) {
        String temp[] = keyPhrase.split(" ");
        
        
        if(temp.length == 1 && indexes2.containsKey(temp[0])){
            Map<Integer, List<Integer>> holder = (Map)indexes2.get(temp[0]);
            List<Integer> output = new ArrayList<Integer>();
            for(Map.Entry entry : holder.entrySet()){
                output.add((int)entry.getKey());
            }
            return output;
        }

        List<Integer> Fail = new ArrayList<Integer>();
        Fail.add(-1);
        
        for(int i = 0; i < temp.length; i++){
            temp[i] = temp[i].toLowerCase();
        }
        

        for(String trans : temp){
            if(!(indexes2.containsKey(trans))){
                return Fail;
            }
        }

        List<Integer> result = new ArrayList<Integer>();
        int[][] documents = new int[temp.length][counter];

        for(int i = 0; i < temp.length; i++){
            Map<Integer, List<Integer>> holder = (Map)indexes2.get(temp[i]);
            for(Map.Entry entry : holder.entrySet()){
                documents[i][(int)entry.getKey()] = (int)entry.getKey()+1;

            }

        }
        int[] sortDoc = new int[counter];
        for(int i = 0; i < sortDoc.length; i++){
            sortDoc[i] = i+1;
        }
        for(int f = 0; f < documents.length; f++){
            for(int j = 0; j < documents[0].length; j++){
                if(documents[f][j] != sortDoc[j]){
                    sortDoc[j] = -1;
                }
            }
        }

        Map<Integer, List<Integer>> checker = new HashMap<Integer, List<Integer>>();
        for(int i = 0; i < temp.length; i++){
            Map<Integer, List<Integer>> holder = (Map)indexes2.get(temp[i]);
            int count = 0;
            for(int j = 0; j < sortDoc.length; j++){
                if(sortDoc[j] != -1){
                    List<Integer> output = holder.get(sortDoc[j]-1);
                    checker.put(j+(i*counter),output);

                }
            }
        }

        /*
         * The comparisons work on 0 to 2 words, but not above, im sorry.
         */

        for(int i = 0; i < temp.length; i++){
            for(int j = 0; j < sortDoc.length; j++){
                if(i > 0){
                    List<Integer> output = checker.get(j+(i*counter));
                    if(output != null){
                        for(int f = 0; f < output.size(); f++){
                            output.set(f, output.get(f)-1);
                        }
                        checker.remove(j+(i*counter));
                        checker.put(j+(i*counter),output);
                    }
                }
            }
        }


        for(int i = 0; i< temp.length; i++){
            for(int j = 0; j < 6; j++){
                if(checker.get(j) != null && checker.get(j+(i*6)) != null){
                    if(checker.get(j).equals(checker.get(j+(i*6))) && i > 0){

                        result.add(j);
                    }
                }
            }

        }

        /*
        for(int i = 0; i < temp.length; i++){
        for(int j = 0; j < pos[i].length; j++){
        if(i > 0){
        if(pos[0][j] == pos[i][j]){
        result.add(i);
        }
        else if(pos[0][j] != pos[i][j]){

        }
        else{

        }
        }
        }
        }*/

        return result;
    }
}
