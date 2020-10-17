package com.baidu.controller;

import com.baidu.entity.Dictionary;
import com.baidu.entity.DictionaryItem;
import com.baidu.service.DictionaryService;
import com.baidu.utils.TreeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping("/")
    public String insertdic() {
        return "insertdic";
    }

    @GetMapping("/test")
    public String get() {
        return "test";
    }

    @GetMapping("/dictionaries/search")
    @ResponseBody
    public Page<Dictionary> search(String searchString, Pageable pageable) {
        return dictionaryService.search(searchString, pageable);
    }

    @GetMapping("/dictionary/dictionaryItems/search")
    @ResponseBody
    public Page<DictionaryItem> findSysTableField(@Param("dictionaryId") String dictionaryId, String searchString, Pageable pageable) {
        return dictionaryService.findSysTableField(dictionaryId, searchString, pageable);
    }

    @PostMapping("/dictionaries")
    public Dictionary save(@RequestBody Dictionary dictionary) throws Exception {
        return dictionaryService.save(dictionary);
    }

    @RequestMapping(value = "/tree/quote", method = RequestMethod.GET)
    public List<? extends TreeBuilder.Node> dictionaryTreeQuote(String entityName, String dicName, String idProperty, String pidProperty, String displayProperty, String dictionaryFilterScript) throws Exception {
        return dictionaryService.dictionaryTreeQuote(entityName, dicName, idProperty, pidProperty, displayProperty, dictionaryFilterScript);
    }
}