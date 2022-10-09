package utils;
import java.util.List;

/**
 * 描述：
 * 创建日期：2022年10月08 16:40:
 * 
 * @author gong zhao
 **/

public class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
