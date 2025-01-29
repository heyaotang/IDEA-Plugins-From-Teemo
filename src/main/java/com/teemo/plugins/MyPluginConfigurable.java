package com.teemo.plugins;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;

import javax.swing.*;
import java.awt.*;

public class MyPluginConfigurable implements Configurable {
    public static final String NAME_HIGHLIGHT_ALL = "all";
    public static final String NAME_HIGHLIGHT_SYNTAX = "syntax";
    public static final String NAME_HIGHLIGHT_NONE = "none";
    private final PropertiesComponent properties = PropertiesComponent.getInstance();
    private JTextField highlightAllField;
    private JTextField highlightSyntaxField;
    private JTextField highlightNoneField;
    private JPanel jPanel;

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Plugins From Teemo";
    }

    @Override
    public JComponent createComponent() {
        this.jPanel = new JPanel(new GridLayout(3 * (3 + 1), 1));
        //
        this.jPanel.add(new JLabel(NAME_HIGHLIGHT_ALL + ":"));
        this.jPanel.add(new JLabel("example: .xml,.sql"));
        this.highlightAllField = new JTextField();
        this.jPanel.add(this.highlightAllField);
        //
        this.jPanel.add(new JLabel(NAME_HIGHLIGHT_SYNTAX + ":"));
        this.jPanel.add(new JLabel("example: .java,.ts,.css,.html"));
        this.highlightSyntaxField = new JTextField();
        this.jPanel.add(this.highlightSyntaxField);
        //
        this.jPanel.add(new JLabel(NAME_HIGHLIGHT_NONE + ":"));
        this.jPanel.add(new JLabel("support regexp, example: ^.*$"));
        this.highlightNoneField = new JTextField();
        this.jPanel.add(this.highlightNoneField);
        //
        return this.jPanel;
    }

    @Override
    public boolean isModified() {
        return false
                || !this.highlightAllField.getText().equals(this.properties.getValue(NAME_HIGHLIGHT_ALL, ".xml,.sql"))
                || !this.highlightSyntaxField.getText().equals(this.properties.getValue(NAME_HIGHLIGHT_SYNTAX, ".java,.ts,.css,.html"))
                || !this.highlightNoneField.getText().equals(this.properties.getValue(NAME_HIGHLIGHT_NONE, "^.*$"));
    }

    @Override
    public void apply() {
        this.properties.setValue(NAME_HIGHLIGHT_ALL, this.highlightAllField.getText());
        this.properties.setValue(NAME_HIGHLIGHT_SYNTAX, this.highlightSyntaxField.getText());
        this.properties.setValue(NAME_HIGHLIGHT_NONE, this.highlightNoneField.getText());
    }

    @Override
    public void reset() {
        this.highlightAllField.setText(this.properties.getValue(NAME_HIGHLIGHT_ALL, ".xml,.sql"));
        this.highlightSyntaxField.setText(this.properties.getValue(NAME_HIGHLIGHT_SYNTAX, ".java,.ts,.css,.html"));
        this.highlightNoneField.setText(this.properties.getValue(NAME_HIGHLIGHT_NONE, "^.*$"));
    }
}