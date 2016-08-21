[계산기하알고리즘](https://github.com/kdw808/algorithm-study/blob/master/NERDS/kdw808.md#계산-기하-알고리즘)
#다각형
## 다각형의 종류
* 볼록 다각형(convex polygon): 모든 내각이 180도 미만인 다각형
  * 두 볼록 다각형의 교집합은 항상 볼록 다각형
  * 볼록 다각형 내부의 두 점을 연결하는 선분은 볼록 다각형의 경계를 절대 교차하지 않음
* 오목 다각형(concave polygon): 180도를 넘는 내각을 갖는 다각형
* 단순 다각형(simple polygon): 다각형의 경계가 스스로를 교차하지 않는 다각형

## 면적 구하기
* 두 벡터 a, b로 만들어진 삼각형의 면적: | a X b | / 2
  * b가 a의 반시계 방향에 위치하는 경우 a X b > 0, 시계 방향이면 a X b < 0
* 임의의 한 점에서 각 꼭지점으로의 벡터를 순서대로 더하는 방법으로 다각형의 면적 계산 가능

```c
// 주어진 단순 다각형 p의 넓이를 구한다.
// p는 각 꼭지점의 위치 벡터의 집합으로 주어진다.
double area(const vector<vector2>& p) {
    double ret = 0;
    for (int i = 0; i < p.size(); ++i) {
        int j = (i + 1) % p.size();
        ret += p[i].x * p[j].y - p[j].x * p[i].y;
    }
    return fabs(ret) / 2.0;
}
```

## 내부 / 외부 판별
* 단순다각형과 다각형의 경계 위에 있지 않은 점 q가 주어질 때 q가 다각형의 내부에 있는지 외부에 있는지 확인
* q에서 시작해 오른쪽으로 뻗어나가는 반직선이 다각형과 몇 번 교차하는지로 판별 가능
    * 내부의 점은 항상 홀수 번, 외부의 점은 짝수 번 교차
    * 꼭지점과 교차하면? 완전히 겹치는 변이 있으면??
        
```c
// 점 q가 다각형 p 안에 포함되어 있을 경우 참, 아닌 경우 거짓을 반환한다.
// q가 다각형의 경계 위에 있는 경우의 반환 값은 정의되어 있지 않다.
bool isInside(vector2 q, const vector<vector2>& p) {
    int crosses = 0;
    for (int i = 0; i < p.size(); ++i) {
        int j = (i + 1) % p.size();
        // (p[i], p[j])가 반직선을 세로로 가로지르는가?
        if ((p[i].y > q.y) != (p[j].y > q.y)) {
            // 가로지르는 x 좌표를 계산한다.
            double atX = (p[j].x - p[i].x) * (q.y - p[i].y) / 
                         (p[j].y - p[i].y) + p[i].x;
            if (q.x < atX)
                ++crosses;
        }
    }
    return crosses % 2 > 0;
}
```

## *직선의 교차점 계산*
* *각 직선을 한 점과 방향벡터의 합으로 표시: a + p * b*
* *점 A, 벡터 B와 점 C, 벡터 D로 표시되는 두 직선의 교차점 구하기*
    * *A + p * B = C + q * D 를 만족하는 p, q를 계산*
    * *x, y 각각에 대한 연립방정식으로 표현*
        * *Ax + p * Bx = Cx + q * Dx*
        * *Ay + p * By = Cy + q * Dy*
    * *방정식을 풀면, p = ((Cx - Ax) * Dy - (Cy - Ay) * Dx) / (Bx * Dy - By * Dx) = ((C - A) X D) / (B X D)*
    * *계산된 p를 이용해 A + p * b를 계산하면 교차점의 좌표 확인 가능*

## 보물섬: 단순 다각형과 직사각형의 교집합 구하기
* 볼록 다각형의 특징: 여러 반평면(half plane)의 교집합으로 구할 수 있음
    * 볼록 다각형의 각 변을 반시계 방향으로 순회하면서 각 변을 포함하는 직선의 왼쪽 반평면들의 교집합
    * 문제에서 주어지는 직사각형이 볼록 다각형이므로 이 특성을 이용할 수 있음
        * 직사각형의 각 변을 기준으로 만들어진 반평면과 보물섬의 교집합의 교집합
* 직선으로 다각형 자르기
    * 입력 다각형의 꼭지점 중 직선의 왼쪽에 있는 점들은 모두 결과 다각형의 꼭지점
    * 입력 다각형의 변이 직선을 가로지르는 경우, 변과 직선의 교차점들도 결과 다각형의 꼭지점

```c
typedef vector<vector2> polygon;
//반평면과 단순 다각형의 교집합을 구한다.
//(a, b)를 포함하는 직선으로 다각형 p를 자른 뒤, (a, b)의 왼쪽에 있는 부분들을 반환한다.
polygon cutPoly(const polygon& p, const vector2& a, const vector2& b) {
    int n = p.size();
    // 각 점이 반평면 내에 있는지 여부를 우선 확인한다.
    vector<bool> inside(n);
    for (int i = 0; i < n; ++i)
        inside[i] = ccw(a, b, p[i]) >= 0;
    polygon ret;
    // 다각형의 각 변을 순회하면서 결과 다각형의 각 점을 발견한다.
    for (int i = 0; i < n; ++i) {
        int j = (i + 1) % n;
        // 반평면 내에 있는 점 p[i]는 항상 결과 다각형에 포함된다.
        if (inside[i])
            ret.push_back(p[i]);
        // 변 p[i]-p[j]가 직선과 교차하면 교차점을 결과 다각형에 포함시킨다.
        if (inside[i] != inside[j]) {
            vector2 cross;
            assert(lineIntersection(p[i], p[j], a, b, cross));
            ret.push_back(cross);
        }
    }
    return ret;
}
// 서덜랜드-호지맨(Sutherland-Hodgman) 알고리즘을 이용한 다각형 클리핑
polygon intersection(const polygon& p, double x1, double y1, double x2, double y2) {
    vector2 a(x1, y1), b(x2, y1), c(x2, y2), d(x1, y2);
    polygon ret = cutPoly(p, a, b);
    ret = cutPoly(ret, b, c);
    ret = cutPoly(ret, c, d);
    ret = cutPoly(ret, d, a);
    return ret;
}
```