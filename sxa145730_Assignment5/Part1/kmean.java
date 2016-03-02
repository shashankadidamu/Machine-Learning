import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Map.Entry;

public class kmean {
		
	void findclustervalues(int[] ci,double[][] p){
		
	}
	static double[][] findnewcentroid(HashMap<Integer,List<Integer>> c,double[][] cv,double[][] p){
		List<Integer> s;
		//String splitBy = ",";
		String[] sarray= null;
		int t=0;
		
		 Set<Entry<Integer, List<Integer>>> set = c.entrySet();
	      Iterator<Entry<Integer, List<Integer>>> iterator = set.iterator();
	      while(iterator.hasNext()) {
	         @SuppressWarnings("rawtypes")
			Map.Entry mentry = (Map.Entry)iterator.next();
	         
	         s= (List<Integer>) mentry.getValue();
	         double x=0,y=0;
	        // sarray=s.split(splitBy);
	         for(int i: s){
	        	 
	        	 x=x+p[i][0];
	        	 y=y+p[i][1];
	         }
	         
	         cv[t][0]=x/s.size();
	         cv[t][1]=y/s.size();
	         t++;
	      }

	      return cv;
	}
	
	static double SSE(double[][] cv, HashMap<Integer,List<Integer>> c,double[][] p){
		
		double x1,x2,y1,y2;
		double distance =0;
		List<Integer> s;
		//String splitBy = ",";
		String[] sarray= null;
		int t=0;
		
		 Set<Entry<Integer, List<Integer>>> set = c.entrySet();
	      Iterator<Entry<Integer, List<Integer>>> iterator = set.iterator();
	      while(iterator.hasNext()) {
	    	  
	         @SuppressWarnings("rawtypes")
			Map.Entry mentry = (Map.Entry)iterator.next();
	         
	         s= (List<Integer>) mentry.getValue();
	         x1=cv[t][0]; y1= cv[t][1];
	         double x=0,y=0;
	        // sarray=s.split(splitBy);
	         for(int i: s){
	        	 x2= p[i][0]; y2= p[i][1];
	        	 distance=distance+((Math.pow(Math.abs(x2-x1), 2)+Math.pow(Math.abs(y2-y1), 2)));
	         }
	         
	      //   System.out.println("sse dstance:"+ distance);
	         t++;
	      }
	      return distance;
	}
	
		public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList a = new ArrayList();
		int[] centroidindices;
		
		double[][] points= new double[100][100];
		HashMap<Integer,List<Integer>> clusters = new HashMap<Integer,List<Integer>>();
		try {
					
			//Reading data
			String dbFile = args[2];
			RandomAccessFile file1 =new RandomAccessFile(dbFile, "rw");
			String splitBy = "\\s+";
			URL url = new URL(args[1]);
			Scanner s1 = new Scanner(url.openStream());
			s1.nextLine();
			String line= "";
			int i=0;
			String[] s = null;			
			
			while(s1.hasNextLine()==true){
				line=s1.nextLine();
				s = line.split(splitBy);
				points[i][0]=Double.parseDouble(s[1]);
				points[i][1]=Double.parseDouble(s[2]);
				i++;
			}
			
			
			//finding initial centroids indices
			Random randomGenerator = new Random();
			
			int k=Integer.parseInt(args[0]);
			double[][] centroidvalues=new double[k][2];
			centroidindices =new int[k];
			int j=0;
			String temp="";
			
			
			while(j<k){
				List<Integer> l=new ArrayList<Integer>();
				
				int randomInt = randomGenerator.nextInt(99);
				 if(a.contains(randomInt)==false){
					l.add(randomInt);
					 
					a.add(randomInt);
					//System.out.println(randomInt);
					centroidindices[j]=randomInt;
					temp=""+centroidindices[j];
					centroidvalues[j][0]=points[randomInt][0];
					centroidvalues[j][1]=points[randomInt][1];
					clusters.put(j,l);
					j++;
				}
			}
			
			//
			double ssetemp=0,ssedist=0;
			for(int iterations=0;iterations<25;iterations++){
				
				
			double distance=100000,tempdist=0,x1,y1,x2,y2;
			int index=0;
			
			for(int p=0;p<points.length;p++){
				distance=100000;
				index=0;
				x2=points[p][0];
				y2=points[p][1];
				//if(a.contains(p)==true)
					//continue;
				for(int n=0;n<centroidvalues.length;n++){
					x1=centroidvalues[n][0];
					y1=centroidvalues[n][1];
					tempdist=Math.sqrt(Math.pow(Math.abs(x2-x1), 2)+Math.pow(Math.abs(y2-y1), 2));
					if(tempdist<distance){
						distance=tempdist;
						index=n;
					}
					
				}
				
				
				//List<Integer> templist=new ArrayList<Integer>();
				
				
				if(clusters.containsValue(p)==false)
					clusters.get(index).add(p);
				
			}
			
			/*
			Set set = clusters.entrySet();
		      Iterator iterator = set.iterator();
		      while(iterator.hasNext()) {
		         Map.Entry mentry = (Map.Entry)iterator.next();
		         System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
		         System.out.println(mentry.getValue());
		      } */

			
		    ssedist=SSE(centroidvalues, clusters, points);
		    if(ssedist==ssetemp)
		    	break;
		    ssetemp=ssedist;
		    //System.out.println(ssetemp);
			centroidvalues=findnewcentroid(clusters,centroidvalues,points);
		
		//print the cluster points values	
		/*	List<Integer> srandom;
			Set<Entry<Integer, List<Integer>>> setrandom = clusters.entrySet();
		      Iterator<Entry<Integer, List<Integer>>> iteratorrandom = setrandom.iterator();
		      while(iteratorrandom.hasNext()) {
		         @SuppressWarnings("rawtypes")
				Map.Entry mentry = (Map.Entry)iteratorrandom.next();
		         
		         srandom= (List<Integer>) mentry.getValue();
		         System.out.println("new cluster");
		         for(int irandom: srandom){
		        	 
		        	 System.out.println(points[irandom][0]+" "+points[irandom][1]);
		        	
		         }
		      }
		      */
		  
		 //setting map values to null     
		      
			 @SuppressWarnings("UnusedAssignment")
	            String tempfiledata=null;
	            Set set=clusters.entrySet();
	            Iterator ifile= set.iterator();
	            FileWriter fw = new FileWriter(dbFile,false);
	            BufferedWriter bw = new BufferedWriter(fw);
	            while(ifile.hasNext()){
	                Map.Entry me=(Map.Entry)ifile.next();
	                tempfiledata=me.getKey()+"	"+me.getValue();
	                bw.write(tempfiledata);
	                bw.newLine();
	            }
	            bw.close();
	            fw.close();
	            
		      for(int cindex=0;cindex<k;cindex++){
		    	  List<Integer> newlist= new ArrayList<Integer>();
		    	  clusters.put(cindex, newlist);
		      }
		      
			}
			System.out.println("SSE:"+ssetemp);
			 
		           
		        
			
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
