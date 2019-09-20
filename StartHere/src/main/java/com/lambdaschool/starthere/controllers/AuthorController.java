package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.ErrorDetail;
import com.lambdaschool.starthere.services.AuthorService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @ApiOperation(value = "Return a course associated with the course id", response = Author.class, responseContainer = "List")
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
            HttpServletRequest request)
    {
        List<Author> allAuthors = authorService.findAll();
        return new ResponseEntity<>(allAuthors, HttpStatus.OK);
    }

    @GetMapping(value = "/authors/{authorid}",
                produces = {"application/json"})
    public ResponseEntity<?> listBooksByAuthorId(HttpServletRequest request, @PathVariable Long authorid)
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");

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

    // localhost:2019/authors/author -- POST
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
