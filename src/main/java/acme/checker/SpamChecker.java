package acme.checker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;


@Service
public class SpamChecker {
	
	public boolean isSpam(final String text, final String strongSpam, final String weakSpam, final Double strongThreshold,final Double weakThreshold) {	
		
		final String[] w = text.split(" ");
		final List<String> wListText = this.getListGivenArray(w);
		final List<String> wList = wListText.stream().map(String::toLowerCase).collect(Collectors.toList());
		final String[] strongSpamWords = strongSpam.split(",");
		final List<String> strongSpamWordsList = this.getListGivenArray(strongSpamWords);
		
		final List<List<String>> multiplesStrongSpamWordsLists = new ArrayList<>();
		for(int i = 0; i<strongSpamWordsList.size(); i++) {
			final String aux = strongSpamWordsList.get(i); //nos encontramos con más de una palabra
			if(aux.trim().contains(" ")) {	
				multiplesStrongSpamWordsLists.add(this.getListGivenArray(aux.trim().split(" ")));
				strongSpamWordsList.remove(aux);
			}
		}
	

		final String[] weakSpamW = weakSpam.split(",");
		final List<String> weakSpamWordsList = this.getListGivenArray(weakSpamW);  
		final List<List<String>> multiplesWeakSpamWordsLists = new ArrayList<>();
		for(int i = 0; i<weakSpamWordsList.size(); i++) {
			final String aux = weakSpamWordsList.get(i);
			if(aux.trim().contains(" ")) {	
				multiplesWeakSpamWordsLists.add(this.getListGivenArray(aux.trim().split(" ")));
				weakSpamWordsList.remove(aux);
			}
		}

		Integer weakContador = 0;
		Integer strongContador = 0;
		for(int i = 0; i<wList.size(); i++) {
			final String word = wList.get(i);  // obtenemos la posición i de la lista de palabras
			
			if(strongSpamWordsList.contains(word)) {
				strongContador++;
			}  else if(weakSpamWordsList.contains(word)) {
				weakContador++;
			} else { //comprobamos si hay múltiples palabras --> [hard, core]
				for(final List<String> aux : multiplesStrongSpamWordsLists) { // [son, of, a, bitch]
					final List<String> copy = this.checkMultiple(aux, i, wList);
					if(copy.isEmpty()) {
						strongContador+= aux.size();
					}
				}
							
				for(final List<String> aux : multiplesWeakSpamWordsLists) { 
					final List<String> copy = this.checkMultiple(aux, i, wList);
					if(copy.isEmpty()) {
						weakContador+= aux.size();
					}
		
				}
					
			}
			
		}
		

		final Pair<Boolean, Boolean> res = this.isSpam(text, strongThreshold,weakThreshold, weakContador, strongContador);
		return !res.getFirst() && !res.getSecond();
	}
	
	public List<String> getListGivenArray(final String[] array) {
		final List<String> res = new ArrayList<>();
		for(final String s : array) {
			res.add(s.trim());
		}
		return res;
	}
	
	
	public Pair<Boolean, Boolean> isSpam(final String origin, final double strongThreshold,final double weakThreshold, final Integer weakContador, final Integer strongContador) {
		final Integer size = origin.split(" ").length;
		final double weakRatio = (double) weakContador/size;
		final double strongRatio = (double) strongContador/size;
		Boolean isWeakSpam = false;
		Boolean isStrongSpam = false;
		if(weakRatio > weakThreshold) isWeakSpam = true;
		if(strongRatio > strongThreshold) isStrongSpam = true;
		
		
		return Pair.of(isWeakSpam, isStrongSpam);
	}
	
	
	
	public List<String> checkMultiple(final List<String> aux, final int i, final List<String> wList) {
		final List<String> copy = new ArrayList<>(aux);
		
		int a = i;
		while(!copy.isEmpty() && a-i < aux.size()) {
			final String s = copy.get(0).toLowerCase().trim();
			if(a < wList.size()) {
				if(s.equals(wList.get(a).trim().toLowerCase())) copy.remove(0);
			} else {
				break;
			}
			
			
			a++;
			
		}
		
		return copy;
	}
	
}
