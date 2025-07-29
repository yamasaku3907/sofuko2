package forest;

import java.awt.Point;
import java.awt.event.MouseEvent;
import mvc.Controller;

/**
 * 樹状整列におけるMVCのコントローラ (C) を担うクラスになります。
 */
public class SpiroController extends Controller {

    /**
     * このクラスのインスタンスを生成するコンストラクタです。
     */
    public SpiroController() {
        super();
    }

    /**
     * マウスのボタンをクリックしたときに動作するメソッドです。
     * @param aMouseEvent マウスイベント 
     */
    @Override
    public void mouseClicked(MouseEvent aMouseEvent) {
        Point aPoint = aMouseEvent.getPoint();
        SpiroView aView = (SpiroView) super.view;
        Node aNode = aView.whichOfNodes(aPoint);

        if (aNode != null) {
            System.out.println("Clicked on: " + aNode.getName());
        }

        // クリックされたらアニメーションを再度実行する
        SpiroModel aModel = (SpiroModel) super.model;
        if (aModel != null) {
            aModel.animate();
        }
    }
}