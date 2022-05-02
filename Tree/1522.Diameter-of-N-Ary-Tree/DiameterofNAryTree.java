public class DiameterofNAryTree {
    public int diameter(Node root) {
        Info info = new Info();
        getDepth(root, info);
        return info.numOfNodes - 1;
    }

    private int getDepth(Node cur, Info info) {
        if (cur == null) {
            return 0;
        }

        int max = 0;
        int secMax = 0;

        if (cur.children != null) {
            for (Node child : cur.children) {
                int d = getDepth(child, info);
                if (d > max) {
                    secMax = max;
                    max = d;
                } else if (d > secMax) {
                    secMax = d;
                }
            }
        }

        info.numOfNodes = Math.max(info.numOfNodes, 1 + max + secMax);
        return 1 + max;
    }

    private static class Info {
        private int numOfNodes;
    }
}