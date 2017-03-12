
/*Count the leading number of 1*/
int numofOne(int num){
    int count =0;
    for (int i = 1<<7; i>=1; i = i>>1) {
        if (num & i) {
            count ++;
        } else {
            break;
        }
    }
    return count;
}

bool validUtf8(int* data, int dataSize) {
    
    if (!data || !dataSize ) return false;
    int count =0;
    for (int i = 0; i<dataSize; i++){
        if(!count) {
		      count = numofOne(data[i]);
		   //	printf("leading ones = %d\n", count);
			    if(count > 4 || count ==1) return false;
			    if(count > 0) count --;
			    continue;
		    }
        
        //decre counter
        if(numofOne(data[i])== 1) {
		     //printf("The %d is =%d\n", i, numofOne(data[i]));
		      count--;
		    }else{
		       return false;
	      }
    }
    
    return count==0;
}
