// Name: Dunxuan Li (Annie)
// USC NetID: dunxuanl
// CSCI 455 PA5
// Spring 2019

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>

using namespace std;

// Commands that are acceptable
void printCmdSummary() {
    cout << endl
         << "Valid commands are <insert> <change> <lookup> <remove> <print> <size> <stats> <help> <quit>. "
         << endl;
}

// Convert command we read from command line into character
char cmdAbbv(string command) {
    if (command == "insert") return 'i';
    if (command == "change") return 'c';
    if (command == "lookup") return 'l';
    if (command == "remove") return 'r';
    if (command == "print") return 'p';
    if (command == "size") return 's';
    if (command == "stats") return 't';
    if (command == "help") return 'h';
    if (command == "quit") return 'q';
}

// Print command summary
void printHelp() {
    cout << "The following is the command summary: " << endl;
    cout << "<insert> name score : insert a new student's name and core in the grade table" << endl;
    cout << "<change> name newscore : Change the score for specified student" << endl;
    cout << "<lookup> name : look up the specified student's score" << endl;
    cout << "<remove> name : remove a student from the grade table" << endl;
    cout << "<print> : print all names and scores in the table" << endl;
    cout << "<size> : print out the number of entries in the table" << endl;
    cout << "<stats> : print out statistics about the grade table" << endl;
    cout << "<help> : print out a brief command summary" << endl;
    cout << "<quit> : exits the program" << endl;

}

int main(int argc, char *argv[]) {

    // gets the hash table size from the command line

    int hashSize = Table::HASH_SIZE;

    Table *grades;  // Table is dynamically allocated below, so we can call
    // different constructors depending on input from the user.

    // If user gives us a specific table size, use it as the size of hashtable
    if (argc > 1) {
        hashSize = atoi(argv[1]);  // atoi converts c-string to int

        if (hashSize < 1) {
            cout << "Command line argument (hashSize) must be a positive number"
                 << endl;
            return 1;
        }

        grades = new Table(hashSize);

    }
        // If a user does not specify the size, use the default size
    else {   // no command line args given -- use default table size
        grades = new Table();
    }


    grades->hashStats(cout);

    // add more code here
    // Reminder: use -> when calling Table methods, since grades is type Table*

    char cmd;
    bool keepgoing = true;
    string name;
    int score;

    printCmdSummary();

    do {
        cout << "cmd> ";
        string command;
        cin >> command;

        if (cin.fail()) {
            cout << "ERROR: input stream failed." << endl;
            keepgoing = false;
        } else {
            cmd = cmdAbbv(command);
            switch (cmd) {
                case 'i':
                    cout << "Please enter student's name and score that you want to add: ";
                    cin >> name;
                    cin >> score;
                    if (!grades->insert(name, score)) {
                        cout << "Warning: the student is in the table already. No duplication will be made." << endl;
                        cout << "[* If you want to update the student's score, use 'change' command instead]" << endl;
                    }
                    break;

                case 'c':
                    cout << "Please enter student's name and new score: ";
                    cin >> name;
                    cin >> score;
                    if (grades->lookup(name) != NULL) {
                        *grades->lookup(name) = score;
                    } else {
                        cout << "Warning: student's name was not found." << endl;
                    }
                    break;

                case 'l':
                    cout << "Please enter student's name you want to look up: ";
                    cin >> name;
                    if (grades->lookup(name) != NULL) {
                        cout << name << "'s score is " << *grades->lookup(name) << endl;
                    } else {
                        cout << "Warning: student's name was not found." << endl;
                    }
                    break;

                case 'r':
                    cout << "Please enter student's name you want to remove from the table: ";
                    cin >> name;
                    if (!grades->remove(name)) {
                        cout << "Warning: student is not in the table. Unable to remove an unexisted student." << endl;
                    }
                    break;

                case 'p':
                    grades->printAll();
                    break;

                case 's':
                    cout << "There are " << grades->numEntries() << " student(s)' data in the table." << endl;
                    break;

                case 't':
                    grades->hashStats(cout);
                    break;

                case 'h':
                    printHelp();
                    break;

                case 'q':
                    keepgoing = false;
                    break;
                default:
                    cout << "ERROR: invalid command" << endl;
                    break;
            }

        }
    } while (keepgoing);

    return 0;
}
