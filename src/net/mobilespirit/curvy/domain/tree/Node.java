package net.mobilespirit.curvy.domain.tree;

/**
 * Bezier curves - University related project
 * User: Vini
 * Date: 01-02-2011
 * Time: 14:58
 * Package: com.vini.geometry.entity.tree
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
