package resources;

public class Subtask extends Task{
    private final int epicID;

    public int getEpicID() {
        return epicID;
    }

    public Subtask(String taskName, String description, int id, int epicID){
        super(taskName,description,id);
        this.epicID = epicID;
        this.status = Status.NEW;
    }
}
