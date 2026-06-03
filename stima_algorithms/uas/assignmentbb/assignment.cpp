#include <cstddef>
#include <iostream> 
#include <vector> 
#include <queue> 
const int INF = 999999;
using namespace std; 
struct Node {
    Node* parent;
    int workerID;
    int jobID;
    // int path_cost;
    int cost;
    vector<bool> assigned;
    vector<vector<int>> reducedMatrix;

    // Constructor to easily initialize a new state
    Node(int worker, int job, const vector<bool>& assigned_jobs, Node* parent_node, const vector<vector<int>>& parentMatrix) {
        workerID = worker;
        jobID = job;
        assigned = assigned_jobs;
        
        if (job != -1) {
            assigned[job] = true;
        }
        
        parent = parent_node;
        reducedMatrix = parentMatrix; 
        cost = 0;
    }
};

// Custom Comparator for the Priority Queue
// Branch and Bound requires a Min-Heap so the lowest cost is always at the top.
struct CompareCost {
    bool operator()(const Node* lhs, const Node* rhs) const {
        return lhs->cost > rhs->cost; 
    }
};

int reduceMatrix(vector<vector<int>>& matrix){ 
    int n = matrix.size(); 
    int reduction_cost = 0; 

    for (int i = 0; i < n; i++) {
        int row_min = INF;
        for (int j = 0; j < n; j++) {
            if (matrix[i][j] < row_min) {
                row_min = matrix[i][j];
            }
        }
        if (row_min != INF && row_min > 0) {
            reduction_cost += row_min;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != INF) {
                    matrix[i][j] -= row_min;
                }
            }
        }
    }

    for (int j = 0; j < n; j++) {
            int col_min = INF;
            for (int i = 0; i < n; i++) {
                if (matrix[i][j] < col_min) {
                    col_min = matrix[i][j];
                }
            }
            // Subtract the minimum from the column
            if (col_min != INF && col_min > 0) {
                reduction_cost += col_min;
                for (int i = 0; i < n; i++) {
                    if (matrix[i][j] != INF) {
                        matrix[i][j] -= col_min;
                }
            }
        }
    }

    return reduction_cost; 
} 
 

int findMinCost(vector<vector<int>> &costMat){ 
    int n = costMat.size(); 

    priority_queue<Node*, vector<Node*>, CompareCost>    pq; 

    vector<bool> assigned(n, false); 

    Node *root = new Node(-1, -1, assigned, nullptr, costMat); 

    root->workerID = -1; 

    pq.push(root); 

    while (!pq.empty()){ 
        Node *min = pq.top(); 
        pq.pop(); 
        int i = min->workerID + 1; 

        if (i == n){ 
            return min->cost; 
        } 
        for (int j = 0; j < n; j++){ 
            Node* child = new Node(i, j, min->assigned, min, min->reducedMatrix); 
            child->cost = min->cost + min->reducedMatrix[i][j];

            for (int k =0; k < n; k++){ 
                child->reducedMatrix[i][k] = INF; 
                child->reducedMatrix[k][j] = INF;
            } 

            child->cost += reduceMatrix(child->reducedMatrix); 
            pq.push(child);
        } 


    }
    return -1; 
} 

int main(){ 
    vector<vector<int>> costMat = {
        {20, 15, 24, 18, 17}, 
        {16, 14, 25, 21, 22}, 
        {18, 19, 21, 17, 19}, 
        {19, 17, 22, 16, 20}, 
        {22, 20, 20, 22, 15}, 
    };
    int minval = findMinCost(costMat);
    cout << "minimum value is " << minval << endl; 
    return 0; 
} 
