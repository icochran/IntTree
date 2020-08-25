import structure5.Queue;
import structure5.QueueList;
import structure5.Stack;
import structure5.StackList;

// CS 201 Homework 6
// Name: Issy Cochran
// Wrote various functions that operate on IntTree's 

public class HW6IntTreeOps extends IntTreeOps {

    public static void main(String[] args) {
    	// Initialize an array of IntTrees to contain seven trees and then let the user select one
    	// or two of them to test the methods with

    	 IntTree t1 = empty();
    	 IntTree t2 = leaf(3);
    	 IntTree t3 = node(1, leaf(2), leaf(3));
    	 IntTree t4 = node(2, leaf(1), leaf(3));
    	 IntTree t5 = node(3, node(1, empty(), leaf(2)), node(4, empty(), leaf(5)));
    	 IntTree t6 = node(3, leaf(1), node(4, leaf(2), leaf(5)));
    	 IntTree t7 = node(1, node(2, leaf(4), leaf(5)), node(3, leaf(6), leaf(7)));
    	 IntTree t8 = node(5, node(2, leaf(1), node(3, empty(), leaf(4))), node(7, leaf(6), node(9, leaf(8), empty())));
    	 IntTree[] trees  = {t1, t2, t3, t4, t5, t6, t7};
    	 levelOrderWrite(t8);
    	 preOrderWrite(t8);

    	 if (args.length == 0 || args.length > 2) {
    		 // if no arguments are entered or too many are print a usage statement.
    		 p("usage: java HW6IntTreeOps <t1>");
    		 p("    or java HW6IntTreeOps <t1> <t2>");
    		 p("");
    		 p("  Tests certain operations on selected tree(s).");
    		 p("  If one tree is specified, runs 'weightedTree()',");
    		 p("    'testFullTree()', and 'testOrderedTree()'.");
    		 p("  If two trees are specified, runs 'compareTrees()'.");
    		 p("  Valid tree numbers are 1 through 7.");
    	 }

    	 if (args.length == 1 && (args[0].equals("1") || args[0].equals("2") ||
    			 args[0].equals("3") || args[0].equals("4") || args[0].equals("5") ||
    			 args[0].equals("6") || args[0].equals("7"))) {
    		 // if one argument is entered and it is a valid tree number run the methods on the tree

    		 IntTree tree = trees[Integer.parseInt(args[0]) - 1];
    		 p("Tree " + args[0] + ":");
    		 printTree(tree);
    		 p("Weighted Tree:");
    		 printTree(weightedTree(tree));
    		 System.out.println("testFullTree:    " + testFullTree(tree));
    		 System.out.println("testOrderedTree: " + testOrderedTree(tree));
    	 }

    	 else if (args.length == 1 && args[0].length() > 1) {
    		 // if one argument is entered but more than one number makes up that argument
    		 // tell the user how to compare trees.

    		 p("Error, if you want to compare two trees use a space,");
    		 p("usage: java HW6IntTreeOps <t1> <t2>");
    		 p("Valid tree numbers are 1 through 7.");
    	 }

    	 else if (args.length == 1) {
    		 // otherwise the user entered an invalid tree number, so print an error statement.
    		 p("Error, " + args[0] + " is not a valid tree number.");
    		 p("Valid tree numbers are 1 through 7.");
    	 }

    	 if (args.length == 2 && ((args[0].equals("1") || args[0].equals("2") ||
    			 args[0].equals("3") || args[0].equals("4") || args[0].equals("5") ||
    			 args[0].equals("6") || args[0].equals("7")) && (args[1].equals("1") ||
    			 args[1].equals("2") || args[1].equals("3") || args[1].equals("4") ||
    			 args[1].equals("5") || args[1].equals("6") || args[1].equals("7")))) {
    		 // if two arguments are entered and they are both valid, compare them

    		 IntTree tree1 = trees[Integer.parseInt(args[0]) - 1];
    		 IntTree tree2 = trees[Integer.parseInt(args[1]) - 1];
    		 p("Tree " + args[0] + ":");
    		 printTree(tree1);
    		 p("Tree " + args[1] + ":");
    		 printTree(tree2);
    		 System.out.println("compareTrees:   " + compareTrees(tree1,tree2));
    	 }

    	 else if (args.length == 2  && (args[0].length() > 1 || args[1].length() > 1)) {
    		 // if two arguments are entered but either invalid numbers were entered or too many
    		 // per argument were entered, explain how to compare trees.
    		 p("Error, if you want to compare two trees use a space,");
    		 p("usage: java HW6IntTreeOps <t1> <t2>");
    		 p("Valid tree numbers are 1 through 7.");
    	 }
    }

    public static IntTree weightedTree(IntTree t) {
        // returns a copy of t where the values at each level are
        // multiplied by their level number (starting to count
        // at the root with 1)

    	// call the recursive function weighted tree
    	return weightedTree1(t, 1);
    }

    public static IntTree weightedTree1(IntTree t, int level) {
    	// given the first level number this function goes through the tree and multiplies
    	// the nodes by their level number by adding 1 to level each time the function is called
    	// until it hits an empty tree.
    	if (isEmpty(t)) {
    		return t;
    	}
    	else {
    		return node(value(t) * level, weightedTree1(left(t), level + 1),
    				weightedTree1(right(t), level + 1));
    	}
    }

    public static boolean compareTrees(IntTree t1, IntTree t2) {
    	// returns true if the two IntTrees have the same shape, meaning same number of nodes
    	// with the same geometric arrangement of the nodes but not necessarily the same values.

    	// if the number of nodes and the height of the tree are not the same, the trees do not have
    	// the same shape
    	if (countNodes(t1) != countNodes(t2) && height(t1) != height(t2)) {
    		return false;
    	}
    	// if both trees are not empty or if only one of them is...
    	else if (!(isEmpty(t1) && isEmpty(t2))) {
    		// compare the left of both trees to each other and compare the right of both trees
    		return compareTrees(left(t1), left(t2)) && compareTrees(right(t1), right(t2));
    	}
    	// otherwise return true
    	else {
    		return true;
    	}
    }

    public static boolean testFullTree(IntTree t) {
    	// returns true if the IntTree is full which means it has the maximum number of nodes
    	// at each level of the tree, false if otherwise.

    	// if its empty it is a full tree
    	if (isEmpty(t)) {
    		return true;
    	}
    	// if the left side is empty but the right is not, or vice versa, or if the
    	// left height does not equal the right height, return false
    	else if ((isEmpty(left(t)) && !isEmpty(right(t))) || (isEmpty(right(t)) && !isEmpty(left(t)))
    			|| height(left(t)) != height(right(t))) {
    		return false;
    	}
    	// otherwise call the function separately on the left side and right side.
    	else {
    		return testFullTree(left(t)) && testFullTree(right(t));
    	}
    }

    public static boolean testOrderedTree(IntTree t) {
    	// A binary tree is defined to be "ordered" if, for every node of the tree,
    	// all values stored in the entire left subtree of this node are less or equal
    	// than the value stored in this node, and all values stored in the right subtree
    	// are greater or equal than the value stored in this root node.
    	// Returns a boolean value that should be true if the values stored in the input
    	// tree are ordered and false otherwise.

    	// if the tree is empty or if it is a leaf it is ordered
    	if (isEmpty(t) || isLeaf(t)) {
    		return true;
    	}
    	// if the left side is not empty and the root is less than the node on the left, or
    	// if the right side is not empty and the root is greater than the node on the right,
    	// return false
    	else if ((!isEmpty(left(t)) && value(t) < value(left(t))) || (!isEmpty(right(t)) &&
    			value(t) > value(right(t)))) {
    		return false;
    	}
    	// if the left is empty but the right is not call recursive testOrderedTree2 on the right side
    	// and check if the right side is ordered according to the root of the right tree
    	else if (isEmpty(left(t)) && !isEmpty(right(t))) {
    		return testOrderedTree2(right(t), value(t)) && testOrderedTree(right(t));
    	}
    	// if its the same but for the left side call recursive testOrderedTree1 on the left side
    	// and check if the left side is ordered according to the root of the left tree
    	else if (isEmpty(right(t)) && !isEmpty(left(t))) {
    		return testOrderedTree1(left(t), value(t)) && testOrderedTree(left(t));
    	}
    	// otherwise call all four things
    	else {
    		return testOrderedTree1(left(t), value(t)) && testOrderedTree2(right(t), value(t))
    				&& testOrderedTree(right(t)) && testOrderedTree(left(t));
    	}
    }

    public static boolean testOrderedTree1(IntTree t, int node) {
    	// using the value of the node of the original tree this function tests whether
    	// the rest of the left side is less than it recursively

    	// if it is a leaf and the node of the left of the original tree is not greater
    	// than the root, it is ordered
    	if (isLeaf(t) && !(value(t) > node)) {
    		return true;
    	}
    	// if the node of the left of the original tree is greater than the root it is not ordered
    	else if (value(t) > node) {
    		return false;
    	}
    	// if nothing has returned so far continue in the same fashion as the original method.
    	else if (isEmpty(left(t)) && !isEmpty(right(t))) {
    		return testOrderedTree1(right(t), node) && testOrderedTree(right(t));
    	}
    	else if (isEmpty(right(t)) && !isEmpty(left(t))) {
    		return testOrderedTree1(left(t), node) && testOrderedTree(left(t));
    	}
    	else {
    		return testOrderedTree1(left(t), node) && testOrderedTree1(right(t), node)
    				&& testOrderedTree(right(t)) && testOrderedTree(left(t));
    	}
    }

    public static boolean testOrderedTree2(IntTree t, int node) {
    	// using the value of the node of the original tree this function tests whether
    	// the rest of the right side is greater than it recursively

    	// if it is a leaf and the node of the right of the original tree is not less
    	// than the root, it is ordered
    	if (isLeaf(t) && !(value(t) < node)) {
    		return true;
    	}
    	// if the node of the right of the original tree is less than the root it is not ordered
    	else if (value(t) < node) {
    		return false;
    	}
    	// if nothing has returned so far continue in the same fashion as the original method.
    	else if (isEmpty(left(t)) && !isEmpty(right(t))) {
    		return testOrderedTree2(right(t), node)  && testOrderedTree(right(t));
    	}
    	else if (isEmpty(right(t)) && !isEmpty(left(t))) {
    		return testOrderedTree2(left(t), node) && testOrderedTree(left(t));
    	}
    	else {
    		return testOrderedTree2(left(t), node) && testOrderedTree2(right(t), node)
    				 && testOrderedTree(right(t)) && testOrderedTree(left(t));
    	}
    }

    /*public static void levelOrderWrite(IntTree t) {
    	// prints all of the IntTree's values in level-order.

    	if (isEmpty(t)) {
            System.out.print("");
    	}
    	else if (isLeaf(t)) {
    		System.out.print(Integer.toString(value(t)) + "  ");
    	}
    	else {
    		System.out.print(value(t) + "  " + value(left(t)) + "  " + value(right(t)) + "  ");
    		levelOrderWrite(left(left(t)));
    		levelOrderWrite(right(left(t)));
    		levelOrderWrite(left(right(t)));
    		levelOrderWrite(right(right(t)));
    	}
    } */

    public static void preOrderWrite(IntTree t) {
        // prints out the contents of the tree t using preOrder
        // traversal of the nodes
        if (!(isEmpty(t))) {
            System.out.print("  " + value(t));
            preOrderWrite(right(t));
            preOrderWrite(left(t));
        }
    }

    public static void levelOrderWrite(IntTree t) {
        // prints out the contents of the tree t in level order
        Stack<IntTree> q = new StackList<IntTree>();
        q.add(t);
        while (!q.isEmpty()) {
            IntTree node = q.remove();
            if (!isEmpty(node)) {
                System.out.print("  " + value(node));
                q.add(left(node));
                q.add(right(node));
            }
        }
        System.out.println();
    }

 // useful shorthand for System.out.println to save some typing
    public static void p(String s) {
        System.out.println(s);
    }
}
