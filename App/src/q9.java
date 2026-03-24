import java.util.*;

class Transaction{

    int id;
    int amount;
    String merchant;

    Transaction(int id,int amount,String merchant){

        this.id=id;

        this.amount=amount;

        this.merchant=merchant;
    }
}

 class FinancialTransactions {

    public static void findTwoSum(
            List<Transaction> list,
            int target){

        HashMap<Integer,Transaction> map =
                new HashMap<>();

        for(Transaction t:list){

            int complement =
                    target - t.amount;

            if(map.containsKey(complement)){

                System.out.println(
                        "Pair found: "+
                                t.id+" and "+
                                map.get(complement).id);

                return;
            }

            map.put(t.amount,t);
        }

        System.out.println("No pair found");
    }

    public static void detectDuplicates(
            List<Transaction> list){

        HashMap<String,
                List<Transaction>> map =
                new HashMap<>();

        for(Transaction t:list){

            String key =
                    t.amount+"-"+t.merchant;

            map.putIfAbsent(key,
                    new ArrayList<>());

            map.get(key).add(t);
        }

        for(String key:map.keySet()){

            if(map.get(key).size()>1){

                System.out.println(
                        "Duplicate found: "+key);
            }
        }
    }

    public static void main(String args[]){

        List<Transaction> list =
                new ArrayList<>();

        list.add(new Transaction(1,500,"StoreA"));

        list.add(new Transaction(2,300,"StoreB"));

        list.add(new Transaction(3,200,"StoreC"));

        list.add(new Transaction(4,500,"StoreA"));

        findTwoSum(list,500);

        detectDuplicates(list);
    }
}