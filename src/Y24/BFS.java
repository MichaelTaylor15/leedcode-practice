package Y24;

import java.util.*;

public class BFS {

    //102. 二叉树的层序遍历
    public static List<List<Integer>> levelOrder(TreeNodeII root) {
        List<List<Integer>> res=new ArrayList<>();
        Queue<TreeNodeII> queue=new ArrayDeque<>();
        if (root==null) return null;
        queue.add(root);
        while (!queue.isEmpty()){
            List<Integer> list=new ArrayList<>();
            int size=queue.size();
            //只遍历一层的node
            while (size-->0){
                TreeNodeII node=queue.poll();
                list.add(node.val);
                if (node.left!=null){
                    queue.add(node.left);
                }
                if (node.right!=null){
                    queue.add(node.right);
                }
            }
            res.add(list);
        }
        return res;
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
        levelOrder(root);
    }
}
