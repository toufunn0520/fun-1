typedef struct {
    int value[300];
    int times[300];
} HitCounter;

/** Initialize your data structure here. */
HitCounter* hitCounterCreate() {
    HitCounter *hit = (HitCounter *)malloc(sizeof(HitCounter));
    memset(hit, 0,sizeof(HitCounter));
    return hit;
}

/** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
void hitCounterHit(HitCounter* obj, int timestamp) {
    if(!obj) return;
    int index = timestamp%300;
    if(obj->times[index]!=timestamp){
        obj->times[index] = timestamp;
        obj->value[index] = 1;
    }else{
        obj->value[index] ++;
    }
    return;
}

/** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
int hitCounterGetHits(HitCounter* obj, int timestamp) {
    if(!obj) return;
    int result = 0;
    for(int i=0; i<300; i++){
        if(timestamp - obj->times[i]<300){
            result += obj->value[i];
        }
    }
    return result;
}

void hitCounterFree(HitCounter* obj) {
    if(obj){
        free(obj);
    }
    obj = NULL;
    return;
}

/**
 * Your HitCounter struct will be instantiated and called as such:
 * struct HitCounter* obj = hitCounterCreate();
 * hitCounterHit(obj, timestamp);
 * int param_2 = hitCounterGetHits(obj, timestamp);
 * hitCounterFree(obj);
 */
