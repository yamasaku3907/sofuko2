package forest;

import java.io.File;
import java.util.ArrayList;
import mvc.Model;

/**
 * 樹状整列におけるMVCのモデル (M) を担うクラスになります。
 */
public class SpiroModel extends Model {

    /**
     * 樹状整列それ自身を記憶しておくフィールドです。
     */
    private Forest forest;

    /**
     * このクラスのインスタンスを生成するコンストラクタです。
     * @param aFile 樹状整列データファイル 
     */
    public SpiroModel(File aFile) {
        // TODO: 実装する
    }

    /**
     * アニメーションを行うメソッドです。
     */
    public void animate() {
        // TODO: 実装する
    }

    /**
     * 樹状整列を行うメソッドです。
     */
    public void arrange() {
        // TODO: 実装する
    }

    /**
     * 自分自身が変化したことを依存物たちに放送 (updateを依頼) するメソッドです。
     */
    @Override
    public void changed() {
        // TODO: 実装する
    }

    /**
     * 樹状整列それ自身を応答するメソッドです。
     * @return 樹状整列それ自身 
     */
    public Forest forest() {
        // TODO: 実装する
        return null;
    }

    /**
     * 樹状整列データファイルから樹状整列それ自身を生成するメソッドです。
     * @param aFile 樹状整列データファイル 
     */
    protected void read(File aFile) {
        // TODO: 実装する
    }

    /**
     * 樹状整列の根元 (ルート) になるノードを探し出して応答するメソッドです。
     * @return ルートノード、ただし、見つからないときはnull 
     */
    public Node root() {
        // TODO: 実装する
        return null;
    }

    /**
     * 樹状整列の根元(ルート)になるノードたちを探し出して応答するメソッドです。
     * @return ルートノードたち、ただし、見つからないときは空リスト 
     */
    public ArrayList<Node> roots() {
        // TODO: 実装する
        return null;
    }
}