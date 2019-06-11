/*  Name: Dunxuan Li (Annie)
 *  USC NetID: dunxuanl
 *  CS 455 Spring 2019
 *
 *  See ecListFuncs.h for specification of each function.
 */


// for NULL
#include <cstdlib>

#include "ecListFuncs.h"
#include <vector>

using namespace std;


int lastIndexOf(ListType list, int val) {
    // If list is null, return -1
    int last = -1;
    int index = 0;

    // Check through the whole linked list
    // If find a new val matches with our target, update our index
    while (list != NULL) {
        if (list->data == val) {
            last = index;
        }

        // If no new match found, go and check next node
        list = list->next;
        index++; // Increase the index by one every time when we move to the next node
    }
    return last;
}


int longestNondecreasingSequence(ListType list) {
    int longestLength = 1;
    int currLength = 1;

    // If list is null, return 0
    if (list == NULL) {
        return 0;
    }

    while (list != NULL) {
        int currVal = list->data;
        list = list->next;

        // Move the pointer to the next node, and compare previous node and current node
        // If current node is greater than previous node, increase length by 1
        if (list != NULL && list->data >= currVal) {
            currLength++;

            // If current length is greater than longest length so far, update longest length
            if (currLength >= longestLength) {
                longestLength = currLength;
            }
        } else {
            if (currLength >= longestLength) {
                longestLength = currLength;
            }
            currLength = 1;
        }
    }

    return longestLength;
}


void removeAdjacentEvens(ListType &list) {
    ListType tempList = list;

    if (tempList != NULL) {
        while (tempList->next != NULL) {

            // Check if current node is even
            // If it is even, check if next node is even
            if (tempList->data % 2 == 0) {

                // Move the pointer one unit to the right until meet an odd node
                while (tempList->next != NULL && tempList->next->data % 2 == 0) {
                    Node *temp = tempList->next;
                    tempList->next = tempList->next->next; // Move the pointer of current node to the next next node
                    delete temp; // Free the memory for the deleted node
                }
                if (tempList->next != NULL) {
                    tempList = tempList->next;
                }
            } else {
                tempList = tempList->next;
            }
        }
    }
}


void mirror(ListType &list) {
    ListType tempList = list;
    ListType lastNode;
    vector<int> nums;

    while (tempList != NULL) {
        nums.push_back(tempList->data);
        if (tempList->next == NULL) {
            lastNode = tempList;
        }
        tempList = tempList->next;
    }
    tempList = lastNode;
    for (int i = nums.size() - 1; i >= 0; i--) {
        tempList->next = new Node(nums[i]);
        tempList = tempList->next;
    }
}

void rotateLeft(ListType &list, int k) {
    ListType tempList = list;
    ListType rotateHead = list;
    ListType lastNode;
    bool kEqualsLast = false; // Check if rotate point is at the end of list
    int index = 1;

    // Rotate only when there is more than one node in LinkedList
    if (tempList != NULL && tempList->next != NULL) {

        while (tempList != NULL) {
            // Store the last node for appending
            if (tempList->next == NULL) {
                lastNode = tempList;
            }

            // If index = k, break the list into two parts
            // Change the head of list
            if (index == k) {
                ListType temp = tempList;
                if (tempList->next != NULL) {
                    list = tempList->next;
                } else {
                    kEqualsLast = true;
                }
                tempList = tempList->next;
                temp->next = NULL;
            } else {
                tempList = tempList->next;
            }
            index++;
        }
        // Case 1: if rotate point is in the middle of list, return original list
        // Case 2: if k = 0, then return original list
        // Case 3: if k does not equal list length, return original list
        // For all other cases, append rotate head to the tail of linkedlist
        if (index > k && k != 0 && kEqualsLast == false) {
            lastNode->next = rotateHead;
        }
    }
}

// 2018 Fall
void dupe(ListType &list) {
    ListType tempList = list;
    while (tempList != NULL) {
        int val = tempList->data;
        ListType nextNode = tempList->next;
        ListType dupeNode = new Node(val, nextNode);
        tempList->next = dupeNode;
        if (nextNode != NULL) {
            tempList = tempList->next->next;
        } else return;
    }
}

// 2018 Spring
int numRuns(ListType list) {
    int num = 0;
    if (list != NULL) {
        while (list != NULL) {
            int length = 0;
            while (list->next != NULL && list->next->data == list->data) {
                list = list->next;
                length++;
            }
            if (length > 0) {
                num++;
            } else {
                list = list->next;
            }
        }
    }
    return num;
}

// 2017 Fall
void removeEveryOther(ListType &list) {
    ListType tempList = list;
    while (tempList != NULL) {
        if (tempList->next != NULL) {
            ListType secondNode = tempList->next;
            tempList->next = tempList->next->next;
            tempList = tempList->next;
            delete secondNode;
        } else {
            tempList = tempList->next;
        }
    }
}

// 2017 Spring
void gut(ListType &list) {
    ListType tempList = list;
    if (tempList != NULL && tempList->next != NULL && tempList->next->next != NULL) {
        while (tempList->next->next != NULL) {
            ListType midNode = tempList->next;
            tempList->next = tempList->next->next;
            delete midNode;
        }
    }
}

// 2016 Spring
void compress(ListType & list){
    ListType tempList = list;
    ListType tempNode;
    while(tempList != NULL){
        while(tempList->next != NULL && tempList->next->data == tempList->data){
            tempNode = tempList->next;
            tempList->next = tempList->next->next;
            delete tempNode;
        }
        tempList = tempList->next;
    }
}

// 2015 Fall
bool allUnique(ListType list){
    ListType tempList;
    // If linkedlist is empty / only one element is in it => return true
    if(list == NULL || list->next == NULL) return true;
    while(list != NULL){
        tempList = list->next;
        while(tempList != NULL){
            if(tempList->data == list->data){
                return false;
            }
            tempList = tempList->next;
        }
        list = list->next;
    }
    return true;
}

// 2014 Spring
ListType fibSeq(int n){
    ListType firstNode = new Node(1);
    ListType tempList = firstNode;
    int f1 = 0, f2 = firstNode->data;
    for(int i = 1; i < n; i++){
        ListType secondNode = new Node(f1+f2);
        tempList->next = secondNode;
        f1 = tempList->data;
        f2 = tempList->next ->data;
        tempList = tempList->next;
    }
    return firstNode;
}