/*=============================================================================
| Assignment: HW 02 - Building and managing a Skiplist
|
| Author: Quang Pham
| Language: Java
|
| To Compile: javac Hw02.java
|
| To Execute: java Hw02 filename
| where filename is in the current directory and contains
| commands to insert, search, delete, print & quit.
|
| Class: COP3503 - CS II Summer 2021
| Instructor: McAlpin
| Due Date: per assignment
|
+=============================================================================*/

import java.io.File;
import java.io.FileNotFoundException;
import  java.util.*;


public class Hw02 {
    public static void main(String[] args) {
        complexityIndicator();
        String word_string;

        //new input file
        try {
            File inputFile = new File(args[0]);

            if (args.length > 1) {
                if (args[1].compareToIgnoreCase("r") == 0) {
                    long seed = System.currentTimeMillis();

                    Scanner scan = new Scanner(inputFile);

                    Skip_list skipList = new Skip_list(seed);

                    List<String> varList = new ArrayList<>(); //arraylist to store the string from the input
                    System.out.println("For the input file named " + inputFile.getName());
                    System.out.println("RNG is seeded with, " + seed);
                    while (scan.hasNext()) {
                        try {
                            word_string = scan.next();
                            varList.add(word_string);

                            if (varList.size() == 2) {
                                int key = Integer.parseInt(varList.get(1));

                                if (varList.get(0).compareToIgnoreCase("i") == 0) {
                                    skipList.insert(key);
                                } else if (varList.get(0).compareToIgnoreCase("d") == 0) {
                                    skipList.delete(key);
                                } else if (varList.get(0).compareToIgnoreCase("s") == 0) {
                                    skipList.search(skipList.head, key);
                                }
                                varList.clear();
                            } else if (varList.size() == 1) {
                                if (varList.get(0).compareToIgnoreCase("p") == 0) {
                                    varList.clear();
                                    skipList.printAll();
                                } else if (varList.get(0).compareToIgnoreCase("q") == 0) {
                                    varList.clear();
                                    scan.close();
                                    skipList.quit();
                                }
                            }
                        } catch (IllegalArgumentException e) {
                            System.err.println("A command " + varList.get(0) + " missing numeric parameter.");
                        }
                    }
                }
            }
            else {
                long seed = 42;
                Scanner scan = new Scanner(inputFile);

                Skip_list skipList = new Skip_list(seed);

                List<String> varList = new ArrayList<>(); //arraylist to store the string from the input
                System.out.println("For the input file named " + inputFile.getName());
                System.out.println("With the RNG unseeded, ");
                while (scan.hasNext()) {
                    try {
                        word_string = scan.next();
                        varList.add(word_string);

                        if (varList.size() == 2) {
                            int key = Integer.parseInt(varList.get(1));

                            if (varList.get(0).compareToIgnoreCase("i") == 0) {
                                skipList.insert(key);
                            } else if (varList.get(0).compareToIgnoreCase("d") == 0) {
                                skipList.delete(key);
                            } else if (varList.get(0).compareToIgnoreCase("s") == 0) {
                                skipList.search(skipList.head, key);
                            }
                            varList.clear();
                        } else if (varList.size() == 1) {
                            if (varList.get(0).compareToIgnoreCase("p") == 0) {
                                varList.clear();
                                skipList.printAll();
                            } else if (varList.get(0).compareToIgnoreCase("q") == 0) {
                                varList.clear();
                                scan.close();
                                skipList.quit();
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.err.println("A command " + varList.get(0) + " missing numeric parameter.");
                    }
                }
            }

        } catch (FileNotFoundException err) {
            System.err.println("WARNING: FILE IS NOT FOUND");
        }
    }

    public static void complexityIndicator () {
        System.err.println("\nqu511289;3;48");
    }
}

class Skip_list {
    class Node {
        int key;
        Node left;
        Node right;
        Node up;
        Node down;

        public Node (int value) {
            key = value;
            left = right = up = down = null;
        }
    }

    Node sentinel = new Node(Integer.MIN_VALUE); //call this sentinel at the main or in the skip list class
    Node nil = new Node (Integer.MAX_VALUE); //use this nil at the main or in the skip list class

    private int heightoflevel = 0;

    Node head;
    Node tail;
    Random random;
    long seed;

        //create a new empty linked list;
        public Skip_list (long seed) {
            this.seed = seed;
            random = new Random(seed);
            head = sentinel; //head is negative infinity
            tail = nil;     //tail is positive infinity
            head.right = tail;
            tail.left = head;
        }

        public Node search (Node node, int key) {

            if (node == null) {
                System.out.println(key + " NOT FOUND" );
                return null;
            }

            //node is found print the found string and return the current node;
            else if (node.key == key) {
                System.out.println(key + " found");
            }
            else if (node.key < key) {
                return search(node.right, key);
            }
            else {
                node = node.left;
                return search(node.down, key);
            }

            return node;
        }

        public void delete (int key) {
            Node nodeFound = findPosition(key);
            if ((nodeFound == null) || (nodeFound.key != key)) {
                System.out.println(key + " integer not found - delete not successful");
            }
            else {
                System.out.println(key + " deleted");
                while (true) {
                    removeThenConnectReferences(nodeFound);
                    if (nodeFound.up != null) {
                        nodeFound = nodeFound.up;
                    }
                    else {
                        break;
                    }
                }
            }
        }

        private void removeThenConnectReferences(Node nodeFound) {
            Node nodeFoundRight = nodeFound.right;
            Node nodeFoundLeft = nodeFound.left;

            nodeFoundLeft.right = nodeFoundRight;
            nodeFoundRight.left = nodeFoundLeft;
        }

        public void insert(int key) {


            int levels = -1;
            int numberOfHeadsCoin = -1;


            Node position = findPosition(key); //found the position where to put


            if ((position != null) && (position.key == key)) {
                return;
            }

            Node currentNode;

                do {
                    // promote when it hits head
                    levels++;
                    numberOfHeadsCoin++; //counter for amounts of coin flip to head;
                    IncreaseLevel(levels);


                    currentNode = position;

                    promote(position, currentNode, key);
                    while (position.up == null) {
                        position = position.left;
                    }
                    position = position.up;


                } while (Math.abs(random.nextInt()) % 2 != 0);
        }

        private void promote (Node position, Node currentNode, int key) {

            Node newNode = insertBetweenNodes(currentNode, key);
            Node NodeAtLowLevel = position.down;
            setUpAndDown(NodeAtLowLevel, key, newNode);

        }

        private Node findPosition(int data) {
            if (head == null) {
                System.out.println("There are no sentinel, please check the head if it's correct.");
                return null;
            }
            //starting the search at the highest position
            Node n = head;
            while (n.down != null) {
                n = n.down;

                //this check if the transversal reaches the end of the of list or less than or equal to the key.
                // If either one failed, then stay where you are and go down instead.
                while (n.right.key <= data) {
                    n = n.right;
                }
            }

            return n;
        }

        private Node insertBetweenNodes(Node currentNode, int key) {
            Node newNode = new Node(key);
            setBeforeAndAfter(currentNode, newNode);

            return newNode;
        }
        private void setUpAndDown (Node NodeLowerLevel, int key, Node newNode) {

            if (NodeLowerLevel != null) {

                while (NodeLowerLevel.right.key <= key) {
                    NodeLowerLevel = NodeLowerLevel.right;
                }
                newNode.down = NodeLowerLevel;
                NodeLowerLevel.up = newNode;
            }

}

        private void setBeforeAndAfter(Node temp, Node newNode) {

            newNode.right = temp.right;
            newNode.left = temp;
            temp.right.left = newNode;
            temp.right = newNode;
        }


        private void IncreaseLevel(int level) {
            if (level >= heightoflevel) {
                heightoflevel++;
                NewEmptyLevel();
            }
        }

        private void NewEmptyLevel() {
            Node newheadnode = new Node (sentinel.key);
            Node newtailnode = new Node (nil.key);

            newheadnode.right = newtailnode;
            newtailnode.left = newheadnode;
            newheadnode.down = head;
            newtailnode.down = tail;
            head.up = newheadnode;
            tail.up = newtailnode;

            head = newheadnode;
            tail = newtailnode;
        }

        public void printAll () {
            Node starting;
            Node highestLevel = head;

            System.out.println("the current Skip List is shown below: ");
            if (highestLevel.down.key == Integer.MIN_VALUE) {
                System.out.println("---infinity");
            }

            while (highestLevel.down != null) {
                highestLevel = highestLevel.down;
            }

            while (highestLevel.right != null) {
                highestLevel = highestLevel.right;
                starting = highestLevel;
                if (highestLevel.key != Integer.MAX_VALUE) {
                    System.out.print(" " + starting.key + "; ");
                    while (starting.up != null) {
                        System.out.print(" " + starting.key + "; ");
                        starting = starting.up;
                    }
                    System.out.println();
                }
            }
            if (highestLevel.key == Integer.MAX_VALUE) {
                System.out.println("+++infinity");
            }
            System.out.println("---End of Skip List---");
        }


    public void quit() {

        System.exit(0);
    }
}

/*=============================================================================
| I Quang Pham 4238107 affirm that this program is
| entirely my own work and that I have neither developed my code together with
| any another person, nor copied any code from any other person, nor permitted
| my code to be copied or otherwise used by any other person, nor have I
| copied, modified, or otherwise used programs created by others. I acknowledge
| that any violation of the above terms will be treated as academic dishonesty.
+=============================================================================*/