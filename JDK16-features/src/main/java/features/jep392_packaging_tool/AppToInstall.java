package features.jep392_packaging_tool;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import lombok.SneakyThrows;

/**
 * mvn clean package jpackage -i target -n AppToInstall --main-jar JDK16-features-1.0-SNAPSHOT.jar  --main-class features.jep392_packaging_tool.AppToInstall --java-options
 * "--enable-preview" install and enjoy:)
 */

public class AppToInstall extends JFrame {

  public AppToInstall() {
    this.setName("Installed Java Application");
    Box box = Box.createVerticalBox();
    box.setBackground(Color.BLACK);
    add(box);

    var header = new JLabel("Hello Lviv Java Club!");
    header.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    header.setForeground(Color.RED.brighter());
    header.setFont(new Font("Serif", Font.PLAIN, 30));
    box.add(header);

    var iconLabel = new JLabel(
        new ImageIcon("/Users/omelnyk/Development/JDK14-15-features/JDK16-features/src/main/resources/lviv_java16_club.png")
    );
    iconLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    box.add(iconLabel);

    var linkLabel = new JLabel("Subscribe to YouTube channel!");
    linkLabel.setForeground(Color.BLUE.darker());
    linkLabel.setFont(new Font("Serif", Font.PLAIN, 24));
    linkLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    linkLabel.addMouseListener(new MouseAdapter() {
      @SneakyThrows
      public void mouseClicked(MouseEvent e) {
        Desktop.getDesktop().browse(new URI("https://www.youtube.com/channel/UCbBzU09lr8FLFaTMbXY4zBQ"));
      }
    });
    linkLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    box.add(linkLabel);
  }

  public static void main(String[] args) {
    AppToInstall frame = new AppToInstall();
    frame.setMinimumSize(new Dimension(640, 550));
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
