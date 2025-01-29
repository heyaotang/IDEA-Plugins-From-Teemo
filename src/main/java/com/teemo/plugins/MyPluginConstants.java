package com.teemo.plugins;

public class MyPluginConstants {
    // 配置项名称常量
    public static final String NAME_HIGHLIGHT_ALL = "all";
    public static final String NAME_HIGHLIGHT_SYNTAX = "syntax";
    public static final String NAME_HIGHLIGHT_NONE = "none";

    // 默认值常量
    public static final String DEFAULT_HIGHLIGHT_ALL = ".xml,.sql";
    public static final String DEFAULT_HIGHLIGHT_SYNTAX = ".java,.ts,.css,.html";
    public static final String DEFAULT_HIGHLIGHT_NONE = "^.*$";

    // 示例文本常量
    public static final String EXAMPLE_HIGHLIGHT_ALL = "example: .xml,.sql";
    public static final String EXAMPLE_HIGHLIGHT_SYNTAX = "example: .java,.ts,.css,.html";
    public static final String EXAMPLE_HIGHLIGHT_NONE = "support regexp, example: ^.*$";
} 