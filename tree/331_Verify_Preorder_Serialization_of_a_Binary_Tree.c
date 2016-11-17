/*方法简单的说就是不断的砍掉叶子节点。最后看看能不能全部砍掉。
已例子一为例，：”9,3,4,#,#,1,#,#,2,#,6,#,#” 遇到x # #的时候，就把它变为 #*/

/*我们在遍历的时候，计算diff = outdegree – indegree. 当一个节点出现的时候，diff – 1，因为它提供一个入度；当节点不是#的时候，diff+2(提供两个出度) 如果序列式合法的，那么遍历过程中diff >=0 且最后结果为0.*/
#include <string.h>

bool isValidSerialization(char* preorder) {
     /*method2*/
     if(!preorder) return false;
     int res = 1;
    /*generate an char array*/
     char *token = strtok(preorder, ",");

     while (token != NULL)
    {   
        if (--res < 0) return false;
        if(strcmp(token, "#")) {
            res +=2 ;
        }
        //printf("%s res=%d\n", token, res);
        token = strtok(NULL, ",");
    }
     return res==0? true: false;
}
