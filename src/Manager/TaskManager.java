package Manager;

import resources.Epic;
import resources.Subtask;
import resources.Task;

import java.util.List;

public interface TaskManager {
    int getNextID();

    List<Task> getAllTask();

    List<Task> getAllEpic();

    List<Subtask> getAllSubtask();

    List<Subtask> getEicSubtask(Epic epic);

    void deleteAllTask();

    void deleteAllEpic();

    void deleteAllSubTask();

    Task getTaskById(int id);

    Epic getEpicById(int id);

    Subtask getSubtaskById(int id);

    Task addTask(Task task);

    Epic addEpic(Epic epic);

    Subtask addSubtask(Subtask subtask);

    Task updateTask(Task task);

    Epic updateEpic(Epic epic);

    Subtask updateSubtask(Subtask subtask);

    void  deleteTaskById (int id);

    void  deleteEpicById(int id);

    void deleteSubtaskById(int id);

    void updateEpicStatus(Epic epic);

    List<Task> getHistory();

   void printEpicWithSubtasks(int epicId);

    public void printHistory();

    public void printTasks();
}
