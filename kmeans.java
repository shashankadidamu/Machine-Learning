package kmeans;
import java.io.*;
import java.net.*;
import java.util.*;

public class kmean {
		
	void findclustervalues(int[] ci,double[][] p){
		
	}
	static double[][] findnewcentroid(HashMap<Integer,String> c,double[][] cv,double[][] p){
		String s="";
		String splitBy = ",";
		String[] sarray= null;
		int t=0;
		 Set set = c.entrySet();
	      Iterator iterator = set.iterator();
	      while(iterator.hasNext()) {
	         Map.Entry mentry = (Map.Entry)iterator.next();
	         
	         s=(String) mentry.getValue();
	         sarray=s.split(splitBy);
	         double x=0,y=0;
	         for(int i=0;i<sarray.length;i++){
	        	 x=0;y=0;
	        	 int indeex = Integer.parseInt(sarray[i]);
	        	 x=x+p[indeex][0];
	        	 y=y+p[indeex][1];
	        	 
	         }
	         cv[t][0]=x/sarray.length;
	         cv[t][1]=y/sarray.length;
	         t++;
	      }

	      return cv;
	}
	
		public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList a = new ArrayList();
		int[] centroidindices;
		
		double[][] points= new double[100][100];
		HashMap<Integer,List<Integer>> clusters = new HashMap<Integer,List<Integer>>();
		try {
					
			//Reading data
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
					System.out.println(randomInt);
					centroidindices[j]=randomInt;
					temp=""+centroidindices[j];
					centroidvalues[j][0]=points[randomInt][0];
					centroidvalues[j][1]=points[randomInt][1];
					clusters.put(j,l);
					j++;
				}
			}
			
			//
			double distance=100000,tempdist=0,x1,y1,x2,y2;
			int index=0;
			for(int p=0;p<points.length;p++){
				distance=100000;
				index=0;
				x2=points[p][0];
				y2=points[p][1];
				if(a.contains(p)==true)
					continue;
				for(int n=0;n<centroidvalues.length;n++){
					x1=centroidvalues[n][0];
					y1=centroidvalues[n][1];
					tempdist=Math.sqrt(Math.pow(Math.abs(x2-x1), 2)+Math.pow(Math.abs(y2-y1), 2));
					if(tempdist<distance){
						distance=tempdist;
						index=n;
					}
					
				}
				
				List<Integer> templist=new ArrayList<Integer>();
				templist=clusters.get(index);
				templist.add(p);
				temp=temp+","+p;
				clusters.put(index, templist);
			}
			
			//centroidvalues=findnewcentroid(clusters,centroidvalues,points);
			 Set set = clusters.entrySet();
		      Iterator iterator = set.iterator();
		      while(iterator.hasNext()) {
		         Map.Entry mentry = (Map.Entry)iterator.next();
		         System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
		         System.out.println(mentry.getValue());
		      }


			
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
