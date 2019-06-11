// Name: Dunxuan Li (Annie)
// USC NetID: dunxuanl
// CS 455 PA5
// Spring 2019

// pa5list.cpp
// a program to test the linked list code necessary for a hash table chain

// You are not required to submit this program for pa5.

// We gave you this starter file for it so you don't have to figure
// out the #include stuff.  The code that's being tested will be in
// listFuncs.cpp, which uses the header file listFuncs.h

// The pa5 Makefile includes a rule that compiles these two modules
// into one executable.

#include <iostream>
#include <string>
#include <cassert>

using namespace std;

#include "listFuncs.h"





int main() {

   ListType node1 = new Node("Annie", 100);
   ListType node2 = new Node("Miles", 90);
   ListType node3 = new Node("Jack", 59);
   ListType node4 = new Node("Kate", 0);

   node1->next = node2;
   node2->next = node3;
   node3->next = node4;
   ListType list = node1;

   // Case 0: original list: print the list
   printList(list);
   // Case 1: return the size of a list
   cout << "Size of linked list is " << listSize(list) << endl;
   cout << endl;

   // Case 2: look up an element
   cout << "In the list: Annie's grade is " << listLookup(list, "Annie") << endl;
   cout << "Not in the list: Kay's grade is " << listLookup(list, "Kay") << endl;
   cout << endl;

   // Case 3: change an element
   cout << "In the list: Change Mile's grade to 100" << endl;
   listChange(list, "Miles", 100);
   printList(list);
   cout << endl;

   cout << "Not in the list: Change Michael's (does not exist) grade to 100" << endl;
   listChange(list, "Micheal", 100);
   printList(list);
   cout << endl;

   // Case 4: insert a new node
   cout << " --- Insert Bob ---  " << endl;
   listInsertFront(list, "Bob", 200);
   printList(list);
   cout << "Size of linked list is " << listSize(list) << endl;
   cout << endl;

   // Case 5: remove a node
   cout << "Remove Kate" << endl;
   cout << "Has Kate been removed? "  << listRemove(list, "Kate") << endl;
   printList(list);
   cout << endl;

   cout << "Remove Bob" << endl;
   cout << "Has Bob been removed? "  << listRemove(list, "Bob") << endl;
   printList(list);
   cout << endl;

   cout << "Has Annie been removed? "  << listRemove(list, "Annie") << endl;
   cout << "Has JAck been removed? "  << listRemove(list, "Jack") << endl;
   cout << "Has Miles been removed? "  << listRemove(list, "Miles") << endl;\
   printList(list);

   listRemove(list, "Miles");
   printList(list);

   listInsertFront(list, "Annie", 100);
   printList(list);
   return 0;
}
