// Name: Dunxuan Li (Annie)
// USC NetID: dunxuanl
// CSCI 455 PA5
// Spring 2019

// Table.cpp  Table class implementation


#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>

using namespace std;


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************

// create an empty table, i.e., one where numEntries() is 0
// (Underlying hash table is HASH_SIZE.)
Table::Table() {
    hashSize = HASH_SIZE;
    table = new ListType[hashSize];
    initialTable(table, hashSize, NULL);
    numData = 0;
}


// create an empty table, i.e., one where numEntries() is 0
// such that the underlying hash table is hSize
Table::Table(unsigned int hSize) {
    hashSize = hSize;
    table = new ListType[hashSize];
    initialTable(table, hashSize, NULL);
    numData = 0;
}

// returns the address of the value that goes with this key
// or NULL if key is not present.
//   Thus, this method can be used to either lookup the value or
//   update the value that goes with this key.
int *Table::lookup(const string &key) {

    // Apply hash function to find corresponding hash index in table
    unsigned int hashIndex = hashCode(key);

    // Result will be address of the value if the key is in the list
    // Otherwise result will be NULL if the key was not present in the list
    // We check given bucket using listlookup methods
    return listLookup(table[hashIndex], key);
}

// remove a pair given its key
// false iff key wasn't present
bool Table::remove(const string &key) {
    // Apply hash function to find corresponding hash index in table
    unsigned int hashIndex = hashCode(key);
    if (listRemove(table[hashIndex], key)) {
        numData--;
        return true;
    }
    return false;
}

// insert a new pair into the table
// return false iff this key was already present
//         (and no change made to table)
bool Table::insert(const string &key, int value) {
    // Apply hash function to find corresponding hash index in table
    unsigned int hashIndex = hashCode(key);
    if (listInsertFront(table[hashIndex], key, value)) {
        numData++;
        return true;
    }
    return false;
}

// number of entries in the table
int Table::numEntries() const {

    return numData;
}

// print out all the entries in the table, one per line.
void Table::printAll() const {
    for (int i = 0; i < hashSize; i++) {
        ListType list = table[i];
        printList(list);
    }
}

// hashStats: for diagnostic purposes only
// prints out info to let us know if we're getting a good distribution
// of values in the hash table.
//   number of buckets: 997
//   number of entries: 10
//   number of non-empty buckets: 9
//   longest chain: 2
void Table::hashStats(ostream &out) const {
    out << "number of buckets: " << hashSize << endl;
    out << "number of entries: " << numEntries() << endl;
    out << "number of non-empty buckets: " << numNonEmptyBucket() << endl;
    out << "longest chain: " << longestChain() << endl;
}


// add definitions for your private methods here

void Table::initialTable(TableType &table, unsigned int hashSize, ListType list) {
    for (int i = 0; i < hashSize; i++) {
        table[i] = list;
    }
}

int Table::numNonEmptyBucket() const {
    int numNonEmpty = 0;

    // Go through the whole table, add size of each bucket together
    for (int i = 0; i < hashSize; i++) {
        if (listSize(table[i]) != 0) {
            numNonEmpty++;
        }
    }
    return numNonEmpty;
}

int Table::longestChain() const {
    int longest = 0;

    // Go through the whole table, update the longest chain size
    for (int i = 0; i < hashSize; i++) {
        if (listSize(table[i]) > longest) {
            longest = listSize(table[i]);
        }
    }
    return longest;
}