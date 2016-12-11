/*
在判断完edges.length == n - 1后， 我们可以想到使用Union-Find中的Quick-Find。
先构造一个长为节点数n的数组id，初始化数组每个元素与他们的index相等。
接下来遍历无向图的edges矩阵，假如edges[i][0]和edges[i][1]已经联通，则这个图存在环，我们返回false，
否则我们把这两个元素联通起来 - 遍历id数组，将其中所有值为pid的元素值更新为qid。
数组遍历结束之后返回true。
*/
void Union(int p, int q, int* res, int len){
    int pp = res[p];
    int qq = res[q];
    
    for(int i = 0; i < len; i++) {
        if(res[i] == pp) {
            res[i] = qq;
        }
    }
}

bool IsConnected(int p, int q, int* res){
    return res[p] == res[q]? true:false;
}

bool validTree(int n, int** edges, int edgesRowSize, int edgesColSize) {

    if( !n || !edges || edgesRowSize != n-1 ) return false;

    int *res = (int*)malloc(sizeof(int)*n);
    for(int i =0; i<n; i++) res[i] = i;
    
    for(int i =0; i<n-1; i++) {
        if(!IsConnected(edges[i][0],edges[i][1], res)){
            Union(edges[i][0],edges[i][1], res, n);
        }else{
            return false;
        }
    }
    return true;
}
