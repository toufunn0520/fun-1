// Example program
#include <iostream>
#include <string>
#include <algorithm>
#include <queue>
#include <math.h>  
#include <vector>
using namespace std;

struct Point { 
    double x;
    double y; 
    Point(double a, double b) {
        x = a;
        y = b;
    }
};

double getDistance(Point a, Point b) {
    return sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
}
typedef bool (*comp)(Point, Point);
Point global_origin = Point(0,0);
bool compare(Point a, Point b)
{
   return (getDistance(a, global_origin)< getDistance(b, global_origin));
}

vector<Point> Solution(vector<Point> &array, Point origin, int k) {
    global_origin = Point(origin.x, origin.y);
    priority_queue<Point, std::vector<Point>, comp> pq(compare);
    vector<Point> ret;
    for (int i = 0; i < array.size(); i++) {
        Point p = array[i];
        pq.push(p);
        if (pq.size() > k)
            pq.pop();
    }
    int index = 0;
    while (!pq.empty()){
        Point p = pq.top();
        ret.push_back(p);
        pq.pop();
    }
    return ret;
}
