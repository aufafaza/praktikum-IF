#include <iostream> 
#include <vector> 

using namespace std; 


int partition(vector<int>& arr, int low, int high){ 
	int i = low -1; 
	int pivot = arr[high];

	for (int j = low; j < high; j++){
		if (arr[j] < pivot){
			i++;
			swap(arr[i], arr[j]);
		}
	}
	swap(arr[i+1], arr[high]);
	return i+1; 
} 

void quicksort(vector<int>& arr, int low, int high){
	if (low >= high) return; 

	int pi = partition(arr, low, high);
	quicksort(arr, low, pi-1);
	quicksort(arr, pi + 1, high);
} 

int main(){
	vector<int> T {1, 5, 2, 3, 4, 10, 6};

	for (int x : T) cout << x << ' ';
	cout << "post sort" << endl; 
	quicksort(T, 0, T.size() - 1); 
	for (int x : T) cout << x << ' '; 
	cout << endl; 
} 

