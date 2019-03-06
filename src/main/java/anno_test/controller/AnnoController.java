package anno_test.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.ibatis.session.SqlSessionFactory;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import anno_test.maps.TestMappable;

@org.springframework.stereotype.Controller
public class AnnoController {
	@Autowired
	Connection con;

	@Autowired
	TestMappable testMappable;

	@Autowired
	SqlSessionFactory sqlsession;
	
	// 몽고디비 커넥션
	// 1. client
	MongoClient mc = new MongoClient("localhost", 27017);
	// 2. 데이터베이스
	MongoDatabase mdb = mc.getDatabase("test");
	
	@RequestMapping(value = { "/mongo_connect" }, method = RequestMethod.GET)
	public String testConnection(ModelMap m) {
		m.addAttribute("message", mdb.getName());
		return "home";
	}
	
	@RequestMapping(value = { "/mongo_collection" }, method = RequestMethod.GET)
	public String testCollection(ModelMap m) {
		MongoIterable<String> cols = mdb.listCollectionNames();		
		List<String> list = StreamSupport.stream(cols.spliterator(), false).collect(Collectors.toList());
		m.addAttribute("list", list);
		return "test_mongo";
	}
	
	@RequestMapping(value = { "/mongo_document" }, method = RequestMethod.GET)
	public String testDocument(ModelMap m) {
		//collection
		MongoCollection<Document> coll = mdb.getCollection("shoes");
		// 출력
				
		ArrayList<Object> lists = new ArrayList<>();
		
		Document doc = null;						
		//Iterable Object
		FindIterable<Document> docs = coll.find();
		Iterator<Document> it = docs.iterator();
		while(it.hasNext()) {
			doc = it.next();
			lists.add(doc);
			System.out.println(doc);
		}		
		// 출력
		m.addAttribute("lists", lists);
		return "test_mongo_document";
	}

	/* 맵퍼 XML ############################################################### */
	/* 5. DELETE */

	/* 4. UPDATE */
	
	/* 3. INSERT */
	
	/* 2. WHERE SELECT */

	
	/* 1. 쿼리없는 SELECT */
	@RequestMapping(value = { "/get_list_xml" }, method = RequestMethod.GET)
	public String getListXml(ModelMap m) {
		System.out.println("getListXml START!");
		m.addAttribute("message", String.format("%s, %s", testMappable.getClass(), testMappable.selectTest()));
		return "home";
	}

	/* 맵퍼 애노테이션 ############################################################### */
	// 1. select
	@RequestMapping(value = { "/get_list_where" }, method = RequestMethod.GET)
	public String getListWhere(Model m) {
		m.addAttribute("message", testMappable.getListWhere(2));
		return "get_list_where";
	}


	/* 1. 쿼리없는 SELECT */
	@RequestMapping(value = { "/get_list" }, method = RequestMethod.GET)
	public String getList(ModelMap m) {
		m.addAttribute("message", String.format("%s, %s", testMappable.getClass(), testMappable.getList()));
		return "home";
	}



	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap m) {		
		m.addAttribute("message", "HI MESSAGE");		
		return "home";
	}
}
