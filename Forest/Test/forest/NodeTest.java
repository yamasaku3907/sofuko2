package forest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

/**
 * Nodeクラスをテストするためのクラス。
 */
public class NodeTest {

    private Node testNode;
    private final String nodeName = "TestNode";

    /**
     * 各テストメソッドが実行される前に、新しいNodeインスタンスを生成します。
     */
    @Before
    public void setUp() {
        testNode = new Node(nodeName);
    }

    /**
     * コンストラクタが正しくフィールドを初期化するかをテストします。
     */
    @Test
    public void testConstructor() {
        System.out.println("Testing Node constructor...");
        
        // 1. nullでないことを確認
        assertNotNull("Node should not be null", testNode);
        
        // 2. 名前が正しく設定されているか
        assertEquals("Name should be set correctly", nodeName, testNode.getName());
        
        // 3. locationが(0,0)で初期化されているか
        assertEquals("Initial location should be (0,0)", new Point(0, 0), testNode.getLocation());
        
        // 4. statusがUnKnownで初期化されているか
        assertEquals("Initial status should be UnKnown", Constants.UnKnown, testNode.getStatus());
        
        // 5. extent（大きさ）が名前を元に計算されているか（0より大きいことを確認）
        assertTrue("Extent width should be greater than 0", testNode.getExtent().x > 0);
        assertTrue("Extent height should be greater than 0", testNode.getExtent().y > 0);
    }

    /**
     * setLocationメソッドとgetLocationメソッドが正しく動作するかをテストします。
     */
    @Test
    public void testSetAndGetLocation() {
        System.out.println("Testing set/get Location...");
        
        Point newLocation = new Point(100, 200);
        testNode.setLocation(newLocation);
        
        assertEquals("Location should be updated", newLocation, testNode.getLocation());
    }

    /**
     * setStatusメソッドとgetStatusメソッドが正しく動作するかをテストします。
     */
    @Test
    public void testSetAndGetStatus() {
        System.out.println("Testing set/get Status...");

        Integer newStatus = Constants.Visited;
        testNode.setStatus(newStatus);

        assertEquals("Status should be updated", newStatus, testNode.getStatus());
    }
}
