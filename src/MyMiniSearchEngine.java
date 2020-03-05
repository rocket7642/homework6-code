import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;

public class MyMiniSearchEngine {
    // default solution. OK to change.
    // do not change the signature of index()
    private Map<String, List<List<Integer>>> indexes;

    // disable default constructor
    private MyMiniSearchEngine() {
    }

    public MyMiniSearchEngine(List<String> documents) {
        index(documents);
    }

    // each item in the List is considered a document.
    // assume documents only contain alphabetical words separated by white spaces.
    private void index(List<String> texts) {

        for(int f = 0; f < texts.size(); f++){
            Map<String, List<Integer>> catagories = new HashMap<String, List<Integer>>();
            String temp[] = texts.get(f).split(" ");

            List<List<Integer>> input = new ArrayList<List<Integer>>();
            for(int i = 0; i < temp.length; i++){
                List<Integer> holder = new ArrayList<Integer>();
                if(!(catagories.containsKey(temp[i]))){
                    catagories.put(temp[i], holder);
                }
                if(catagories.containsKey(temp[i])){
                    catagories.get(temp[i]).add(i);
                }
            }

            for(int i = 0; i < temp.length; i++){
                System.out.println(catagories.get(temp[i]) + ":" + temp[i]);
            }
            System.out.println("---");
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
