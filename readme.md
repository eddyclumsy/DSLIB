# Data structures

The course has its own Data Structures Library with an implementation of the main data structures (src/main/java/**) and a suite of unit test sets (src/test/java/*). In this way, we can analyze internally how they are implemented, and make improvements or variations. 

Below we list the most used adts in this subject:

* uoc.ds.adt.sequential
  * List, LinkedList
  * Queue, QueueArrayImpl
  * Stack, StackArrayImpl
  * DictionaryArrayImpl, DictionaryLinkedListImpl

* uoc.ds.adt.nonlinear
  * PriorityQueue
  * Dictionary
  * DictionaryAVLImpl
  * HasTable
  * Tree, BinaryTree, AVLTree

* uoc.ds.adt.nonlinear.graphs
  * DirectedGraphImp
  * UnDirectedGraphImpl
  * ...

* uoc.ds.adt.nonlinear.algorithms
  * HeapSort
  * MergeSort
  * MinimumPaths
  * SortingAlgorithm

We strongly recommend using the maven tool to manage the project
```
mvn compile
mvn test
mvn verify
mvn package
```