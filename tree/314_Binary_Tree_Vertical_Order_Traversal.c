/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */
/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *columnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */
int MIN(int p, int q){
    return p<q?p:q;
}

int MAX(int p, int q){
    return p>q?p:q;
}

void findRange(struct TreeNode* root, int* range, int col){
    if(!root) return;
    range[0] = MIN(range[0], col);
    range[1] = MAX(range[1], col);
    findRange(root->left, range, col-1);
    findRange(root->right, range, col+1);
}

int finddepth(struct TreeNode* root){
    if(!root) return 0;
    int left = finddepth(root->left);
    int right = finddepth(root->right);
    return MAX(left, right)+1;
}
#define MAX_Q_SIZE 500
/*UTILITY FUNCTIONS*/
struct LevelTree {
    struct TreeNode *node;
    int col;
};
struct LevelTree** createQueue(int *front, int *rear)
{
    struct LevelTree **queue = malloc(sizeof(struct LevelTree*) * MAX_Q_SIZE);
    *front = *rear = 0;
    return queue;
}
 
void enQueue(struct LevelTree **queue, int *rear, struct LevelTree  *new_node)
{
    queue[*rear] = new_node;
    (*rear)++;
}
 
struct node *deQueue(struct LevelTree **queue, int *front)
{
    (*front)++;
    return queue[*front - 1];
}
void internal(struct TreeNode* root, int** res, int** columnSizes, int col){
    // level order traversal
    int rear, front;
    struct LevelTree **queue = createQueue(&front, &rear);
    struct LevelTree *temp_levelnode = malloc(sizeof(struct LevelTree));
    temp_levelnode->node = root;
    temp_levelnode->col = col;
    enQueue(queue, &rear, temp_levelnode);
    int size = rear-front;
    
    while(size){
        size = rear-front;
        for(int i =0; i<size; i++){
            temp_levelnode= deQueue(queue, &front);
            int cur_col = temp_levelnode->col;
            res[cur_col][(*columnSizes)[cur_col]] = temp_levelnode->node->val;
             ((*columnSizes)[cur_col])++;
            //printf("Bingo add %d, %dth val=%d\n", cur_col,(*columnSizes)[cur_col], root->val);
            
            if(temp_levelnode->node->left) {
                struct LevelTree *left_levelnode = malloc(sizeof(struct LevelTree));
                left_levelnode->node = temp_levelnode->node->left;
                left_levelnode->col = cur_col-1;
                enQueue(queue, &rear, left_levelnode);
            }
            if(temp_levelnode->node->right) {
                struct LevelTree *right_levelnode = malloc(sizeof(struct LevelTree));
                right_levelnode->node = temp_levelnode->node->right;
                right_levelnode->col = cur_col+1;
                enQueue(queue, &rear, right_levelnode);

            }
            free(temp_levelnode);
        }
    }
    free(queue);
    return;

}
int** verticalOrder(struct TreeNode* root, int** columnSizes, int* returnSize) {
    *returnSize = 0;
    if(!root) return NULL;
    int range[2] = {0};
    findRange(root, range, 0);
    *returnSize = range[1]- range[0]+1;
   // printf("Check the range is %d\n", *returnSize);
    int depth = finddepth(root);
    depth= depth*2;
   // printf("Check the depth is %d\n", depth);
    int **res = malloc(sizeof(int*)*(*returnSize));
    for(int i = 0; i< *returnSize; i++){
        res[i] = malloc(sizeof(int)* depth);
    }
    *columnSizes = malloc(sizeof(int)*(*returnSize));
    memset(*columnSizes, 0, sizeof(int)*(*returnSize));
    internal(root, res, columnSizes, -range[0]);
    return res;
    
}


******************JAVA*******************
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        Queue<TreeNode> q= new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();
        q.add(root);
        cols.add(0);
        int min = 0;
        int max = 0;
        /*BFS*/
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i<size; i++){
                TreeNode cur = q.poll();
                int col = cols.poll();
                if(!map.containsKey(col)){
                    map.put(col, new ArrayList<Integer>());
                }
                map.get(col).add(cur.val);
  
                if( cur.left != null){
                    q.add(cur.left);
                    cols.add(col-1);
                    min = Math.min(min, col - 1);
                }                
                if( cur.right!=null){
                    q.add(cur.right);
                    cols.add(col+1);            
                    max = Math.max(max, col + 1);

                } 
            }
            
        }
        for(int i =min; i<=max;i++){
            res.add(map.get(i));
        }
        return res;
        
    }
}
