/*The main idea is based on greedy. Let's say the range of the current jump is [curBegin, curEnd], curFarthest is the farthest point that all points in [curBegin, curEnd] can reach. Once the current point reaches curEnd, then trigger another jump, and set the new curEnd with curFarthest, then keep the above steps, as the following:
*/
int MAX(int p, int q){
    return p>q?p:q; 
}
int jump(int* nums, int numsSize) {
    int jumps = 0, curEnd = 0, curFarthest = 0;
	  for (int i = 0; i < numsSize-1; i++) {
		curFarthest = MAX(curFarthest, i + nums[i]);
		if (i == curEnd) {
			jumps++;
			curEnd = curFarthest;
		}
	}
	return jumps;
}
