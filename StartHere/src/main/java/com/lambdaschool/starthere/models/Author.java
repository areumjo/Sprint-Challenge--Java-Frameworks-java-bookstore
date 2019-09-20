package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "Author", description = "The Author Entity")
@Entity
@Table(name = "author")
public class Author extends Auditable
{
    @ApiModelProperty(name = "authorid", value = "primary key for Author", required = true, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long authorid;

    @ApiModelProperty(name = "lastname", value = "Author Last Name", required = true, example = "Hamblin")
    @Column(nullable = false)
    private String lastname;

    @ApiModelProperty(name = "firstname", value = "Author First Name", required = true, example = "Ryan")
    @Column(nullable = false)
    private String firstname;

    @ManyToMany(mappedBy = "authors")
    @JsonIgnoreProperties("authors")
    private List<Book> books = new ArrayList<>();

    public Author() {
    }

    public Author(String lastname, String firstname) {
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public long getAuthorid() {
        return authorid;
    }

    public void setAuthorid(long authorid) {
        this.authorid = authorid;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
