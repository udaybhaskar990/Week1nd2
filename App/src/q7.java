import java.util.*;

 class AutoCompleteSystem {

    HashMap<String,Integer> frequency =
            new HashMap<>();

    public void addQuery(String query){

        frequency.put(query,
                frequency.getOrDefault(query,0)+1);
    }

    public List<String> search(String prefix){

        List<String> result =
                new ArrayList<>();

        for(String query:frequency.keySet()){

            if(query.startsWith(prefix)){

                result.add(query);
            }
        }

        result.sort((a,b)->
                frequency.get(b)-frequency.get(a));

        if(result.size()>5)
            return result.subList(0,5);

        return result;
    }

    public static void main(String args[]){

        AutoCompleteSystem ac =
                new AutoCompleteSystem();

        ac.addQuery("java");

        ac.addQuery("javascript");

        ac.addQuery("java tutorial");

        ac.addQuery("python");

        ac.addQuery("java");

        System.out.println(
                ac.search("jav"));
    }
}

