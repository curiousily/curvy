package net.mobilespirit.curvy.domain.tree;

/**
 * Curvy - university related project
 * User: Venelin Valkov <valkov@mobile-spirit.net>
 * Date: 06-03-2011
 * Time: 23-15
 * Package: net.mobilespirit.curvy.domain.tree
 */
public class Node<Element_Type> {

    private Element_Type value;
    private int leftParent;
    private int rightParent;
    public static final int NO_PARENT = -1;

    Node(int leftParent, int rightParent, Element_Type value) {
        this.leftParent = leftParent;
        this.rightParent = rightParent;
        this.value = value;
    }

    public void leftParent(int parent) {
        leftParent = parent;
    }

    public void rightParent(int parent) {
        rightParent = parent;
    }

    public Element_Type value() {
        return value;
    }

    public int leftParent() {
        return leftParent;
    }

    public int rightParent() {
        return rightParent;
    }
}
