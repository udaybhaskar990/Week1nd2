import java.util.*;

class TokenBucket{

    int tokens;
    int maxTokens;
    long lastRefillTime;
    int refillRate;

    TokenBucket(int max,int rate){

        this.maxTokens = max;

        this.tokens = max;

        this.refillRate = rate;

        this.lastRefillTime =
                System.currentTimeMillis();
    }
}

 class RateLimiter {

    HashMap<String,TokenBucket> clients =
            new HashMap<>();

    public boolean allowRequest(String clientId){

        clients.putIfAbsent(clientId,
                new TokenBucket(5,1));

        TokenBucket bucket =
                clients.get(clientId);

        refill(bucket);

        if(bucket.tokens>0){

            bucket.tokens--;

            return true;
        }

        return false;
    }

    public void refill(TokenBucket bucket){

        long now =
                System.currentTimeMillis();

        long diff =
                (now-bucket.lastRefillTime)/1000;

        int tokensToAdd =
                (int)diff*bucket.refillRate;

        if(tokensToAdd>0){

            bucket.tokens =
                    Math.min(bucket.maxTokens,
                            bucket.tokens+tokensToAdd);

            bucket.lastRefillTime = now;
        }
    }

    public static void main(String args[]){

        RateLimiter rl =
                new RateLimiter();

        for(int i=1;i<=7;i++){

            System.out.println(
                    rl.allowRequest("client1"));
        }
    }
}
