package net.mobilespirit.curvy.domain.tree;

import net.mobilespirit.curvy.domain.point.Point2D;
import net.mobilespirit.curvy.util.MathUtil;

import java.util.Arrays;
import java.util.List;

import static net.mobilespirit.curvy.util.MathUtil.progression;

/**
 * Curvy - university related project
 * User: Venelin Valkov <valkov@mobil-espirit.net>
 * Date: 01-02-2011
 * Time: 14:49
 * Package: net.mobilespirit.curvy.domain.tree
 */
public class CasteljauTree {

    private double u;
    private List<Point2D> pointList;
    private Node<Point2D>[] tree;
    private double k, l;

    public CasteljauTree(double u, List<Point2D> pointList) {
        this.u = u;
        this.pointList = pointList;
        tree = new Node[progression(pointList.size())];
    }

    public int getHeight() {
        return pointList.size();
    }

    public int getWidth() {
        return pointList.size();
    }

    public List<Node<Point2D>> getTreeNodes() {
        return Arrays.asList(tree);
    }

    private void computeCoefficients() {
        k = 1.0 - u;
        l = u;
    }

    public void build() {
        computeCoefficients();
        for(int i = 0; i < pointList.size(); i++) {
            tree[i] = new Node<Point2D>(Node.NO_PARENT, Node.NO_PARENT, pointList.get(i));
        }
        int startAt = 0;
        for(int level = pointList.size() - 1; level >= 0; level--) {
            int prevLevel = level + 1;
            int prevFirst = startAt;
            startAt += prevLevel;
            for(int j = 0; j < level; j++) {
                Node<Point2D> leftChild = tree[prevFirst + j];
                Node<Point2D> rightChild = tree[prevFirst + j + 1];
                double x = k * leftChild.value().x() + l * rightChild.value().x();
                double y = k * leftChild.value().y() + l * rightChild.value().y();
                Point2D point = new Point2D(MathUtil.roundTo(x, 5), MathUtil.roundTo(y, 5));
                tree[startAt + j] = new Node<Point2D>(Node.NO_PARENT, Node.NO_PARENT, point);
                leftChild.rightParent(startAt + j);
                rightChild.leftParent(startAt + j);
            }
        }
    }

//    public void print() {
//        int printed = 0;
//        int shouldPrint = 1;
//        for(int i = tree.length - 1; i >= 0; i--) {
//            if(printed == shouldPrint) {
//                System.out.println();
//                shouldPrint++;
//                printed = 0;
//            }
//            System.out.print("(" + tree[i].value().x() + ", " + tree[i].value().y() + ") ");
//            printed++;
//        }
//    }
}

