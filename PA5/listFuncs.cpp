// Name: Dunxuan Li (Annie)
// USC NetID: dunxuanl
// CSCI 455 PA5
// Spring 2019


#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string &theKey, int theValue) {
    key = theKey;
    value = theValue;
    next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
    key = theKey;
    value = theValue;
    next = n;
}




//*************************************************************************
// put the function definitions for your list functions below

// Insert a new node in the list
// If the target key has been already in the list, insert nothing, and return false
// Else, insert the new node into the first position of the list as a head
bool listInsertFront(ListType &list, std::string target, int val) {

    // Here, if a list is NULL, we will just add new item as the first node
    // ContainsKey() will return false for an empty list
    // So that an empty list will still be able to go into this if statement
    if (!containsKey(list, target)) {
        Node *insertedNode = new Node(target, val, list);
        list = insertedNode;
        return true;
    }
        // If a list has already had target key, then do nothing
        // => since we don't want have duplicated keys
    else {
        return false;
    }
}

// Change a node's value
// If the target key is not in the list, do nothing, and return false
// Else, change the corresponding value to new value

/*bool listChange(ListType &list, std::string target, int newVal) {

    // Create a temp list, since we passed list by reference here
    // But we only want to change target's value
    Node *temp = list;
    if (containsKey(list, target)) {
        while (temp != NULL) {
            if (temp->key == target) {
                temp->value = newVal;
                return true;
            }
            temp = temp->next;
        }
    } else {
        return false;
    }
}*/


bool listRemove(ListType &list, std::string target) {

    // Create two temp pointers here, since we passed the original list by reference here
    // But we only want to change our original list when we remove a target
    Node *curr = list; // Points to current node
    Node *prev = NULL; // Points to previous node (one node before current node)

    if (containsKey(list, target)) {
        while (curr != NULL) {

            // If we want to remove the head of list, move the pointer one unit to right
            if (list->key == target) {
                list = list->next;
                return true;
            }
            // For other cases, we will need to move previous node's pointer to point to current's next node
            if (curr->key == target) {
                prev->next = curr->next;
                curr = NULL;

                return true;
            }
            // move each pointer one unit to right
            prev = curr;
            curr = curr->next;

        }
    }
        // Include the case that a list is empty, then do nothing, and return false
    else {
        return false;
    }
}

// Given a key, find a pointer to its corresponding value
// If the target key is not found in the list or list is empty return NULL
// Else, return a pointer that points to the corresponding value
int *listLookup(ListType list, std::string target) {
    if (containsKey(list, target)) {

        while (list != NULL) {
            if (list->key == target) {
                return &(list->value);
            }
            list = list->next;
        }
    } else {
        return NULL;
    }
}


// Print the whole list
void printList(ListType list) {
    if (list != NULL) {
        while (list != NULL) {
            cout << list->key << " " << list->value << endl;
            list = list->next;
        }
    }
}

// Return the size of list
int listSize(ListType list) {
    int size = 0;

    while (list != NULL) {
        size++;
        list = list->next;
    }

    return size;
}

// A helper function to check if a list has target key
// If a list does not contain the key or a list is empty, return false
// Else, return true
bool containsKey(ListType list, std::string target) {
    if (list != NULL) {

        while (list != NULL) {
            if (list->key == target) {
                return true;
            }
            // Each iteration, move pointer one unit
            list = list->next;
        }
    } else {
        return false;
    }
}