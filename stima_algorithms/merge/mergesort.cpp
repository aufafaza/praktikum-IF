#include <iostream> 
#include <vector> 
using namespace std;


void merge(vector<int>& arr, int left, int mid, int right){ 
	// get amount of element 
	int n1 = mid - left + 1; 
	int n2 = right - mid; 

	// creat a vector to store 
	vector<int> L, R;
	L.reserve(n1);
	R.reserve(n2);

	for (int i = 0; i < n1; i++){
		L.push_back(arr[left + i]);
	}
	for (int j = 0; j < n2; j++){
		R.push_back(arr[mid + 1 + j]);
	} 

	int i = 0, j = 0, k = left; 

	while (i < n1 && j < n2){
		if (L[i] <= R[j]){
			arr[k] = L[i];
			i++;
		}else{
			arr[k] = R[j];
			j++;
		}
		k++;
	}
	// case if i or j is not empty yet 
	while (i < n1){
		arr[k] = L[i];
		i++;
		k++;
	}
	while (j < n2){
		arr[k] = R[j];
		j++;
		k++;
	}
}
void mergeSort(vector<int>& arr, int left, int right) { 
	if (left >= right) return;

	int mid = left + (right - left)/2; 

	mergeSort(arr, left, mid);
	mergeSort(arr, mid + 1, right); 
	merge(arr, left, mid, right); 
} 

int main(){ 
	vector<int> v1{5, 2, 3, 10, 1, 4}; 
	for (int x : v1) cout << x << ' ';	
	mergeSort(v1, 0, v1.size() - 1);
	cout << "post sort" << endl;
	for(int x : v1) cout << x << ' ';
}
