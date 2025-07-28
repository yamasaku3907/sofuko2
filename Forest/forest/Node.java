package forest;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.FontMetrics;

/**
 * 樹状整列におけるノード (節)を担うクラスになります。
 */
public class Node extends Component {

    /**
     * ノード名:ラベル文字列を記憶するフィールドです。
     */
    private String name;

    /**
     * ノードの場所(位置: 座標)を記憶するフィールドです。
     */
    private Point location;

    /**
     * ノードの大きさ(幅と高さ)を記憶するフィールドです。
     */
    private Point extent;

    /**
     * 樹状整列する際のノードの状態を記憶するフィールドです。
     */
    private Integer status;

    /**
     * このクラスのインスタンスを生成するコンストラクタです。
     * @param aString ノード名: ラベル文字列 
     */
    public Node(String aString) {
        super();
        this.name = aString;
        this.location = new Point(0, 0);
        this.status = Constants.UnKnown;
        // フォント情報を基にノードの大きさを計算
        this.setFont(Constants.DefaultFont);
        int width = this.stringWidth(aString) + Constants.Margin.x * 2;
        int height = this.stringHeight(aString) + Constants.Margin.y * 2;
        this.extent = new Point(width, height);
    }

    /**
     * ノード(節)を描画するメソッドです。
     * @param aGraphics グラフィクス (描画コンテクスト) 
     */
    public void draw(Graphics aGraphics) {
         // ノードの矩形を描画
        aGraphics.setColor(Constants.BackgroundColor);
        aGraphics.fillRect(this.location.x, this.location.y, this.extent.x, this.extent.y);
        aGraphics.setColor(Constants.ForegroundColor);
        aGraphics.drawRect(this.location.x, this.location.y, this.extent.x, this.extent.y);

        // ノード名を描画
        FontMetrics metrics = aGraphics.getFontMetrics();
        int strX = this.location.x + Constants.Margin.x;
        int strY = this.location.y + metrics.getAscent() + Constants.Margin.y;
        aGraphics.drawString(this.name, strX, strY);
    }

    /**
     * ノード(節)の描画領域を応答するメソッドです。
     * @return ノード(節)の描画領域 (Rectangleのインスタンス) 
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.location, new java.awt.Dimension(this.extent.x, this.extent.y));
    }

    /**
     * ノード(節)の大きさを応答するメソッドです。
     * @return ノード(節)の大きさ (幅と高さ) 
     */
    public Point getExtent() {
        return this.extent;
    }

    /**
     * ノード(節)の位置を応答するメソッドです。
     * @return ノード(節)の位置(座標) 
     */
    @Override
    public Point getLocation() {
        return this.location;
    }

    /**
     * ノード(節)の名前を応答するメソッドです。
     * @return ノード名(ラベル文字列) 
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * ノード(節)の状態を応答するメソッドです。
     * @return ノードの状態 
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * ノード(節)の大きさを設定するメソッドです。
     * @param aPoint ノードの大きさ(幅と高さ) 
     */
    public void setExtent(Point aPoint) {
        this.extent = aPoint;
    }

    /**
     * ノード(節)の位置を設定するメソッドです。
     * @param aPoint ノードの位置(座標) 
     */
    @Override
    public void setLocation(Point aPoint) {
        this.location = aPoint;
    }

    /**
     * ノード(節)の名前を設定するメソッドです。
     * @param aString ノードの名前(ラベル) 
     */
    @Override
    public void setName(String aString) {
        this.name = aString;
    }

    /**
     * ノード(節)の状態を設定するメソッドです。
     * @param anInteger ノードの状態 
     */
    public void setStatus(Integer anInteger) {
        this.status = anInteger;
    }

    /**
     * 文字列の高さを応答するメソッドです。
     * @param string 文字列 
     * @return 文字列の高さ 
     */
    protected int stringHeight(String string) {
        FontMetrics metrics = this.getFontMetrics(this.getFont());
        return metrics.getHeight();
    }

    /**
     * 文字列の幅を応答するメソッドです。
     * @param string 文字列 
     * @return 文字列の幅 
     */
    protected int stringWidth(String string) {
        FontMetrics metrics = this.getFontMetrics(this.getFont());
        return metrics.stringWidth(string);
    }

    /**
     * 自分自身を文字列に変換するメソッドです。
     * @return 自分自身を表す文字列 
     */
    @Override
    public String toString() {
        return "[Node=" + this.name + "]";
    }
}
