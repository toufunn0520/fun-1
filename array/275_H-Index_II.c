/*BST in log(n)*/

int hIndex(int* citations, int citationsSize) {
    if(!citations && !citationsSize) return 0;
    int start =0;
    int end = citationsSize-1;
    /*BST find the 1st element  a[i] >= (n-1)-i*/
    /*for paper[m]. there should be at least (len – m) papers with citations >= citations[m]*/
    while(start<= end){
        int mid = start + (end-start)/2;
        if(citations[mid] == citationsSize-mid){
            return citations[mid];
        }else if(citations[mid] < citationsSize-mid){
            start = mid+1;
        } else{
            end = mid-1;
        }
    }
 //   printf("cann't find\n");
    return citationsSize-start;
    //check start 1st
}
