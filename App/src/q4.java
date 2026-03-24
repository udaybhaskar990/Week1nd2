import java.util.*;

 class PlagiarismDetector {

    HashMap<String, Set<String>> index =
            new HashMap<>();

    int N = 3;

    public List<String> getNGrams(String text){

        List<String> grams =
                new ArrayList<>();

        String words[] = text.split(" ");

        for(int i=0;i<=words.length-N;i++){

            String gram = "";

            for(int j=0;j<N;j++){

                gram += words[i+j]+" ";
            }

            grams.add(gram.trim());
        }

        return grams;
    }

    public void addDocument(String docId,String text){

        List<String> grams = getNGrams(text);

        for(String gram:grams){

            index.putIfAbsent(gram,new HashSet<>());

            index.get(gram).add(docId);
        }
    }

    public double checkSimilarity(String text){

        List<String> grams = getNGrams(text);

        int matches = 0;

        for(String gram:grams){

            if(index.containsKey(gram))
                matches++;
        }

        return (matches*100.0)/grams.size();
    }

    public static void main(String args[]){

        PlagiarismDetector pd =
                new PlagiarismDetector();

        pd.addDocument("doc1",
                "the cat sat on the mat");

        pd.addDocument("doc2",
                "java is a programming language");

        double sim = pd.checkSimilarity(
                "the cat sat on floor");

        System.out.println(
                "Similarity: "+sim+"%");
    }
}
