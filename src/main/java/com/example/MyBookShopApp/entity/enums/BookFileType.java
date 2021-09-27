package com.example.MyBookShopApp.entity.enums;

public enum BookFileType {

    PDF(".pdf"),
    EPUB(".epub"),
    FB2(".fb2");

    private final String fileExtensions;

    BookFileType(String fileExtensions) {
        this.fileExtensions = fileExtensions;
    }

    public static String getExtensionType(Integer typeId) {
        switch (typeId) {
            case 1:
                return BookFileType.PDF.fileExtensions;
            case 2:
                return BookFileType.EPUB.fileExtensions;
            case 3:
                return BookFileType.FB2.fileExtensions;
            default: return "";
        }
    }
}
