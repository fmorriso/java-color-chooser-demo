/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

//package components;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * ColorChooserDemo.java requires these files:
 *   CrayonPanel.java
 *   images/red.gif
 *   images/yellow.gif
 *   images/green.gif
 *   images/blue.gif
 */
@SuppressWarnings("serial")
public class ColorChooserDemo extends JPanel implements ActionListener, ChangeListener, Runnable
{
    public JLabel banner;
    public JColorChooser tcc;
    private Dimension scaledSize;
    private final String title;

    public ColorChooserDemo(String title, Dimension scaledSize)
    {
        super(new BorderLayout());

        this.title = title;
        this.scaledSize = scaledSize;

        // Set up banner to use as custom preview panel
        banner = new JLabel("Welcome to the Tutorial Zone!", JLabel.CENTER);
        banner.setForeground(Color.yellow);
        banner.setBackground(Color.blue);
        banner.setOpaque(true);
        banner.setFont(new Font("SansSerif", Font.BOLD, 24));
        // banner.setPreferredSize(new Dimension(100 * 16, 65 * 3));
        Dimension bannerSize = new Dimension(scaledSize.width, scaledSize.height/2);
        banner.setPreferredSize(bannerSize);

        JPanel bannerPanel = new JPanel(new BorderLayout());
        bannerPanel.add(banner, BorderLayout.CENTER);
        bannerPanel.setBorder(BorderFactory.createTitledBorder("Banner"));

        // Set up color chooser for setting background color
        JPanel panel = new JPanel(); // use FlowLayout
        JButton bcc = new JButton("Show Color Chooser...");
        bcc.addActionListener(this);
        panel.add(bcc);
        panel.setBorder(BorderFactory.createTitledBorder("Choose Background Color"));

        // Set up color chooser for setting text color
        tcc = new JColorChooser();
        tcc.getSelectionModel().addChangeListener(this);
        tcc.setBorder(BorderFactory.createTitledBorder("Choose Text Color"));

        // Remove the preview panel
        tcc.setPreviewPanel(new JPanel());

        // Override the chooser panels with our own
        AbstractColorChooserPanel panels[] = {new CrayonPanel()};
        tcc.setChooserPanels(panels);
        tcc.setColor(banner.getForeground());

        add(bannerPanel, BorderLayout.PAGE_START);
        add(panel, BorderLayout.CENTER);
        add(tcc, BorderLayout.PAGE_END);
    }

    public void actionPerformed(ActionEvent e)
    {
        Color newColor = JColorChooser.showDialog(ColorChooserDemo.this, "Choose Background Color", banner.getBackground());
        if (newColor != null) {
            banner.setBackground(newColor);
        }
    }

    public void stateChanged(ChangeEvent e)
    {
        Color newColor = tcc.getColor();
        banner.setForeground(newColor);
    }

    /**
     * Create the GUI and show it. For thread safety, this method should be invoked from the event-dispatching thread.
     */
    @Override
    public void run()
    {
        // Create and set up the window.
        JFrame frame = new JFrame(this.title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(scaledSize);
        frame.setLocation(0, 0);
        frame.setLocationRelativeTo(null);

        // Create and set up the content pane.
        JComponent newContentPane = new ColorChooserDemo(title, scaledSize);
        newContentPane.setOpaque(true); // content panes must be opaque
        frame.setContentPane(newContentPane);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
