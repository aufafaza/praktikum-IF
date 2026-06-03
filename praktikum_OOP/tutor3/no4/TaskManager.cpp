#include "TaskManager.hpp" 
#include <string>

void TaskManager::add(const string& id, const string& title, int priority, int duration){
	tasks.push_back(Task(id, title, priority, duration)); 	
}

    /**
     * @brief Inserts an existing Task object into the internal container.
     * @param task Task object to be appended.
     */
void TaskManager::add(const Task& task){
	tasks.push_back(task);
} 

    /**
     * @brief Updates title, priority, and duration for the first task matching @p id.
     * @details If no matching id is found, the container remains unchanged.
     * @param id Target task identifier.
     * @param title New title value.
     * @param priority New priority value.
     * @param duration New duration value.
     */
void TaskManager::update(const string& id, const string& title, int priority, int duration){
	for (auto& task :tasks){ 
		if (task.getId() == id) { 
			task.setTitle(title); 
			task.setPriority(priority);
			task.setDuration(duration);
			return; 
		} 
	} 
}

    /**
     * @brief Removes the first task matching @p id.
     * @param id Target task identifier.
     * @return true if a task is removed, false otherwise.
  	   */
bool TaskManager::remove(const string& id){
	for (auto it = tasks.begin(); it != tasks.end(); ++it){
		if (it->getId() == id) { 
			tasks.erase(it);
			return true; 
		} 
	}
	return false; 
} 

    /**
     * @brief Finds a task by id.
     * @param id Target task identifier.
     * @return Pointer to the stored task, or nullptr if not found.
     */
const Task* TaskManager::find(const string& id) const{
	for (const auto& task : tasks) { 
		if (task.getId() == id) {
			return &task;
		} 
	} 
	return nullptr; 
} 

    /**
     * @brief Sorts tasks by priority, duration, and id.
     * @details Ordering rule: higher priority first, then shorter duration,
     *          then lexicographically smaller id.
     */
void TaskManager::sort(){
	std::sort(tasks.begin(), tasks.end(), [](const Task& a, const Task& b){ 
		if (a.getPriority() != b.getPriority()){
		return a.getPriority() > b.getPriority();
		}
		if (a.getDuration() != b.getDuration()) { 
		return a.getDuration() < b.getDuration();
		}
		return a.getId() < b.getId(); 
	});
} 

    /**
     * @brief Computes total duration for tasks with priority >= @p minPriority.
     * @param minPriority Minimum priority threshold.
     * @return Sum of matching task durations.
     */
long long TaskManager::totalDuration(long long minPriority) const{
	long long total = 0; 
	for (const auto& task : tasks) { 
		if (task.getPriority() >= minPriority){
			total += task.getDuration();
		} 
	} 
	return total; 
} 

    /**
     * @brief Prints tasks whose title contains @p keyword as a case-sensitive substring.
     * @param keyword Substring to match against task titles.
     * @details Prints "EMPTY" if no task matches.
     */
void TaskManager::print(const string& keyword) const{
	bool found = false ;
	for (const auto& task : tasks){ 
		if (task.getTitle().find(keyword) != string::npos){
			cout << task.getId() << "|" << task.getTitle() << "|" << to_string(task.getPriority()) << "|" << to_string(task.getDuration()) << endl;
			found = true;
		}
	} 
	if (!found) cout << "EMPTY" << endl; 
} 

    /**
     * @brief Prints all tasks in their current container order.
     * @details Prints "EMPTY" if the container has no elements.
     */
void TaskManager::print() const{
	if (tasks.empty()) {
        cout << "EMPTY" << endl;
        return;
    }
    for (const auto& task : tasks) {
	cout << task.getId() << "|" << task.getTitle() << "|" << to_string(task.getPriority()) << "|" << to_string(task.getDuration()) << endl;}
}

