/*Binary Search
这题我们也可以用二分查找法来做，我们由于是有序矩阵，那么左上角的数字一定是最小的，而右下角的数字一定是最大的，所以这个是我们搜索的范围，然后我们算出中间数字mid，由于矩阵中不同行之间的元素并不是严格有序的，所以我们要在每一行都查找一下mid，我们使用upper_bound，这个函数是查找第一个大于目标数的元素，如果目标数在比该行的尾元素大，则upper_bound返回该行元素的个数，如果目标数比该行首元素小，则upper_bound返回0, 我们遍历完所有的行可以找出中间数是第几小的数，然后k比较，进行二分查找，本解法的整体时间复杂度为O(nlgn*lgX)，其中X为最大值和最小值的差值
上面的解法还可以进一步优化到O(nlgX)，其中X为最大值和最小值的差值，我们并不用对每一行都做二分搜索法，我们注意到每列也是有序的，我们可以利用这个性质，从数组的左下角开始查找，如果比目标值小，我们就向右移一位，而且我们知道当前列的当前位置的上面所有的数字都小于目标值，那么cnt += i+1，反之则向上移一位，这样我们也能算出cnt的值。其余部分跟上面的方法相同*/


/*go through entir matrix find the number of elements are small/equal to target*/
int findlowerbound(int** matrix,int target, int row, int col){
    //start for bottom left to top right since it is sorted
    int i = row-1;
    int j = 0;
    int res =0;
    while(i>=0 && j<=col-1){
        if(matrix[i][j]<=target){
            res+=i+1;
            j++;
        }else{
            i--;
        }
    }
    
    return res;
}

int kthSmallest(int** matrix, int matrixRowSize, int matrixColSize, int k) {
    int Row = matrixRowSize;
    int Col = matrixRowSize;
    int min = matrix[0][0];
    int max = matrix[Row-1][Col-1];
   // printf("MIN=%d MAX=%d\n", min, max);
    while(min < max){
        int mid = (min + (max-min)/2);
      //  printf("mid=%d\n", mid);
        int cnt = findlowerbound(matrix, mid, Row, Col);
        if (cnt < k) min = mid + 1;
            else max = mid;
    }
    return min;
}

