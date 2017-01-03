/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */
/** Encodes a tree to a single string. */
void helpers(struct TreeNode* root, char* res, int *index){
    if(!root) {
        memcpy(&res[(*index)],"X,",2);
        *index = strlen(res);
        return;
    }
    sprintf(&res[(*index)],"%d,",root->val);
    *index = strlen(res);
    helpers(root->left, res, index);
    helpers(root->right, res, index);
}
char* serialize(struct TreeNode* root) {
    if(!root) return NULL;
    char * res = (char*) malloc(sizeof(char)*100000);   // stupid to have huge initialization 
    memset(res, 0, 100000);
    int index =0;
    helpers(root, res, &index);
    //printf("Output data is %s total is%d\n", res, index);
    return res;
}

/** Decodes your encoded data to tree. */
struct TreeNode* helperd(char** input,  int *cur, int total){
    if (*cur >=total) return NULL;
    if(!strcmp(input[(*cur)] , "X")) {
       // printf("The %dth is Bingo\n", *cur);
        (*cur)++;       // don't forget to increment cur
        return NULL;
    }else{
        struct TreeNode* root = (struct TreeNode*)malloc(sizeof(struct TreeNode));
        root->val = atoi(input[*cur]);
       // printf("The %dth value is %d\n", *cur, root->val);
        (*cur)++;
        root->left = helperd(input, cur, total);
        root->right = helperd(input, cur, total);
        return root;
    }
}
struct TreeNode* deserialize(char* data) {
    if(!data) return NULL;
    char *p = strtok (data, ",");   // split array by ,
    char *array[100000];
    int i =0;
    while (p != NULL)
    {
        array[i++] = p;
       // printf("The %dth is %s\n",i-1, array[i-1]);
        p = strtok (NULL, ",");
    }
    int index =0;
    return helperd(array, &index, i);
}

// Your functions will be called as such:
// char* data = serialize(root);
// deserialize(data);
