package forest;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

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
        super();
		this.nodes = new ArrayList<Node>();
		this.branches = new ArrayList<Branch>();
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
        // TODO: 実装する
    }

    /**
     * 樹状整列するセカンドレベル(二番階層) のメソッドです。
     * @param aModel モデル 
     */
    public void arrange(SpiroModel aModel) {
        // TODO: 実装する
    }

    /**
     * 樹状整列する再帰レベル (N番階層) のメソッドです。
     * @param aNode ノード 
     * @param aPoint ノードの位置(座標) 
     * @param aModel モデル (nullのときはアニメーションを行わない) 
     * @return 樹状整列に必要だった大きさ(幅と高さ) 
     */
    protected Point arrange(Node aNode, Point aPoint, SpiroModel aModel) {
        // TODO: 実装する
        return null;
    }

    /**
     * フォレスト(木・林・森・亜格子状の森) の領域 (矩形)を応答するメソッドです。
     * @return フォレスト領域 (矩形) 
     */
    public Rectangle bounds() {
        // TODO: 実装する
        return null;
    }

    /**
     * フォレスト(木・林・森・亜格子状の森)を描画するメソッドです。
     * @param aGraphics グラフィクス (描画コンテクスト) 
     */
    public void draw(Graphics aGraphics) {
        // 保持している全てのブランチとノードを描画する
		for (Branch aBranch : this.branches)
		{
			aBranch.draw(aGraphics);
		}
		for (Node aNode : this.nodes)
		{
			aNode.draw(aGraphics);
		}
    }

    /**
     * フォレスト(木・林・森・亜格子状の森)の領域 (矩形)を水に流す(チャラにする) メソッドです。
     */
    public void flushBounds() {
        // TODO: 実装する
    }

    /**
     * チックタックの間、スリープし、モデルが変化した、と騒ぐ(広める: 放送する) メソッドです。
     * @param aModel モデル 
     */
    protected void propagate(SpiroModel aModel) {
        // TODO: 実装する
    }

    /**
     * フォレストの根元 (ルート)となるノード群を応答するメソッドです。
     * @return ルートノード群 
     */
    public ArrayList<Node> rootNodes() {
        // TODO: 実装する
        return null;
    }

    /**
     * 引数で指定されたノード群をノード名でソート (並び替えを)するメソッドです。
     * @param nodeCollection ノード群 
     * @return ソートされたノード群 
     */
    protected ArrayList<Node> sortNodes(ArrayList<Node> nodeCollection) {
        // TODO: 実装する
        return null;
    }

    /**
     * 引数で指定されたノードのサブノード群を応答するメソッドです。
     * @param aNode ノード 
     * @return サブノード群 
     */
    public ArrayList<Node> subNodes(Node aNode) {
        ArrayList<Node> subNodes = new ArrayList<Node>();
		for (Branch aBranch : this.branches)
		{
			if (aBranch.start() == aNode)
			{
				subNodes.add(aBranch.end());
			}
		}
		return subNodes;

    }

    /**
     * 引数で指定されたノードのスーパーノード群を応答するメソッドです。
     * @param aNode ノード 
     * @return スーパーノード群 
     */
    public ArrayList<Node> superNodes(Node aNode) {
        // TODO: 実装する
        return null;
    }

    /**
     * 自分自身を文字列に変換するメソッドです。
     * @return 自分自身を表す文字列 
     */
    @Override
    public String toString() {
        return "Forest(nodes: " + this.nodes.size() + ", branches: " + this.branches.size() + ")";
    }

    /**
     * 指定された位置(座標)にノードが存在するかを調べるメソッドです。
     * @param aPoint 位置 (モデル座標) 
     * @return ノード、もしも見つからなかった場合には、nullを応答します。
     */
    public Node whichOfNodes(Point aPoint) {
        // TODO: 実装する
        return null;
    }
}