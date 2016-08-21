#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

struct vector2{
    double x, y;
    explicit vector2(double x_ = 0, double y_ =0) : x(x_), y(y_){}
    double cross(const vector2& rhs) const{
        return x * rhs.y - rhs.x * y;
    }
    vector2 operator-(const vector2& rhs) const {
        return vector2(x - rhs.x, y - rhs.y);
    }
    vector2 operator+(const vector2& rhs) const {
        return vector2(x + rhs.x, y + rhs.y);
    }
    vector2 operator*(double rhs) const {
        return vector2(x * rhs, y * rhs);
    }
};
typedef vector<vector2> polygon;

double cross(const vector2& a, const vector2& b){
    return a.cross(b);
}

double area(const polygon& p) {
    double ret = 0;
    for (int i = 0; i < p.size(); i++) {
        int j = (i + 1) % p.size();
        ret += cross(p[i], p[j]);
    }
    return fabs(ret) / 2.0;
}

vector2 lineIntersection(const vector2& a, const vector2& b, const vector2& c, const vector2& d) {
    return a + (b - a) * (cross(c - a, d - c) / cross(b - a, d - c));
}

polygon cutPoly(const polygon& p, const vector2& a, const vector2& b) {
    int n = p.size();
    vector<bool> inside(n);
    for (int i = 0; i < n; ++i)
        inside[i] = cross(b - a, p[i] - a) >= 0;
    polygon ret;
    for (int i = 0; i < n; ++i) {
        int j = (i + 1) % n;
        if (inside[i])
            ret.push_back(p[i]);
        if (inside[i] != inside[j]) {
            vector2 cross;
            cross = lineIntersection(p[i], p[j], a, b);
            ret.push_back(cross);
        }
    }
    return ret;
}

polygon intersection(const polygon& p, double x1, double y1, double x2, double y2) {
    if ((x1 < x2) != (y1 < y2)) {
        double t = y1;
        y1 = y2;
        y2 = t;
    }
    vector2 a(x1, y1), b(x2, y1), c(x2, y2), d(x1, y2);
    polygon ret = cutPoly(p, a, b);
    ret = cutPoly(ret, b, c);
    ret = cutPoly(ret, c, d);
    ret = cutPoly(ret, d, a);
    return ret;
}

int main(void) {
    int C;
    int x1, x2, y1, y2, N;
    polygon p;
    cout.precision(15);
    for (cin >> C; C--; ) {
        cin >> x1 >> y1 >> x2 >> y2 >> N;
        p.resize(N);
        for (int i = 0; i < N; i++) {
            cin >> p[i].x >> p[i].y;
        }
        cout << area(intersection(p, x1, y1, x2, y2)) << endl;
    }
}