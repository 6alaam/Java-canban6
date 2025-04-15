package Manager;

import resources.Epic;
import resources.Status;
import resources.Subtask;
import resources.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {

    private static int ID = 1;

    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();

    private final HistoryManager historyManager = Managers.getDefaulthistory();


    @Override
    public int getNextID() {
        return ID++;
    }

    @Override
    public List<Task> getAllTask() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Task> getAllEpic() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<Subtask> getAllSubtask() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public List<Subtask> getEicSubtask(Epic epic) {
        return epic.getSubtasksList();
    }

    @Override
    public void deleteAllTask() {
        tasks.clear();
    }

    @Override
    public void deleteAllEpic() {
        epics.clear();
        subtasks.clear();
    }

    @Override
    public void deleteAllSubTask() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.clearSubtask();
            epic.setStatus(Status.NEW);
        }
    }

    @Override
    public Task getTaskById(int id) {
        historyManager.add(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public Epic getEpicById(int id) {
        historyManager.add(epics.get(id));
        return epics.get(id);
    }

    @Override
    public Subtask getSubtaskById(int id) {
        historyManager.add(subtasks.get(id));
        return subtasks.get(id);
    }

    @Override
    public Task addTask(Task task) {
        task.setId(getNextID());
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public Epic addEpic(Epic epic) {
        epic.setId(getNextID());
        epics.put(epic.getId(), epic);
        return epic;
    }

    @Override
    public Subtask addSubtask(Subtask subtask) {
        subtask.setId(getNextID());
        Epic epic = epics.get(subtask.getEpicID());
        epic.addSubtask(subtask);
        subtasks.put(subtask.getId(), subtask);
        updateEpicStatus(epic);
        return subtask;
    }

    @Override
    public Task updateTask(Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public Epic updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
        return epic;
    }

    @Override
    public Subtask updateSubtask(Subtask subtask) {
        int subtaskid = subtask.getId();
        int epicid = subtask.getEpicID();
        Subtask oldsubtask = subtasks.get(subtaskid);
        subtasks.replace(subtaskid, subtask);

        Epic epic = epics.get(epicid);
        ArrayList<Subtask> subtaskList = epic.getSubtasksList();
        subtaskList.remove(oldsubtask);
        subtaskList.add(subtask);
        epic.setSubtasksList(subtaskList);
        return subtask;
    }

    @Override
    public void deleteTaskById(int id) {
        tasks.remove(id);

    }

    @Override
    public void deleteEpicById(int id) {
        epics.remove(id);
    }

    @Override
    public void deleteSubtaskById(int id) {
        Subtask subtask = subtasks.get(id);
        int epicid = subtask.getEpicID();
        subtasks.remove(id);
        Epic epic = epics.get(epicid);
        ArrayList<Subtask> subtaskList = epic.getSubtasksList();
        subtaskList.remove(subtask);
        epic.setSubtasksList(subtaskList);
        updateEpicStatus(epic);

    }

    @Override
    public void updateEpicStatus(Epic epic) {
        int allisDone = 0 ;
        int allisNew = 0;
        ArrayList<Subtask> listSubtask = epic.getSubtasksList();
        for (Subtask subtask : listSubtask){
            if(subtask.getStatus() == Status.DONE){
                allisDone ++;
                            }
            if(subtask.getStatus() == Status.NEW){
                allisNew++;
            }
        }
        if (allisDone == listSubtask.size()){
            epic.setStatus(Status.DONE);
        }
        if ((allisNew == listSubtask.size())){
            epic.setStatus(Status.NEW);
        }
        else {
            epic.setStatus(Status.IN_PROGRESS);
        }

    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    @Override
    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Нет задач для отображения.");
            return;
        }
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            Integer key = entry.getKey();
            Task task = entry.getValue();
            System.out.println("ID: " + key + ", " + task);
        }
    }
    @Override
    public void printEpicWithSubtasks(int epicId) {
        Epic epic = getEpicById(epicId);
        if (epic == null) {
            System.out.println("Эпик с ID " + epicId + " не найден.");
            return;
        }
        System.out.println("Эпик: " + epic);
        List<Subtask> subtaskList = epic.getSubtasksList();
        if (subtaskList.isEmpty()) {
            System.out.println("У этого эпика нет сабтасков.");
        } else {
            System.out.println("Сабтаски:");
            for (Subtask subtask : subtaskList) {
                System.out.println(" - " + subtask);
            }
        }
    }
    @Override
    public void printHistory() {
        System.out.println("Печать истории");
        List<Task> history = historyManager.getHistory(); // Получаем историю задач
        if (history.isEmpty()) {
            System.out.println("Нет задач в истории.");
            return;
        }
        for (Task task : history) {
            System.out.println("Задача: " + task); // Выводим каждую задачу
        }
    }



}
