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
        var endFlags = Arrays.stream(properties.getValue(MyPluginConfigurable.NAME_HighlightingSettings1, "").split(",")).map(x -> x.toUpperCase(Locale.ROOT).trim()).collect(Collectors.toList());
        for (String endFlag : endFlags) {
            if (file.getName().toUpperCase(Locale.ROOT).endsWith(endFlag)) {
                FileEditorManagerListener.super.fileOpened(source, file);
                PsiFile psiFile = PsiManager.getInstance(source.getProject()).findFile(file);
                if (psiFile != null) {
                    HighlightingSettingsPerFile highlightingSettingsPerFile = HighlightingSettingsPerFile.getInstance(source.getProject());
                    String level = properties.getValue(MyPluginConfigurable.NAME_HighlightingSettings2, "").toUpperCase(Locale.ROOT).trim();
                    if ("NOACTION".equals(level)) {

                    } else if ("NONE".equals(level)) {
                        highlightingSettingsPerFile.setHighlightingSettingForRoot(psiFile, FileHighlightingSetting.fromInspectionsLevel(InspectionsLevel.NONE));
                    } else if ("SYNTAX".equals(level)) {
                        highlightingSettingsPerFile.setHighlightingSettingForRoot(psiFile, FileHighlightingSetting.fromInspectionsLevel(InspectionsLevel.SYNTAX));
                    }
                    if ("ALL".equals(level)) {
                        highlightingSettingsPerFile.setHighlightingSettingForRoot(psiFile, FileHighlightingSetting.fromInspectionsLevel(InspectionsLevel.ALL));
                    }
                }
                break;
            }
        }
    }
}
