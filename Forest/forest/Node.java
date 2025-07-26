package forest;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

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
        // TODO: 実装する
    }

    /**
     * ノード(節)を描画するメソッドです。
     * @param aGraphics グラフィクス (描画コンテクスト) 
     */
    public void draw(Graphics aGraphics) {
        // TODO: 実装する
    }

    /**
     * ノード(節)の描画領域を応答するメソッドです。
     * @return ノード(節)の描画領域 (Rectangleのインスタンス) 
     */
    @Override
    public Rectangle getBounds() {
        // TODO: 実装する
        return super.getBounds();
    }

    /**
     * ノード(節)の大きさを応答するメソッドです。
     * @return ノード(節)の大きさ (幅と高さ) 
     */
    public Point getExtent() {
        // TODO: 実装する
        return null;
    }

    /**
     * ノード(節)の位置を応答するメソッドです。
     * @return ノード(節)の位置(座標) 
     */
    @Override
    public Point getLocation() {
        // TODO: 実装する
        return super.getLocation();
    }

    /**
     * ノード(節)の名前を応答するメソッドです。
     * @return ノード名(ラベル文字列) 
     */
    @Override
    public String getName() {
        // TODO: 実装する
        return super.getName();
    }

    /**
     * ノード(節)の状態を応答するメソッドです。
     * @return ノードの状態 
     */
    public Integer getStatus() {
        // TODO: 実装する
        return null;
    }

    /**
     * ノード(節)の大きさを設定するメソッドです。
     * @param aPoint ノードの大きさ(幅と高さ) 
     */
    public void setExtent(Point aPoint) {
        // TODO: 実装する
    }

    /**
     * ノード(節)の位置を設定するメソッドです。
     * @param aPoint ノードの位置(座標) 
     */
    @Override
    public void setLocation(Point aPoint) {
        // TODO: 実装する
    }

    /**
     * ノード(節)の名前を設定するメソッドです。
     * @param aString ノードの名前(ラベル) 
     */
    @Override
    public void setName(String aString) {
        // TODO: 実装する
    }

    /**
     * ノード(節)の状態を設定するメソッドです。
     * @param anInteger ノードの状態 
     */
    public void setStatus(Integer anInteger) {
        // TODO: 実装する
    }

    /**
     * 文字列の高さを応答するメソッドです。
     * @param string 文字列 
     * @return 文字列の高さ 
     */
    protected int stringHeight(String string) {
        // TODO: 実装する
        return 0;
    }

    /**
     * 文字列の幅を応答するメソッドです。
     * @param string 文字列 
     * @return 文字列の幅 
     */
    protected int stringWidth(String string) {
        // TODO: 実装する
        return 0;
    }

    /**
     * 自分自身を文字列に変換するメソッドです。
     * @return 自分自身を表す文字列 
     */
    @Override
    public String toString() {
        // TODO: 実装する
        return super.toString();
    }
}