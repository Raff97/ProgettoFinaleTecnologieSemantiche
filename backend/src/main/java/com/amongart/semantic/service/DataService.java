package com.amongart.semantic.service;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.amongart.semantic.compare.ResponseCompare;
import com.amongart.semantic.depict.ResponseDepict;
import com.amongart.semantic.important.ResponseFamousWork;
import com.amongart.semantic.itemwork.ResponseWork;
import com.amongart.semantic.name.ResponseName;
import com.amongart.semantic.notablework.ResponseNotableWork;

public interface DataService {
    ResponseWork getWork(String nameWork) throws ClientProtocolException, IOException;

	ResponseName getPaintingName(String nameWorkData) throws ClientProtocolException, IOException;

	ResponseNotableWork getNotableWork(String idNameWorkWikidate) throws ClientProtocolException, IOException;

	ResponseFamousWork getFamousWorks() throws ClientProtocolException, IOException;

	ResponseDepict getDepicts(String idWikidataWork) throws ClientProtocolException, IOException;

	ResponseCompare getSimilar(String idWikidataDepict, String startingYear, String finishYear, String limit)  throws ClientProtocolException, IOException;
}
