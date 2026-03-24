import java.util.*;

class DNSEntry{

    String ipAddress;
    long expiryTime;

    DNSEntry(String ip,long ttl){

        this.ipAddress = ip;

        this.expiryTime =
                System.currentTimeMillis()+ttl;
    }
}

 class DNSCache {

    HashMap<String,DNSEntry> cache =
            new HashMap<>();

    public String resolve(String domain){

        DNSEntry entry = cache.get(domain);

        if(entry!=null &&
                System.currentTimeMillis()<entry.expiryTime){

            return "Cache HIT → "+entry.ipAddress;
        }

        String newIP = queryDNS(domain);

        cache.put(domain,
                new DNSEntry(newIP,5000));

        return "Cache MISS → "+newIP;
    }

    public String queryDNS(String domain){

        return "142.250."+new Random().nextInt(200)+".1";
    }

    public void cleanup(){

        long now = System.currentTimeMillis();

        cache.entrySet().removeIf(
                e -> e.getValue().expiryTime < now);
    }

    public static void main(String args[]){

        DNSCache dns = new DNSCache();

        System.out.println(dns.resolve("google.com"));

        System.out.println(dns.resolve("google.com"));

        try{
            Thread.sleep(6000);
        }
        catch(Exception e){}

        System.out.println(dns.resolve("google.com"));
    }
}

