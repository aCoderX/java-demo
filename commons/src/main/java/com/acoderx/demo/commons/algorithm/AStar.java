package com.acoderx.demo.commons.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by xudi on 2018/5/3.
 * A* 寻路算法
 */
public class AStar {

    public static void main(String[] args){
        List<Node> wall = new ArrayList<>();
        wall.add(new AStar.Node(3,1));
        wall.add(new AStar.Node(3,2));
        wall.add(new AStar.Node(3,3));
        AStar star = new AStar(wall);
        Node result = star.aStarSearch(new Node(1,2),new Node(5,2));
        if (result != null) {
            System.out.println(result.getX() + "--" + result.getY());
            while (result.getParent() != null) {
                result = result.getParent();
                System.out.println(result.getX() + "--" + result.getY());
            }
        }
    }

    private List<Node> openList = new ArrayList<>();
    private List<Node> closeList = new ArrayList<>();
    private List<Node> wallList = new ArrayList<>();

    public AStar(List<Node> wallList) {
        this.wallList = wallList;
    }

    public Node aStarSearch(Node start, Node end) {
        // 把起点加入 open list
        openList.add(start);
        //主循环，每一轮检查一个当前方格节点
        while (openList.size() > 0) {
            // 在OpenList中查找 F值最小的节点作为当前方格节点
            Node current = findOpenListMinNode();
            // 当前方格节点从open list中移除
            openList.remove(current);
            // 当前方格节点进入 close list
            closeList.add(current);
            // 找到所有邻近节点
            List<Node> neighbors = findNeighbors(current);
            for (Node node : neighbors) {
                if (!openList.contains(node) && !wallList.contains(node) && !closeList.contains(node)) {
                    //邻近节点不在OpenList中，标记父亲、G、H、F，并放入OpenList
                    markAndInvolve(current, end, node);
                }
            }
            //如果终点在OpenList中，直接返回终点格子
            for (Node node : openList) {
                if (node.equals(end)) {
                    return node;
                }
            }
        }
        //OpenList用尽，仍然找不到终点，说明终点不可到达，返回空
        return null;
    }
    private Node findOpenListMinNode(){
        return openList.stream().min(Comparator.comparing((s) -> s.getH() + s.getG())).get();
    }
    private List<Node> findNeighbors(Node node) {
        List<Node> result = new ArrayList<>();
        //上
        result.add(new Node(node.getX(), node.getY() + 1));
        //下
        result.add(new Node(node.getX(), node.getY() - 1));
        //左
        result.add(new Node(node.getX() - 1, node.getY()));
        //右
        result.add(new Node(node.getX() + 1, node.getY()));
        return result;
    }
    private void markAndInvolve(Node current,Node end,Node node) {
        int h = Math.abs(end.getX() - node.getX()) + Math.abs(end.getY() - node.getY());
        node.setH(h);
        node.setG(current.getG()+1);
        node.setParent(current);
        openList.add(node);
    }

    static class Node{
        private int x;
        private int y;
        private int g;
        private int h;
        private Node parent;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public Node() {
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public int getH() {
            return h;
        }

        public void setH(int h) {
            this.h = h;
        }

        public int getG() {
            return g;
        }

        public void setG(int g) {
            this.g = g;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;

            Node node = (Node) o;

            if (getX() != node.getX()) return false;
            return getY() == node.getY();
        }

        @Override
        public int hashCode() {
            int result = getX();
            result = 31 * result + getY();
            return result;
        }
    }
}
