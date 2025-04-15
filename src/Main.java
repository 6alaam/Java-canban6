import Manager.InMemoryHistoryManager;
import Manager.InMemoryTaskManager;
import Manager.Managers;
import Manager.TaskManager;
import resources.Epic;
import resources.Subtask;
import resources.Task;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();
     //   InMemoryHistoryManager inMemoryHistoryManager = Managers.getDefaulthistory();
        taskManager.addTask(new Task("test1","testtask1", taskManager.getNextID()));

        taskManager.printTasks();
        taskManager.getTaskById(2);
        System.out.println();
        System.out.println(taskManager.getHistory());
    }}
