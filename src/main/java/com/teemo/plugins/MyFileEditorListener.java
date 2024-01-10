package com.teemo.plugins;

import com.intellij.codeInsight.daemon.impl.analysis.FileHighlightingSetting;
import com.intellij.codeInsight.daemon.impl.analysis.HighlightingSettingsPerFile;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.markup.InspectionsLevel;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;

/** auto set file highlighting inspections level to SYNTAX */
public class MyFileEditorListener implements FileEditorManagerListener {

    private static final String[] FILE_EXTENSIONS = {".java", ".ts", ".tsx", ".css", ".scss", ".less", ".html"};
    private static final Logger LOG = Logger.getInstance(MyFileEditorListener.class);

    @Override
    public void fileOpened(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
        LOG.info("File opened: " + file.getPath());
        for (String extension : FILE_EXTENSIONS) {
            if (file.getName().endsWith(extension)) {
                FileEditorManagerListener.super.fileOpened(source, file);
                PsiFile psiFile = PsiManager.getInstance(source.getProject()).findFile(file);
                if (psiFile != null) {
                    HighlightingSettingsPerFile highlightingSettingsPerFile = HighlightingSettingsPerFile.getInstance(source.getProject());
                    highlightingSettingsPerFile.setHighlightingSettingForRoot(psiFile, FileHighlightingSetting.fromInspectionsLevel(InspectionsLevel.SYNTAX));
                }
                break;
            }
        }
    }
}
