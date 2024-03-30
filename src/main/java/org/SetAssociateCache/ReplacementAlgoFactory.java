package org.SetAssociateCache;

public class ReplacementAlgoFactory {
    IReplacementAlgo createReplacementAlgo(String replacementAlgoName) {
        switch (replacementAlgoName) {
            case "LruAlgorithm":
                return new LRUReplacementAlgo();
            case "MruAlgorithm":
                return new MRUReplacementAlgo();
            default:
                // If you want to test other replacement algos, add them to the switch statement here...
                throw new RuntimeException(String.format("Unknown replacement algo '%s'", replacementAlgoName));
        }
    }
}