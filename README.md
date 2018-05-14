# mydatastructures
Implementation of LinkedList and Stack

**MyLinkedList**
----------------

 Standard Linked List implementation with the following additional methods.

   a.  swap
        receives two index positions as parameters, and swaps the nodes at
        these positions by changing the links, provided both positions are 
        within the current size

   b.  shift
        receives an integer (positive or negative) and shifts the list this
        many positions forward (if positive) or backward (if negative).  
           1,2,3,4    shifted +2    3,4,1,2
           1,2,3,4    shifted -1    4,1,2,3

   c.  erase 
        receives an index position and number of elements as parameters, and
        removes elements beginning at the index position for the number of 
        elements specified, provided the index position is within the size
        and together with the number of elements does not exceed the size

   d.  insertList
        receives another MyLinkedList and an index position as parameters, and 
        copies the list from the passed list into the list at the specified
        position, provided the index position does not exceed the size.

   e.  main
        add code to the main method to demonstrate each of your methods
  
**MyStack**
----------------

Implementation of Stack that uses an ArrayList with additional method to check for balanced symbols.

**Binary Search Tree**
----------------

 Standard BST implementation with the following additional methods.
 
    a) nodeCount
        Recursively traverses the tree and returns the count of nodes.

    b) isFull 
        Returns true if the tree is full.  A full tree has every node 
          as either a leaf or a parent with two children.

    c) compareStructure 
        Compares the structure of current tree to another tree and returns
          true if they match.

            For example, these two trees have the same structure:
                   5           10
                  / \         /  \
                 3   8       5   15
                / \         / \
               1   4       2   7

    d) equals
        Compares the current tree to another tree and returns true
          if they are identical.

    e) copy
        Creates and returns a new tree that is a copy of the original tree.

    f) mirror
        Creates and returns a new tree that is a mirror image of the original tree.
        For example, for the tree on the left, the tree on the right is returned:
    
            100                 100
           /   \               /   \
          50   150    -->     150  50
         /                           \
        40                           40
         \                           /
         45                         45

    g) isMirror 
        Returns true if the tree is a mirror of the passed tree.

    h) rotateRight
        Performs a single rotation on the node having the passed value.
        If a RotateRight on 100 is performed:

           100                  50
          /   \                /   \
         50   150    -->      40   100
        /                      \     \
       40                      45    150
        \ 
        45
      
    g) rotateLeft 
        As above but left rotation.

    i) printLevels - performs a level-by-level printing of the tree.

    j) main - demonstrate in your main method that all of your new methods work.
