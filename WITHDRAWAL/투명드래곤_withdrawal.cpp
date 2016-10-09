#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

const double ACCEPTABLE_ERROR = 0.00000001;

struct ClassInfo {
    int attendeeCount;
    int myRank;
};

class Comparator {
public:
    Comparator(double targetRate) : targetRate(targetRate) {}
    bool operator()(const ClassInfo& a, const ClassInfo& b) const
    {
        return a.myRank - a.attendeeCount * targetRate < b.myRank - b.attendeeCount * targetRate;
    }
private: 
    double targetRate;
};

double calculateCumulativeRank(vector<ClassInfo>& classList, int count)
{
    int sumRank = 0;
    int sumAttendeeCount = 0;
    for (int i = 0; i < count; i++)
    {
        sumRank += classList[i].myRank;
        sumAttendeeCount += classList[i].attendeeCount;
    }
    return ((double) sumRank) / sumAttendeeCount;
}

double solve(vector<ClassInfo>& classList, int minCount)
{
    double minRank = 0;
    double maxRank = calculateCumulativeRank(classList, classList.size());
    
    while (maxRank - minRank > ACCEPTABLE_ERROR)
    {
        double targetRank = (minRank + maxRank) / 2;
        Comparator comp(targetRank);
        sort(classList.begin(), classList.end(), comp);
        double cumulativeRank = calculateCumulativeRank(classList, minCount);
        if (cumulativeRank > targetRank)
            minRank = targetRank;
        else
            maxRank = cumulativeRank;
    }
    return minRank;
}

int main(void)
{
    int tcCount;
    
    cout.precision(10);
    cin >> tcCount;
    for (int i = 0; i < tcCount; i++) {
        int classCount, minCount;
        vector<ClassInfo> classList;
        
        cin >> classCount >> minCount;
        for (int j = 0; j < classCount; j++) {
            ClassInfo info;
            cin >> info.myRank >> info.attendeeCount;
            classList.push_back(info);
        }
        cout << solve(classList, minCount) << endl;
    }
}