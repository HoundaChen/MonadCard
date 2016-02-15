package hd.work.monadcard;


import java.util.*;



public class RandomText {

	
        public int[] Rdnum(int a , int b){
        	List<Integer>list = new ArrayList<Integer>();
        	
        	for(int i =0 ; i < a ; i++){
        		list.add(i);
        	}
        	int count = a;
        	int [] item = new int[b];
        	for(int i = 0 ; i < b; i++){
        		int RandomInt = new Random().nextInt(count);
        		item[i]=list.get(RandomInt);
        		list.remove(RandomInt);
        		count--;
        		   		        	}
        	return item;
        	
        }
   	
}
