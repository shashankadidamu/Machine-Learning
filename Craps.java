
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Craps {
	 
	static int s_balance1=1000,s_balance2=1000,s_balance3=1000;
	static int wager1=100,wager2=100,wager3=100,point=0;
	
	static String[] play_game() throws IOException{
		Random r=new Random();
		int no_of_games1=0,no_of_games2=0,no_of_games3=0;
		String[] print=new String[3];
		for(int i=1;i<11;i++){
		int dice1,dice2,min=1,max=6,sum=0;
		boolean flag=false;
		dice1= r.nextInt((max-min)+1)+min;
		dice2= r.nextInt((max-min)+1)+min;
		sum=dice1+dice2;
		
		if(sum==7||sum==11){
			if(s_balance1!=0){
			s_balance1=s_balance1+wager1;
			no_of_games1++;
			}
			if(s_balance2!=0){
			s_balance2=s_balance2+wager2;
			 wager2=100;
			 no_of_games2++;
			}
			if(s_balance3!=0){
			 s_balance3=s_balance3+wager3;
			 if(s_balance3<=wager3*2)
				 wager3=s_balance3;
			 else
				 wager3=wager3*2;
			 no_of_games3++;
			}
		}
		
		else if(sum==2||sum==3||sum==12){
			if(s_balance1!=0){
			 s_balance1=s_balance1-wager1;
			 no_of_games1++;
			}
			if(s_balance2!=0){
			 s_balance2=s_balance2-wager2;
			 if(s_balance2<=wager2*2)
				 wager2=s_balance2;
			 else
				 wager2=wager2*2;
			 no_of_games2++;
			}
			if(s_balance3!=0){
			 s_balance3=s_balance3-wager3;
			 wager3=100;
			 no_of_games3++;
			}
		}
		
		 else{
			 point=sum;
			 do{
				 dice1= r.nextInt((max-min)+1)+min;
				 dice2= r.nextInt((max-min)+1)+min;
				 sum=dice1+dice2;
				 if(point==sum){
					 if(s_balance1!=0){
					 s_balance1=s_balance1+wager1;
					 no_of_games1++;
					 }
					 if(s_balance2!=0){
					 s_balance2=s_balance2+wager2;
					 wager2=100;
					 no_of_games2++;
					 }
					 if(s_balance3!=0){
					 s_balance3=s_balance3+wager3;
					 if(s_balance3<=wager3*2)
						 wager3=s_balance3;
					 else
						 wager3=wager3*2;
					 no_of_games3++;
					 }
					 flag=true;
				 }
				 else if(sum==7){
					 if(s_balance1!=0){
					 s_balance1=s_balance1-wager1;
					 no_of_games1++;
					 }
					 if(s_balance2!=0){
					 s_balance2=s_balance2-wager2;
					 if(s_balance2<=wager2*2)
						 wager2=s_balance2;
					 else
						 wager2=wager2*2;
					 no_of_games2++;
					 }
					 if(s_balance3!=0){
					 s_balance3=s_balance3-wager3;
					 wager3=100;
					 no_of_games3++;
					 }
					 flag=true;
				 }
			 }while(flag!=true);
		 }
		}
		print[0]="1"+"	"+no_of_games1+"	"+s_balance1;
		print[1]="2"+"	"+no_of_games2+"	"+s_balance2;
		print[2]="3"+"	"+no_of_games3+"	"+s_balance3;
		s_balance1=1000;s_balance2=1000;s_balance3=1000;wager1=100;wager2=100;wager3=100;
		return print;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String[] print_output=new String[3];
		String txtfile=".//output.txt";
		FileWriter fw= new FileWriter(txtfile,false);
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write("Strategy"+" "+"No_of_games"+"	"+"End_Balance");
		bw.newLine();
		for(int i=1;i<6;i++){
		print_output=play_game();
		bw.write("Round:"+i);
		bw.newLine();
		bw.write(print_output[0]);
		bw.newLine();
		bw.write(print_output[1]);
		bw.newLine();
		bw.write(print_output[2]);
		bw.newLine();
		}
		bw.close();
		fw.close();
	}

}
