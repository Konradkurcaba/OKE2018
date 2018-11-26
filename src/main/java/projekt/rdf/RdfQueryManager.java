package projekt.rdf;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

public class RdfQueryManager {
	
	public boolean checkWord(String aWord)
	{
		List<String> wordTypesList =  getWordType(aWord);
		
		if(wordTypesList.stream()
		.anyMatch(word -> {
			if (Constants.getTypesList().contains(word))return true;
			else return false;
		})
		) return true;
		else return false;
		
	}
	
	
	public List<String> getWordType(String aWord) {
		String queryValue = "PREFIX dbres: <http://dbpedia.org/resource/> PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
				+ "select ?o where {dbres:"+ aWord + " rdf:type ?o}";		
	           
		Query query = QueryFactory.create(queryValue);
		try (QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query)) {
			// Set the DBpedia specific timeout.
			((QueryEngineHTTP) qexec).addParam("timeout", "10000");

			// Execute.
			ResultSet rs = qexec.execSelect();
			List<String> resultList = new ArrayList<>();
			while(rs.hasNext())
			{
				QuerySolution qSolution = rs.nextSolution() ;
				RDFNode rdfNode = qSolution.get("o");
				resultList.add(rdfNode.toString());
			}
			return resultList;
	}

}
}
