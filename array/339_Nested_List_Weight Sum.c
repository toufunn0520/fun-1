/**
 * *********************************************************************
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * *********************************************************************
 *
 * // Return true if this NestedInteger holds a single integer, rather than a nested list.
 * bool NestedIntegerIsInteger(struct NestedInteger *);
 *
 * // Return the single integer that this NestedInteger holds, if it holds a single integer
 * // The result is undefined if this NestedInteger holds a nested list
 * int NestedIntegerGetInteger(struct NestedInteger *);
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
void helper(struct NestedInteger** nestedList, int nestedListSize, int* sum, int level){
    
     int len = nestedListSize;
     for (int i =0; i<len; i++){
         if(!NestedIntegerIsInteger((nestedList[i]))){
             helper(NestedIntegerGetList(nestedList[i]), NestedIntegerGetListSize(nestedList[i]), sum, level+1);
         }else{
            (*sum) += NestedIntegerGetInteger(nestedList[i]) * (level+1);
         }
     }
     return;
}
 
int depthSum(struct NestedInteger** nestedList, int nestedListSize) {
    int sum =0;
    int level = 0;
    if(!nestedList || !nestedListSize) return 0;
    helper(nestedList, nestedListSize, &sum, 0);
    return sum;
}
