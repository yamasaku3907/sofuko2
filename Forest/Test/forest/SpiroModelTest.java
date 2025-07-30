package forest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * SpiroModelクラスをテストするためのクラス。
 */
public class SpiroModelTest {

    private File testFile;
    private SpiroModel testModel;

    /**
     * 各テストの前に、テスト用の小さなファイルを作成し、
     * それを元にSpiroModelインスタンスを生成します。
     */
    @Before
    public void setUp() throws Exception {
        // テスト用の小さな木構造データを持つ一時ファイルを作成
        testFile = File.createTempFile("test-model", ".txt");
        testFile.deleteOnExit();

        try (PrintWriter out = new PrintWriter(testFile, "UTF-8")) {
            out.println("nodes:");
            out.println("10, RootNode");
            out.println("20, ChildNode1");
            out.println("30, ChildNode2");
            out.println("branches:");
            out.println("10, 20");
            out.println("10, 30");
        }
        
        // テスト対象のモデルを生成
        testModel = new SpiroModel(testFile);
    }

    /**
     * コンストラクタ（と内部で呼ばれるreadメソッド）が正しくForestを構築するかをテストします。
     */
    @Test
    public void testModelInitializationAndRead() {
        System.out.println("Testing SpiroModel initialization...");
        
        // 1. Modelと、それが保持するForestがnullでないことを確認
        assertNotNull("Model should not be null", testModel);
        assertNotNull("Forest within the model should not be null", testModel.forest());
        
        // 2. Forest内のノード数とブランチ数が正しいかを確認
        assertEquals("Forest should have 3 nodes and 2 branches",
                     "[Forest=3 nodes, 2 branches]",
                     testModel.forest().toString());
    }

    /**
     * rootsメソッドが正しくルートノードを返すかをテストします。
     */
    @Test
    public void testRootsMethod() {
        System.out.println("Testing roots() method...");
        
        ArrayList<Node> roots = testModel.roots();
        assertNotNull("Roots list should not be null", roots);
        assertEquals("There should be exactly 1 root node", 1, roots.size());
        assertEquals("The root node's name should be 'RootNode'", "RootNode", roots.get(0).getName());
    }
}

