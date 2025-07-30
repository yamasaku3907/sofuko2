package forest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

/**
 * Branchクラスをテストするためのクラス。
 */
public class BranchTest {

    private Node startNode;
    private Node endNode;
    private Branch testBranch;

    /**
     * 各テストメソッドが実行される前に、テスト用のNodeとBranchインスタンスを生成します。
     */
    @Before
    public void setUp() {
        startNode = new Node("StartNode");
        endNode = new Node("EndNode");
        testBranch = new Branch(startNode, endNode);
    }

    /**
     * コンストラクタが正しくフィールドを初期化し、
     * start()およびend()メソッドが正しいNodeを返すかをテストします。
     */
    @Test
    public void testConstructorAndGetters() {
        System.out.println("Testing Branch constructor and getters...");

        // 1. nullでないことを確認
        assertNotNull("Branch should not be null", testBranch);

        // 2. start()メソッドが正しい始点ノードを返すか
        // assertEqualsよりも厳密なassertSameを使い、全く同じインスタンスかを比較します。
        assertSame("start() should return the correct start node instance", startNode, testBranch.start());

        // 3. end()メソッドが正しい終点ノードを返すか
        assertSame("end() should return the correct end node instance", endNode, testBranch.end());
    }

    /**
     * toStringメソッドが期待されるフォーマットの文字列を生成するかをテストします。
     */
    @Test
    public void testToString() {
        System.out.println("Testing Branch toString()...");

        String expectedString = "[Branch=StartNode -> EndNode]";
        assertEquals("toString() should produce the correct format", expectedString, testBranch.toString());
    }
}
