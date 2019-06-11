// ectest.cpp
// CSCI 455 Spring 2019, Extra Credit assignment

// Note: this uses separate compilation.  You put your code in ecListFuncs.cpp
// Code in this file calls those funcs.
// You do not need to modify or submit this file.


#include <iostream>

// for istringstream
#include <sstream>

#include "ecListFuncs.h"

using namespace std;

int promptForInt(string prompt);

void buildList(ListType &theList);

void doHelp();

void doLastIndexOf(ListType list);

bool readAndDispatchCommand(ListType &theList);

void dupe(ListType &theList);

int numRuns(ListType list);

void removeEveryOther(ListType &list);

void gut(ListType &list);

void compress(ListType &list);

bool allUnique(ListType list);

ListType fibSeq(int n);

//*************************************************************************


/* a little test program */

int main() {

    bool keepgoing = true;

    ListType theList = NULL;

    doHelp();

    do {

        keepgoing = readAndDispatchCommand(theList);

        cout << "The list: ";
        printList(theList);

    } while (keepgoing);

    return 0;
}


/*
 * reads a command and executes it.
 * The command execution updates and/or uses theList, thus it's passed
 * by reference here.
 * Returns false iff the command entered was q (quit)
 */
bool readAndDispatchCommand(ListType &theList) {

    char cmd;

    cout << "\nPlease enter a command [b, p, h, q, d, n, r, g, c, u, s, f]: ";
    cin >> cmd;

    if (cmd == 'b') {
        clearList(theList);
        cout << "Please enter a sequence of ints followed by <nl> (e.g:1 2 3): ";
        buildList(theList);
    }
        /*else if (cmd == 'i') {
           int val = promptForInt ("Value to insert");
           insertFront (theList, val);
        }
        else if (cmd == 'r') {
           int num = promptForInt("How many spots to rotate left by");
           rotateLeft(theList, num);
        }
        else if (cmd == 'l') {
           doLastIndexOf(theList);
        }
        else if (cmd == 's') {
           cout << "Longest nondecreasing sequence: ";

           cout << longestNondecreasingSequence(theList) << endl;
        }
        else if (cmd == 'e') {
           removeAdjacentEvens(theList);
        }
        else if (cmd == 'm') {
           mirror(theList);
        }*/
    else if (cmd == 'p') {
        cout << "Print list: " << endl;
        printList(theList);
        cout << endl;
    } else if (cmd == 'd') {
        dupe(theList);
    } else if (cmd == 'n') {
        cout << "Number of run is " << numRuns(theList) << endl;
    } else if (cmd == 'r') {
        removeEveryOther(theList);
    } else if (cmd == 'g') {
        gut(theList);
    } else if (cmd == 'c') {
        compress(theList);
    } else if (cmd == 'u') {
        if (allUnique(theList) == true) {
            cout << "All numbers are unique." << endl;
        } else {
            cout << "Not all numbers are unique" << endl;
        }
    } else if (cmd == 'f') {
        int n = promptForInt("Fib sequence number n");
        theList = fibSeq(n);
    } else if (cmd == 'q') {
        return false;
    } else {
        doHelp();
    }

    return true;

}


/*
 * promptForInt: Prompts for and reads an integer.
 */
int promptForInt(string prompt) {
    int value;

    cout << prompt << ": ";
    cin >> value;
    return value;
}



// build the list in forward order
// old value of theList is destroyed.
// (needs to be O(n))

// Note: this code uses istringstream: this is the analogous feature
//     to using a Scanner on a String in Java.
void buildList(ListType &theList) {

    string lineStr;

    getline(cin, lineStr);  // consume rest of previous line

    getline(cin, lineStr);

    if (lineStr.empty()) {
        theList = NULL;
        return;
    }

    istringstream istr(lineStr);

    Node *last = NULL;
    int i;

    istr >> i;   // first one is a special case
    theList = new Node(i);
    last = theList;

    while (istr >> i) { // comes out false if we reach end of string (i.e., eoln)
        last->next = new Node(i);
        last = last->next;
    }

}


void doHelp() {
    cout << "Valid commands are" << endl;
    cout << "         b(uild new list), i(nsert in front), r(otate left), l(ast index of), "
         << endl;
    cout << "         s(longest nondecreasing sequence), e(remove adjacent evens), " << endl;
    cout << "         m(irror list), p(rint), h(elp), q(uit)." << endl;
}


void doLastIndexOf(ListType list) {

    int num = promptForInt("Value to get last index of");
    int loc = lastIndexOf(list, num);
    cout << "Last index of " << num << " in list is " << loc << endl;
}





//*****************************************************************
// utility list funcs and Node methods
// (Note: prototypes for these functions are in ecListFuncs.h)

void insertFront(ListType &list, int val) {
    list = new Node(val, list);
}


void printList(ListType list) {

    if (list == NULL) {
        cout << "<empty>";
    }

    Node *p = list;
    while (p != NULL) {
        cout << p->data << " ";
        p = p->next;
    }
    cout << endl;
}


void clearList(ListType &list) {

    Node *rest = list;

    while (list != NULL) {
        rest = list->next;  // rest is all but the first element
        delete list;  // reclaims one node only
        list = rest;
    }

}

Node::Node(int item) {
    data = item;
    next = NULL;
}

Node::Node(int item, Node *n) {
    data = item;
    next = n;
}
