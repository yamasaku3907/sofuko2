package forest;

import java.awt.Graphics;
import java.awt.Point;
import mvc.View;

/**
 * 樹状整列におけるMVCのビュー (V) を担うクラスになります。
 */
public class SpiroView extends View {

    /**
     * このクラスのインスタンスを生成するコンストラクタです。
     * @param aModel モデル (Modelのインスタンス) 
     */
    public SpiroView(SpiroModel aModel) {
        super(aModel);
        // TODO: 実装する
    }

    /**
     * このパネル(ビュー) の描画が必要になったときに動作するメソッドです。
     * @param aGraphics グラフィクス (描画コンテクスト) 
     */
    @Override
    public void paintComponent(Graphics aGraphics) {
        // TODO: 実装する
    }

    /**
     * 指定された位置 (座標) にノードが存在するかを調べるメソッドです。
     * @param aPoint 位置 (ビュー座標) 
     * @return ノード、もしも見つからなかった場合には、nullを応答します。
     */
    public Node which0fNodes(Point aPoint) {
        // TODO: 実装する
        return null;
    }
}