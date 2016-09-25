#include <bits/stdc++.h>
using namespace std;

struct Comparator {
  const vector<int>& group;
  int t;
  Comparator(const vector<int>& _group, int _t): group(_group), t(_t) {}
  bool operator () (int a, int b) {
    if (group[a] != group[b])
      return group[a] < group[b];
    return group[a + t] < group[b + t];
  }
};

vector<int> getSuffixArray(const string& s) {
  int n = s.size();
  int t = 1;
  vector<int> group(n + 1);
  for (int i = 0; i < n; i++)
    group[i] = s[i];
  group[n] = -1;

  vector<int> ret(n);
  for (int i = 0; i < n; i++)
    ret[i] = i;

  while (t < n) {
    Comparator compareUsing2T(group, t);
    sort(ret.begin(), ret.end(), compareUsing2T);
    t *= 2;
    if (t >= n) break;

    vector<int> newGroup(n + 1);
    newGroup[n] = -1;
    newGroup[ret[0]] = 0;
    for (int i = 1; i < n; i++) {
      if (compareUsing2T(ret[i - 1], ret[i])) {
        newGroup[ret[i]] = newGroup[ret[i - 1]] + 1;
      }
      else {
        newGroup[ret[i]] = newGroup[ret[i - 1]];
      }
    }
    group = newGroup;
  }
  return ret;
}

int commonPrefix(const string& s, int i, int j) {
  int ret = 0;
  while (i < s.size() && j < s.size() && s[i] == s[j]) {
    i++, j++, ret++;
  }
  return ret;
}

int solve(const string& input, int k) {
  if (k > input.size())
    return 0;

  vector<int> suffixArr = getSuffixArray(input);
  int ret = 0;
  for (int i = 0; i + k <= input.size(); i++) {
    ret = max(ret, commonPrefix(input, suffixArr[i], suffixArr[i + k - 1]));
  }
  return ret;
}

int main() {
  int C, K;
  cin >> C;
  while (C-- > 0) {
    cin >> K;
    string input;
    cin >> input;
    cout << solve(input, K) << endl;
  }
  return 0; 
}