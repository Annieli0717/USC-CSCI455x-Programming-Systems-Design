// ecListFuncs.h
// CSCI 455 Spring 2019, Extra Credit assignment
// You do not need to modify or submit this file.

#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H


//*************************************************************************
// Node type used for lists
struct Node {
   int data;
   Node *next;

   Node(int item);
   Node(int item, Node *n);

};

typedef Node * ListType;

//*************************************************************************
// Utility functions used by the test driver code
//    (these are defined in ectest.cpp)

// inserts val at the front of list
// PRE: list is a well-formed list
void insertFront(Node *&list, int val);

// makes list into an empty list
//   reclaiming memory used by nodes 
// PRE: list is a well-formed list
// POST: list' is NULL
void clearList(Node * &list);

// prints list elements, space separated, ending with '\n'
// prints empty list as "<empty>"
// PRE: list is a well-formed list
void printList(Node *list);



//*************************************************************************
// Functions you need to write for this assignment:
//   (implementations go in ecListFuncs.cpp)



/*
 * lastIndexOf
 *
 * PRE: list is a well-formed list.
 *
 * returns index of last occurence of val in the list;
 *   -1 if val appears nowhere in the list.  Count indices
 *   starting from 0, as we do with arrays.
 *
 *  Example1: list = (3 7 5), val = 7             returns 1
 *  Example2: list = (3 7 5 7), val = 7           returns 3
 *  Example3: list = (3 4 5), val = 8             returns -1
 *  Example4: list = (3 9 5 4 9 2 9 9 4 5), 
 *                                    val = 9     returns 7
 *  Example5: list = (), val = 8                  returns -1
 */
int lastIndexOf(ListType list, int val);



/*
 * longestNondecreasingSequence
 *
 * PRE: list is a well-formed list.
 *
 * returns length of the longest nondecreasing sequence in the list
 *
 *  Example1: list = (3 7 5)                returns 2 (sequence: 3, 7)
 *  Example2: list = (3 7 5 7 7 9)          returns 4 (sequence: 5, 7, 7, 9)
 *  Example3: list = (5 4 3)                returns 1 (each value is a sequence of length 1)
 *  Example3: list = (3)                    returns 1
 *  Example4: list = (3 9 5 4 9 2 9 4 5)    returns 2 (sequence: there are four 
 *                                                           sequences of length 2)
 *  Example5: list = ()                     returns 0
 */
int longestNondecreasingSequence(ListType list);



/*
 * removeAdjacentEvens
 *
 * PRE: list is a well-formed list.
 *
 * removes all even numbers that immediately follow an even number in the list
 *
 * Examples:
 *  list before call  list after call
 *    ()                ()
 *    (6 2 5 2 8 4 3)   (6 5 2 3)
 *    (4 2 5 1)         (4 5 1)
 *    (5 3 7)           (5 3 7)
 *    (2 3 4 7)         (2 3 4 7)
 *    (2 6 4)           (2)
 */
void removeAdjacentEvens(ListType & list);



/* 
 * mirror
 *
 * PRE: list is a well-formed list
 * 
 * modifies list so it starts with the same values as before, but
 * those elements are now followed by the same values in reverse
 * order.  Thus the updated list is twice as long as it was before the call.
 *
 * Examples:
 *  list before call  list after call to mirror
 *    ()                ()
 *    (6 5 2 3)         (6 5 2 3 3 2 5 6)
 *    (1 2 3)           (1 2 3 3 2 1)
 *    (3)               (3 3)
 */
void mirror(ListType & list);



/*
 * rotateLeft
 *
 * PRE: list is a well formed list
 *
 * rotates the list left by k spots.  The rotation moves nodes at the
 * front of the list to the back of the list, otherwise maintaining
 * the relative order of nodes.  If k <= 0 or k >= list length, list
 * remains unchanged.  
 * Note: this function does not create or destroy any nodes, nor
 * change any data values in nodes: just relinks the list in a
 * different way.
 *
 * Examples:
 *    list          k       list
 *   before call           after call
 *   (1 2 3 4 5)    1      (2 3 4 5 1)
 *   (1 2 3 4 5)    2      (3 4 5 1 2)
 *   (1 2 3 4 5)    3      (4 5 1 2 3)
 *   (1 2 3 4 5)    5      (1 2 3 4 5)
 *   (1 2 3 4 5)    32     (1 2 3 4 5)
 *   (1 2 3 4 5)    0      (1 2 3 4 5)
 *   (3)           any     (3)
 *   ()            any     ()
 */
void rotateLeft(ListType & list, int k);

//*************************************************************************



// 2018 Fall
void dupe(ListType & list);

// 2018 Spring
int numRuns(ListType list);

// 2017 Fall
void removeEveryOther(ListType & list);

// 2017 Spring
void gut(ListType & list);

// 2016 Spring
void compress(ListType & list);

// 2015 Fall
bool allUnique(ListType list);

// 2014 Spring
ListType fibSeq(int n);

#endif
