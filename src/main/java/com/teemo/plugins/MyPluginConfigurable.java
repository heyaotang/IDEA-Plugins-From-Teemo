package com.teemo.plugins;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;

import javax.swing.*;
import java.awt.*;

public class MyPluginConfigurable implements Configurable {
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
        this.jPanel.add(new JLabel(MyPluginConstants.NAME_HIGHLIGHT_ALL + ":"));
        this.jPanel.add(new JLabel(MyPluginConstants.EXAMPLE_HIGHLIGHT_ALL));
        this.highlightAllField = new JTextField();
        this.jPanel.add(this.highlightAllField);
        //
        this.jPanel.add(new JLabel(MyPluginConstants.NAME_HIGHLIGHT_SYNTAX + ":"));
        this.jPanel.add(new JLabel(MyPluginConstants.EXAMPLE_HIGHLIGHT_SYNTAX));
        this.highlightSyntaxField = new JTextField();
        this.jPanel.add(this.highlightSyntaxField);
        //
        this.jPanel.add(new JLabel(MyPluginConstants.NAME_HIGHLIGHT_NONE + ":"));
        this.jPanel.add(new JLabel(MyPluginConstants.EXAMPLE_HIGHLIGHT_NONE));
        this.highlightNoneField = new JTextField();
        this.jPanel.add(this.highlightNoneField);
        //
        return this.jPanel;
    }

    @Override
    public boolean isModified() {
        return false
                || !this.highlightAllField.getText().equals(this.properties.getValue(MyPluginConstants.NAME_HIGHLIGHT_ALL, MyPluginConstants.DEFAULT_HIGHLIGHT_ALL))
                || !this.highlightSyntaxField.getText().equals(this.properties.getValue(MyPluginConstants.NAME_HIGHLIGHT_SYNTAX, MyPluginConstants.DEFAULT_HIGHLIGHT_SYNTAX))
                || !this.highlightNoneField.getText().equals(this.properties.getValue(MyPluginConstants.NAME_HIGHLIGHT_NONE, MyPluginConstants.DEFAULT_HIGHLIGHT_NONE));
    }

    @Override
    public void apply() {
        this.properties.setValue(MyPluginConstants.NAME_HIGHLIGHT_ALL, this.highlightAllField.getText());
        this.properties.setValue(MyPluginConstants.NAME_HIGHLIGHT_SYNTAX, this.highlightSyntaxField.getText());
        this.properties.setValue(MyPluginConstants.NAME_HIGHLIGHT_NONE, this.highlightNoneField.getText());
    }

    @Override
    public void reset() {
        this.highlightAllField.setText(this.properties.getValue(MyPluginConstants.NAME_HIGHLIGHT_ALL, MyPluginConstants.DEFAULT_HIGHLIGHT_ALL));
        this.highlightSyntaxField.setText(this.properties.getValue(MyPluginConstants.NAME_HIGHLIGHT_SYNTAX, MyPluginConstants.DEFAULT_HIGHLIGHT_SYNTAX));
        this.highlightNoneField.setText(this.properties.getValue(MyPluginConstants.NAME_HIGHLIGHT_NONE, MyPluginConstants.DEFAULT_HIGHLIGHT_NONE));
    }
}