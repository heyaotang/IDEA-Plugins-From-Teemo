package com.teemo.plugins;

import com.intellij.codeInsight.daemon.impl.analysis.FileHighlightingSetting;
import com.intellij.codeInsight.daemon.impl.analysis.HighlightingSettingsPerFile;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.markup.InspectionsLevel;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public class MyFileEditorListener implements FileEditorManagerListener {

    private static final Logger LOG = Logger.getInstance(MyFileEditorListener.class);
    private final PropertiesComponent properties = PropertiesComponent.getInstance();

    @Override
    public void fileOpened(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
        LOG.info("File opened: " + file.getPath());
        var allFlags = Arrays.stream(this.properties.getValue(MyPluginConstants.NAME_HIGHLIGHT_ALL, MyPluginConstants.DEFAULT_HIGHLIGHT_ALL).split(",")).map(x -> x.toUpperCase(Locale.ROOT).trim()).collect(Collectors.toList());
        var syntaxFlags = Arrays.stream(this.properties.getValue(MyPluginConstants.NAME_HIGHLIGHT_SYNTAX, MyPluginConstants.DEFAULT_HIGHLIGHT_SYNTAX).split(",")).map(x -> x.toUpperCase(Locale.ROOT).trim()).collect(Collectors.toList());
        var noneFlags = Arrays.stream(this.properties.getValue(MyPluginConstants.NAME_HIGHLIGHT_NONE, MyPluginConstants.DEFAULT_HIGHLIGHT_NONE).split(",")).map(x -> x.toUpperCase(Locale.ROOT).trim()).collect(Collectors.toList());
        //
        for (String allFlag : allFlags) {
            if (file.getName().toUpperCase(Locale.ROOT).endsWith(allFlag)) {
                FileEditorManagerListener.super.fileOpened(source, file);
                PsiFile psiFile = PsiManager.getInstance(source.getProject()).findFile(file);
                if (psiFile != null) {
                    HighlightingSettingsPerFile highlightingSettingsPerFile = HighlightingSettingsPerFile.getInstance(source.getProject());
                    highlightingSettingsPerFile.setHighlightingSettingForRoot(psiFile, FileHighlightingSetting.fromInspectionsLevel(InspectionsLevel.ALL));
                }
                return;
            }
        }
        //
        for (String syntaxFlag : syntaxFlags) {
            if (file.getName().toUpperCase(Locale.ROOT).endsWith(syntaxFlag)) {
                FileEditorManagerListener.super.fileOpened(source, file);
                PsiFile psiFile = PsiManager.getInstance(source.getProject()).findFile(file);
                if (psiFile != null) {
                    HighlightingSettingsPerFile highlightingSettingsPerFile = HighlightingSettingsPerFile.getInstance(source.getProject());
                    highlightingSettingsPerFile.setHighlightingSettingForRoot(psiFile, FileHighlightingSetting.fromInspectionsLevel(InspectionsLevel.SYNTAX));
                }
                return;
            }
        }
        //
        for (String noneFlag : noneFlags) {
            if (file.getName().toUpperCase(Locale.ROOT).endsWith(noneFlag) || file.getName().toUpperCase(Locale.ROOT).matches(noneFlag)) {
                FileEditorManagerListener.super.fileOpened(source, file);
                PsiFile psiFile = PsiManager.getInstance(source.getProject()).findFile(file);
                if (psiFile != null) {
                    HighlightingSettingsPerFile highlightingSettingsPerFile = HighlightingSettingsPerFile.getInstance(source.getProject());
                    highlightingSettingsPerFile.setHighlightingSettingForRoot(psiFile, FileHighlightingSetting.fromInspectionsLevel(InspectionsLevel.NONE));
                }
                return;
            }
        }
    }
}
