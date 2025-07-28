package forest;

import java.io.File;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
        super();
		this.read(aFile);
    }

    /**
     * アニメーションを行うメソッドです。
     */
    public void animate() {
        // TODO: ステップ4で実装
		// this.forest.propagate(this);
		this.changed(); // Modelが変更されたことを通知
    }

    /**
     * 樹状整列を行うメソッドです。
     */
    public void arrange() {
        // TODO: ステップ3で実装
		this.forest.arrange();
		this.changed(); // Modelが変更されたことを通知
    }

    /**
     * 自分自身が変化したことを依存物たちに放送 (updateを依頼) するメソッドです。
     */
    @Override
    public void changed() {
        super.changed();
    }

    /**
     * 樹状整列それ自身を応答するメソッドです。
     * @return 樹状整列それ自身 
     */
    public Forest forest() {
        return this.forest;
    }

    /**
     * 樹状整列データファイルから樹状整列それ自身を生成するメソッドです。
     * @param aFile 樹状整列データファイル 
     */
    protected void read(File aFile) {
        // 新しいForestインスタンスを作成
		this.forest = new Forest();
		// IDをキーにしてNodeを高速に検索するための一時的なMap
		Map<Integer, Node> nodeMap = new HashMap<Integer, Node>();
		String aLine = null;
		// 現在読み込んでいるセクションを記憶する変数
		String currentSection = null;

		try (BufferedReader reader = new BufferedReader(new FileReader(aFile)))
		{
			while ((aLine = reader.readLine()) != null)
			{
				aLine = aLine.trim();
				if (aLine.isEmpty()) { continue; }

				// セクションの判定
				if (aLine.equals(Constants.TagOfNodes)) { currentSection = "nodes"; continue; }
				if (aLine.equals(Constants.TagOfBranches)) { currentSection = "branches"; continue; }
				if (aLine.equals(Constants.TagOfTrees)) { currentSection = "trees"; continue; }

				// 各セクションの処理
				if ("nodes".equals(currentSection))
				{
					String[] parts = aLine.split(",", 2);
					if (parts.length < 2) { continue; }
					Integer id = Integer.valueOf(parts[0].trim());
					String name = parts[1].trim();
					Node aNode = new Node(name);
					this.forest.addNode(aNode);
					nodeMap.put(id, aNode);
				}
				else if ("branches".equals(currentSection))
				{
					String[] parts = aLine.split(",", 2);
					if (parts.length < 2) { continue; }
					Integer fromId = Integer.valueOf(parts[0].trim());
					Integer toId = Integer.valueOf(parts[1].trim());
					Node fromNode = nodeMap.get(fromId);
					Node toNode = nodeMap.get(toId);
					if (fromNode != null && toNode != null)
					{
						Branch aBranch = new Branch(fromNode, toNode);
						this.forest.addBranch(aBranch);
					}
				}
			}
		}
		catch (IOException e) { e.printStackTrace(); }
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