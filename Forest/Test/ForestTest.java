package forest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * Forestクラスをテストするためのクラス。
 */
public class ForestTest {

    private Forest testForest;
    private Node rootNode;
    private Node childNode1;
    private Node childNode2;
    private Node grandchildNode;

    /**
     * 各テストの前に、テスト用のForestインスタンスと階層的なノード群を生成します。
     * * <pre>
     * Root
     * |- Child1
     * |   `- Grandchild
     * `- Child2
     * </pre>
     */
    @Before
    public void setUp() {
        testForest = new Forest();

        // ノードを生成
        rootNode = new Node("Root");
        childNode1 = new Node("Child1");
        childNode2 = new Node("Child2");
        grandchildNode = new Node("Grandchild");

        // ノードをForestに追加
        testForest.addNode(rootNode);
        testForest.addNode(childNode1);
        testForest.addNode(childNode2);
        testForest.addNode(grandchildNode);

        // ブランチをForestに追加
        testForest.addBranch(new Branch(rootNode, childNode1));
        testForest.addBranch(new Branch(rootNode, childNode2));
        testForest.addBranch(new Branch(childNode1, grandchildNode));
    }

    /**
     * ノードとブランチが正しく追加されるかをテストします。
     * Forestクラスに getNodes() と getBranches() メソッドを追加する必要があります。
     */
    @Test
    public void testAddNodeAndBranch() {
        System.out.println("Testing addNode and addBranch...");
        // assertEquals(4, testForest.getNodes().size());
        // assertEquals(3, testForest.getBranches().size());
        // Javadocにgetterがないため、toString()で簡易的に確認
        assertEquals("[Forest=4 nodes, 3 branches]", testForest.toString());
    }

    /**
     * subNodesメソッドが、指定したノードの子ノード群を正しく返すかをテストします。
     */
    @Test
    public void testSubNodes() {
        System.out.println("Testing subNodes...");
        ArrayList<Node> rootSubs = testForest.subNodes(rootNode);
        assertEquals("Root should have 2 sub-nodes", 2, rootSubs.size());
        assertTrue("Sub-nodes of Root should include Child1", rootSubs.contains(childNode1));
        assertTrue("Sub-nodes of Root should include Child2", rootSubs.contains(childNode2));

        ArrayList<Node> child1Subs = testForest.subNodes(childNode1);
        assertEquals("Child1 should have 1 sub-node", 1, child1Subs.size());
        assertTrue("Sub-node of Child1 should be Grandchild", child1Subs.contains(grandchildNode));

        ArrayList<Node> leafSubs = testForest.subNodes(childNode2);
        assertEquals("Child2 should have 0 sub-nodes", 0, leafSubs.size());
    }

    /**
     * superNodesメソッドが、指定したノードの親ノード群を正しく返すかをテストします。
     */
    @Test
    public void testSuperNodes() {
        System.out.println("Testing superNodes...");
        ArrayList<Node> grandchildSupers = testForest.superNodes(grandchildNode);
        assertEquals("Grandchild should have 1 super-node", 1, grandchildSupers.size());
        assertTrue("Super-node of Grandchild should be Child1", grandchildSupers.contains(childNode1));

        ArrayList<Node> child1Supers = testForest.superNodes(childNode1);
        assertEquals("Child1 should have 1 super-node", 1, child1Supers.size());
        assertTrue("Super-node of Child1 should be Root", child1Supers.contains(rootNode));

        ArrayList<Node> rootSupers = testForest.superNodes(rootNode);
        assertEquals("Root should have 0 super-nodes", 0, rootSupers.size());
    }

    /**
     * rootNodesメソッドが、森全体のルートノードを正しく見つけ出すかをテストします。
     */
    @Test
    public void testRootNodes() {
        System.out.println("Testing rootNodes...");
        ArrayList<Node> roots = testForest.rootNodes();
        assertNotNull("Root nodes list should not be null", roots);
        assertEquals("There should be 1 root node", 1, roots.size());
        assertTrue("The root node should be 'Root'", roots.contains(rootNode));
    }
}
