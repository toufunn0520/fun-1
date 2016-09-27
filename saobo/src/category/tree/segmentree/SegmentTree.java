package category.tree.segmentree;

public class SegmentTree {

    public SegmentTreeNode build(int start, int end, int[] array) {

        if (start > end) {
            return null;
        }

        SegmentTreeNode root = new SegmentTreeNode(start, end);

        if (start != end) {
            int mid = (start + end) / 2;
            root.left = build(start, mid, array);
            root.right = build(mid + 1, end, array);

            root.max = Math.max(root.left.max, root.right.max);
        } else {
            root.max = array[start];
        }

        return root;
    }
}
