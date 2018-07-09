package com.xwatcher.web.controller;

import com.xwatcher.web.model.index.HelloModel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meng li on 2017/3/2.
 */
@RestController
public class IndexController {

    @RequestMapping(value = "/")
    public HelloModel index() {
        HelloModel model = new HelloModel();
        model.setName("lemon.li");
        model.setAge(31);
        model.setSex("male");

        return model;
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public HelloModel index_Post(@RequestBody HelloModel model) {
        return model;
    }

    @RequestMapping(value = "/put", method = RequestMethod.PUT)
    public HelloModel index_put(@RequestBody HelloModel model) {
        return model;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<HelloModel> index_list() {

        List<HelloModel> helloModels=new ArrayList<HelloModel>();

        HelloModel model = new HelloModel();
        model.setName("lemon.li.01");
        model.setAge(31);
        model.setSex("male");
        helloModels.add(model);

        model = new HelloModel();
        model.setName("lemon.li.02");
        model.setAge(31-2);
        model.setSex("female");
        helloModels.add(model);

        model = new HelloModel();
        model.setName("lemon.li.03");
        model.setAge(31+3);
        model.setSex("male");
        helloModels.add(model);

        model = new HelloModel();
        model.setName("lemon.li.04");
        model.setAge(31-4);
        model.setSex("female");
        helloModels.add(model);

        model = new HelloModel();
        model.setName("lemon.li.05");
        model.setAge(31+5);
        model.setSex("male");
        helloModels.add(model);

        model = new HelloModel();
        model.setName("lemon.li.06");
        model.setAge(31-6);
        model.setSex("female");
        helloModels.add(model);

        return helloModels;
    }
    @RequestMapping(value = "/list02",method = RequestMethod.GET)
    public List<HelloModel> index_list02() {

        List<HelloModel> helloModels=new ArrayList<HelloModel>();

        HelloModel model = new HelloModel();
        model.setName("lemon.li.01");
        model.setAge(31);
        model.setSex("male");
        helloModels.add(model);

        model = new HelloModel();
        model.setName("lemon.li.02");
        model.setAge(31-2);
        model.setSex("female");
        helloModels.add(model);

        return helloModels;
    }
}
