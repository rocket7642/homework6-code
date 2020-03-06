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
        
        for(Map.Entry entry : indexes2.entrySet()){
            System.out.print(entry.getKey() + ":");
            Map<Integer, List<Integer>> temp = (Map)entry.getValue();
            for(Map.Entry entry2 : temp.entrySet()){
                System.out.println(entry2.getKey()+ ":" + entry2.getValue());
            }
        }
    }

    // search(key) return all the document ids where the given key phrase appears.
    // key phrase can have one or two words in English alphabetic characters.
    // return an empty list if search() finds no match in all documents.
    public List<Integer> search(String keyPhrase) {
        // homework
        return new ArrayList<>(); // place holder
    }
}
