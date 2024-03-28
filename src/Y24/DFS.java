package Y24;

class TreeNodeII {
    int val;
    TreeNodeII left;
    TreeNodeII right;
    TreeNodeII() {}
    TreeNodeII(int val) { this.val = val; }
    TreeNodeII(int val, TreeNodeII left, TreeNodeII right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
//深度优先搜索
public class DFS {
    //前序遍历
    public static void preDFS(TreeNodeII root){
        if (root!=null){
            System.out.print(root.val+"  ");
            preDFS(root.left);
            preDFS(root.right);
        }
    }
    //中序遍历
    public static void midDFS(TreeNodeII root){
        if (root!=null){
            midDFS(root.left);
            System.out.print(root.val+"  ");
            midDFS(root.right);
        }
    }
    //后序遍历
    public static void postDFS(TreeNodeII root){
        if (root!=null){
            postDFS(root.left);
            postDFS(root.right);
            System.out.print(root.val+"  ");
        }
    }
    //99. 恢复二叉搜索树
    //中序遍历，如有
    public static void recoverTree(TreeNodeII root) {

    }
    //104. 二叉树的最大深度
    //dfs 当null return 0;叶子结点的左节点和右节点为空，+1
    public static int maxDepth(TreeNodeII root) {
        if (root==null){
            return 0;
        }
        int maxL=maxDepth(root.left);
        int maxR=maxDepth(root.right);
        return Math.max(maxL,maxR)+1;
    }
    //226. 翻转二叉树
    //左右子树反转
    public static TreeNodeII invertTree(TreeNodeII root) {
        if (root==null) return null;
        TreeNodeII left=root.left;
        root.left=root.right;
        root.right=left;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
    //101. 对称二叉树
    //左节点val=右节点val
    public static boolean isSymmetric(TreeNodeII root) {
        if (root==null){
            return true;
        }
        return isSymmetricSub(root.left,root.right);
    }
    //dfs终止条件 null
    public static boolean isSymmetricSub(TreeNodeII left,TreeNodeII right){
        if (left==null && right==null){
            return true;
        }else if (left==null || right==null){
            return false;
        }
        return left.val==right.val && isSymmetricSub(left.left,right.right) && isSymmetricSub(left.right,right.left);
    }
    //543. 二叉树的直径
    //左子树的最长高度+右子树的最长高度
    static int res=0;
    public static int maxLen(TreeNodeII root, int val){
        if (root==null){
            return val;
        }
        val++;
        int l=maxLen(root.left,val);
        int r=maxLen(root.right,val);
        if (l+r>res){
            res=l+r;
        }
        return Math.max(l,r);
    }
    public static int diameterOfBinaryTree(TreeNodeII root) {
        int val=-1;
        maxLen(root,val);
        return res;
    }

    //108. 将有序数组转换为二叉搜索树
    public TreeNodeII sortedArrayToBST(int[] nums) {
        return dfsBST(nums,0,nums.length);
    }
    //[-10,-3,0,5,9]
    public TreeNodeII dfsBST(int[] nums,int l,int r){
        if (l>r){
            return null;
        }
        int mid=(l+r)/2;
        TreeNodeII node=new TreeNodeII(nums[mid]);
        node.left=dfsBST(nums,l,mid-1);
        node.right=dfsBST(nums,mid+1,r);
        return node;
    }

    public static void main(String[] args) {
//                   1
//                 /   \
//                2     2
//               / \   / \
//              3   4 4   3

        TreeNodeII root=new TreeNodeII(1);
        root.left=new TreeNodeII(2);
        root.right=new TreeNodeII(2);
        root.left.left=new TreeNodeII(3);
        root.left.right=new TreeNodeII(4);
        root.right.left=new TreeNodeII(4);
        root.right.right=new TreeNodeII(3);
        //recoverTree(tree);
        //preDFS(tree);
        //postDFS(tree);
        //maxDepth(tree);
        //invertTree(root);
        //preDFS(root);
        System.out.println(diameterOfBinaryTree(root));
    }
}
