package org.SetAssociateCache;

import java.util.Arrays;

public class SetAssociativeCacheFactory {
     public static SetAssociativeCache<String, String> CreateStringCache(int setCount, int setSize, String replacementAlgoName) {
        return new SetAssociativeCache<>(setCount, setSize);
    }
   
    public static Object InvokeCacheMethod(String inputLine, SetAssociativeCache<String, String> cacheInstance) {
        String[] callArgs = Arrays.stream(inputLine.split(",", -1)).map(a -> a.trim()).toArray(n -> new String[n]);
        String methodName = callArgs[0].toLowerCase();
  
        switch (methodName) {
            case "get":
                return cacheInstance.get(callArgs[1]);
            case "set":
                cacheInstance.set(callArgs[1], callArgs[2]);
                return null;
            case "containskey":
                return cacheInstance.containsKey(callArgs[1]);
            case "getcount":
                return cacheInstance.getCount();
            
            
            default:
                throw new RuntimeException(String.format("Unknown method name '{%s}'", methodName));
        }
    }
}