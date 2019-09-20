package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.ErrorDetail;
import com.lambdaschool.starthere.services.AuthorService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController
{
    private static final Logger logger = LoggerFactory.getLogger(UseremailController.class);

    @Autowired
    AuthorService authorService;

    // custom swagger
    @ApiOperation(value = "Return 3 authors on each page", response = Author.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). " + "Default sort order is ascending. " + "Multiple sort criteria are supported.")
    })
    @GetMapping(value = "/authors",
            produces = {"application/json"})
    public ResponseEntity<?> listAllAuthors(
            @PageableDefault(page = 0,
                    size = 3)
                    Pageable pageable)
    {
        List<Author> allAuthors = authorService.findAll(pageable);
        return new ResponseEntity<>(allAuthors, HttpStatus.OK);
    }

    // all - authors
    @GetMapping(value = "/allAuthors", produces = {"application/json"})
    public ResponseEntity<?> reallListAllAuthors()
    {
        List<Author> allAuthors = authorService.findAll(Pageable.unpaged());
        return new ResponseEntity<>(allAuthors, HttpStatus.OK);
    }

    // custom swagger
    @ApiOperation(value = "Return an author associated with the author id", response = Author.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Author by author-id Found", response = Author.class),
            @ApiResponse(code = 404, message = "Author by author-id Not Found", response = ErrorDetail.class)
    })
    @GetMapping(value = "/authors/{authorid}",
                produces = {"application/json"})
    public ResponseEntity<?> listAuthorByAuthorId(
            @ApiParam(value = "Author id", required = true, example = "1")
            @PathVariable long authorid)
    {
//        logger.trace(request.getMethod()
//                .toUpperCase() + " " + request.getRequestURI() + " accessed");
        Author newAut = authorService.findAuthorById(authorid);
        return new ResponseEntity<>(newAut, HttpStatus.OK);
    }

    // localhost:2019/authors/{authorid} -- DELETE
    @DeleteMapping("/authros/{authorid}")
    public ResponseEntity<?> deleteCourseById(@PathVariable long authorid)
    {
        authorService.delete(authorid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // custom swagger
    // localhost:2019/authors/author -- POST
    @ApiOperation(value = "Creates a new Author.", notes = "The newly created author id will be sent in the location header", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Author Successfully Created", response = void.class),
            @ApiResponse(code = 500, message = "Error creating author", response = ErrorDetail.class)
    })
    @PostMapping(value = "/author",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewCourse(@Valid @RequestBody Author newAuthor) throws URISyntaxException
    {
        newAuthor = authorService.save(newAuthor);
        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCourseURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{authorid}").buildAndExpand(newAuthor.getAuthorid()).toUri();
        responseHeaders.setLocation(newCourseURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // localhost:2019/authors/author/{authorid} -- PUT
    @PutMapping(value = "/author/{authorid}")
    public ResponseEntity<?> updateCourse(@RequestBody Author updateAuthor,
                                          @PathVariable long authorid)
    {
        authorService.update(updateAuthor, authorid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
