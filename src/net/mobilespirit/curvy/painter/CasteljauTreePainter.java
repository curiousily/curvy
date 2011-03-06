package net.mobilespirit.curvy.painter;

import android.graphics.*;
import net.mobilespirit.curvy.domain.Point.Point2D;
import net.mobilespirit.curvy.domain.tree.CasteljauTree;
import net.mobilespirit.curvy.domain.tree.Node;

/**
 * Curvy - university related project
 * User: Venelin Valkov <valkov@mobile-spirit.net>
 * Date: 3/4/11
 * Time: 3:40 PM
 */
public class CasteljauTreePainter {

    private static final int CIRCLE_RADIUS = 80;
    private static final int PADDING = 10;
    private static final int COLUMN_PADDING = CIRCLE_RADIUS / 2;
    private static final int TEXT_SIZE = 22;
    private static final int CHAR_TO_PIXEL_RATIO = 9;

    private final Paint circlePaint = new Paint();
    private final Paint textPaint = new Paint();
    private final PositionPointer pointer;
    private final CasteljauTree tree;

    private CasteljauTreePainter(CasteljauTree tree) {
        this.tree = tree;
        pointer = new PositionPointer(getHeight() - (CIRCLE_RADIUS + PADDING));
        initPaints();
    }

    private void initPaints() {
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.GRAY);

        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(TEXT_SIZE);
    }

    public int getWidth() {
        return (tree.getWidth() * CIRCLE_RADIUS) * 2 + (tree.getWidth() * PADDING) + 2 * COLUMN_PADDING + PADDING;
    }

    public int getHeight() {
        return (tree.getHeight() * CIRCLE_RADIUS) * 2 + (tree.getHeight() * PADDING) + 2 * COLUMN_PADDING + PADDING;
    }

    public static Bitmap createBitmap(CasteljauTree tree) {
        CasteljauTreePainter painter = new CasteljauTreePainter(tree);
        Bitmap bitmap = Bitmap.createBitmap(painter.getWidth(), painter.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        painter.draw(canvas);
        return bitmap;
    }

    private boolean shouldAdvanceToNextRow(int printedNodes, int nodesAtRow) {
        return printedNodes == nodesAtRow;
    }

    public void draw(Canvas canvas) {

        int printedNodes = 0;
        int nodesAtRow = tree.getWidth();
        pointer.moveToStart();
        for (Node<Point2D> node : tree.getTreeNodes()) {
            if (shouldAdvanceToNextRow(printedNodes, nodesAtRow)) {
                pointer.moveToNextRow();
                nodesAtRow--;
                printedNodes = 0;
            }
            drawNode(canvas, node.value());
            pointer.moveToNextColumn();
            printedNodes++;
        }
    }

    private void drawNode(Canvas canvas, Point2D point) {
        canvas.drawCircle(pointer.getCurrentColumn(), pointer.getCurrentRow(), CIRCLE_RADIUS, circlePaint);
        String coordinates = point.x() + " : " + point.y();
        int coordinatesInPixels = coordinates.length() * CHAR_TO_PIXEL_RATIO;
        canvas.drawText(coordinates, pointer.getCurrentColumn() - (coordinatesInPixels / 2), pointer.getCurrentRow() + (TEXT_SIZE / 2), textPaint);
    }

    private class PositionPointer {

        private int currentRowPosition = 0;
        private int currentColumnPosition = 0;
        private int rowCount = 0;

        public PositionPointer(int initialRowPosition) {
            currentRowPosition = initialRowPosition;
        }

        public void moveToStart() {
            moveToStartColumn();
        }

        public int getCurrentColumn() {
            return currentColumnPosition;
        }

        public int getCurrentRow() {
            return currentRowPosition;
        }

        public void moveToNextColumn() {
            currentColumnPosition += (CIRCLE_RADIUS * 2) + PADDING;
        }

        public void moveToNextRow() {
            currentRowPosition -= (2 * CIRCLE_RADIUS) + PADDING;
            rowCount++;
            moveToStartColumn();
        }

        private void moveToStartColumn() {
            currentColumnPosition = CIRCLE_RADIUS + COLUMN_PADDING + (rowCount * CIRCLE_RADIUS) + PADDING;
        }
    }
}
