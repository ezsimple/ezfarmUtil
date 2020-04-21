package ezfarm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.mkeasy.utils.QueryMap;
import io.mkeasy.webapp.processor.QueryFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelloController {
	
	@RequestMapping("/")
	public Object hello() throws Exception {

		String ns = "ezfarm.office.project";
		String nsId="selectEzProjCode";
		
		QueryMap params = new QueryMap();
		Object result = QueryFactory.execute(ns, nsId, params);
		result = QueryFactory.getResult(ns, nsId, result);
		
		log.debug("result : {}", result);
		
		return result;
	}

}
