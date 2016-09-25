#include <cstdio>
#include <string>
#include <vector>
#include <deque>
#include <algorithm>
using namespace std;

// suffix array using bucket sort
vector<int> suffixArray(const string &s) {
	int n = s.size();
	vector<int> ret(n), bckt(n), bpos(n), pos2bckt(n+1, -1);
	for (int i=0; i<n; ++i) ret[i] = i;
	sort(ret.begin(), ret.end(), [&s](int a, int b) {return s[a]<s[b];});
	for (int i=1, c=0; i<n; ++i) {
		if (s[ret[i]] != s[ret[i-1]]) ++c;
		bckt[i] = c;
	}
	for (int h=1; h<n; h <<= 1) {
		if (bckt[n-1] == n-1) break;
		vector<int> temp(n), relabel(n);
		for (int i=0; i<n; ++i) pos2bckt[ret[i]] = bckt[i];
		for (int i=n-1; i>=0; --i) bpos[bckt[i]] = i;
		for (int i=0; i<n; ++i)
			if (ret[i] >= n-h) temp[bpos[bckt[i]]++] = ret[i];
		for (int i=0; i<n; ++i)
			if (ret[i] >= h) temp[bpos[pos2bckt[ret[i]-h]]++] = ret[i]-h;
		for (int i=1, c=0; i<n; ++i) {
			if (bckt[i] != bckt[i-1]) ++c;
			else if (pos2bckt[temp[i-1]+h] != pos2bckt[temp[i]+h]) ++c;
			relabel[i] = c;
		}
		ret = temp; bckt = relabel;
	}
	return ret;
}

// sa = suffixArray(s)
// ret[i] = lcp(sa[i], sa[i+1])
vector<int> getLCP(const string &s, const vector<int> &sa) {
	int n = sa.size();
	vector<int> ret(n-1), rank(n);
	for (int i=0; i<n; ++i) rank[sa[i]] = i;
	for (int i=0, h=0; i<n; ++i) {
		if (rank[i] == 0) continue;
		int j = sa[rank[i]-1];
		while (s[i+h] == s[j+h]) ++h;
		ret[rank[i]-1] = h;
		if (h) --h;
	}
	return ret;
}

int solve(int k, const string &s) {
	int n = s.size();
	if (k == 1) return n;
	if (k > n) return 0;
	auto sa = suffixArray(s);
	auto lcp = getLCP(s, sa);
	int ans = 0;
	deque<int> dq;
	for (int i=0; i<n-1; ++i) {
		while (!dq.empty() && lcp[dq.back()] >= lcp[i])
			dq.pop_back();
		dq.push_back(i);
		if (dq.front() == i-k+1) dq.pop_front();
		if (i >= k-2) ans = max(ans, lcp[dq.front()]);
	}
	return ans;
}

int k;
char dat[4001];

int main() {
	int T;
	scanf("%d", &T);
	while(T--) {
		scanf("%d %s", &k, dat);
		printf("%d\n", solve(k, dat));
	}
	return 0;
}