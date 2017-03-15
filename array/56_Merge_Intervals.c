struct Interval* newInterval(int start, int end){
     struct Interval* res = malloc(sizeof(struct Interval));
     res->start = start;
     res->end =end;
     return res;
}
// This function is used in qsort to decide the relative order
// of elements at addresses p and q.

int comparator(const void *p, const void *q)
{
    // Get the values at given addresses
    struct Interval l = *(struct Interval*)p;
    struct Interval r = *(struct Interval*)q;
    int diff = l.start - r.start;
    return diff;
}
int MAX(int p, int q) {
    return p>q?p:q;
}
struct Interval* merge(struct Interval* intervals, int intervalsSize, int* returnSize) {
    *returnSize = 0;
    if(!intervals || !intervalsSize) return NULL;
    /*sort by start*/
    qsort((void*)intervals, intervalsSize, sizeof(intervals[0]), comparator);
    struct Interval *result = malloc(sizeof(struct Interval)* intervalsSize);
    result[*returnSize].end= intervals[0].end;
    result[*returnSize].start= intervals[0].start;

    for(int i =1; i<intervalsSize; i++) {
        if(result[*returnSize].end < intervals[i].start){
            (*returnSize) ++;
             result[*returnSize].start = intervals[i].start;
             result[*returnSize].end = intervals[i].end;
        }else {
            result[*returnSize].end = MAX(result[*returnSize].end, intervals[i].end);
        }
    }
    (*returnSize)++;
    return result;
}
