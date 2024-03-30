The main purpose of this project is to implement a Set-Associative Cache in Java. 
This cache is a type of cache memory that is faster than direct-mapped cache. 
It uses a combination of both Least Recently Used (LRU) and Most Recently Used (MRU) replacement algorithms to decide which item in a CacheSet to evict when the cache is full.  

To support a custom replacement algorithm, 
you would need to create a new class that implements the IReplacementAlgo interface 
and add a new case to the createReplacementAlgo method in the ReplacementAlgoFactory class.

To run this project, you can start from the Starter.java file as a entry.