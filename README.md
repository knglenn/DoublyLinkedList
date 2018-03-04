# DoublyLinkedList
Java implementation of a doubly linked list

Usage: java DoublyLinkedList `<name of file with commands>`

All commands must be provided as a space-separated list in a text file. An example file, linkTest.txt, has been included in the repository.

Commands:  
* Add a node with value of `key` to the head - use "key.in"  
* Add a node with value of `newKey` after a node with value of `key` - use "newKey.in_key"  
* Delete a node with value of `key` - use "key.del"  
* Search for a node with value of `key` - use "key.sch"  

Example: `1.in 2.in 3.in 3.del 2.sch 4.in_1`

The output will be `1 4 2` and `2 was found.`
  
Duplicate keys are not allowed.
Only integers are allowed as keys. 

The updated list will be output to the command line after each command. 

