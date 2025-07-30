package forest;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Point;
import mvc.View;
import mvc.Model;
import mvc.Controller;

/**
 * 樹状整列におけるMVCのビュー (V) を担うクラスになります。
 */
public class SpiroView extends View {

    /**
     * このクラスのインスタンスを生成するコンストラクタです。
     * 
     * @param aModel モデル (Modelのインスタンス)
     */
    public SpiroView(SpiroModel aModel) {
        super(aModel, new SpiroController());
    }

    /**
     * このパネル(ビュー) の描画が必要になったときに動作するメソッドです。
     * 
     * @param aGraphics グラフィクス (描画コンテクスト)
     */
    @Override
    public void paintComponent(Graphics aGraphics) {
        // 1. 背景を塗りつぶします。
        aGraphics.setColor(this.getBackground());
        aGraphics.fillRect(0, 0, this.getWidth(), this.getHeight());

        SpiroModel aModel = (SpiroModel) this.model;
        if (aModel == null)
            return;
        Forest forest = aModel.forest();
        if (forest == null)
            return;

        // 2. スクロール量を考慮して描画の原点をずらします。
        Graphics g2 = (Graphics) aGraphics.create();

        Point scroll = this.scrollAmount();
        g2.translate(-scroll.x, -scroll.y);

        // 3. Forestオブジェクトに直接描画を委譲します。
        // これにより、レイアウト計算の途中経過が描画されます。
        forest.draw(g2);

        g2.dispose(); // コピーしたGraphicsオブジェクトを破棄します。

        // 4. Forest全体のサイズに合わせてビューの推奨サイズを設定し、
        // スクロールバーが正しく機能するようにします。
        Dimension newSize = forest.bounds().getSize();
        if (!this.getPreferredSize().equals(newSize)) {
            this.setPreferredSize(newSize);
            this.revalidate(); // レイアウトを再検証させます。
        }
    }

    /**
     * 指定された位置 (座標) にノードが存在するかを調べるメソッドです。
     * 
     * @param aPoint 位置 (ビュー座標)
     * @return ノード、もしも見つからなかった場合には、nullを応答します。
     */
    public Node whichOfNodes(Point aPoint) {
        SpiroModel aModel = (SpiroModel) this.model;
        if (aModel == null || aModel.forest() == null)
            return null;

        Point scroll = this.scrollAmount();
        Point modelPoint = new Point(aPoint.x + scroll.x, aPoint.y + scroll.y);

        return aModel.forest().whichOfNodes(modelPoint);
    }

    /**
     * このビューが保持しているモデルを応答します。
     * テストクラスからprotectedなフィールド(model)にアクセスするために用意されています。
     * 
     * @return このビューに紐付いたモデル
     */
    public Model getModel() {
        return this.model;
    }

    /**
     * 
     * このビューが保持しているコントローラを応答します。
     * 
     * テストクラスからprotectedなフィールド(controller)にアクセスするために用意されています。
     * 
     * @return このビューに紐付いたコントローラ
     */
    public Controller getController() {
        return this.controller;
    }
}