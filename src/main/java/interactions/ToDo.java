package interactions;
import interactions.Task;

public class ToDo extends Task {
    private boolean haveToDo, haveDeadline, isEvent;

    public String getDeadline() {
        return deadline;
    }
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    private String deadline;

    public String getEventFrom() {
        return eventFrom;
    }

    public void setEventFrom(String eventFrom) {
        this.eventFrom = eventFrom;
    }

    private String eventFrom;

    public String getEventTo() {
        return eventTo;
    }

    public void setEventTo(String eventTo) {
        this.eventTo = eventTo;
    }

    private String eventTo;
    public boolean HaveDeadline() {
        return haveDeadline;
    }
    public void setHaveDeadline(boolean haveDeadline) {
        this.haveDeadline = haveDeadline;
    }
    public boolean isEvent() {
        return isEvent;
    }
    public void setEvent(boolean event) {
        isEvent = event;
    }
    public boolean HaveToDo() {
        return haveToDo;
    }

    public void setHaveToDo(boolean haveToDo) {
        this.haveToDo = haveToDo;
    }

    public ToDo(String task) {
        super(task);
        this.haveToDo = false;
        this.haveDeadline = false;
        this.isEvent = false;
    }
    @Override
    public void print() {
        String taskType = "[ ]";
        String additionalInfo = "";
        if (haveToDo) {
            taskType = "[T]";
        }
        else if (haveDeadline) {
            taskType = "[D]";
            additionalInfo = " (by: " + deadline + ")";
        }
        else if (isEvent) {
            taskType = "[E]";
            additionalInfo = " (from: " + eventFrom + " to: " + eventTo + ")";
        }
        System.out.print(taskType);
        System.out.print(isMarked() ? "[X] " : "[ ] ");
        System.out.println(getTask() + additionalInfo);
    }
}
