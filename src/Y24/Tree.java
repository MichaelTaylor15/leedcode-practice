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
    //todo 230. 二叉搜索树中第K小的元素
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
    public static void main(String[] args) {
//        Integer[] arr={1,null,2,3};
//        TreeNode root=new TreeNode();
//        root.val=1;
//        root.left=null;
//        root.right=new TreeNode();
//        root.right.val=2;
//        root.right.left=new TreeNode();
//        root.right.left.val=3;
//        System.out.println(preorderTraversal(root));
        TreeNode root=new TreeNode(5);
        root.left=new TreeNode(3);
        root.left.right=new TreeNode(4);
        root.left.left=new TreeNode(2);
        root.left.left.left=new TreeNode(1);
        root.right=new TreeNode(6);
        //System.out.println(isValidBST(root));
        System.out.println(kthSmallest(root,3));
    }
}
