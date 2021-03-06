package com.example.erp.common;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeBuilder {

    public static List<TreeNode> build(List<TreeNode> treeNodes,Integer topPid){
        List<TreeNode> nodes = new ArrayList<>();
        for (TreeNode n1 : treeNodes) {
            if (n1.getPid()==topPid){
                nodes.add(n1);
            }
            for (TreeNode n2 : treeNodes){
                if (n2.getPid()==n1.getId()){
                    n1.getChildren().add(n2);
                }
            }
        }
        return nodes;
    }
}
