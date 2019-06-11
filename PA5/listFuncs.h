// Name: Dunxuan Li (Annie)
// USC NetID: dunxuanl
// CSCI 455 PA5
// Spring 2019


//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.

// Note2: it's good practice to not put "using" statement in *header* files.  Thus
// here, things from std libary appear as, for example, std::string

#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H


struct Node {
    std::string key;
    int value;

    Node *next;

    Node(const std::string &theKey, int theValue);

    Node(const std::string &theKey, int theValue, Node *n);
};


typedef Node *ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.

// Insert target key into list.
// If the key has already been in the list, return false, and don't do the insert
// Else, do the insertion, and return true
bool listInsertFront(ListType &list, std::string target, int val); // Because we will make change on list, we pass by reference here

// Change the value for a target key.
// If the key is not present, return false
// Else, change the value to the new value
bool listChange(ListType &list, std::string target, int newVal); // Because we will make change on list, we pass by reference here

// Remove a target key
// If the key is not in the list, return false
// Else, remove that key from list, and return true
bool listRemove(ListType &list, std::string target); // Because we will make change on list, we pass by reference here

// Look up the target key and return a pointer points to address of corresponding value
// If the key is not in the list or the list is empty, return NULL
// Else, return address of corresponding value
int * listLookup(ListType list, std::string target);


// Print out all entries in list (key + value)
void printList(ListType list);

// Print out the number of entries in a list
int listSize(ListType list);

// A helper function that checks if a list contains target key
// If does not, return false
// Else, return true
bool containsKey(ListType list, std::string target);



// keep the following line at the end of the file
#endif
