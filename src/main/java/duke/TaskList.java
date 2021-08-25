package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private final List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void save(Storage storage) throws IOException {
        storage.write(tasks);
    }

    public void remove(int index) throws IndexOutOfBoundsException {
        Task task = tasks.remove(index - 1);
        Ui.showMessage("Noted. I've removed this task:\n  " +
                task +
                "\nNow you have " + tasks.size() + " tasks in the list.\n");
    }

    public void add(Task task) {
        tasks.add(task);
        String msg = "Got it. I've added this task:\n  " +
                task +
                "\nNow you have " + tasks.size() + " tasks in the list.\n";

        Ui.showMessage(msg);
    }

    public void setDone(int index) {
        Task task = tasks.get(index - 1);
        task.setDone();
        Ui.showMessage("Nice! I've marked this task as done:\n" + task + "\n");
    }

    public void filter(String searchString) {
        List<Task> searchResult = tasks
                .stream()
                .filter(str -> str.getDescription().contains(searchString))
                .collect(Collectors.toList());

        StringBuilder output;
        if (searchResult.isEmpty()) {
            output = new StringBuilder("No Result match your search keyword.\n");
        } else {
            output = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 1; i <= searchResult.size(); i++) {
                Task task = searchResult.get(i - 1);
                output.append(i).append(". ").append(task).append("\n");
            }
        }

        Ui.showMessage(output.toString());
    }

    @Override
    public String toString() {
        StringBuilder output;

        if (tasks.isEmpty()) {
            output = new StringBuilder("Hey! You have not added any task...\n");
        } else {
            output = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 1; i <= tasks.size(); i++) {
                Task task = tasks.get(i - 1);
                output.append(i).append(". ").append(task).append("\n");
            }
        }

        return output.toString();
    }
}
