


/*/
    *************************************>> Segment tree is represented as an array! <<***********************************************************
 */
public class SegmentTreeRangeMinimumQuery {
    public static void main(String[] args) {
        int[] inputArray = {2, 5, 4, 9};
        int[] segTree = new int[calculateLengthOfSegTree(inputArray.length)];
        segTree = constructTree(inputArray, segTree, 0, inputArray.length, 0);
        printSegTree(segTree);
        System.out.println(" ");
        System.out.println(rangeMinQuery(segTree, 2, 2, 0, inputArray.length-1, 0));
    }

    static int rangeMinQuery(int[] segTree, int qlow, int qhigh, int low, int high, int currPos) {

        if(qlow <= low && qhigh >= high) { //Total overlap with ql, qh and l, h
            return segTree[currPos];
        }

        if(qlow > high || qhigh < low) { //No overlap
            return Integer.MAX_VALUE;
        }

        int mid = (low+high)/2;

        return Math.min(
                rangeMinQuery(segTree, qlow, qhigh, low, mid, 2*currPos+1),
                rangeMinQuery(segTree, qlow, qhigh, mid+1, high, 2*currPos+2)
        );

    }

    static int[] constructTree( int[] array, int[] segTree, int low, int high, int currPos) {
        if(low == high-1) {
            segTree[currPos] = array[low];
            return segTree;
        }

        int mid = (low+high)/2;

        constructTree(array, segTree, low, mid, 2*currPos+1);
        constructTree(array, segTree, mid, high, 2*currPos+2);

        segTree[currPos] = Math.min(segTree[2*currPos+1], segTree[2*currPos+2]);

        return segTree;
    }

    static int calculateLengthOfSegTree(int arrLength) {
        if( ( arrLength & (arrLength-1) ) == 0) {
            return arrLength*2-1;
        }

        //Implement----

        return 0;

    }

    static void printSegTree(int[] segTree) {
        for (int i = 0; i < segTree.length; i++) {
            System.out.print(segTree[i] + " ");
        }
    }
}
