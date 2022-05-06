package sg.edu.nus.iss.day22redo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.nus.iss.day22redo.service.GiphyService;

@SpringBootTest
class Day22redoApplicationTests {

	@Autowired
	private GiphyService giphySvc;

	@Test
	void shouldLoad10Images() {
		List<String> gifs = giphySvc.getGifs("pokemon", 10, "g");
		assertEquals(10, gifs.size(), "Default number of gifs");
	}

	@Test
	void shouldLoad10ImagesFromPhraseAndRating(){
		List<String> gifs = giphySvc.getGifs("pokemon", "g");
		assertEquals(10, gifs.size(), "Default number of gifs");
	}
	
	@Test
	void shouldLoad10ImagesFromPhraseAndLimit(){
		List<String> gifs = giphySvc.getGifs("pokemon", 10);
		assertEquals(10, gifs.size(), "Default number of gifs");
	}

	@Test
	void shouldLoad10ImagesFromPhrase(){
		List<String> gifs = giphySvc.getGifs("pokemon");
		assertEquals(10, gifs.size(), "Default number of gifs");
	}
    

}
