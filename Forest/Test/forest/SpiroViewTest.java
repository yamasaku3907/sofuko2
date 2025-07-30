package forest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull; 
import static org.junit.Assert.assertSame;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import mvc.Controller;
import mvc.Model;

import org.junit.Before;
import org.junit.Test;

/**
 * SpiroViewクラスをテストするためのクラス。
 */
public class SpiroViewTest {

    private SpiroModel testModel;
    private SpiroView testView;
    private File testFile;
    private Node rootNode;

    @Before
    public void setUp() throws Exception {
        testFile = File.createTempFile("test-view", ".txt");
        testFile.deleteOnExit();

        try (PrintWriter out = new PrintWriter(testFile, "UTF-8")) {
            out.println("nodes:");
            out.println("1, Root");
            out.println("branches:");
        }
        
        testModel = new SpiroModel(testFile);
        rootNode = testModel.root();
        
        // ★ SpiroViewのコンストラクタに合わせて修正
        testView = new SpiroView(testModel); 
    }

    /**
     * コンストラクタが正しくModelとControllerを初期化するかをテストします。
     */
    @Test
    public void testInitialization() {
        System.out.println("Testing SpiroView initialization...");
        
        // ★ これでSpiroViewに追加したメソッドを呼び出せるようになります
        assertNotNull("View should have a model", testView.getModel());
        assertSame("View's model should be the one we passed", testModel, testView.getModel());
        assertNotNull("View should have a controller", testView.getController());
    }

    /**
     * whichOfNodesメソッドが正しく動作するかをテストします。
     */
    @Test
    public void testWhichOfNodes() {
        System.out.println("Testing whichOfNodes()...");
        
        Point nodeLocation = rootNode.getLocation();
        Point nodeExtent = rootNode.getExtent();
        Point pointInsideNode = new Point(nodeLocation.x + nodeExtent.x / 2, nodeLocation.y + nodeExtent.y / 2);
        
        Node foundNode = testView.whichOfNodes(pointInsideNode);
        assertSame("Should find the node at the correct location", rootNode, foundNode);
        
        Node notFoundNode = testView.whichOfNodes(new Point(999, 999));
        assertNull("Should return null for a point with no node", notFoundNode);
    }
    
    /**
     * paintComponentメソッドが例外を発生させずに実行できるかをテストします。
     */
    @Test
    public void testPaintComponentDoesNotCrash() {
        System.out.println("Testing paintComponent() does not crash...");
        try {
            BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
            Graphics g = image.getGraphics();
            testView.paintComponent(g);
            g.dispose();
        } catch (Exception e) {
            org.junit.Assert.fail("paintComponent threw an exception: " + e.getMessage());
        }
    }
}
