package forest;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 樹状整列におけるフォレスト (木・林・森・亜格子状の森)を担うクラス。
 */
public class Forest {

    /**
     * ノード(節)群(たち)を記憶するフィールド。
     */
    private ArrayList<Node> nodes;

    /**
     * ブランチ(枝) 群(たち)を記憶するフィールド。
     */
    private ArrayList<Branch> branches;

    /**
     * 樹状整列したフォレスト (森)の領域 (矩形)を記憶するフィールド。
     */
    private Rectangle bounds;

    /**
     * このクラスのインスタンスを生成するコンストラクタ。
     */
    public Forest() {
        this.nodes = new ArrayList<>();
        this.branches = new ArrayList<>();
        this.bounds = new Rectangle(0, 0, 0, 0);
    }

    /**
     * ブランチ(枝)を追加するメソッド。
     * @param aBranch ブランチ (枝) 
     */
    public void addBranch(Branch aBranch) {
        this.branches.add(aBranch);
    }

    /**
     * ノード(節)を追加するメソッド。
     * @param aNode ノード (節) 
     */
    public void addNode(Node aNode) {
        this.nodes.add(aNode);
    }

    /**
     * 樹状整列するトップ (一番上位)のメソッド。
     */
    public void arrange() {
        this.arrange(null);
    }

    /**
     * 樹状整列するセカンドレベル(二番階層) のメソッド。
     * @param aModel モデル 
     */
    public void arrange(SpiroModel aModel) {
        Integer counter = 0;
        for (Node node : this.nodes) {
            Integer height = node.getExtent().y + Constants.Margin.y + Constants.Interval.y;
            node.setStatus(Constants.UnVisited);
            node.setLocation(new Point(0, height*counter++));
        }

        Point aPoint = new Point(0,0);
		ArrayList<Node> rootNodes = this.rootNodes();
        for (Node node: rootNodes) {
            Point secondPoint = this.arrange(node, aPoint, aModel);
			aPoint = new Point(0, secondPoint.y + Constants.Interval.y);
        }
        this.flushBounds();
    }

    /**
     * 樹状整列する再帰レベル (N番階層) のメソッド。
     * @param aNode ノード 
     * @param aPoint ノードの位置(座標) 
     * @param aModel モデル (nullのときはアニメーションを行わない) 
     * @return 樹状整列に必要だった大きさ(幅と高さ) 
     */
    protected Point arrange(Node aNode, Point aPoint, SpiroModel aModel) {
        aNode.setStatus(Constants.Visited);
        
        ArrayList<Node> subNodes = this.sortNodes(this.subNodes(aNode));
        if (subNodes.isEmpty()) {
            // 子がいない場合、自身の位置を確定して自身のサイズを返す
            aNode.setLocation(aPoint);
            this.propagate(aModel);
            return new Point(aNode.getExtent().x, aNode.getExtent().y);
        }

        // 子ノードたちを再帰的に配置
        Point subTreeStartPoint = new Point(aPoint.x + aNode.getExtent().x + Constants.Interval.x, aPoint.y);
        int totalSubTreeHeight = 0;
        int maxSubTreeWidth = 0;

        for (Node subNode : subNodes) {
            Point childStartPoint = new Point(subTreeStartPoint.x, subTreeStartPoint.y + totalSubTreeHeight);
            Point childExtent = this.arrange(subNode, childStartPoint, aModel);
            // 子ノードが使った高さと、子ノード同士の間隔を加算
            totalSubTreeHeight += childExtent.y + Constants.Interval.y;
            maxSubTreeWidth = Math.max(maxSubTreeWidth, childExtent.x);
        }
        // 最後のノードの下の間隔は不要なので引く
        if (totalSubTreeHeight > 0) {
            totalSubTreeHeight -= Constants.Interval.y;
        }
        
        // 親ノードのY座標を、子ノード群の高さの中心にくるように計算
        int middleOfChildren = subTreeStartPoint.y + (totalSubTreeHeight / 2);
        int newParentY = middleOfChildren - (aNode.getExtent().y / 2);
        aNode.setLocation(new Point(aPoint.x, newParentY));
        this.propagate(aModel); // 親ノードの位置を更新したので再描画

        // この部分木全体が占める幅と高さを計算
        int totalWidth = aNode.getExtent().x + Constants.Interval.x + maxSubTreeWidth;
        int totalHeight = Math.max(aNode.getExtent().y, totalSubTreeHeight);

        return new Point(totalWidth, totalHeight);
    }

    /**
     * フォレスト(木・林・森・亜格子状の森) の領域 (矩形)を応答するメソッド。
     * @return フォレスト領域 (矩形) 
     */
    public Rectangle bounds() {
        this.bounds = new Rectangle();
        this.nodes.forEach(aNode -> this.bounds.add(aNode.getBounds()));
        return this.bounds;
    }

    /**
     * フォレスト(木・林・森・亜格子状の森)を描画するメソッド。
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
     * フォレスト(木・林・森・亜格子状の森)の領域 (矩形)を水に流す(チャラにする) メソッド。
     */
    public void flushBounds() {
        this.bounds = new Rectangle(0, 0, Constants.Margin.x, Constants.Margin.y);
    }

    /**
     * チックタックの間、スリープし、モデルが変化した、と騒ぐ(広める: 放送する) メソッド。
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
     * フォレストの根元 (ルート)となるノード群を応答するメソッド。
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
     * 引数で指定されたノード群をノード名でソート (並び替えを)するメソッド。
     * @param nodeCollection ノード群 
     * @return ソートされたノード群 
     */
    protected ArrayList<Node> sortNodes(ArrayList<Node> nodeCollection) {
        Collections.sort(nodeCollection, Comparator.comparing(Node::getName));
        return nodeCollection;
    }

    /**
     * 引数で指定されたノードのサブノード群を応答するメソッド。
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
     * 引数で指定されたノードのスーパーノード群を応答するメソッド。
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
