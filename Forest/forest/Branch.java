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
        if (this.start == null || this.end == null) return;
        
        Point startLocation = this.start.getLocation();
        Point endLocation = this.end.getLocation();
        Point startExtent = this.start.getExtent();
        Point endExtent = this.end.getExtent();

        // 始点ノードの中心下部から、終点ノードの中心上部へ線を引きます。
        int x1 = startLocation.x + startExtent.x / 2;
        int y1 = startLocation.y + startExtent.y;
        int x2 = endLocation.x + endExtent.x / 2;
        int y2 = endLocation.y;

        aGraphics.setColor(Constants.ForegroundColor);
        aGraphics.drawLine(x1, y1, x2, y2);
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
        String startName = (this.start != null) ? this.start.getName() : "null";
        String endName = (this.end != null) ? this.end.getName() : "null";
        return "[Branch=" + startName + " -> " + endName + "]";
    }
}
