package sg.edu.nus.iss.day22redo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.day22redo.service.GiphyService;

@Controller 
@RequestMapping(path="/search")
public class GiphyController { 

    @Autowired
    private GiphyService giphySvc;

    @GetMapping
    public String getGifSearch(Model model, 
        @RequestParam String searchPhrase,
        @RequestParam Integer limit,
        @RequestParam String rating  ) {
            
            System.out.printf(">>> searchPhrase = %s, limit = %d, rating = %s\n", searchPhrase, limit, rating);
            model.addAttribute("searchPhrase", searchPhrase);

            List<String> gifs = giphySvc.getGifs(searchPhrase, limit, rating);

            return "result"; 
        }

}