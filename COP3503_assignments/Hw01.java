
/*=============================================================================
| Assignment: HW 01 - Building and managing a BST
|
| Author: Quang Pham
| Language: Java
|
| To Compile: javac Hw1.java
|
| To Execute: java Hw1
|
| Class: COP3503 - CS II Spring 2021
| Instructor: McAlpin
| Due Date: 06/23/2021
|
+=============================================================================*/
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;


public class Hw01 {
    public static void complexityIndicator () {
        System.err.println("\nqu511289; 3; 48");
    }
    public static void main(String[] args)  {
        try {
            //variable to store the string from the input file//
            String word_string;
            String line_string;


            //new input file//
            File inputFile = new File(args[0]);

            //scanner variable//
            Scanner scan = new Scanner(inputFile);
            Scanner scanLine = new Scanner (inputFile);

            //create a new tree//
            Binary_Search_tree tree = new Binary_Search_tree();

            //ArrayList to store the string from the input//
            List<String> varList = new ArrayList<>();

            //While loop to scan through the input file and print it nothing else //
            System.out.println(inputFile.getName() + " contains:");
            while (scanLine.hasNext()) {
                line_string = scanLine.nextLine();
                System.out.println(line_string);
            }

            //While loop to scan through the input file and do the input storing and the commands//
            while (scan.hasNext()) {
                try {
                    word_string = scan.next(); //scan word by word

                    varList.add(word_string); //add the word to the arraylist

                    //check to ensure the list size at least has two elements in it. After it done being used,
                    //the ArrayList is clear for reuse.
                 if (varList.size() == 2) {
                        int key = Integer.parseInt(varList.get(1));

                        if (varList.get(0).compareToIgnoreCase("i") == 0) {
                            tree.insertNode(varList.get(0), key);
                        } else if (varList.get(0).compareToIgnoreCase("d") == 0) {
                            tree.deleteNode(varList.get(0), key);
                        } else if (varList.get(0).compareToIgnoreCase("s") == 0) {
                            tree.searchNode(varList.get(0), key);
                        }
                        varList.clear();
                    }

                 // to execute the command that doesn't have the numeric parameter. ArrayList size can be 1 //
                 else if (varList.size() == 1) {
                     if (varList.get(0).compareToIgnoreCase("p") == 0) {
                         varList.clear();
                         tree.print_tree();
                     } else if (varList.get(0).compareToIgnoreCase("q") == 0) {
                         varList.clear();
                         tree.quit();
                     }
                 }
                } catch (IllegalArgumentException e) {
                    System.err.println("A command " + varList.get(0) + " missing numeric parameter.");
                }
            }
            scan.close();

            tree.getDepth_shell();
            tree.countChildren_shell();

            complexityIndicator();



        } catch (FileNotFoundException e) {
            System.out.println("WARNING: File is not found. Please check on the filename or the path.");
        } catch (IOException e) {
            System.out.println("ERROR: File is already existed");
            e.printStackTrace();
        }
    }
}


//Code to support a BST//
class Binary_Search_tree {
    // Below is the node class that generate a node. Every node contain left and right child. Empty child will be null.
    class Node  {
        int key;
        Node left;
        Node right;

        public Node(int item) {
            key = item;
            left = right = null;
        }
    }
        Node root;


    /* Constructor class for the binary tree */
        Binary_Search_tree () throws IOException {
        root = null;
    }

    //a method for node insert. We will use a recursive call.
        private Node insert (Node root, int key) {
        if (root == null) {

            root = new Node(key);
            return root;

        }
        //less goes left
        if (key < root.key) {

            root.left = insert(root.left, key);
        }

        //equal to or greater than goes right
        else {
            root.right = insert(root.right, key);
        }

        return root;
    }

        private Node delete(Node root, int key) {
        //check if the tree is empty. When it is, then return the value of nothing
            if (root == null) {
                System.out.println("command- d " + key + " integer " + key + "  NOT found - NOT deleted.");
                return null;
            }

            if (key < root.key) {
                root.left = delete(root.left, key);
            }

            else if (key > root.key) {
                root.right = delete(root.right, key);
            }

            //if the key is equal to the root key, then that node will be deleted
            else {
                /* we need to find the minimum value of that node inorder predecessor, it will be
                 * checking and finding the minimal value so it can replaced with the node, then delete that node */

                //case 1: if leaf, just delete
                if (root.left == null && root.right== null) {
                    return null;
                }
                //case 2: if the node only has one child, then return the other node
                else if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                }
                // case 3: if node with two children. Do the inorder successor (smallest in the right subtree)
                else {
                    root.key = find_the_smallest(root.right);
                    //delete that inorder successor node
                    root.right = delete(root.right, root.key);
                }

            }
            return root;
        }

        private int find_the_smallest (Node root) {
        int minval = root.key;

        while (root.left != null) {
            minval = root.left.key;
            root = root.left;
        }
        return minval;
    }

        private Node search (Node root, int key) {
        //check for  the value isn't exist throw error so I do throw and catch
            //
            if (root == null) {
                System.out.println("command - s " + key + " integer " + key + " NOT FOUND.");
                return null;
            }
            else if (root.key == key) {
                System.out.println("command -s " + key + " integer " + key + " found.");
            } else if (root.key < key) {
                return search(root.right, key);
            } else {
                return search(root.left, key);
            }
            return root;
    }

        private void print_tree_inorder (Node root){
        //check if the tree is empty, then it will print out error say no tree
        if (root != null)
        {
            print_tree_inorder(root.left); //recur on the left branch

            System.out.print(root.key + " ");

            print_tree_inorder(root.right);
        }

    }

        public void searchNode (String command, int value){
        if (command.compareToIgnoreCase("s") == 0) {
            search(root, value);
        }
    }

        public void insertNode (String command, int value) {
        if (command.compareToIgnoreCase("i") == 0) {
            root = insert(root, value);
        }
    }

        public void deleteNode (String command, int value){
        if (command.compareToIgnoreCase("d") == 0) {
            root = delete(root, value);
        }

    }

        public void print_tree () throws IOException {
            if (root == null) {
                System.out.println("Tree is empty");
            }
            else {
                System.out.println("Print tree inorder:");
                print_tree_inorder(root);
                System.out.println();
            }
    }
        //do nothing and just go back to the command
        public void quit () { }

    //Depth of a tree. Find the highest Branch depth and return the number.
        private int getDepth(Node root) {
        if (root == null) {
            return 0; //empty tree has 0
        }
        else {
            int leftBranchDepth = getDepth(root.left);
            int rightBranchDepth = getDepth(root.right);

            //this comparison is used to check and get back the larger subtree;
            if (leftBranchDepth > rightBranchDepth) {
                return (leftBranchDepth + 1);
            }
            else
                return (rightBranchDepth + 1);
        }
    }

    //Depending on the passing root (from a left subtree, or from a right subtree), it count the nodes on the left branch of that bst and then the right branch bst
        private int countChildren(Node root) {

        if (root == null) {
            return 0;
        }
            return countChildren(root.left) + countChildren(root.right) + 1;
    }

    public void getDepth_shell(){

        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);


        System.out.println("left depth: " + leftDepth);
        System.out.println("right depth: " + rightDepth);
    }

    public void countChildren_shell (){
        int leftChildren = countChildren(root.left);
        int rightChildren = countChildren(root.right);

        System.out.println("left children: " + leftChildren);
        System.out.println("right children: " + rightChildren);
    }
}



/*=============================================================================
| I Quang Pham,  ID: 4238107  affirm that this program is
| entirely my own work and that I have neither developed my code together with
| any another person, nor copied any code from any other person, nor permitted
| my code to be copied or otherwise used by any other person, nor have I
| copied, modified, or otherwise used programs created by others. I acknowledge
| that any violation of the above terms will be treated as academic dishonesty.
+=============================================================================*/