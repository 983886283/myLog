/**
 * @license Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	 // Define changes to default configuration here. For example:    
    //config.language = 'fr'; //设置语言风格   fr法语    
    //config.uiColor = '#63bbb2';//修改边框颜色    
         //设置宽高    
/*    config.width = 700;    
    config.height = 200;  */  
    config.toolbar = 'MyToolbar';      
    //config.toolbar = 'Full';      
    //config.toolbar = 'Basic';      
    config.toolbar_Full =       
    [       
        ['Source','-','Save','NewPage','Preview','-','Templates'],       
        ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],       
        ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],       
        ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'],       
        '/',       
        ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],       
        ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote','CreateDiv'],       
        ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],       
        ['BidiLtr', 'BidiRtl'],       
        ['Link','Unlink','Anchor'],       
        ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak','Iframe'],       
        '/',       
        ['Styles','Format','Font','FontSize'],       
        ['TextColor','BGColor'],       
        ['Maximize', 'ShowBlocks','-','About']       
    ];     
    
    config.toolbar_Basic =       
    [       
        ['Bold', 'Italic', '-', 'NumberedList', 'BulletedList', '-', 'Link', 'Unlink','-','About']       
    ];     
    config.toolbar_MyToolbar =       
    	[       
            ['Source','Preview','-','Templates','PasteText'],       
            ['Undo','Redo','-','Find','Replace','-','RemoveFormat'],       
            ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'],
            ['Outdent','Indent','Blockquote','CreateDiv'],       
            ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],       
            ['Link','Unlink','Anchor'],
            ['Image','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'], 
            '/',
            ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],       
            ['NumberedList','BulletedList','-'],
            ['Styles','Format','Font','FontSize'],       
            ['TextColor','BGColor'],       
            ['Maximize', 'ShowBlocks','CodeSnippet']       
        ];     
      
  //工具栏是否可以被收缩  
    config.toolbarCanCollapse = true;  
    //工具栏的位置  
    config.toolbarLocation = 'top';//可选：bottom  
    //工具栏默认是否展开  
    config.toolbarStartupExpanded = true;  
    // 取消 “拖拽以改变尺寸”功能 plugins/resize/plugin.js  
    config.resize_enabled = false;  
    //改变大小的最大高度  
    config.resize_maxHeight = 3000;  
    //改变大小的最大宽度  
    config.resize_maxWidth = 3000;  
    //改变大小的最小高度  
    config.resize_minHeight = 250;  
    //改变大小的最小宽度  
    config.resize_minWidth = 750;  
    // 当提交包含有此编辑器的表单时，是否自动更新元素内的数据  
    config.autoUpdateElement = true;  
    // 设置是使用绝对目录还是相对目录，为空为相对目录  
    config.baseHref = ''  
    // 编辑器的z-index值  
    config.baseFloatZIndex = 10000;  
    //设置快捷键  
    config.keystrokes = [  
       [ CKEDITOR.ALT + 121 /*F10*/, 'toolbarFocus' ],  //获取焦点  
        [ CKEDITOR.ALT + 122 /*F11*/, 'elementsPathFocus' ],  //元素焦点  
       [ CKEDITOR.SHIFT + 121 /*F10*/, 'contextMenu' ],  //文本菜单  
       [ CKEDITOR.CTRL + 90 /*Z*/, 'undo' ],  //撤销  
        [ CKEDITOR.CTRL + 89 /*Y*/, 'redo' ],  //重做  
        [ CKEDITOR.CTRL + CKEDITOR.SHIFT + 90 /*Z*/, 'redo' ],  //  
        [ CKEDITOR.CTRL + 76 /*L*/, 'link' ],  //链接  
        [ CKEDITOR.CTRL + 66 /*B*/, 'bold' ],  //粗体  
        [ CKEDITOR.CTRL + 73 /*I*/, 'italic' ],  //斜体  
        [ CKEDITOR.CTRL + 85 /*U*/, 'underline' ],  //下划线  
        [ CKEDITOR.ALT + 109 /*-*/, 'toolbarCollapse' ]  
    ]  
    //设置快捷键 可能与浏览器快捷键冲突 plugins/keystrokes/plugin.js.  
    config.blockedKeystrokes = [  
        CKEDITOR.CTRL + 66 /*B*/,  
        CKEDITOR.CTRL + 73 /*I*/,  
        CKEDITOR.CTRL + 85 /*U*/  
    ]  
    //设置编辑内元素的背景色的取值 plugins/colorbutton/plugin.js.  
    config.colorButton_backStyle = {  
        element : 'span',  
        styles : { 'background-color' : '#(color)' }  
    }  
};    