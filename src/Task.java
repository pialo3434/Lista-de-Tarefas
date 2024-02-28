class Task {
    String title;
    String description;
    String type;
    String priority;
    Integer id;
    
    //Construtor
    public Task(String title, String description, String type, String priority, User user) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.priority = priority;
        this.id = user.nextTaskId++;
    }

    public String getTitle() { //Obter titulo 
        return title;
    }

    public void setTitle(String title) { //Mudar titulo
        this.title = title;
    }

    public String getDescription() { //Obter descrição
        return description;
    }

    public void setDescription(String description) { //Definir descrição
        this.description = description;
    }

    public String getType() { //Obter tipo de tarefa
        return type;
    }

    public void setType(String type) { //Definir o tipo de tarefa
        this.type = type;
    }

    public String getPriority() { //Obter prioridade
        return priority;
    }

    public void setPriority(String priority) { //Definir prioridade
        this.priority = priority;
    }

    public Integer getId() { //Obter ID
        return id;
    }

    public String toString() { //Passar todos os parametros para string
        return "Task [id=" + id + ", title=" + title + ", description=" + description + ", type=" + type + ", priority=" + priority + "]";
    }
}
