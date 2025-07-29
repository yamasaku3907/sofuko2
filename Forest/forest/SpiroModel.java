package forest;

import java.io.File;
import java.util.ArrayList;
import mvc.Model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingUtilities;

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
     * 
     * @param aFile 樹状整列データファイル
     */
    public SpiroModel(File aFile) {
        super();
        this.read(aFile);
        if (this.forest != null) {
            this.arrange();
        }
    }

    /**
     * アニメーションを行うメソッドです。
     */
    public void animate() {
        if (this.forest == null)
            return;
        this.forest.arrange(this);
        this.changed();
    }

    /**
     * 樹状整列を行うメソッドです。
     */
    public void arrange() {
        if (this.forest == null)
            return;
        this.forest.arrange();
        this.changed();
    }

    /**
     * 自分自身が変化したことを依存物たちに放送 (updateを依頼) するメソッドです。
     */
    @Override
    public void changed() {
        SwingUtilities.invokeLater(() -> {
            super.changed(); // これがViewのupdate() -> repaint()を呼び出します
        });
    }

    /**
     * 樹状整列それ自身を応答するメソッドです。
     * 
     * @return 樹状整列それ自身
     */
    public Forest forest() {
        return this.forest;
    }

    /**
     * 樹状整列データファイルから樹状整列それ自身を生成するメソッドです。
     * 
     * @param aFile 樹状整列データファイル
     */
    protected void read(File aFile) {
        this.forest = new Forest();
        Map<String, Node> nodeMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(aFile))) {
            String line;
            String currentSection = "";
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith(Constants.TagOfTrees)) {
                    continue;
                }

                if (line.equalsIgnoreCase(Constants.TagOfNodes)) {
                    currentSection = Constants.TagOfNodes;
                    continue;
                } else if (line.equalsIgnoreCase(Constants.TagOfBranches)) {
                    currentSection = Constants.TagOfBranches;
                    continue;
                }

                String[] parts = line.split(",", 2);
                if (parts.length < 2)
                    continue;

                if (currentSection.equals(Constants.TagOfNodes)) {
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    Node node = new Node(name);
                    nodeMap.put(id, node);
                    this.forest.addNode(node);
                } else if (currentSection.equals(Constants.TagOfBranches)) {
                    Node fromNode = nodeMap.get(parts[0].trim());
                    Node toNode = nodeMap.get(parts[1].trim());
                    if (fromNode != null && toNode != null) {
                        this.forest.addBranch(new Branch(fromNode, toNode));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.forest = null; // 読み込み失敗
        }
    }

    /**
     * 樹状整列の根元 (ルート) になるノードを探し出して応答するメソッドです。
     * 
     * @return ルートノード、ただし、見つからないときはnull
     */
    public Node root() {
        if (this.forest == null)
            return null;
        ArrayList<Node> roots = this.forest.rootNodes();
        return roots.isEmpty() ? null : roots.get(0);
    }

    /**
     * 樹状整列の根元(ルート)になるノードたちを探し出して応答するメソッドです。
     * 
     * @return ルートノードたち、ただし、見つからないときは空リスト
     */
    public ArrayList<Node> roots() {
        if (this.forest == null)
            return new ArrayList<Node>();
        return this.forest.rootNodes();
    }
}
