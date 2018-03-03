import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.util.Scanner;

/**
 * Created by kevin on 2/24/2018.
 */
public class DoublyLinkedList {
    private LinkedNode head;
    private int size;

    public DoublyLinkedList() {
        head = null;
        size = 0;
    }
    public int search(int k) {
        int temp = 0;
        //case of null list
        if(head == null) {
            return -1;
        }
        LinkedNode currentNode = head;
        while (currentNode != null) {
            temp++;
            if(currentNode.Data() == k) {
                //System.out.println(k + "found at node " + temp + ".");
                return temp;
            }
            if(currentNode.next != null) {
                currentNode = currentNode.next;
            }
            else {
                return -1;

            }
        }
        //if while loop finishes with no matches, key is not in the list
        return -1;
        //System.out.println("Key not found.");
    }
    // adding a node at the head
    public void addHead(int data) {
        if(head == null) {
            head = new LinkedNode(null, null, data);

        }
        else {
            LinkedNode newNode = new LinkedNode(head, null, data);
            //create link to previous node
            head.prev = newNode;
            //set head to the new node
            head = newNode;
        }
        //account for new node;
        size++;
    }
    public void addTail(int data) {
        if(head == null) {
            head = new LinkedNode(null, null, data);
        }
        else {
            LinkedNode currentNode = head;
            while(currentNode.next != null) {
                currentNode = currentNode.next;
            }
            LinkedNode newNode = new LinkedNode(null, currentNode, data);
            currentNode.next = newNode;
        }
        size++;
    }

    public void removeHead() {
        if(head == null) {
            return;
        }
        head = head.next;
        head.prev = null;
        size--;
    }

    public void removeTail() {
        if(head == null) {
            head = null;
            size--;
            return;
        }
        else {
            LinkedNode currentNode = head;
            while(currentNode.next.next !=null) {
                currentNode = currentNode.next;
            }
            currentNode.next = null;
            size--;
        }
    }
    public void removAtKey(int i) {
        if(head == null) {
            return;
        }
        //not tryna go out of bounds here
        if(i > size || i < 1) {
            return;
        }
        LinkedNode currentNode = head;
        int j = 1;
        while (j < i) {
            currentNode = currentNode.next;
            j++;
        }
        if(currentNode.prev == null) { //*
            //this means we're at the head, so we can use our method to remove from the head
            removeHead();
        }
        else if(currentNode.next == null) { //*
            //this means we're at the tail, so we can use our method to remove from the tail
            removeTail();
        }
        else {
            currentNode.prev.next = currentNode.next;
            currentNode.next.prev = currentNode.prev;
            size--;
        }

    }
    public void insAtKey(int data, int i) {
        if (head == null) {
            return;
        }
        //not tryna go out of bounds here
        if(i > size || i < 1) {
            return;
        }
        LinkedNode currentNode = head;
        int j = 0;
        //nullpointerexception occurs without second condition if trying to add to end of list
        while((j < i) && (currentNode.next != null)) {
            currentNode = currentNode.next;
            j++;
        }

        if(currentNode.prev == null) {
            //this means we're at the head, so we can use our method to add to the head
            addHead(data);
        }
        else if(currentNode.next == null) { //**
            //this means we're at the tail, so we can use our method to add to the tail
            addTail(data);
        }
        else {
            LinkedNode newNode = new LinkedNode(currentNode.prev, currentNode,data);
            newNode.prev = currentNode.prev;
            newNode.next = currentNode;
            currentNode.prev.next = newNode;
            currentNode.prev = newNode;
            size++;
        }



    }


    public void print() {
        LinkedNode currentNode = head;
        while(currentNode != null) {
            System.out.print(currentNode.Data() + " ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }
    public int size() {
        //returns number of links
        return size;
    }


    public String[] readFile(String inFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inFile));
           // String line = reader.readLine();
            String line = null;
            String[] val = null;
            while ((line = reader.readLine()) != null) {
                 val = line.split(" ");
            }
            reader.close();
            return val;
        }
        catch(java.io.IOException e) {
            System.out.println("Either the file was not found, or something went wrong. Please try again.");
            System.exit(0);
        }
        return null;
    }
    public void execute(String[] target) {
        DoublyLinkedList dubz = new DoublyLinkedList();
        int key;
        //DEPRECATED
        String[] searchResults = new String[target.length];
        String[] duplicateErrors = new String[target.length];
        String[] invalidComErrors = new String[target.length];
        String[] invalidKeyErrors = new String[target.length];

        System.out.println("\nInput read from file: ");
        for(int i = 0; i < target.length; i ++) {
            System.out.print(target[i] + " ");
        }
        System.out.println("\n");

        for(int i = 0; i < target.length; i++) {
            //System.out.println(target[i]);
            try {
                if (target[i].endsWith(".in")) {
                    //System.out.println(target[i] + "contains .in");

                    //regex deletes nondigits
                    key = Integer.parseInt(target[i].replaceAll("\\D+", ""));
                    if (dubz.search(key) != -1) {
                        //duplicateErrors[i] = "Command '" + target[i]  +"' rejected. Reason: The key (" + key + ") already exists within the list.";
                        System.out.println("\nCommand '" + target[i] + "' rejected. Reason: The key (" + key + ") already exists within the list.");
                    } else {
                        System.out.print("\nCommand '" + target[i] + "' successful. \nList before operation: ");
                        if (dubz.size() > 0) {
                            dubz.print();
                        } else {
                            System.out.println("The list is empty!");
                        }
                        dubz.addHead(key);
                        System.out.print("List after operation: ");
                        dubz.print();

                    }

                } else if (target[i].contains(".sch")) {
                    key = Integer.parseInt(target[i].replaceAll("\\D+", ""));
                    if (dubz.search(key) != -1) {
                        //searchResults[i] = key + " was found.";
                        System.out.println("\nCommand : '" + target[i] + "' successful. Result: \n'" + key + "' was found.");
                    } else {
                        //searchResults[i] = key + " was not found.";
                        System.out.println("\nCommand : '" + target[i] + "' successful. Result: \n'" + key + "' was not found.");
                    }
                } else if (target[i].contains(".in_")) {
                    key = Integer.parseInt(target[i].substring(0, target[i].indexOf(".")));
                    int key2 = Integer.parseInt(target[i].substring(target[i].indexOf("_") + 1));
                    //System.out.println(target[i] + " your key is " + key + " key2 is " + 7);
                    int index = dubz.search(key2);
                    if (index != -1) {
                        if (dubz.search(key) != -1) {
                            //duplicateErrors[i] = "Command '" + target[i]  +"' rejected. Reason: \nThe key (" + key + ") already exists within the list.";
                            System.out.println("\nCommand '" + target[i] + "' rejected. Reason: The key (" + key + ") already exists within the list.");
                        } else {
                            System.out.print("\nCommand '" + target[i] + "' successful. \nList before operation: ");
                            dubz.print();
                            dubz.insAtKey(key, index);
                            System.out.print("List after operation: ");
                            dubz.print();
                        }

                    } else {
                        //invalidKeyErrors[i] = "Command '" + target[i]  +"' rejected. Reason: The key (" + key + ") does not exist within the list.";
                        System.out.println("\nCommand '" + target[i] + "' rejected. Reason: The key (" + key2 + ") does not exist within the list.");
                    }
                } else if (target[i].contains(".del")) {
                    key = Integer.parseInt(target[i].replaceAll("\\D+", ""));
                    int delIndex = dubz.search(key);
                    if (delIndex != -1) {
                        System.out.print("\nCommand '" + target[i] + "' successful. \nList before operation: ");
                        dubz.print();
                        dubz.removAtKey(delIndex);
                        System.out.print("List after operation: ");
                        dubz.print();
                    } else {
                        //invalidKeyErrors[i] = "Command '" + target[i]  +"' rejected. Reason: The key (" + key + ") does not exist within the list.";
                        System.out.println("\nCommand '" + target[i] + "' rejected. Reason: The key (" + key + ") does not exist within the list.");
                    }
                } else {
                    //invalidComErrors[i] = "Command '" + target[i]  +"' rejected. Reason: Not a valid command.";
                    System.out.println("\nCommand '" + target[i] + "' rejected. Reason: Not a valid command.");
                }
            }
            catch(Exception e) {
                System.out.println("\nERROR: The command '" + target[i] + "' is not formatted correctly. Please modify your input file and try again. This command has been ignored.");
            }
        }


        System.out.println("\nEnd of command list. Your finalized Doubly Linked List: ");
        dubz.print();

        //DEPRECATED
        /*System.out.println("\n\nSearch Results: ");
        for(int i = 0; i < searchResults.length; i ++) {
            if(searchResults[i] != null) {
                System.out.println(searchResults[i]);
            }

        }
        System.out.println("\nERRORS:");
        for(int i = 0; i < duplicateErrors.length; i ++) {
            if(duplicateErrors[i] != null) {
                System.out.println(duplicateErrors[i]);
            }

        }
        for(int i = 0; i < invalidKeyErrors.length; i ++) {
            if(invalidKeyErrors[i] != null) {
                System.out.println(invalidKeyErrors[i]);
            }

        }
        for(int i = 0; i < invalidComErrors.length; i ++) {
            if(invalidComErrors[i] != null) {
                System.out.println(invalidComErrors[i]);
            }

        }
        System.out.println(); */
    }
    public static void main(String[] args) {

        DoublyLinkedList run = new DoublyLinkedList();

        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter a file name.");
        String userIn = scan.nextLine();

        run.execute(run.readFile(userIn));


    }
}
