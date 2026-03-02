import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main application frame for the Story Viewer
 * Demonstrates GUI components, event handling, and layout managers
 *
 * @author Aftab Ahmmed
 * @studentID 23344296
 */
public class Story extends JFrame {

    private final JComboBox<String> storyEntry;
    private final JLabel storyText;
    private final JLabel picStory;

    private final Individual_Story[] allStories;

    //Dynamic radio button management
    private final JPanel radioButtonPanel;
    private final ButtonGroup radioButtonGroup;
    private final List<JRadioButton> radioButtons;

    /**
     * Constructor for Story viewer application
     */
    public Story() throws IOException {
        super("Story Viewer");

        // Load images
        BufferedImage myPicture1 = ImageIO.read(new File("src/London.jpg"));
        BufferedImage myPicture2 = ImageIO.read(new File("src/British_Museum.jpg"));
        BufferedImage myPicture3 = ImageIO.read(new File("src/Mount_Rushmore.jpg"));
        BufferedImage myPicture4 = ImageIO.read(new File("src/Florida.jpg"));
        BufferedImage myPicture6 = ImageIO.read(new File("src/Paris.jpg"));
        BufferedImage myPicture7 = ImageIO.read(new File("src/ParcdesPrinces.jpeg"));
        BufferedImage myPicture8 = ImageIO.read(new File("src/Louvre.jpg"));
        BufferedImage myPicture9 = ImageIO.read(new File("src/Old_Trafford.jpg"));

        // Create Individual Story objects
        Individual_Story ukTrip = new Individual_Story("Trip to the UK",
                new BufferedImage[]{myPicture1, myPicture2, myPicture9}, "Had a great time in the United Kingdom! #BigBen #UK #MUN");

        Individual_Story usTrip = new Individual_Story("Off to the States",
                new BufferedImage[]{myPicture3, myPicture4}, "Lovely nature and weather here in America. #USA");

        Individual_Story parisTrip = new Individual_Story("Weekend in Paris",
                new BufferedImage[]{myPicture6, myPicture7, myPicture8}, "Just some lads in Paris... #Paris #France #Louvre #PSG");

        allStories = new Individual_Story[]{ukTrip, usTrip, parisTrip};

        String[] storyTitles = new String[allStories.length];
        for (int i = 0; i < allStories.length; i++) {
            storyTitles[i] = allStories[i].getStoryTitle();
        }

        // GUI Setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //  JComboBox
        storyEntry = new JComboBox<>(storyTitles);
        storyEntry.setMaximumRowCount(3);
        add(storyEntry, BorderLayout.NORTH);

        //  Story image area
        picStory = new JLabel(new ImageIcon(allStories[0].getStoryImage(0)));
        storyText = new JLabel(allStories[0].getStoryDescription(),SwingConstants.CENTER);

        JPanel storyPanel = new JPanel();
        storyPanel.setPreferredSize(new Dimension(600, 500));
        storyPanel.setBorder(BorderFactory.createTitledBorder("Story Content"));

        // Dynamic radio button setup
        radioButtonPanel = new JPanel();
        radioButtonGroup = new ButtonGroup();
        radioButtons = new ArrayList<>();

        JButton addStory = new JButton("Add Story");

        JPanel controlPanel = new JPanel();
        controlPanel.add(radioButtonPanel); // Add the dynamic radio button panel
        controlPanel.add(addStory);

        // MIDDLE PANEL TO HOLD STORY CONTENT
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(picStory, BorderLayout.NORTH);
        contentPanel.add(storyText, BorderLayout.SOUTH);

        // SOUTH PANEL TO HOLD BUTTONS
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(addStory, BorderLayout.SOUTH);
        buttonPanel.add(controlPanel, BorderLayout.NORTH);

        add(contentPanel);
        add(buttonPanel, BorderLayout.SOUTH);

        // Create radio buttons for initial story
        createRadioButtonsForStory(0);

        // Story Listener for dynamic radio buttons
        storyEntry.addItemListener(event -> {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                int index = storyEntry.getSelectedIndex();

                picStory.setIcon(new ImageIcon(allStories[index].getStoryImage(0)));
                storyText.setText(allStories[index].getStoryDescription());

                // Update radio buttons dynamically for the selected story
                createRadioButtonsForStory(index);
            }
        });

        ActionListener listener = e -> {
            String cmd = e.getActionCommand();

            if (cmd.equals("add")) {
                System.out.println("Add Story Button pressed!");
            }
        };

        addStory.setActionCommand("add");
        addStory.addActionListener(listener);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Creates radio buttons dynamically based on the current story's image count
     *
     * @param storyIndex the index of the story to create radio buttons for
     */
    private void createRadioButtonsForStory(int storyIndex) {
        // Clear existing radio buttons
        radioButtonPanel.removeAll();
        radioButtonGroup.clearSelection();
        radioButtons.clear();

        Individual_Story currentStory = allStories[storyIndex];
        int numImages = currentStory.getNumPicButtons();

        // Create new radio buttons for this story
        for (int i = 0; i < numImages; i++) {
            JRadioButton radioButton = new JRadioButton();
            final int imageIndex = i; // Need final for lambda

            radioButton.addActionListener(e -> picStory.setIcon(new ImageIcon(currentStory.getStoryImage(imageIndex))));

            radioButtonGroup.add(radioButton);
            radioButtons.add(radioButton);
            radioButtonPanel.add(radioButton);
        }

        // Select the first radio button
        if (!radioButtons.isEmpty()) {
            radioButtons.get(0).setSelected(true);
        }

        // UI refresh
        radioButtonPanel.revalidate();
        radioButtonPanel.repaint();
    }
}