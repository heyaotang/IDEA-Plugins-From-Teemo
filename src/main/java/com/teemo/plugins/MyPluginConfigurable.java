package com.teemo.plugins;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;

import javax.swing.*;

import com.intellij.ide.util.PropertiesComponent;

import java.awt.*;

public class MyPluginConfigurable implements Configurable {
    private final PropertiesComponent properties = PropertiesComponent.getInstance();
    private JTextField highlightingSettings1Field;
    private JTextField highlightingSettings2Field;
    public static final String NAME_HighlightingSettings1 = "HighlightingSettings1";
    public static final String NAME_HighlightingSettings2 = "HighlightingSettings2";
    private JPanel jPanel;

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Plugins From Teemo";
    }

    @Override
    public JComponent createComponent() {
        jPanel = new JPanel(new GridLayout(6, 1));
        jPanel.add(new JLabel("HighlightingSettings 1:"));
        jPanel.add(new JLabel("example: .java,.ts,.tsx,.css,.scss,.less,.html,pom.xml"));
        highlightingSettings1Field = new JTextField();
        jPanel.add(highlightingSettings1Field);
        jPanel.add(new JLabel("HighlightingSettings 2:"));
        jPanel.add(new JLabel("example: NOACTION/NONE/SYNTAX/ALL"));
        highlightingSettings2Field = new JTextField();
        jPanel.add(highlightingSettings2Field);
        return jPanel;
    }

    @Override
    public boolean isModified() {
        return false
                || !highlightingSettings1Field.getText().equals(properties.getValue("HighlightingSettings1", ""))
                || !highlightingSettings2Field.getText().equals(properties.getValue("HighlightingSettings2", ""));
    }

    @Override
    public void apply() {
        properties.setValue("HighlightingSettings1", highlightingSettings1Field.getText());
        properties.setValue("HighlightingSettings2", highlightingSettings2Field.getText());
    }

    @Override
    public void reset() {
        highlightingSettings1Field.setText(properties.getValue("HighlightingSettings1", ""));
        highlightingSettings2Field.setText(properties.getValue("HighlightingSettings2", ""));
    }
}