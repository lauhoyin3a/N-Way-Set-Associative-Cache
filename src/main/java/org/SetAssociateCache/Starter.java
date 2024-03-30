package org.SetAssociateCache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Starter {

    public final static String LruAlgorithm = "LRUReplacementAlgo";
    public final static String MruAlgorithm = "MRUReplacementAlgo";
    public static void main(String[] args) throws IOException {
        SetAssociativeCacheRunner.parseInput(System.in);
    }
    /**
     * Parses Test Case input to instantiate and invoke a SetAssociativeCache
     *
     * NOTE: You can typically ignore anything in here. Feel free to collapse...
     */
    static class SetAssociativeCacheRunner {
        public static void parseInput(InputStream inputStream) throws IOException {
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputReader);
            String line;
            int lineCount = 0;
            SetAssociativeCache<String, String> cache = null;
            while (!isNullOrEmpty(line = reader.readLine())) {
                lineCount++;
                OutParam<String> replacementAlgoName = new OutParam<>();
                if (lineCount == 1) {
                    cache = createCache(line, replacementAlgoName);
                } else {
                    // All remaining lines invoke instance methods on the SetAssociativeCache
                    Object retValue = SetAssociativeCacheFactory.InvokeCacheMethod(line, cache);
                    // Write the method's return value (if any) to stdout
                    if (retValue != null) {
                        System.out.println(retValue);
                    }
                }
            }
        }
    }
    private static SetAssociativeCache<String, String> createCache(String inputLine, OutParam<String> replacementAlgoName) {
        String[] cacheParams = Arrays.stream(inputLine.split(",")).map(s -> s.trim()).toArray(n -> new String[n]);
        int setCount = Integer.parseInt(cacheParams[0]);
        int setSize = Integer.parseInt(cacheParams[1]);
        replacementAlgoName.value = cacheParams[2];
        return SetAssociativeCacheFactory.CreateStringCache(setCount, setSize, replacementAlgoName.value);
    }

    static class OutParam<T> {
        public T value;
    }
    private static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

}
