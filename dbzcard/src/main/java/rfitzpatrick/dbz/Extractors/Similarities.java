package rfitzpatrick.dbz.Extractors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class Similarities {

	
	public Similarities(){
		
	}
	
	
	public double cosine_similarity(String x, String y){ // Closer to 1 is better
		char[] x_words = x.toCharArray();
		char[] y_words = y.toCharArray();
		Set<Character> x_set = new HashSet<Character>();
		Set<Character> y_set = new HashSet<Character>();
		Set<Character> all_words = new HashSet<Character>();
		List<Integer> x_vector = new ArrayList<Integer>();
		List<Integer> y_vector = new ArrayList<Integer>();
		for(char word:x_words){
			x_set.add(word);
			all_words.add(word);
		}
		for(char word:y_words){
			y_set.add(word);
			all_words.add(word);
		}
		for(char word:all_words){
			x_vector.add(Collections.frequency(x_set, word));
			y_vector.add(Collections.frequency(y_set, word));
		}
		double dot_product = 0;
		double mag_x = 0;
		double mag_y = 0;
		for(int i = 0; i < all_words.size();i++){
			dot_product = dot_product + (x_vector.get(i)*y_vector.get(i));
			mag_x = mag_x + (Math.pow(x_vector.get(i),2));
			mag_y = mag_y + (Math.pow(y_vector.get(i),2));
		}
		mag_x = Math.sqrt(mag_x);
		mag_y = Math.sqrt(mag_y);
		double cosine = dot_product/(mag_x*mag_y);
		return cosine;
	}
	public double jaccard_coefficient(String x,String y){ //Closer to 1 is better
		char[] x_words = x.toCharArray();
		char[] y_words = y.toCharArray();
		Set<Character> x_set = new HashSet<Character>();
		Set<Character> y_set = new HashSet<Character>();
		Set<Character> all_words = new HashSet<Character>();
		List<Integer> x_vector = new ArrayList<Integer>();
		List<Integer> y_vector = new ArrayList<Integer>();
		for(char word:x_words){
			x_set.add(word);
			all_words.add(word);
		}
		for(char word:y_words){
			y_set.add(word);
			all_words.add(word);
		}
		for(char word:all_words){
			x_vector.add(Collections.frequency(x_set, word));
			y_vector.add(Collections.frequency(y_set, word));
		}
		double min = 0;
		double max = 0;
		for(int i:x_vector){
			min = min + Math.min(x_vector.get(i), y_vector.get(i));
			max = max + Math.max(x_vector.get(i), y_vector.get(i));
		}
		double coefficient = min/max;
		return coefficient;
	}
	
}
