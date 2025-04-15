package Manager;

import resources.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{

    public static final  int Max_History_Storage = 10;

    public final List<Task> historyList= new ArrayList<>();

    @Override
    public void add(Task task) {
        if(task == null){
            return;
        }
        if (historyList.size() == Max_History_Storage){
            historyList.removeFirst();
        }
    }

    @Override
    public List<Task> getHistory() {
        return historyList;
    }

    @Override
    public String toString() {
        return "InMemoryHistoryManager{" +
                "historyList=" + historyList +
                '}';
    }
}
