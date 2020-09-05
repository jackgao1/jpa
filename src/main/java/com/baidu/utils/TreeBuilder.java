package com.baidu.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeBuilder {

    @SuppressWarnings("unchecked")
    public List<? extends Node> buildListToTree(List<? extends Node> dirs) {
        List<Node> roots = findRoots(dirs);
        List<Node> notRoots = (List<Node>) CollectionUtils.subtract(dirs, roots);
        for (Node root : roots) {
            root.setChildren(findChildren(root, notRoots));
        }
        return roots;
    }

    private List<Node> findRoots(List<? extends Node> allNodes) {
        List<Node> results = new ArrayList<Node>();
        for (Node node : allNodes) {
            boolean isRoot = true;
            for (Node comparedOne : allNodes) {
                if (StringUtils.isNotBlank(node.getParentId()) && node.getParentId().equals(comparedOne.getId())) {
                    isRoot = false;
                    break;
                }
            }
            if (isRoot) {
                node.setLevel(0);
                results.add(node);
                node.setRootId(node.getId());
            }
        }
        return results;
    }

    @SuppressWarnings("unchecked")
    private List<Node> findChildren(Node root, List<Node> allNodes) {
        List<Node> children = new ArrayList<Node>();

        for (Node comparedOne : allNodes) {
            if (StringUtils.isNotBlank(comparedOne.getParentId()) && comparedOne.getParentId().equals(root.getId())) {
                comparedOne.setParent(root);
                comparedOne.setLevel(root.getLevel() + 1);
                children.add(comparedOne);
            }
        }
        List<Node> notChildren = (List<Node>) CollectionUtils.subtract(allNodes, children);
        for (Node child : children) {
            List<Node> tmpChildren = findChildren(child, notChildren);
            if (tmpChildren == null || tmpChildren.size() < 1) {
                child.setLeaf(true);
            } else {
                child.setLeaf(false);
            }
            child.setChildren(tmpChildren);
        }
        return children;
    }

    private List<Node> getLeafChildren(List<Node> resultList, List<Node> children) {
        for (Node node : children) {
            if (node.isLeaf()) {
                resultList.add(node);
            } else {
                getLeafChildren(resultList, node.getChildren());
            }
        }
        return resultList;
    }

    public static class Node{
        private String id;
        private String parentId;
        private Node parent;
        private List<Node> children;
        private int level;
        private String rootId;
        private boolean leaf;
        private Map<String, Object> other;

        public Node(){}
        public Node(String id, String parentId){
            this.id = id;
            this.parentId = parentId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParentId() {
            return parentId;
        }
        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
        @JsonIgnore
        public Node getParent() {
            return parent;
        }
        public void setParent(Node parent) {
            this.parent = parent;
        }
        public List<Node> getChildren() {
            return children;
        }
        public void setChildren(List<Node> children) {
            this.children = children;
        }
        public int getLevel() {
            return level;
        }
        public void setLevel(int level) {
            this.level = level;
        }
        public String getRootId() {
            return rootId;
        }
        public void setRootId(String rootId) {
            this.rootId = rootId;
        }
        public boolean isLeaf() {
            return leaf;
        }
        public void setLeaf(boolean leaf) {
            this.leaf = leaf;
        }
        public Map<String, Object> getOther() {
            return other;
        }
        public void setOther(Map<String, Object> other) {
            this.other = other;
        }

        public void put(String key, Object o) {
            if (other == null) {
                other = new HashMap<>();
            }
            other.put(key, o);
        }

        public void remove(String key) {
            if (other == null) {
                return;
            }
            other.remove(key);
        }
    }
}
