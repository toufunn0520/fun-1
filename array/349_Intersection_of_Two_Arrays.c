/*
 *349. Intersection of Two Arrays  QuestionEditorial Solution
 Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
 * Return an array of size *returnSize.
 * Note: The returned array must be malloced, assume caller calls free().
 */
 
 
 // This function is used in qsort to decide the relative order
// of elements at addresses p and q.
int comparator(const void *p, const void *q)
{
    // Get the values at given addresses
    int  l = *(const int *)p;
    int  r = *(const int *)q;
    if (l <r) return -1;
    if (l==r) return 0;
    return 1;
} 
 
 // A utility function to print an array
void printArr(int *arr, int n)
{
    int i;
    for (i = 0; i < n; ++i)
        printf("%d ", arr[i]);
}
int* intersection(int* nums1, int nums1Size, int* nums2, int nums2Size, int* returnSize) {
    //sort 
    if(!nums1 || !nums2 || !nums1Size || !nums2Size ){
        *returnSize =0;
        return NULL;
    }
    qsort((void*)nums2, nums2Size, sizeof(int), comparator);
    qsort((void*)nums1, nums1Size, sizeof(int), comparator);

    int *result = (int*)malloc(1000*sizeof(int));
    int i = 0;
    int j = 0;
    int len =0;
    int flag =0;
    while( i < nums1Size && j < nums2Size){
        if(nums1[i] == nums2[j]){
            if(nums1[i]!=flag || len ==0){
                result[len]=nums1[i];
                len++;
                flag =nums1[i];
            }
            i++;
            j++;
        }else if(nums1[i]< nums2[j]){
            i++;
        }else{
            j++;
        }
    }
    
    *returnSize = len ;
    return result;
}
