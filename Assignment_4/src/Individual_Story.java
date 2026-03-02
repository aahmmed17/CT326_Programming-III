/**
 * Represents an individual story with title, images, and description
 *
 * @author Aftab Ahmmed
 * @studentID 23344296
 * @version 1.0
 */
import java.awt.image.BufferedImage;

public class Individual_Story {

    private final String storyTitle;
    private final BufferedImage[] storyImages;
    private final String storyDescription;

    /**
     * Constructor for Individual Story
     *
     * @param storyTitle the title of the story
     * @param storyImages array of images for the story
     * @param storyDescription the story description text
     */
    public Individual_Story(String storyTitle, BufferedImage[] storyImages, String storyDescription ){
        this.storyTitle = storyTitle;
        this.storyImages = storyImages;
        this.storyDescription = storyDescription;

    }

    /**
     * Gets the number of images in this story
     *
     * @return number of images
     */
    public int getNumPicButtons(){
        return storyImages.length;
    }

    /**
     * Gets the story title
     *
     * @return story title
     */
    public String getStoryTitle(){
        return storyTitle;
    }


    /**
     * Gets the story description
     *
     * @return story description
     */
    public String getStoryDescription(){
        return storyDescription;

    }

    /**
     * Gets a specific story image by index
     *
     * @param index the index of the image
     * @return the BufferedImage at the specified index
     */
    public BufferedImage getStoryImage(int index) {
        return storyImages[index];
    }
}
