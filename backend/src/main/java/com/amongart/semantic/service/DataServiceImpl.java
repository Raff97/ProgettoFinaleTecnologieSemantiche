package com.amongart.semantic.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.amongart.semantic.compare.CompareElement;
import com.amongart.semantic.compare.Date;
import com.amongart.semantic.compare.ResponseCompare;
import com.amongart.semantic.compare.ResponseCompareItem;
import com.amongart.semantic.compare.ResultCompare;
import com.amongart.semantic.dbpedia.ResponseDbpedia;
import com.amongart.semantic.depict.DepictElement;
import com.amongart.semantic.depict.ResponseDepict;
import com.amongart.semantic.depict.ResponseDepictItem;
import com.amongart.semantic.depict.ResultDepict;
import com.amongart.semantic.important.DateImportant;
import com.amongart.semantic.important.FamousWork;
import com.amongart.semantic.important.ResponseFamousWork;
import com.amongart.semantic.important.ResponseFamousWorkItem;
import com.amongart.semantic.important.ResultImportant;
import com.amongart.semantic.itemwork.Inception;
import com.amongart.semantic.itemwork.ResponseWork;
import com.amongart.semantic.itemwork.ResultWork;
import com.amongart.semantic.name.Element;
import com.amongart.semantic.name.ResponseName;
import com.amongart.semantic.name.ResponseNameItem;
import com.amongart.semantic.name.ResultName;
import com.amongart.semantic.notablework.ElementWork;
import com.amongart.semantic.notablework.NotableWork;
import com.amongart.semantic.notablework.NotableWorkInception;
import com.amongart.semantic.notablework.ResponseNotableWork;
import com.amongart.semantic.notablework.ResultNotableWork;
import com.google.gson.Gson;

@Service
public class DataServiceImpl implements DataService {

	@Override
	public ResponseWork getWork(String idWikidataWork) throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		//%2C virgola
		//%20 spazio
		idWikidataWork = idWikidataWork.replace(",", "%2C");
		idWikidataWork = idWikidataWork.replace(" ", "%20");
		HttpGet httpRequest = new HttpGet("https://query.wikidata.org/sparql?query=SELECT%20DISTINCT%20%3Flabel%20%3FauthorLabel%20%3Finception%20%3Fpic%20%3Fauthor%20%3FpicAuthor%0AWHERE%20%7B%0Awd%3A" + idWikidataWork + "%20rdfs%3Alabel%20%3Flabel%20.%0Awd%3A" + idWikidataWork + "%20wdt%3AP170%20%3Fauthor%20.%0A%3Fauthor%20rdfs%3Alabel%20%3FauthorLabel%20.%0Awd%3A" + idWikidataWork + "%20wdt%3AP571%20%3Finception%20.%0Awd%3A" + idWikidataWork + "%20wdt%3AP18%20%3Fpic%20.%0A%3Fauthor%20wdt%3AP18%20%3FpicAuthor%20.%0AFILTER%20(lang(%3Flabel)%20%3D%20%27en%27)%20.%0AFILTER%20(lang(%3FauthorLabel)%20%3D%20%27en%27)%0A%20%20%7D");
		Gson gson = new Gson();

		httpRequest.addHeader("Accept", String.valueOf(ContentType.APPLICATION_JSON));
		HttpResponse httpResponse = httpClient.execute(httpRequest);

		HttpEntity httpResponseEntity = httpResponse.getEntity();
		ResultWork wikidataWorkResult = gson.fromJson(EntityUtils.toString(httpResponseEntity),
				ResultWork.class);
		
		ResponseWork response = getResponse(wikidataWorkResult);
		response.setIdWikidataItem(idWikidataWork);
		ResponseDbpedia responseDbpedia = getDescription(response.getIdWikidataCreator(), response.getIdWikidataItem());
		
		response.setDescriptionCreator(getDesciptionAuthor(responseDbpedia));
		response.setDescriptionItem(getDesciptionItem(responseDbpedia));
		
		return response;
	}
	
	@Override
	public ResponseNotableWork getNotableWork(String idCreatorWikidata) throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		//%2C virgola
		//%20 spazio
		idCreatorWikidata = idCreatorWikidata.replace(",", "%2C");
		idCreatorWikidata = idCreatorWikidata.replace(" ", "%20");
		HttpGet httpRequest = new HttpGet("https://query.wikidata.org/sparql?query=SELECT%20%3FnotableWorkItem%20%3FnotableWorkInception%20%3FnotableWorkLabel%20%20%3FnotableWorkPic%20WHERE%20%7B%0Awd%3A"+idCreatorWikidata+"%20wdt%3AP800%20%3FnotableWorkItem.%0A%3FnotableWorkItem%20wdt%3AP18%20%3FnotableWorkPic.%0A%3FnotableWorkItem%20rdfs%3Alabel%20%3FnotableWorkLabel.%0A%3FnotableWorkItem%20wdt%3AP571%20%3FnotableWorkInception.%0AFILTER%20(lang(%3FnotableWorkLabel)%20%3D%20%27en%27)%0A%7D%0A");
		Gson gson = new Gson();

		httpRequest.addHeader("Accept", String.valueOf(ContentType.APPLICATION_JSON));
		HttpResponse httpResponse = httpClient.execute(httpRequest);

		HttpEntity httpResponseEntity = httpResponse.getEntity();
		ResultNotableWork wikidataNotableResult = gson.fromJson(EntityUtils.toString(httpResponseEntity),
				ResultNotableWork.class);
		
		ResponseNotableWork response = getResponse(wikidataNotableResult);
		
		return response;
	}

	@Override
	public ResponseName getPaintingName(String nameWorkData) throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		//%2C virgola
		//%20 spazio
		nameWorkData = nameWorkData.replace(",", "%2C");
		nameWorkData = nameWorkData.replace(" ", "%20");
		HttpGet httpRequest = new HttpGet("https://query.wikidata.org/sparql?query=SELECT%20DISTINCT%20%3Fitem%20%3Flabel%20%3Fauthor%20%3FlabelAuthor%20WHERE%20%7B%0A%3Fitem%20wdt%3AP31%20wd%3AQ3305213%20.%0A%3Fitem%20rdfs%3Alabel%20%3Flabel%20.%0A%3Fitem%20wdt%3AP170%20%3Fauthor%20.%0A%3Fauthor%20rdfs%3Alabel%20%3FlabelAuthor%20.%0AFILTER(REGEX(%3Flabel%2C%20%22"+nameWorkData+"%22%2C%20%22i%22)).%0AFILTER%20(lang(%3Flabel)%20%3D%20%27en%27)%20.%0AFILTER%20(lang(%3FlabelAuthor)%20%3D%20%27en%27)%20.%0A%7D%0ALIMIT%205");
		Gson gson = new Gson();

		httpRequest.addHeader("Accept", String.valueOf(ContentType.APPLICATION_JSON));
		HttpResponse httpResponse = httpClient.execute(httpRequest);

		HttpEntity httpResponseEntity = httpResponse.getEntity();
		ResultName wikidataResult = gson.fromJson(EntityUtils.toString(httpResponseEntity),
				ResultName.class);
		
		ResponseName response = getResponseName(wikidataResult);
		
		return response;
	}
	
	@Override
	public ResponseFamousWork getFamousWorks() throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		
		Random rand = new Random(); 
	    int upperbound = 80;
	    int int_random = rand.nextInt(upperbound) +1; 
		
		HttpGet httpRequest = new HttpGet("https://query.wikidata.org/sparql?query=SELECT%20DISTINCT%20%3Fitem%20%3Flabel%20%3Fauthor%20%3FauthorLabel%20%3Fimage%20%3Fdate%20WHERE%20%7B%20%0A%20%20%3Fitem%20p%3AP528%20%5B%20pq%3AP972%20wd%3AQ41634361%5D.%20%0A%20%20%3Fitem%20rdfs%3Alabel%20%3Flabel%20.%0A%20%20%3Fitem%20wdt%3AP170%20%3Fauthor%20.%0A%20%20%3Fauthor%20rdfs%3Alabel%20%3FauthorLabel%20.%0A%20%20%3Fitem%20wdt%3AP571%20%3Fdate%20.%0A%20%20%3Fitem%20wdt%3AP18%20%3Fimage%0A%20%20FILTER%20(lang(%3Flabel)%20%3D%20%27en%27)%20.%0A%20%20FILTER%20(lang(%3FauthorLabel)%20%3D%20%27en%27)%20.%0A%20%20FILTER%20(%3Fitem%20!%3D%20wd%3AQ1091086%20%26%26%20%3Fitem%20!%3D%20wd%3AQ219831%20%26%26%20%3Fitem%20!%3D%20wd%3AQ151047%20%26%26%20%3Fitem%20!%3D%20wd%3AQ21129709)%20.%0A%20%20FILTER%20NOT%20EXISTS%20%7B%0A%20%20%20%20%3Fitem%20wdt%3AP571%20%3Fdate2%20.%0A%20%20%20%20%3Fitem%20wdt%3AP18%20%3Fimage2%20.%0A%20%20%20%20%3Fitem%20wdt%3AP170%20%3Fauthor2%20.%0A%20%20%20%20FILTER%20(%3Fdate2%20%3E%20%3Fdate%20%7C%7C%20str(%3Fimage2)%20%3E%20str(%3Fimage)%20%7C%7C%20str(%3Fauthor2)%20%3E%20str(%3Fauthor))%0A%20%20%7D%20.%0A%7D%20ORDER%20BY%20RAND()%0AOFFSET%20" + int_random + "%0ALIMIT%206");
		Gson gson = new Gson();

		httpRequest.addHeader("Accept", String.valueOf(ContentType.APPLICATION_JSON));
		HttpResponse httpResponse = httpClient.execute(httpRequest);

		HttpEntity httpResponseEntity = httpResponse.getEntity();
		ResultImportant wikidataResult = gson.fromJson(EntityUtils.toString(httpResponseEntity),
				ResultImportant.class);
		
		ResponseFamousWork response = getResponseFamous(wikidataResult);
		
		return response;
	}
	
	@Override
	public ResponseDepict getDepicts(String idWikidataWork) throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		//%2C virgola
		//%20 spazio
		idWikidataWork = idWikidataWork.replace(",", "%2C");
		idWikidataWork = idWikidataWork.replace(" ", "%20");
		HttpGet httpRequest = new HttpGet("https://query.wikidata.org/sparql?query=SELECT%20%3Fdepict%20%3FdepictLabel%0AWHERE%20%0A%7B%0A%20%20wd%3A"+idWikidataWork+"%20wdt%3AP180%20%3Fdepict%20.%0A%20%20%3Fdepict%20rdfs%3Alabel%20%3FdepictLabel%20.%0A%20%20%0A%20%20FILTER%20(lang(%3FdepictLabel)%20%3D%20%27en%27)%0A%7D%20LIMIT%205");
		Gson gson = new Gson();

		httpRequest.addHeader("Accept", String.valueOf(ContentType.APPLICATION_JSON));
		HttpResponse httpResponse = httpClient.execute(httpRequest);

		HttpEntity httpResponseEntity = httpResponse.getEntity();
		ResultDepict wikidataDepictResult = gson.fromJson(EntityUtils.toString(httpResponseEntity),
				ResultDepict.class);
		
		ResponseDepict response = getResponse(wikidataDepictResult);
		
		return response;
	}
	
	@Override
	public ResponseCompare getSimilar(String idWikidataDepict, String startingYear, String finishYear, String limit)
			throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		//%2C virgola
		//%20 spazio
		idWikidataDepict = idWikidataDepict.replace(",", "%2C");
		idWikidataDepict = idWikidataDepict.replace(" ", "%20");
		startingYear = startingYear.replace(",", "%2C");
		startingYear = startingYear.replace(" ", "%20");
		finishYear = finishYear.replace(",", "%2C");
		finishYear = finishYear.replace(" ", "%20");
		limit = limit.replace(",", "%2C");
		limit = limit.replace(" ", "%20");
		HttpGet httpRequest = new HttpGet("https://query.wikidata.org/sparql?query=SELECT%20DISTINCT%20%3Fitem%20%3FitemLabel%20%3Fauthor%20%3FauthorLabel%20%3Fdate%20%3Fimage%0AWHERE%20%0A%7B%0A%20%20%3Fitem%20wdt%3AP180%20wd%3A"+idWikidataDepict+"%20.%0A%20%20%3Fitem%20wdt%3AP31%20wd%3AQ3305213%20.%0A%20%20%3Fitem%20rdfs%3Alabel%20%3FitemLabel%20.%0A%20%20%3Fitem%20wdt%3AP571%20%3Fdate%20.%0A%20%20%3Fitem%20wdt%3AP18%20%3Fimage%20.%0A%20%20%3Fitem%20wdt%3AP170%20%20%3Fauthor%20.%0A%20%20%3Fauthor%20rdfs%3Alabel%20%3FauthorLabel%20.%0A%20%20%0A%20%20FILTER%20(lang(%3FitemLabel)%20%3D%20'en')%0A%20%20FILTER%20(lang(%3FauthorLabel)%20%3D%20'en')%20%20%0A%20%20FILTER%20(%3Fdate%3E%20%22"+startingYear+"-01-01%22%5E%5Exsd%3AdateTime%20%26%26%20%3Fdate%20%3C%20%22"+finishYear+"-01-01%22%5E%5Exsd%3AdateTime)%0A%20%20FILTER%20NOT%20EXISTS%20%7B%0A%20%20%3Fitem%20wdt%3AP571%20%3Ffirst_date%0A%20%20filter%20(%3Ffirst_date%20%3E%20%3Fdate)%0A%7DFILTER%20NOT%20EXISTS%20%7B%0A%20%20%3Fitem%20wdt%3AP18%20%3Fimage2%0A%20%20filter%20(%3Fimage2%20!%3D%20%3Fimage)%0A%7D%20%0A%7D%20ORDER%20BY%20RAND()%0ALIMIT%20"+limit);
		Gson gson = new Gson();

		httpRequest.addHeader("Accept", String.valueOf(ContentType.APPLICATION_JSON));
		HttpResponse httpResponse = httpClient.execute(httpRequest);

		HttpEntity httpResponseEntity = httpResponse.getEntity();
		ResultCompare wikidataResult = gson.fromJson(EntityUtils.toString(httpResponseEntity),
				ResultCompare.class);
		
		ResponseCompare response = getResponse(wikidataResult);
		
		return response;
	}
	
	private ResponseCompare getResponse(ResultCompare result) {
		ResponseCompare response = new ResponseCompare();
		if(Objects.isNull(result) || Objects.isNull(result.getResults()) || CollectionUtils.isEmpty(result.getResults().getBindings())) {
			return response;
		}
		
		for(CompareElement element : result.getResults().getBindings()) {
			ResponseCompareItem item = new ResponseCompareItem();
			item.setDateItem(getDate(element.getDate()));
			item.setIdWikidataCreator(element.getAuthor().getValue().split("/")[4]);
			item.setIdWikidataItem(element.getItem().getValue().split("/")[4]);
			item.setImageItem(element.getImage().getValue());
			item.setNameCreator(element.getAuthorLabel().getValue());
			item.setNameItem(element.getItemLabel().getValue());
			
			response.getItems().add(item);
		}
		
		return response;
	}

	private String getDate(Date dateItem) {
		if(Objects.isNull(dateItem) || Objects.isNull(dateItem.getValue())) {
			return null;
		}
		return dateItem.getValue().split("-")[0];
	}

	private ResponseDepict getResponse(ResultDepict result) {
		ResponseDepict response = new ResponseDepict();
		if(Objects.isNull(result) || Objects.isNull(result.getResults()) || CollectionUtils.isEmpty(result.getResults().getBindings())) {
			return response;
		}
		
		for(DepictElement element : result.getResults().getBindings()) {
			ResponseDepictItem item = new ResponseDepictItem();
			item.setIdWikidataDepict(element.getDepict().getValue().split("/")[4]);
			item.setNameDepict(element.getDepictLabel().getValue());
			response.getItems().add(item);
		}
		
		return response;
	}

	private ResponseFamousWork getResponseFamous(ResultImportant result) {
		ResponseFamousWork response = new ResponseFamousWork();
		if(Objects.isNull(result) || Objects.isNull(result.getResults()) || CollectionUtils.isEmpty(result.getResults().getBindings())) {
			return response;
		}
		
		for(FamousWork element : result.getResults().getBindings()) {
			ResponseFamousWorkItem item = new ResponseFamousWorkItem();
			item.setNameItem(element.getLabel().getValue());
			item.setNameCreator(element.getAuthorLabel().getValue());
			item.setIdWikidataCreator(element.getAuthor().getValue().split("/")[4]);
			item.setIdWikidataItem(element.getItem().getValue().split("/")[4]);
			item.setImageItem(element.getImage().getValue());
			item.setDateItem(getDate(element.getDate()));
			response.getItems().add(item);
		}
		
		return response;
	}

	private ResponseName getResponseName(ResultName result) {
		ResponseName response = new ResponseName();
		if(Objects.isNull(result) || Objects.isNull(result.getResults()) || CollectionUtils.isEmpty(result.getResults().getBindings())) {
			return response;
		}
		
		for(Element element : result.getResults().getBindings()) {
			ResponseNameItem item = new ResponseNameItem();
			item.setNameItem(element.getLabel().getValue());
			item.setUrlItem(element.getItem().getValue());
			item.setNameCreator(element.getLabelAuthor().getValue());
			item.setUrlCreator(element.getAuthor().getValue());
			item.setIdWikidataAuthor(item.getUrlCreator().split("/")[4]);
			item.setIdWikidataItem(item.getUrlItem().split("/")[4]);
			response.getItems().add(item);
		}
		
		return response;
	}

	private String getDesciptionAuthor(ResponseDbpedia response) {
		if(Objects.isNull(response) || Objects.isNull(response.getResults()) || CollectionUtils.isEmpty(response.getResults().getBindings())) {
			return null;
		}
		return response.getResults().getBindings().get(0).getAbstractAuthor()!=null ? response.getResults().getBindings().get(0).getAbstractAuthor().getValue() : null;
	}
	
	private String getDesciptionItem(ResponseDbpedia response) {
		if(Objects.isNull(response) || Objects.isNull(response.getResults()) || CollectionUtils.isEmpty(response.getResults().getBindings())) {
			return null;
		}
		return response.getResults().getBindings().get(0).getAbstractPainting()!=null ? response.getResults().getBindings().get(0).getAbstractPainting().getValue() : null;
	}
	
	private ResponseDbpedia getDescription(String wikidataCreator, String wikidataItem) throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpRequest = new HttpGet("https://dbpedia.org/sparql?query=PREFIX+dbres%3A+%3Chttp%3A%2F%2Fdbpedia.org%2Fresource%2F%3E%0D%0APREFIX+dbpedia-owl%3A+%3Chttp%3A%2F%2Fdbpedia.org%2Fontology%2F%3E%0D%0APrefix+wikidata%3A+%3Chttp%3A%2F%2Fwww.wikidata.org%2Fentity%2F%3E%0D%0A%0D%0ASELECT+%3FabstractAuthor%2C%3FabstractPainting++WHERE%0D%0A%7B%0D%0A++%3FAuthor+owl%3AsameAs+wikidata%3A"+wikidataCreator+"+.%0D%0A++%3FPainting+owl%3AsameAs+wikidata%3A"+wikidataItem+".%0D%0A++%3FAuthor+dbpedia-owl%3Aabstract+%3FabstractAuthor+.%0D%0A++%3FPainting+dbpedia-owl%3Aabstract+%3FabstractPainting+.%0D%0A%0D%0A++FILTER%28langMatches%28lang%28%3FabstractPainting%29%2C%22en%22%29%29%0D%0A++FILTER%28langMatches%28lang%28%3FabstractAuthor%29%2C%22en%22%29%29%0D%0A++%0D%0A%7D&format=application%2Fsparql-results%2Bjson");
		Gson gson = new Gson();

		HttpResponse httpResponse = httpClient.execute(httpRequest);

		HttpEntity httpResponseEntity = httpResponse.getEntity();
		ResponseDbpedia result = gson.fromJson(EntityUtils.toString(httpResponseEntity),
				ResponseDbpedia.class);
		
		return result;
	}
	
	private ResponseNotableWork getResponse(ResultNotableWork result) {
		ResponseNotableWork response = new ResponseNotableWork();
		if(Objects.isNull(result) || Objects.isNull(result.getResults()) || CollectionUtils.isEmpty(result.getResults().getBindings())) {
			return response;
		}
		Map<String, NotableWork> mapNotableWork = new HashMap<>();
		for (ElementWork item : result.getResults().getBindings()) {
			if (mapNotableWork.get(item.getNotableWorkLabel().getValue()) == null) {
				NotableWork notableWork = new NotableWork();
				notableWork.setNameWork(item.getNotableWorkLabel().getValue());
				notableWork.setUrlWork(item.getNotableWorkItem().getValue());
				notableWork.setImageWork(item.getNotableWorkPic().getValue());
				notableWork.setDateWork(getDate(item.getNotableWorkInception()));
				notableWork.setIdWikidataWork(notableWork.getUrlWork().split("/")[4]);
				mapNotableWork.put(item.getNotableWorkLabel().getValue(), notableWork);
				response.getNotableWorks().add(notableWork);
			}

		}
		
		return response;
	}

	private static ResponseWork getResponse(ResultWork result) {
		ResponseWork response = new ResponseWork();
		if(Objects.isNull(result) || Objects.isNull(result.getResults()) || CollectionUtils.isEmpty(result.getResults().getBindings())) {
			return response;
		}
		response.setNameItem(result.getResults().getBindings().get(0).getLabel().getValue());
		response.setImageItem(result.getResults().getBindings().get(0).getPic().getValue());
		response.setDateItem(getDate(result.getResults().getBindings().get(0).getInception()));
		response.setUrlCreator(result.getResults().getBindings().get(0).getAuthor().getValue());
		response.setNameCreator(result.getResults().getBindings().get(0).getAuthorLabel().getValue());
		response.setIdWikidataCreator(response.getUrlCreator().split("/")[4]);
		response.setImageCreator(result.getResults().getBindings().get(0).getPicAuthor().getValue());
		
		return response;
	}

	private static String getDate(NotableWorkInception dateItem) {
		if(Objects.isNull(dateItem) || Objects.isNull(dateItem.getValue())) {
			return null;
		}
		return dateItem.getValue().split("-")[0];
	}

	private static String getDate(Inception dateItem) {
		if(Objects.isNull(dateItem) || Objects.isNull(dateItem.getValue())) {
			return null;
		}
		return dateItem.getValue().split("-")[0];
	}
	
	private String getDate(DateImportant dateItem) {
		if(Objects.isNull(dateItem) || Objects.isNull(dateItem.getValue())) {
			return null;
		}
		return dateItem.getValue().split("-")[0];
	}

}

