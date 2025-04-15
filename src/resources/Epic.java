package resources;

import java.util.ArrayList;

public class Epic extends Task {

    private ArrayList<Subtask> subtasksList = new ArrayList<>();

    public Epic(String taskName, String description, int id) {
        super(taskName, description, id);
        this.status = Status.NEW;

    }

    public void addSubtask(Subtask subtask) {
        subtasksList.add(subtask);
    }

    public void clearSubtask() {
        subtasksList.clear();
    }

    public ArrayList<Subtask> getSubtasksList() {
        return subtasksList;
    }

    public void setSubtasksList(ArrayList<Subtask> subtasksList) {
        this.subtasksList = subtasksList;
    }
}
