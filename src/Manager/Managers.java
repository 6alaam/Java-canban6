package Manager;

public class Managers {
    public static TaskManager getDefault(){
        return new InMemoryTaskManager();
    }

    public static InMemoryHistoryManager getDefaulthistory(){
        return new InMemoryHistoryManager();
    }
}
