import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class User {

    private String username;
    private String password;
    Integer nextTaskId;
    public ArrayList<Task> tasks;

    
    //Construtor
    public User(String u, String p) {
        this.username = u;
        this.password = p;
        this.nextTaskId = 1;
        this.tasks = new ArrayList<Task>();
    }
    
    //Editar a tarefa
    public void editTask(Integer id, String title, String description, String type, String priority) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setTitle(title);
                t.setDescription(description);
                t.setType(type);
                t.setPriority(priority);
                break;
            }
        }
    }
    
    //retornar a tarefa de acordo com o ID da mesma
    public Task getTaskById(Integer id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    //Apagar a tarefa
    public void deleteTask(Integer id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                tasks.remove(t);
                break;
            }
        }
    }

    
    //Atualizar a tabela
    public void refresh(JTable jtable) {
        DefaultTableModel model = (DefaultTableModel) jtable.getModel();
        //Limpar as rows existentes
        model.setRowCount(0);

        //Correr a tabela de cada utilizador a das Tarefas (tasks) e adicionar 3 dados o titulo, o ID e a prioridade á mesma
        for (Task task : tasks) {
            String formattedId = String.format("%03d", task.getId());
            Object[] rowData = {formattedId, task.getTitle(), task.getPriority()}; //Por os dados numa array
            model.addRow(rowData); //Adicionar á tabela
        }
    }


    public ArrayList<Task> getAllTasks() { //Retornar todas as tarefas
        return tasks;
    }


    
    public String getUsername() { //Obter nome de utilizador
        return username;
    } 
    
    public String getPassword() { //Obter palavra-chave
        return password;
    }
    

    public void addTask(Task task) { //Adicionar tarefa á lista de tarefas
        tasks.add(task);
    }

    public void removeTask(Task task) { //Remover a tarefa da lista
        tasks.remove(task);
    }
}