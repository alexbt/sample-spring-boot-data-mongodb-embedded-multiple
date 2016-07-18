package com.alexbt.mongodb;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mongo")
public class MongoController {

	@Autowired
	@Qualifier("one")
	private ModelMongoRepository mongoRepository;

	@Autowired
	@Qualifier("two")
	private ModelMongoRepository mongoRepository2;

	@Autowired
	@Qualifier("two-other")
	private OtherModelMongoRepository mongoRepository2Other;

	@RequestMapping(path = "/repo/one", method = RequestMethod.GET)
	public Iterable<Model> findByRepoOne() throws IOException {
		return mongoRepository.findAll();
	}

	@RequestMapping(path = "/repo/two", method = RequestMethod.GET)
	public Iterable<Model> findByRepoTwo() throws IOException {
		return mongoRepository2.findAll();
	}

	@RequestMapping(path = "/repo/twoother", method = RequestMethod.GET)
	public Iterable<OtherModel> findByRepo2bis() throws IOException {
		return mongoRepository2Other.findAll();
	}

	@RequestMapping(value = "/repo/{value}", method = RequestMethod.GET)
	public void saveByRepo(@PathVariable String value) {
		Model model = new Model();
		model.setId(System.currentTimeMillis());
		model.setValue(value);

		OtherModel model2 = new OtherModel();
		model2.setId(System.currentTimeMillis());
		model2.setValue(value);

		mongoRepository.save(model);
		mongoRepository2Other.save(model2);
		mongoRepository2.save(model);
	}
}
