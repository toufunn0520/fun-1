/**
 * *********************************************************************
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * *********************************************************************
 *
 * // Initializes an empty nested list and return a reference to the nested integer.
 * struct NestedInteger *NestedIntegerInit();
 *
 * // Return true if this NestedInteger holds a single integer, rather than a nested list.
 * bool NestedIntegerIsInteger(struct NestedInteger *);
 *
 * // Return the single integer that this NestedInteger holds, if it holds a single integer
 * // The result is undefined if this NestedInteger holds a nested list
 * int NestedIntegerGetInteger(struct NestedInteger *);
 *
 * // Set this NestedInteger to hold a single integer.
 * void NestedIntegerSetInteger(struct NestedInteger *ni, int value);
 *
 * // Set this NestedInteger to hold a nested list and adds a nested integer elem to it.
 * void NestedIntegerAdd(struct NestedInteger *ni, struct NestedInteger *elem);
 *
 * // Return the nested list that this NestedInteger holds, if it holds a nested list
 * // The result is undefined if this NestedInteger holds a single integer
 * struct NestedInteger **NestedIntegerGetList(struct NestedInteger *);
 *
 * // Return the nested list's size that this NestedInteger holds, if it holds a nested list
 * // The result is undefined if this NestedInteger holds a single integer
 * int NestedIntegerGetListSize(struct NestedInteger *);
 * };
 */
 int helper(struct NestedInteger** nestedList, int nestedListSize, int*sum){
     
    int len = nestedListSize;
    int cur_sum =0;
    struct NestedInteger** cur_list = (struct NestedInteger **)malloc(sizeof(struct NestedInteger *)*1000);
    int newlen = 0;
    
    for (int i =0; i<len; i++){
         if(!NestedIntegerIsInteger(nestedList[i])){
            int total = NestedIntegerGetListSize(nestedList[i]);
            for (int j =0; j<total; j++){
                struct NestedInteger* cur = (NestedIntegerGetList(nestedList[i]))[j];
                if(cur){
                    cur_list[newlen] = NestedIntegerInit();
                    if(NestedIntegerIsInteger(cur)){
                        NestedIntegerSetInteger(cur_list[newlen], NestedIntegerGetInteger(cur));
                    }else{
                        int t = NestedIntegerGetListSize(cur);
                        for (int k =0; k<t; k++){
                            NestedIntegerAdd(cur_list[newlen],NestedIntegerGetList(cur)[k]);
                        }
                    }
                    newlen++;
                }
            }
         }else{
            *sum += NestedIntegerGetInteger(nestedList[i]);
         }
    }
     cur_sum = (*sum);
     cur_sum += (!newlen) ? 0 : helper(cur_list, newlen, sum);
     return cur_sum;
}

int depthSumInverse(struct NestedInteger** nestedList, int nestedListSize) {
    int sum =0;
    int level = 0;
    if(!nestedList || !nestedListSize) return 0;
    return helper(nestedList, nestedListSize, &sum);
}
