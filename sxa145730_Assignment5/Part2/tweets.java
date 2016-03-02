import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import org.json.*;

public class tweets {
	
	static double jaccdist(Set<String> cv,Set<String> pv){
		
		double commoncount =0,totalcount=0;
		double distance;
		Iterator it= pv.iterator();
		while(it.hasNext()){
			if(cv.contains(it.next())){
				commoncount++;
			}
					
		}
		totalcount=cv.size()+pv.size()-commoncount;
		distance= 1-(commoncount/totalcount);
		return distance;
	}

static double SSE(HashMap<Integer,Long> c, HashMap<Integer,Set<Long>> cl,HashMap<Long,Set<String>> p){
		
		double dist=0;
		
		
		for(Map.Entry<Integer, Set<Long>> ent: cl.entrySet()){
	    	  Set<Long> pl=ent.getValue();
	    	  
	    	  for(Long a:pl){
	    		  dist=dist+jaccdist(p.get(a),p.get(c.get(ent.getKey())));
	    		  
	    	  }
		
		}
	     return dist;
	}
	public static void main(String[] args) throws JSONException, IOException {
		// TODO Auto-generated method stub
				
				String jsonData = "";
				String[] tweetswords;
				List<Set<String>> wordSetLevels = new ArrayList();
				HashMap<Long,Set<String>> points = new HashMap<Long,Set<String>>();
				HashMap<Integer,Set<Long>> clusters = new HashMap<Integer,Set<Long>>();
				HashMap<Integer,Long> centroids = new HashMap<Integer,Long>();
				
				String splitBy = "\\s+";
				int k= Integer.parseInt(args[0]);
				String tweetsfile = args[2];
				String seedsfile = args[1];
				String outputfile = args[3];
				  jsonData="[";
				BufferedReader br = null;
				try {
					String line;
					br = new BufferedReader(new FileReader(tweetsfile));
					while ((line = br.readLine()) != null) {
						jsonData += line +","+ "\n";
					}
					 jsonData+="]";
					 
				} finally {
					try {
						if (br != null)
							br.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
				
				JSONArray array = new JSONArray(jsonData);
				for(int index = 0; index < array.length(); ++index) {
					Set<String> tweettext=new HashSet<String>();
	                JSONObject offerObject = array.getJSONObject(index);
	               
	                String text = offerObject.getString("text");
	                String result= text.replaceAll("@\\p{L}+", "");
	                tweetswords=result.split(splitBy);
	                for(int i=0;i<tweetswords.length;i++){
	                	tweettext.add(tweetswords[i]);
	                }
	                points.put(offerObject.getLong("id"), tweettext);
	                //String result_new = result.replaceAll("[^\\w]", "");
	                //System.out.println("result:"+ result);
	                
				//System.out.println("text: " + offerObject.getString("text"));
				//System.out.println("id: " + offerObject.getLong("id"));
				wordSetLevels.add(tweettext);
				
				}
				
				
				br = new BufferedReader(new FileReader(seedsfile));
				String line;
				int i=0;
				Long id;
				
				while ((line = br.readLine()) != null) {
					Set<Long> tempset=new HashSet<Long>();
					line =line.replace(",", "");
					id=Long.parseLong(line);
					tempset.add(id);
					centroids.put(i, id);
					//System.out.println("check"+centroids);
					clusters.put(i, tempset);
					i++;
					
				}
				//System.out.println(clusters);
				
				
				double ssetemp=0,ssedist=0;
				for(int iterations=0;iterations<25;iterations++){
					double der=0;
				double distance,tempdistance;
				int clusternumber=0,tempcnumber=0;
				Set<Entry<Long, Set<String>>> set = points.entrySet();
			      Iterator<Entry<Long, Set<String>>> iterator = set.iterator();
			      while(iterator.hasNext()) {
			    	  tempdistance=Double.MAX_VALUE;
			    	  tempcnumber=0;
			    	  @SuppressWarnings("rawtypes")
					Map.Entry mentry= (Map.Entry)iterator.next();
			    	  @SuppressWarnings("unchecked")
					Long pointkey =  (Long) mentry.getKey();
 			    	  @SuppressWarnings("unchecked")
 			    	 
					Set<String> pointvalue = (Set<String>) mentry.getValue();
			    	  
			    	  Set<Entry<Integer, Long>> set2 = centroids.entrySet();
				      Iterator<Entry<Integer, Long>> iterator2 = set2.iterator();
				      while(iterator2.hasNext()) {
				    	  	
				    	  	@SuppressWarnings("rawtypes")
							Map.Entry mentry2 = (Map.Entry)iterator2.next();
				    	  	
					        Long centroidid =(Long) mentry2.getValue();
					        Set<String> centroidvalue= points.get(centroidid);
				    	 
					        distance=jaccdist(centroidvalue, pointvalue);
					        //System.out.println(distance);
					        if(distance<tempdistance){
					        	tempdistance=distance;
					        	clusternumber=(Integer) mentry2.getKey();
					        }
					        //tempcnumber++;
				      }
				      der=der+tempdistance;
				      //System.out.println(clusternumber);
				      clusters.get(clusternumber).add(pointkey);
				      
			      }
			      //System.out.println(der);
			      //System.out.println("centroids--:"+centroids+"\n"+"clusters:"+clusters);
			      //System.out.println(clusters);
			      ssedist=SSE(centroids, clusters, points);
				    
				   
			      
			      for(Map.Entry<Integer, Set<Long>> ent: clusters.entrySet()){
			    	  Set<Long> pl=ent.getValue();
			    	  double min_dist=Double.MAX_VALUE;
			    	  long new_centr=0;
			    	  for(Long a:pl){
			    		  double dist=0d;
			    		  for(Long b:pl){
			    			 
			    			  dist=dist+jaccdist(points.get(a),points.get(b));
			    		  }
			    		  if(dist<min_dist)
			    		  {
			    			  min_dist=dist;
			    			  new_centr=a;
			    		  }
			    	  }
			    	  
			    	  centroids.put(ent.getKey(), new_centr);
			    	  //System.out.println(centroids);
			    		  
			      }
			      
			  
			      
				}
				 System.out.println("SSE:"+ssedist);
				 @SuppressWarnings("UnusedAssignment")
		            String tempfiledata=null;
		            Set set=clusters.entrySet();
		            Iterator ifile= set.iterator();
		            FileWriter fw = new FileWriter(outputfile,false);
		            BufferedWriter bw = new BufferedWriter(fw);
		            while(ifile.hasNext()){
		                Map.Entry me=(Map.Entry)ifile.next();
		                tempfiledata=me.getKey()+"	"+me.getValue();
		                bw.write(tempfiledata);
		                bw.newLine();
		            }
		            bw.close();
		            fw.close();
			//System.out.println(clusters);
			      
	}

}
