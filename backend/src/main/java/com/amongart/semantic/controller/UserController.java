package com.amongart.semantic.controller;


import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amongart.semantic.compare.RequestCompare;
import com.amongart.semantic.compare.ResponseCompare;
import com.amongart.semantic.depict.ResponseDepict;
import com.amongart.semantic.important.ResponseFamousWork;
import com.amongart.semantic.itemwork.ResponseWork;
import com.amongart.semantic.name.ResponseName;
import com.amongart.semantic.notablework.ResponseNotableWork;
import com.amongart.semantic.service.DataService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@CrossOrigin
@Api(value = "User Controller")
public class UserController {

    private DataService dataService;

    @Autowired
    public UserController(DataService userService) {
        this.dataService = userService;
    }

    @ApiOperation(value = "Get the data", notes = "", response = ResponseEntity.class, httpMethod = "GET")
    @RequestMapping(value="/get-data-work", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseWork getTheData(@RequestParam String idNameWorkWikidate) throws ClientProtocolException, IOException{
        return  dataService.getWork(idNameWorkWikidate);
    }
    
    @ApiOperation(value = "Get name painting", notes = "", response = ResponseEntity.class, httpMethod = "GET")
    @RequestMapping(value="/get-name-painting", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseName getNamePainting(@RequestParam String nameWork) throws ClientProtocolException, IOException{
        return  dataService.getPaintingName(nameWork);
    }

    @ApiOperation(value = "Get notable works", notes = "", response = ResponseEntity.class, httpMethod = "GET")
    @RequestMapping(value="/get-notable-work", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseNotableWork getNotableWorks(@RequestParam String idWikidataCreator) throws ClientProtocolException, IOException{
        return  dataService.getNotableWork(idWikidataCreator);
    }
    
    @ApiOperation(value = "Get the famous works ", notes = "", response = ResponseEntity.class, httpMethod = "GET")
    @RequestMapping(value="/famous-works", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseFamousWork getFamousWorks() throws ClientProtocolException, IOException{
        return  dataService.getFamousWorks();
    }
    
    @ApiOperation(value = "Get the depict of a work ", notes = "", response = ResponseEntity.class, httpMethod = "GET")
    @RequestMapping(value="/depicts-work", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDepict getDepicts(@RequestParam String idWikidataWork) throws ClientProtocolException, IOException{
        return  dataService.getDepicts(idWikidataWork);
    }
    
    @ApiOperation(value = "Get similar works ", notes = "", response = ResponseEntity.class, httpMethod = "POST")
    @RequestMapping(value="/similar-work", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseCompare getSimilar(@RequestBody RequestCompare request) throws ClientProtocolException, IOException{
        return  dataService.getSimilar(request.getIdWikidataDepict(), request.getStartingYear(), request.getFinishYear(), request.getLimit());
    }
    
}
