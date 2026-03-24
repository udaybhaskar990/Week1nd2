import java.util.*;

class MultiLevelCache {

    LinkedHashMap<String,String> L1 =
            new LinkedHashMap<>(5,0.75f,true){

                protected boolean removeEldestEntry(
                        Map.Entry eldest){

                    return size()>5;
                }
            };

    HashMap<String,String> L2 =
            new HashMap<>();

    HashMap<String,String> database =
            new HashMap<>();

    public MultiLevelCache(){

        database.put("video1","Video Data 1");

        database.put("video2","Video Data 2");

        database.put("video3","Video Data 3");
    }

    public String getVideo(String id){

        if(L1.containsKey(id)){

            return "L1 HIT → "+L1.get(id);
        }

        if(L2.containsKey(id)){

            String data=L2.get(id);

            L1.put(id,data);

            return "L2 HIT → promoted to L1";
        }

        if(database.containsKey(id)){

            String data=database.get(id);

            L2.put(id,data);

            return "DB HIT → added to L2";
        }

        return "Video not found";
    }

    public static void main(String args[]){

        MultiLevelCache mc =
                new MultiLevelCache();

        System.out.println(mc.getVideo("video1"));

        System.out.println(mc.getVideo("video1"));

        System.out.println(mc.getVideo("video2"));

    }
}
