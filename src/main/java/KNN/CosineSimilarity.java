package KNN;
//This class implements Metric interface and is used to calculate CosineSimilarity
//Notice: the CosineSimilarity is transformed into (1 / cosine) in order to be unified with Euclidean distance and L1 distance 

import com.jsilva.almamater.models.Survey;
import com.jsilva.almamater.models.University;

public class CosineSimilarity implements Metric {

	@Override
	public double getDistance(University s, Survey e) {
		assert s.getPoints().length == e.getPoints().length : "s and e are different types of records!";
		int numOfAttributes = s.getPoints().length;
		double cosine,sNorm,eNorm,se;
		int i;
		
		// get s * e
		se = 0;
		for(i = 0; i < numOfAttributes; i ++){
			se += s.getPoints()[i] * e.getPoints()[i];
		}
		
		// get s norm
		sNorm = 0;
		for(i = 0; i < numOfAttributes; i ++){
			sNorm += Math.pow(s.getPoints()[i], 2);
		}
		sNorm = Math.sqrt(sNorm);
		
		// get e norm
		eNorm = 0;
		for(i = 0; i < numOfAttributes; i ++){
			eNorm += Math.pow(e.getPoints()[i], 2);
		}
		eNorm = Math.sqrt(eNorm);
		
		// get cosine similarity
		if(se < 0)
			se = 0 - se; // Since KNN finds K nearest neighbors with certain range, the sign of se will not affect the result
		
		cosine = se / (sNorm * eNorm);
		
		// transform cosine similarity into dissimilarity such that this is unified with EuclideanDistance and L1Distance
		if(cosine == 0.0)
			return Double.MAX_VALUE;
		return 1 / cosine;
	}

}
