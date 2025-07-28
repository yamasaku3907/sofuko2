package forest;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 樹状整列におけるフォレスト (木・林・森・亜格子状の森)を担うクラスになります。
 */
public class Forest {

    /**
     * ノード(節)群(たち)を記憶するフィールドです。
     */
    private ArrayList<Node> nodes;

    /**
     * ブランチ(枝) 群(たち)を記憶するフィールドです。
     */
    private ArrayList<Branch> branches;

    /**
     * 樹状整列したフォレスト (森)の領域 (矩形)を記憶するフィールドです。
     */
    private Rectangle bounds;

    /**
     * このクラスのインスタンスを生成するコンストラクタです。
     */
    public Forest() {
        this.nodes = new ArrayList<>();
        this.branches = new ArrayList<>();
        this.bounds = new Rectangle(0, 0, 0, 0);
    }

    /**
     * ブランチ(枝)を追加するメソッドです。
     * @param aBranch ブランチ (枝) 
     */
    public void addBranch(Branch aBranch) {
        this.branches.add(aBranch);
    }

    /**
     * ノード(節)を追加するメソッドです。
     * @param aNode ノード (節) 
     */
    public void addNode(Node aNode) {
        this.nodes.add(aNode);
    }

    /**
     * 樹状整列するトップ (一番上位)のメソッドです。
     */
    public void arrange() {
        this.arrange(null);
    }

    /**
     * 樹状整列するセカンドレベル(二番階層) のメソッドです。
     * @param aModel モデル 
     */
    public void arrange(SpiroModel aModel) {
        this.flushBounds();
        for (Node node : this.nodes) {
            node.setStatus(Constants.UnVisited);
        }
        ArrayList<Node> roots = this.sortNodes(this.rootNodes());
        Point currentTopLeft = new Point(Constants.Margin.x, Constants.Margin.y);
        for (Node root : roots) {
            if (root.getStatus().equals(Constants.Visited)) continue;
            Point extent = this.arrange(root, new Point(currentTopLeft.x, currentTopLeft.y), aModel);
            currentTopLeft.x += extent.x; // 次の木の開始位置をX軸方向にずらす
        }
    }

    /**
     * 樹状整列する再帰レベル (N番階層) のメソッドです。
     * @param aNode ノード 
     * @param aPoint ノードの位置(座標) 
     * @param aModel モデル (nullのときはアニメーションを行わない) 
     * @return 樹状整列に必要だった大きさ(幅と高さ) 
     */
    protected Point arrange(Node aNode, Point aPoint, SpiroModel aModel) {
        if (aNode.getStatus().equals(Constants.Visited)) return new Point(0, 0);

        aNode.setStatus(Constants.Visited);
        ArrayList<Node> subNodes = this.sortNodes(this.subNodes(aNode));

        Point subTreesExtent = new Point(0, 0);
        Point currentSubPoint = new Point(aPoint.x, aPoint.y + aNode.getExtent().y + Constants.Interval.y);

        for (Node subNode : subNodes) {
            Point childExtent = this.arrange(subNode, new Point(currentSubPoint.x, currentSubPoint.y), aModel);
            currentSubPoint.x += childExtent.x;
            subTreesExtent.x += childExtent.x;
            subTreesExtent.y = Math.max(subTreesExtent.y, childExtent.y);
        }

        int nodeWidth = aNode.getExtent().x;
        int childrenWidth = subTreesExtent.x > 0 ? subTreesExtent.x - Constants.Interval.x : 0;
        int nodeX = aPoint.x + Math.max(0, (childrenWidth - nodeWidth) / 2);
        aNode.setLocation(new Point(nodeX, aPoint.y));

        if (aModel != null) {
            this.propagate(aModel);
        }

        int totalWidth = Math.max(nodeWidth, childrenWidth);
        int totalHeight = aNode.getExtent().y + (subNodes.isEmpty() ? 0 : Constants.Interval.y + subTreesExtent.y);

        this.bounds.add(aNode.getBounds());

        return new Point(totalWidth + Constants.Interval.x, totalHeight);
    }

    /**
     * フォレスト(木・林・森・亜格子状の森) の領域 (矩形)を応答するメソッドです。
     * @return フォレスト領域 (矩形) 
     */
    public Rectangle bounds() {
        return this.bounds;
    }

    /**
     * フォレスト(木・林・森・亜格子状の森)を描画するメソッドです。
     * @param aGraphics グラフィクス (描画コンテクスト) 
     */
    public void draw(Graphics aGraphics) {
        for (Branch branch : this.branches) {
            branch.draw(aGraphics);
        }
        for (Node node : this.nodes) {
            node.draw(aGraphics);
        }
    }

    /**
     * フォレスト(木・林・森・亜格子状の森)の領域 (矩形)を水に流す(チャラにする) メソッドです。
     */
    public void flushBounds() {
        this.bounds = new Rectangle(0, 0, Constants.Margin.x, Constants.Margin.y);
    }

    /**
     * チックタックの間、スリープし、モデルが変化した、と騒ぐ(広める: 放送する) メソッドです。
     * @param aModel モデル 
     */
    protected void propagate(SpiroModel aModel) {
        try {
            Thread.sleep(Constants.SleepTick);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (aModel != null) {
            aModel.changed();
        }
    }

    /**
     * フォレストの根元 (ルート)となるノード群を応答するメソッドです。
     * @return ルートノード群 
     */
    public ArrayList<Node> rootNodes() {
        ArrayList<Node> roots = new ArrayList<>();
        for (Node aNode : this.nodes) {
            if (this.superNodes(aNode).isEmpty()) {
                roots.add(aNode);
            }
        }
        return roots;
    }

    /**
     * 引数で指定されたノード群をノード名でソート (並び替えを)するメソッドです。
     * @param nodeCollection ノード群 
     * @return ソートされたノード群 
     */
    protected ArrayList<Node> sortNodes(ArrayList<Node> nodeCollection) {
        Collections.sort(nodeCollection, Comparator.comparing(Node::getName));
        return nodeCollection;
    }

    /**
     * 引数で指定されたノードのサブノード群を応答するメソッドです。
     * @param aNode ノード 
     * @return サブノード群 
     */
    public ArrayList<Node> subNodes(Node aNode) {
        ArrayList<Node> subs = new ArrayList<>();
        for (Branch aBranch : this.branches) {
            if (aBranch.start().equals(aNode)) {
                subs.add(aBranch.end());
            }
        }
        return subs;
    }

    /**
     * 引数で指定されたノードのスーパーノード群を応答するメソッドです。
     * @param aNode ノード 
     * @return スーパーノード群 
     */
    public ArrayList<Node> superNodes(Node aNode) {
        ArrayList<Node> supers = new ArrayList<>();
        for (Branch aBranch : this.branches) {
            if (aBranch.end().equals(aNode)) {
                supers.add(aBranch.start());
            }
        }
        return supers;
    }

    /**
     * 自分自身を文字列に変換するメソッドです。
     * @return 自分自身を表す文字列 
     */
    @Override
    public String toString() {
        return "[Forest=" + this.nodes.size() + " nodes, " + this.branches.size() + " branches]";
    }

    /**
     * 指定された位置(座標)にノードが存在するかを調べるメソッドです。
     * @param aPoint 位置 (モデル座標) 
     * @return ノード、もしも見つからなかった場合には、nullを応答します。
     */
    public Node whichOfNodes(Point aPoint) {
        // 描画が後になるもの（手前にあるもの）から探索
        for (int i = this.nodes.size() - 1; i >= 0; i--) {
            Node node = this.nodes.get(i);
            if (node.getBounds().contains(aPoint)) {
                return node;
            }
        }
        return null;
    }
}
