import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

/**
 * A class that represents a picture. This class inherits from SimplePicture and
 * allows the student to add functionality to the Picture class.
 *
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture {
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments
     */
    public Picture() {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();
    }

    /**
     * Constructor that takes a file name and creates the picture
     *
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName) {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes a file name and creates the picture
     *
     * @param file the name of the file to create the picture from
     */
    public Picture(java.io.File file) {
        // let the parent class handle this fileName
        super(file);
    }

    /**
     * Constructor that takes the width and height
     * 
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int width, int height) {
        // let the parent class handle this width and height
        super(width, height);
    }

    /**
     * Constructor that takes a picture and creates a copy of that picture
     *
     * @param copyPicture the picture to copy
     */
    public Picture(SimplePicture copyPicture) {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     *
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image) {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////
    /**
     * Method to return a string with information about this picture.
     *
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString() {
        String output = "Picture, filename " + getFileName()
            + " height " + getHeight()
            + " width " + getWidth();
        return output;

    }

    /**
     * Method to create a new picture by scaling the current picture by the given
     * x and y factors
     *
     * @param xFactor the amount to scale in x
     * @param yFactor the amount to scale in y
     * @return the resulting picture
     */
    public Picture scale(double xFactor, double yFactor) {
        // set up the scale transform
        AffineTransform scaleTransform = new AffineTransform();
        scaleTransform.scale(xFactor, yFactor);

        // create a new picture object that is the right size
        Picture result = new Picture((int) (getWidth() * xFactor),
                (int) (getHeight() * yFactor));

        // get the graphics 2d object to draw on the result
        Graphics graphics = result.getGraphics();
        Graphics2D g2 = (Graphics2D) graphics;

        // draw the current image onto the result image scaled
        g2.drawImage(this.getImage(), scaleTransform, null);

        result.setTitle(getTitle());
        return result;
    }

    /**
     * Method to set the blue to 0
     */
    public void zeroBlue() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setBlue(0);
            }
        }
    }
    
    /**
     * Method keeps only the blue value
     */
    public void  keepOnlyBlue() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setGreen(0);
                pixelObj.setRed(0);
            }
        }
    }

    /**
     * Method keeps only the blue value
     */
    public void  keepOnlyRed() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setGreen(0);
                pixelObj.setBlue(0);
            }
        }
    }

    /**
     * Method keeps only the blue value
     */
    public void  keepOnlyGreen() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setBlue(0);
                pixelObj.setRed(0);
            }
        }
    }
    

    /**
     * Removes all the red from this image.
     */
    public void zeroRed() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setRed(0);
            }
        }
    }

    /**
     * Removes all the green from this image.
     */
    public void zeroGreen() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setGreen(0);
            }
        }   
    }

    public void negate() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setBlue( 255 - pixelObj.getBlue() );
                pixelObj.setRed( 255 - pixelObj.getRed());
                pixelObj.setGreen( 255 - pixelObj.getGreen());
            }
        }   
    }

    public void Black_White(){
        Pixel[][] pixels = this.getPixels2D();
        int name;
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                name = (pixelObj.getBlue() + pixelObj.getRed() + pixelObj.getGreen())/3;
                if(name > 127){
                    pixelObj.setBlue(255);
                    pixelObj.setGreen(255);
                    pixelObj.setRed(255);
                }else{
                    pixelObj.setBlue(0);
                    pixelObj.setGreen(0);
                    pixelObj.setRed(0);
                }
            }
        }  
       
    }
    

    public void grayscale() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                int averages = (pixelObj.getBlue() + pixelObj.getGreen() + pixelObj.getRed())/3; 
                pixelObj.setBlue( averages);
                pixelObj.setRed( averages);
                pixelObj.setGreen( averages);
            }
        }   
    }

    public void fixUnderwater(){
        Pixel[][] pixels = this.getPixels2D();
        int MinnRed = pixels[0][0].getRed();
        int MaxRed = pixels[0][0].getRed();
        double scaleRed; 

        int MinnBlue = pixels[0][0].getBlue();
        int MaxBlue = pixels[0][0].getBlue();
        double scaleBlue; 

        int MinnGreen = pixels[0][0].getGreen();
        int MaxGreen = pixels[0][0].getGreen();
        double scaleGreen; 


        //Find the lowest and higest red value 
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                // for thr red
              if(pixelObj.getRed() > MaxRed){
                 MaxRed = pixelObj.getRed();
              }  
              if(pixelObj.getRed() < MinnRed){
                MinnRed = pixelObj.getRed();
             }  
             // for thr blue
             if(pixelObj.getBlue() > MaxBlue){
                MaxBlue = pixelObj.getBlue();
             }  
             if(pixelObj.getBlue() < MinnBlue){
               MinnRed = pixelObj.getBlue();
            }  
            // for thr Green
            if(pixelObj.getGreen() > MaxGreen){
                MaxGreen = pixelObj.getGreen();
             }  
             if(pixelObj.getGreen() < MinnGreen){
               MinnGreen = pixelObj.getGreen();
            }  
            }
        } 
        scaleRed = 255.0/(MaxRed - MinnRed);
        scaleGreen = 255.0/(MaxGreen - MinnGreen);
        scaleBlue = 255.0/(MaxBlue - MinnBlue);
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
              pixelObj.setRed((int)((pixelObj.getRed() - MinnRed)*scaleRed));
              pixelObj.setBlue((int)((pixelObj.getBlue() - MinnBlue)*scaleBlue));
              pixelObj.setGreen((int)((pixelObj.getGreen() - MinnGreen)*scaleGreen));
            }
        } 
        
    }

    public void topToBottom(){
        Pixel[][] pixels = this.getPixels2D();
        System.out.println("hdhe3dh3edhi3edh3dh3iehd3i3e3i");
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int width = pixels.length/2;
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < pixels[0].length; col++) {
                topPixel = pixels[row][col];
                bottomPixel = pixels[(width - row)*2 + row - 1][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        }
    }

    public void rightToLeft(){
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < width / 2; col++) {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        }  
    }

    public void bottomToTop(){
        Pixel[][] pixels = this.getPixels2D();
        System.out.println("hdhe3dh3edhi3edh3dh3iehd3i3e3i");
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int width = pixels.length/2;
        for (int row = width; row < pixels.length; row++) {
            for (int col = 0; col < pixels[0].length; col++) {
                //System.out.println(width - row);
                //System.out.println(width - (width - row));
                topPixel = pixels[width - ( row - width)][col];
                bottomPixel = pixels[row][col];
                topPixel.setColor(bottomPixel.getColor());
            }
        }   
    }

    public void leftToRight(){
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++) {
            for (int col = width/2; col < width; col++) {
                leftPixel = pixels[row][(col - width) * -1];
                rightPixel = pixels[row][col];
                //leftPixel.setRed(255);
                 leftPixel.setColor(rightPixel.getColor());
            }
        } 
    }

    public void mirrorDiagonal(){
        Pixel[][] pixels = this.getPixels2D(); 
        int square; 
        if(pixels.length < pixels[0].length ){
              square = pixels.length; 
        }else{
            square = pixels[0].length; 
        }
        for(int row = 0; row < square; row++ ){
            for(int col = 0; col < square; col++){
                if(row < col){
                 pixels[row][col].setColor(pixels[col][row].getColor()); 
                }
            }
        }
    }

    public void mirrorArms(){
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int row = 27; row < 97; row++) {
            // loop from 13 to just before the mirror point
            for (int col = 13; col < mirrorPoint; col++) {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }
    
    public void mirrorTemple() {
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int row = 27; row < 97; row++) {
            // loop from 13 to just before the mirror point
            for (int col = 13; col < mirrorPoint; col++) {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    /**
     * copy from the passed fromPic to the specified startRow and startCol in the
     * current picture
     *
     * @param fromPic the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copy(Picture fromPic, int startRow, int startCol) {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow;
        fromRow < fromPixels.length
        && toRow < toPixels.length;
        fromRow++, toRow++) {
            for (int fromCol = 0, toCol = startCol;
            fromCol < fromPixels[0].length
            && toCol < toPixels[0].length;
            fromCol++, toCol++) {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }
    }

    /**
     * 
     * @param fromPic The source image we are copying from
     * @param destRow the start row to copy to
     * @param destCol the start col to copy to
     * @param fromRow The start row of fromPic
     * @param fromCol The start col of fromPic
     * @param w Width of the area we wish to copy.
     * @param h Height of the area we wish to copy.
     */
    public void copy(Picture fromPic,int destRow, int destCol, int fromRow, int fromCol, int w, int h) {
        //TODO: Write and test this method
    }

    /**
     * Method to show large changes in color
     *
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection(int edgeDist) {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0;
            col < pixels[0].length - 1; col++) {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col + 1];
                rightColor = rightPixel.getColor();
                if (leftPixel.colorDistance(rightColor)
                > edgeDist)
                    leftPixel.setColor(Color.BLACK);
                else
                    leftPixel.setColor(Color.WHITE);
            }
        }
    }

    /* Main method for testing - each class in Java can have a main 
     * method 
     */
    public static void main(String[] args) {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
    }

} // this } is the end of class Picture, put all new methods before this
//keepOnlyGreen