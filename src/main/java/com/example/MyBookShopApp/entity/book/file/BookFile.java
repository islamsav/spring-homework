package com.example.MyBookShopApp.entity.book.file;

import com.example.MyBookShopApp.entity.book.BookEntity;
import com.example.MyBookShopApp.entity.enums.BookFileType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "book_file")
public class BookFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT NOT NULL AUTO_INCREMENT")
    private Integer id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String hash;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String path;

    @Column(name = "type_id", columnDefinition = "INT NOT NULL")
    private Integer typeId;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private BookEntity book;

    public String getBookFileExtensionString() {
        return BookFileType.getExtensionType(typeId);
    }
}