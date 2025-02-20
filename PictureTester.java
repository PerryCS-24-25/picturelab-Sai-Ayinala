/**
 * This class contains class (static) methods that will help you test the
 * Picture class methods. Uncomment the methods and the code in the main to
 * test.
 *
 * @author Barbara Ericson
 */
public class PictureTester {

    /**
     * Method to test the PictureExplorer
     */
    public static void testExplorer() {
        Picture p = new Picture("BlendedSunsets.jpg");
        Picture smallP = p.scale(0.25,0.25);
        smallP.write("smallMyPicture.jpg");
        p.explore();   
    }

    /**
     * Method to test zeroBlue
     */
    public static void testZeroBlue() {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
    }

    /**
     * Method to test mirrorVertical
     */
    public static void testMirrorDiagonal() {
        Picture caterpillar = new Picture("beach.jpg");
        caterpillar.explore();
        caterpillar.mirrorDiagonal();
        caterpillar.explore();
    }

    /**
     * Method to test mirrorTemple
     */
    public static void testMirrorTemple() {
        Picture temple = new Picture("temple.jpg");
        temple.explore();
        temple.mirrorTemple();
        temple.explore();
    }

    /**
     * Method to test edgeDetection
     */
    public static void testEdgeDetection() {
        Picture swan = new Picture("swan.jpg");
        swan.edgeDetection(10);
        swan.explore();
    }

    /**
     * Method to test scaling a picture
     */
    public static void testScale() {
        Picture temple = new Picture("temple.jpg");
        temple.explore();
        Picture temple2 = temple.scale(0.5, 0.5);
        temple2.explore();
    }

    public static void testKeepOnlyBlue(){
        Picture theOne = new Picture("temple.jpg");
        theOne.keepOnlyBlue();
        theOne.explore(); 
    }

    public static void testKeepOnlyRed(){
        Picture theOne = new Picture("temple.jpg");
        theOne.keepOnlyRed();
        theOne.explore(); 
    }

    public static void testKeepOnlyGreen(){
        Picture theOne = new Picture("temple.jpg");
        theOne.keepOnlyGreen();
        theOne.explore(); 
    }

    public static void testNegate(){
        Picture theNegate = new Picture("temple.jpg");
        theNegate.negate(); 
        theNegate.explore(); 
    }

    public static void testGrayscale(){
        Picture Grey = new Picture("temple.jpg"); 
        Grey.grayscale();
        Grey.explore();
    }
    
    public static void testFixUnderwater(){
        Picture underwater = new Picture("water.jpg"); 
        underwater.fixUnderwater();
        underwater.explore();
    }
    public static void testMirrorArms(){
        Picture temples = new Picture("snowman.jpg");
        temples.explore();
        temples.mirrorArms();
        temples.explore();
    }

    /**
     * Main method for testing. Every class can have a main method in Java
     */
    public static void main(String[] args) {
        // uncomment a call here to run a test
        // and comment out the ones you don't want
        // to run

        //testExplorer();
        //testScale();
        //testZeroBlue();
        //testKeepOnlyBlue();
        //testKeepOnlyRed();
        //testKeepOnlyGreen();
        //testNegate();
        //testGrayscale();
        //testFixUnderwater();
        //testMirrorVertical();
        //testMirrorTemple();
        testMirrorArms();
        //testMirrorGull();
        //testMirrorDiagonal();
        //testCollage();
        //testCopy();
        //testEdgeDetection();
        //testEdgeDetection2();
        //testChromakey();
        //testEncodeAndDecode();
        //testGetCountRedOverValue(250);
        //testSetRedToHalfValueInTopHalf();
        //testClearBlueOverValue(200);
        //testGetAverageForColumn(0);
    }
}
