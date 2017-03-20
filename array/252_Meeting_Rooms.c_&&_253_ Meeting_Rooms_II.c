/**
 * Definition for an interval.
 * struct Interval {
 *     int start;
 *     int end;
 * };
 */
/*Sort input array by their start time*/
int compare(const void* p, const void* q){
    
    struct Interval *l = (struct Interval*)p;
    struct Interval *r = (struct Interval*)q;

    return  (l->start)-(r->start);

}
bool canAttendMeetings(struct Interval* intervals, int intervalsSize) {
    if(!intervals || !intervalsSize) return true;
    
    //qsort 
    qsort((void*)(intervals), intervalsSize, sizeof(struct Interval), compare);
    
    for(int i = 1; i< intervalsSize; i++){
        if(intervals[i].start<intervals[i-1].end) return false;
    }
    return true;
}
