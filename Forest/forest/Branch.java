package forest;

import java.awt.Graphics;

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
        // TODO: 実装する
    }

    /**
     * ブランチ(枝)を描画するメソッドです。
     * 
     * @param aGraphics グラフィクス (描画コンテクスト)
     */
    public void draw(Graphics aGraphics) {
        // TODO: 実装する
    }

    /**
     * ブランチ(枝)の終点となるノードを応答するメソッドです。
     * 
     * @return 終点ノード
     */
    public Node end() {
        // TODO: 実装する
        return null;
    }

    /**
     * ブランチ(枝)の始点となるノードを応答するメソッドです。
     * 
     * @return 始点ノード
     */
    public Node start() {
        // TODO: 実装する
        return null;
    }

    /**
     * 自分自身を文字列に変換するメソッドです。
     * 
     * @return 自分自身を表す文字列
     */
    @Override
    public String toString() {
        // TODO: 実装する
        return super.toString();
    }
}