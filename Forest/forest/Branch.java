package forest;

import java.awt.Graphics;
import java.awt.Point;

/**
 * 樹状整列におけるブランチ (枝) を担うクラスになります。
 */
public class Branch {

    /**
     * ブランチ(枝)の始点となるノードを記憶するフィールドです。
     */
    private Node start;

    /**
     * ブランチ(枝)の終点となるノードを記憶するフィールドです。
     */
    private Node end;

    /**
     * このクラスのインスタンスを生成するコンストラクタです。
     * 
     * @param from ブランチ (枝) の始点となるノード
     * @param to   ブランチ (枝) の終点となるノード
     */
    public Branch(Node from, Node to) {
        super();
		this.start = from;
		this.end = to;
    }

    /**
     * ブランチ(枝)を描画するメソッドです。
     * 
     * @param aGraphics グラフィクス (描画コンテクスト)
     */
    public void draw(Graphics aGraphics) {
        // 始点ノードと終点ノードの中心座標を計算して線を引く
		Point startCenter = new Point(start.getLocation().x + start.getExtent().x / 2, start.getLocation().y + start.getExtent().y / 2);
        Point endCenter = new Point(end.getLocation().x + end.getExtent().x / 2, end.getLocation().y + end.getExtent().y / 2);
        aGraphics.drawLine(startCenter.x, startCenter.y, endCenter.x, endCenter.y);
    }

    /**
     * ブランチ(枝)の終点となるノードを応答するメソッドです。
     * 
     * @return 終点ノード
     */
    public Node end() {
        return this.end;
    }

    /**
     * ブランチ(枝)の始点となるノードを応答するメソッドです。
     * 
     * @return 始点ノード
     */
    public Node start() {
        return this.start;
    }

    /**
     * 自分自身を文字列に変換するメソッドです。
     * 
     * @return 自分自身を表す文字列
     */
    @Override
    public String toString() {
        return "Branch(" + this.start.getName() + " -> " + this.end.getName() + ")";
    }
}