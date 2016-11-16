
/*Segmentation TREE*/

int MAX(int p, int q){
    return p>q? p:q;
}
int MIN(int p, int q){
    return p<q? p:q;
}

struct NumArray {
    int start;
    int end;
    int sum;
    struct NumArray *left;
    struct NumArray *right;
};

struct NumArray *NewNode(int start, int end, int sum){
    struct NumArray *node = (struct NumArray*)malloc(sizeof(struct NumArray));
    node->start = start;
    node->end= end;
    node->sum = sum;
    node->left = NULL;
    node->right = NULL;
    return node;
}

/*Initial helper function*/
struct NumArray* buildTree(int* nums, int start, int end){
    
    if(!nums || start > end) return NULL;
    
    if(start == end) {
        struct NumArray *node = NewNode(start, end, nums[start]);
        return node;
    }
    
    struct NumArray *node = NewNode(start, end, 0);
    int mid = start + (end - start)/2;
    
    node->left = buildTree(nums, start, mid);
    node->right = buildTree(nums, mid+1, end);
    node->sum += (node->left)? node->left->sum : 0;
    node->sum += (node->right)? node->right->sum : 0;
    
    return node;
}

/** Initialize your data structure here. */
struct NumArray* NumArrayCreate(int* nums, int numsSize) {
    struct NumArray *root = buildTree(nums,0, numsSize-1);
    return root;
}
    // Time Complexity: O(log n)
void updatehelper(struct NumArray* numArray, int i, int val) {
     if(!numArray) return;
     if(i == numArray->start && i==numArray->end) {
         numArray->sum = val;
         return;
     }
     
     int mid = numArray->start+(numArray->end - numArray->start)/2;
     if( mid >=i) {
         updatehelper(numArray->left, i, val);
     }else{
         updatehelper(numArray->right, i, val);
     }
     numArray->sum = numArray->left->sum+ numArray->right->sum;
    
}
void update(struct NumArray* numArray, int i, int val) {
    updatehelper(numArray, i, val);
}
    // Time Complexity: O(log n)

int sumhelper(struct NumArray* numArray, int i, int j) {
    
    if(!numArray || i > numArray->end || j< numArray->start) return 0;
    
    if( i <= numArray->start && j>=numArray->end ) return numArray->sum;
    
    int mid = numArray->start + (numArray->end-numArray->start)/2;
    return (sumhelper(numArray->left, i, MIN(mid,j)) + sumhelper(numArray->right, MAX(mid,i), j));
    
}
int sumRange(struct NumArray* numArray, int i, int j) {
     int result = sumhelper(numArray, i, j); 
     return result;    
}

/** Deallocates memory previously allocated for the data structure. */
void NumArrayFree(struct NumArray* numArray) {
    if(!numArray) return; 
    NumArrayFree(numArray->left);
    NumArrayFree(numArray->right);
    numArray->left = NULL;
    numArray->right = NULL;
    free(numArray);
    numArray=NULL;
}

// Your NumArray object will be instantiated and called as such:
// struct NumArray* numArray = NumArrayCreate(nums, numsSize);
// sumRange(numArray, 0, 1);
// update(numArray, 1, 10);
// sumRange(numArray, 1, 2);
// NumArrayFree(numArray);
