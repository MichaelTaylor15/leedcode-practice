package Y24;


import java.util.*;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
}
class FindElements {
    TreeNode tree;
    HashMap<Integer,Integer> hashMap;
    public  FindElements(TreeNode root) {
        if (root.val==-1){
            root.val=0;
        }
        hashMap=new HashMap<>();
        Stack<TreeNode> treeNodes=new Stack<>();
        treeNodes.push(root);
        hashMap.put(0,1);
        while (!treeNodes.isEmpty()){
            TreeNode cur=treeNodes.pop();
            if (cur.right!=null){
                treeNodes.push(cur.right);
                cur.right.val=2* cur.val+2;
                hashMap.put(cur.right.val,1);
            }
            if (cur.left!=null){
                treeNodes.push(cur.left);
                cur.left.val= cur.val*2+1;
                hashMap.put(cur.left.val,1);
            }
        }

        tree=root;
    }

    public boolean find(int target) {
        return hashMap.containsKey(target);
    }

    public static void main(String[] args) {
        TreeNode root=new TreeNode();
        root.val=-1;
        root.left=new TreeNode(-1);
        root.right=new TreeNode(-1);
        root.left.left=new TreeNode(-1);
        root.left.right=new TreeNode(-1);
        FindElements obj=new FindElements(root);
        obj.find(3);
        System.out.println(1);
    }
}

public class Tree {
    //144. 二叉树的前序遍历
    public static List<Integer> preorderTraversal(TreeNode root) {
        if (root==null) return new ArrayList<>();
        List<Integer> list=new ArrayList<>();
        LinkedList<TreeNode> linkedList=new LinkedList<>();
        while (root!=null || !linkedList.isEmpty()){
            if (root==null){
                root=linkedList.pop();
                root=root.right;
            }else {
                list.add(root.val);
                linkedList.push(root);
                root=root.left;
            }
        }
        return list;
    }
    //98. 验证二叉搜索树
    public static boolean isValidBST(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        helper(root,list);
        for (int i=1;i<list.size();i++){
            if (list.get(i)<=list.get(i-1)){
                return false;
            }
        }
        return true;
    }
    public static void helper(TreeNode root,List<Integer> list){
        if (root==null){
            return;
        }
        helper(root.left,list);
        list.add(root.val);
        helper(root.right,list);
    }
    //230. 二叉搜索树中第K小的元素
    static int cnt=0,ans=0;
    public static int kthSmallest(TreeNode root, int k) {
        kthHelper(root,k);
        return ans;
    }
    public static void kthHelper(TreeNode root,int k){
        if (root==null){
            return;
        }
        kthHelper(root.left,k);
        cnt++;
        if (k==cnt){
            ans=root.val;
            return ;
        }
        kthHelper(root.right,k);
    }
    //199. 二叉树的右视图
    //BFS 取每层的最右边一个节点
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        if (root==null) return list;
        Queue<TreeNode> queue=new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size=queue.size();
            int val=0;
            for (int i=0;i<size;i++){
                TreeNode node=queue.poll();
                val=node.val;
                if (node.left!=null){
                    queue.add(node.left);
                }
                if (node.right!=null){
                    queue.add(node.right);
                }
            }
            list.add(val);
        }
        return list;
    }
    //114. 二叉树展开为链表
    //进阶 O(1)空间 先序遍历保存节点至List，遍历List设置left为null，并将right指向下一个节点
    public void flatten(TreeNode root) {
        List<TreeNode> list=new ArrayList<>();
        flattenHelper(root,list);
        for (int i=1;i<list.size();i++){
            TreeNode pre=list.get(i-1);
            pre.left=null;
            pre.right=list.get(i);
        }
    }
    public static void flattenHelper(TreeNode root,List<TreeNode> list){
        if (root==null){
            return;
        }
        list.add(root);
        flattenHelper(root.left,list);
        flattenHelper(root.right,list);
    }
    //todo 105. 从前序与中序遍历序列构造二叉树
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return null;
    }
    //437. 路径总和 III

    //BFS+DFS,选取每个节点做为根节点进行DFS，寻找满足条件的路径
    public static int sum=0;
    public static int pathSum(TreeNode root, int targetSum) {
        Queue<TreeNode> queue=new ArrayDeque<>();
        if (root==null){
            return 0;
        }
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node=queue.poll();
            pathSumHelper(node,targetSum,0);
            if (node.left!=null){
                queue.add(node.left);
            }
            if (node.right!=null){
                queue.add(node.right);
            }
        }
        return sum;
    }
    public static void pathSumHelper(TreeNode root,int targetSum,long cur){
        if (root==null){
            return;
        }
        cur+=root.val;
        if (cur==targetSum){
            sum++;
        }
        pathSumHelper(root.left,targetSum,cur);
        pathSumHelper(root.right,targetSum,cur);
    }

    //todo 236. 二叉树的最近公共祖先
    //后序遍历
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null||p==null||q==null){
            return null;
        }
        lowestCommonAncestor(root.left,p,q);
        lowestCommonAncestor(root.right,p,q);
        return null;
    }
    public static void main(String[] args) {
//                   3
//                 /    \
//                5       1
//              /   \    /  \
//             6     2  0    8
//                  / \
//                 7   4
//
//
        TreeNode root=new TreeNode(3);
        root.left=new TreeNode(5);
        root.left.left=new TreeNode(6);
//        root.left.left.left=new TreeNode(3);
//        root.left.left.right=new TreeNode(-2);
        root.left.right=new TreeNode(2);
        root.left.right.left=new TreeNode(7);
        root.left.right.right=new TreeNode(4);
        root.right=new TreeNode(1);
        root.right.right=new TreeNode(8);
        root.right.left=new TreeNode(0);
        //System.out.println(isValidBST(root));
        //System.out.println(kthSmallest(root,3));
        //System.out.println(rightSideView(root));
        int[] preorder={3,9,20,15,7};
        int[] inorder={9,3,15,20,7};
        //TreeNode tree= buildTree(preorder,inorder);
        //System.out.println(pathSum(root,3));
    }
}
